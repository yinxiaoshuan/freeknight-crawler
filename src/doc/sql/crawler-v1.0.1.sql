CREATE DATABASE `crawier` /*!40100 DEFAULT CHARACTER SET utf8 */

use crawier;

CREATE TABLE `t_album` (
  `a_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `album_id` varchar(20) NOT NULL COMMENT '专辑ID',
  `album_mid` varchar(20) DEFAULT NULL COMMENT '专辑ID编码',
  `album_name` varchar(256) DEFAULT NULL COMMENT '专辑名',
  `site` tinyint(4) DEFAULT NULL COMMENT '站点来源. 1:QQ.',
  `s_id` varchar(20) DEFAULT NULL COMMENT '专辑主歌手',
  `genre` varchar(50) DEFAULT '0' COMMENT '流派',
  `language` varchar(20) DEFAULT '中文' COMMENT '语言',
  `lssue_comp` varchar(100) DEFAULT '' COMMENT '发行公司',
  `type` varchar(20) DEFAULT '' COMMENT '演唱会',
  `lssue_time` date DEFAULT NULL COMMENT '发行时间',
  PRIMARY KEY (`a_id`),
  KEY `inx_amid` (`album_mid`),
  KEY `inx_aaid` (`album_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='专辑';

CREATE TABLE `t_singer` (
  `s_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `s_name` varchar(50) DEFAULT NULL COMMENT '歌手名',
  `alias` varchar(50) DEFAULT NULL COMMENT '别名',
  `fans` int(11) DEFAULT '0' COMMENT '粉丝数',
  `site` tinyint(4) DEFAULT NULL COMMENT '站点来源. 1:QQ.',
  `ss_id` varchar(20) DEFAULT NULL COMMENT '站点歌手ID',
  `sm_id` varchar(20) DEFAULT NULL COMMENT '歌手ID编码',
  `portrait` varchar(256) DEFAULT '0' COMMENT '头像URL',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`s_id`),
  KEY `inx_smid` (`sm_id`),
  KEY `inx_ssid` (`ss_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='歌手';

CREATE TABLE `t_music` (
  `m_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `music_id` varchar(20) NOT NULL COMMENT '音乐ID',
  `music_mid` varchar(20) DEFAULT NULL COMMENT '音乐ID编码',
  `music_name` varchar(256) DEFAULT NULL COMMENT '音乐名',
  `album_id` varchar(20) NOT NULL COMMENT '专辑ID',
  `album_mid` varchar(20) DEFAULT NULL COMMENT '专辑ID编码',
  `singer_id` varchar(20) DEFAULT NULL COMMENT '音乐歌手',
  `singer_mid` varchar(20) DEFAULT NULL COMMENT '音乐歌手ID编码',
  `site` tinyint(4) DEFAULT NULL COMMENT '站点来源. 1:QQ.',
  `genre` varchar(50) DEFAULT '0' COMMENT '流派',
  `lange` varchar(20) DEFAULT '中文' COMMENT '语言',
  `lssue_comp` varchar(100) DEFAULT '' COMMENT '发行公司',
  `type` varchar(20) DEFAULT '' COMMENT '演唱会',
  `lssue_time` date DEFAULT NULL COMMENT '发行时间',
  `c_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  PRIMARY KEY (`m_id`),
  KEY `inx_mmid` (`music_mid`),
  KEY `inx_mid` (`music_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='音乐';