package com.shu.analyzer;

import com.shu.dao.utils.IndexUtil;
import lombok.Getter;
import lombok.Setter;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.tika.exception.TikaException;
import org.apdplat.word.lucene.ChineseWordAnalyzer;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Description：lucene+word分词器进行分词和查找
 * User:wangyiteng
 * Date:2017/4/7
 */
@Getter
@Setter
@Component
public class Lucene {
    private String INDEX_FILE="./tmp/testindex";
    private ChineseWordAnalyzer analyzer;
    private Directory directory;

    //用于提取文件名和文件内容 进行分词
    public void luceneAnalyzer(com.shu.dao.entity.Document document) throws IOException, TikaException {
//        File root = new File("D:/java/bs");
//        File[] fs = root.listFiles();
        String filename = document.getTitle();
        String content = document.getContent();
//        IndexUtil iu = new IndexUtil();
//        for (int i = 0; i < fs.length; i++) {
//             filename = fs[i].getName();
//             content = iu.tikaTool(new File(fs[i].toString()));
            analyzer = new ChineseWordAnalyzer();
            Set<String> result = new HashSet<>();

            TokenStream tokenStream = analyzer.tokenStream("text", content);

            //准备消费
            tokenStream.reset();
            //开始消费
            while (tokenStream.incrementToken()) {
                CharTermAttribute term = tokenStream.getAttribute(CharTermAttribute.class);
                System.out.println(term.toString());
                result.add(term.toString());
            }

            //消费完毕
            tokenStream.close();

            directory = FSDirectory.open(Paths.get(INDEX_FILE));
            IndexWriterConfig config = new IndexWriterConfig(analyzer);
            IndexWriter iwriter = new IndexWriter(directory, config);
            Document doc = new Document();
            doc.add(new Field("filename",filename,TextField.TYPE_STORED));
            for (String text : result) {
                System.out.println(text);
                doc.add(new Field("text", text, TextField.TYPE_STORED));
                iwriter.addDocument(doc);
            }
            iwriter.close();

            directory.close();

    }

    //进行查找
    public void luceneSearch(String text) throws IOException, ParseException {
        directory = FSDirectory.open(Paths.get(INDEX_FILE));
        DirectoryReader ireader = DirectoryReader.open(directory);
        IndexSearcher isearcher = new IndexSearcher(ireader);
        analyzer = new ChineseWordAnalyzer();
        //查找
        QueryParser parser = new QueryParser("text", analyzer);
	    Query query = parser.parse(text);
	    ScoreDoc[] hits = isearcher.search(query, null, 1000).scoreDocs;
        //迭代输出结果
	    for (int i = 0; i < hits.length; i++) {
	        Document hitDoc = isearcher.doc(hits[i].doc);
	        System.out.println(hitDoc.get("filename"));
	    }
	    ireader.close();
	    directory.close();


    }

}
