create database crawier character set utf8;

use crawier;

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
  PRIMARY KEY (`s_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 COMMENT='歌手';

CREATE TABLE `t_album` (
  `a_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `album_id` varchar(20) NOT NULL COMMENT '专辑ID',
  `album_mid` varchar(20) DEFAULT NULL COMMENT '专辑ID编码',
  `album_name` varchar(50) DEFAULT NULL COMMENT '专辑名',
  `site` tinyint(4) DEFAULT NULL COMMENT '站点来源. 1:QQ.',
  `s_id` varchar(20) DEFAULT NULL COMMENT '专辑主歌手',
  `genre` varchar(50) DEFAULT '0' COMMENT '流派',
  `language` varchar(20) DEFAULT '中文' COMMENT '语言',
  `lssue_comp` varchar(100) DEFAULT '' COMMENT '发行公司',
  `type` varchar(20) DEFAULT '' COMMENT '演唱会',
  `lssue_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '发行时间',
  PRIMARY KEY (`a_id`)
) ENGINE=InnoDB AUTO_INCREMENT=552504 DEFAULT CHARSET=utf8 COMMENT='专辑'

CREATE TABLE `t_singer_album_rel` (
  `singer_id` varchar(20) NOT NULL COMMENT '歌手ID',
  `album_id` varchar(20) NOT NULL COMMENT '专辑ID',
  PRIMARY KEY (`singer_id`, `album_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='歌手、专辑关系';