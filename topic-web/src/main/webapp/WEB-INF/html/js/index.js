var app = angular.module("newsApp",[]);

app.controller("mainCtrl",function($scope,$http){

    $scope.pageNow = 1; //当前页

    $scope.showList = true; //控制列表和详情的显示

    $scope.isSearch = false; //判断是否正在搜索

    $scope.searchTime = 0; //搜索的次数统计,用于标记DOM节点以示区别

    $http.get("../index")
        .then(function (response) {
            $scope.allData = response.data;
            $scope.dataNow = $scope.allData.resultDtos.slice(($scope.pageNow-1)*10, ($scope.pageNow-1)*10+10);
            console.log($scope.allData);
            $("#pageBox").page({
                showInfo: true,
                showJump: true,
                showPageSizes: false,
                total:$scope.allData.count,
                firstBtnText: '首页',
                lastBtnText: '尾页',
                prevBtnText: '上一页',
                nextBtnText: '下一页',
                jumpBtnText:'跳转',
                infoFormat:""
            }).on("pageClicked", function (event, pageIndex) {  //点击页码之后的回调
                $scope.jump(pageIndex,"../index");
            }).on('jumpClicked', function (event, pageIndex) {  //点击跳转之后的回调
                $scope.jump(pageIndex,"../index");
            });
        });
    //翻页跳转
    $scope.jump = function(pagenow,url){
        $http.get(url)
            .then(function (response) {
                $scope.pageNow = pagenow; //点击页码翻页
                $scope.allData = response.data;
                $scope.dataNow = $scope.allData.resultDtos.slice(($scope.pageNow-1)*10, ($scope.pageNow-1)*10+10);
            });
    };
    //查看详情
    $scope.showDetail = function(e){
        $scope.showList = false;
        $scope.thisId = $(e.target).data('newsid');
        $scope.targetNews = {};
        console.log($scope.dataNow);
        console.log($scope.thisId);
        for(var i=0;i<10;i++){
            if($scope.dataNow[i].result.id===$scope.thisId){
                //console.log(i);
                $scope.targetNews.title = $scope.dataNow[i].result.title;   //显示标题
                $scope.targetNews.content = $scope.dataNow[i].result.content;   //显示内容
                $scope.targetNews.createTime = $scope.dataNow[i].result.createTime;   //显示时间
            };
        }
    };
    //返回列表
    $scope.backToList = function(){
        $scope.showList = true;
    };
    //点击搜索
    $scope.doSearch = function(){
        console.log($scope.keyWord);
        if(!$scope.keyWord){    //如果搜索框为空则重新初始化
            window.location.reload();
        }else{  //搜索框不为空则执行搜索并分页
            $scope.isSearch = true; //表示为搜索状态
            $scope.searchTime = $scope.searchTime + 1;
            $("div[id*='searchBar']").hide();
            $("#searchPaging").append('<div id="searchBar'+$scope.searchTime+'"></div>');
            $http.get("../index/search/"+$scope.keyWord)
                .then(function (response) {
                    $scope.allData = response.data;
                    $scope.dataNow = $scope.allData.resultDtos.slice(($scope.pageNow-1)*10, ($scope.pageNow-1)*10+10);
                    console.log($scope.allData.count);
                    $("#searchBar"+$scope.searchTime).page({
                        showInfo: true,
                        showJump: true,
                        showPageSizes: false,
                        total:$scope.allData.count,
                        firstBtnText: '首页',
                        lastBtnText: '尾页',
                        prevBtnText: '上一页',
                        nextBtnText: '下一页',
                        jumpBtnText:'跳转',
                        infoFormat:""
                    }).on("pageClicked", function (event, pageIndex) {  //点击页码之后的回调
                        $scope.jump(pageIndex,"../index/search/"+$scope.keyWord);
                    }).on('jumpClicked', function (event, pageIndex) {  //点击跳转之后的回调
                        $scope.jump(pageIndex,"../index/search/"+$scope.keyWord);
                    });
                });
        }
    }
});