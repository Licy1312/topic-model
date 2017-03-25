/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : topic_model

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2017-03-25 22:50:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `document`
-- ----------------------------
DROP TABLE IF EXISTS `document`;
CREATE TABLE `document` (
  `d_id` int(11) NOT NULL AUTO_INCREMENT,
  `create_time` timestamp NULL DEFAULT NULL,
  `d_abstract` longtext,
  `d_content` longtext,
  `d_keywords` varchar(255) DEFAULT NULL,
  `d_path` varchar(255) DEFAULT NULL,
  `d_title` varchar(255) DEFAULT NULL,
  `d_topics` varchar(255) DEFAULT NULL,
  `d_type` int(11) NOT NULL DEFAULT '0',
  `update_time` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `hits` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`d_id`)
) ENGINE=MyISAM AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of document
-- ----------------------------
INSERT INTO `document` VALUES ('1', '2017-03-25 16:35:54', '本文通过英国伦敦著名的连锁百货 DEBENHAMS 和上海友谊股份百货业的对比，深入剖析了双方所存在的差异，包括：\r\n经营理念，赢利模式，资源整合和技术水平等方面，展望上海百货业的未来。\r\n', 'DEBENHAMS 最早由 WILLIAM CLARK 于 1778 年在英国伦敦建立了第一家商店，到了 1928 年这家公司业务由家族型转向了非\r\n家族型，在英国被称为 PUBLIC COMPANY，2003 年，由 BARONESSRETAIL LTD 所拥有，在伦敦股票交易市场上市，在伦敦的牛津\r\n街设立了旗舰店，主要经营女装、男装、居家用品，保健美容，服饰，内衣及儿童服装，不少商店还提供配套的系列服务功能：\r\n餐馆，咖啡店，导购，美容美发，指甲馆.婚礼及礼品的服务等。至今为止，DEBENHAMS 总共有 157 家店铺，覆盖整个英国和爱\r\n尔兰地区，营业面积达 1000 万平方英尺；51 家国际特许授权百货店，覆盖 18 个国家；13 家 DESIRE 店铺（专销自己拥有的品\r\n牌）。\r\n在经营上，DEBENHAMS雇佣了25位伦敦设计师，其中包括JAPERCONRAN，JULIENMACDONALD，JUHNROCHA，MATTHEWWILLIASON\r\n等为本公司所拥有的 55 个品牌专门进行设计；同时也引进第三方竞争对手的品牌，如：OASIS 等。另外，该公司也积极拓展网\r\n上零售业务。\r\n', '零售业 经营模式 比较', 'E:\\licy\\毕业资料\\新建文件夹\\数据源（1000）\\20140630234446821.pdf', '伦敦和上海百货业的经营理念及赢利模式的比较\r\n伦敦和上海百货业的经营理念及赢利模式的比较\r\n', '零售业', '0', '2017-03-25 16:39:59', '0');
