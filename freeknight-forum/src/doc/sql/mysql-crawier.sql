use crawier;

CREATE TABLE `forum` (
  `fid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '论坛ID',
  `fsrcid` varchar(20) NOT NULL COMMENT '源站论坛ID',
  `prev` varchar(20) NOT NULL COMMENT '源站父论坛ID',
  `fname` varchar(64) NOT NULL COMMENT '论坛名',
  `site` varchar(20) NOT NULL COMMENT '源站点',
  `url` varchar(256) DEFAULT NULL COMMENT '源站地址',
  PRIMARY KEY (`fid`),
  UNIQUE KEY `forum_fid_uindex` (`fid`)
) ENGINE=InnoDB AUTO_INCREMENT=809 DEFAULT CHARSET=utf8 COMMENT='论坛表';

CREATE TABLE `topic` (
  `tid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `forum_id` int(11) NOT NULL COMMENT '论坛板块ID',
  `tsid` varchar(20) NOT NULL COMMENT '源站帖子ID',
  `title` varchar(256) NOT NULL COMMENT '标题',
  `author_id` varchar(50) NOT NULL COMMENT '作者ID',
  `author_name` varchar(64) NOT NULL COMMENT '作者名称',
  `reply_num` int(11) NOT NULL DEFAULT '0' COMMENT '评论量',
  `read_num` int(11) NOT NULL DEFAULT '0' COMMENT '阅读量',
  `hot` int(11) NOT NULL DEFAULT '0' COMMENT '热度',
  `recommend` int(11) NOT NULL DEFAULT '0' COMMENT '推荐指数',
  `issue_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '发布时间',
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB AUTO_INCREMENT=616758 DEFAULT CHARSET=utf8 COMMENT='主题/帖子'''

-- 
-- 针对纵横财经, 采用手动插入.
-- 
insert into forum (fsrcid, prev, fname, site, url) value ('2','0','股市大家谈','enoya','http://www.enoya.com/forum.php?mod=forumdisplay&fid=2');
insert into forum (fsrcid, prev, fname, site, url) value ('3','0','每日操盘快讯','enoya','http://www.enoya.com/forum.php?mod=forumdisplay&fid=3');
insert into forum (fsrcid, prev, fname, site, url) value ('15','0','经济全球通','enoya','http://www.enoya.com/forum.php?mod=forumdisplay&fid=15');
insert into forum (fsrcid, prev, fname, site, url) value ('278','0','投资报告交流','enoya','http://www.enoya.com/forum.php?mod=forumdisplay&fid=278');
insert into forum (fsrcid, prev, fname, site, url) value ('23','0','技术精华分析','enoya','http://www.enoya.com/forum.php?mod=forumdisplay&fid=23');
insert into forum (fsrcid, prev, fname, site, url) value ('21','0','创业商道管理','enoya','http://www.enoya.com/forum.php?mod=forumdisplay&fid=21');
insert into forum (fsrcid, prev, fname, site, url) value ('280','0','期货外汇债券','enoya','http://www.enoya.com/forum.php?mod=forumdisplay&fid=280');
insert into forum (fsrcid, prev, fname, site, url) value ('17','0','旧贴回顾','enoya','http://www.enoya.com/forum.php?mod=forumdisplay&fid=17');
insert into forum (fsrcid, prev, fname, site, url) value ('18','0','历史军事天空','enoya','http://www.enoya.com/forum.php?mod=forumdisplay&fid=18');
insert into forum (fsrcid, prev, fname, site, url) value ('22','0','两性婚恋家庭','enoya','http://www.enoya.com/forum.php?mod=forumdisplay&fid=22');
insert into forum (fsrcid, prev, fname, site, url) value ('24','0','绝色美女贴图','enoya','http://www.enoya.com/forum.php?mod=forumdisplay&fid=24');
insert into forum (fsrcid, prev, fname, site, url) value ('25','0','娱乐花边头条','enoya','http://www.enoya.com/forum.php?mod=forumdisplay&fid=25');
insert into forum (fsrcid, prev, fname, site, url) value ('26','0','FLASH心随歌动','enoya','http://www.enoya.com/forum.php?mod=forumdisplay&fid=26');
insert into forum (fsrcid, prev, fname, site, url) value ('27','0','汽车手机时尚','enoya','http://www.enoya.com/forum.php?mod=forumdisplay&fid=27');
insert into forum (fsrcid, prev, fname, site, url) value ('28','0','摄影数码生活','enoya','http://www.enoya.com/forum.php?mod=forumdisplay&fid=28');

-- 
-- 针对发展论坛, 采用手动插入.
-- 
insert into forum (fsrcid, prev, fname, site, url) value ('n500','0','发展论坛','news','http://forum.home.news.cn/list/50-0-0-1.html');
insert into forum (fsrcid, prev, fname, site, url) value ('n505','0','学习时间','news','http://forum.home.news.cn/list/50-505-0-1.html');
insert into forum (fsrcid, prev, fname, site, url) value ('n604','0','两学一做','news','http://forum.home.news.cn/list/50-604-0-1.html');
insert into forum (fsrcid, prev, fname, site, url) value ('n388','0','热点论坛','news','http://forum.home.news.cn/list/50-388-0-1.html');
insert into forum (fsrcid, prev, fname, site, url) value ('n135','0','深水论见','news','http://forum.home.news.cn/list/50-135-0-1.html');
insert into forum (fsrcid, prev, fname, site, url) value ('n133','0','法治论坛','news','http://forum.home.news.cn/list/50-133-0-1.html');
insert into forum (fsrcid, prev, fname, site, url) value ('n840','0','旗帜论坛','news','http://forum.home.news.cn/list/84-0-0-1.html');
insert into forum (fsrcid, prev, fname, site, url) value ('n85','0','国际论坛','news','http://forum.home.news.cn/list/50-85-0-1.html');
insert into forum (fsrcid, prev, fname, site, url) value ('n92','0','军迷大营','news','http://forum.home.news.cn/list/50-92-0-1.html');
insert into forum (fsrcid, prev, fname, site, url) value ('n98','0','互联网金融','news','http://forum.home.news.cn/list/98-0-0-1.html');
insert into forum (fsrcid, prev, fname, site, url) value ('n323','0','谈股论金','news','http://forum.home.news.cn/list/98-323-0-1.html');
insert into forum (fsrcid, prev, fname, site, url) value ('n561','0','E起理财','news','http://forum.home.news.cn/list/98-561-0-1.html');
insert into forum (fsrcid, prev, fname, site, url) value ('n562','0','楼市观察','news','http://forum.home.news.cn/list/98-562-0-1.html');
insert into forum (fsrcid, prev, fname, site, url) value ('n75','0','文化生活','news','http://forum.home.news.cn/list/75-0-0-1.html');
insert into forum (fsrcid, prev, fname, site, url) value ('n145','0','健康养生','news','http://forum.home.news.cn/list/75-145-0-1.html');
insert into forum (fsrcid, prev, fname, site, url) value ('n91','0','文化闲谈','news','http://forum.home.news.cn/list/91-0-0-1.html');
insert into forum (fsrcid, prev, fname, site, url) value ('n83','0','网友俱乐部','news','http://forum.home.news.cn/list/83-0-0-1.html');
insert into forum (fsrcid, prev, fname, site, url) value ('n605','0','当代文坛','news','http://forum.home.news.cn/list/75-605-0-1.html');
insert into forum (fsrcid, prev, fname, site, url) value ('n93','0','体育论坛','news','http://forum.home.news.cn/list/93-0-0-1.html');
insert into forum (fsrcid, prev, fname, site, url) value ('n147','0','我是段子手','news','http://forum.home.news.cn/list/75-147-0-1.html');
insert into forum (fsrcid, prev, fname, site, url) value ('n80','0','摄影论坛','news','http://forum.home.news.cn/list/80-0-0-1.html');
insert into forum (fsrcid, prev, fname, site, url) value ('n508','0','风光旅行','news','http://forum.home.news.cn/list/80-508-0-1.html');
insert into forum (fsrcid, prev, fname, site, url) value ('n509','0','人像艺术','news','http://forum.home.news.cn/list/80-509-0-1.html');
insert into forum (fsrcid, prev, fname, site, url) value ('n152','0','摄影杂谈','news','http://forum.home.news.cn/list/80-152-0-1.html');
insert into forum (fsrcid, prev, fname, site, url) value ('n512','0','活动专区','news','http://forum.home.news.cn/list/80-512-0-1.html');
insert into forum (fsrcid, prev, fname, site, url) value ('n623','0','草原魅影','news','http://forum.home.news.cn/list/80-623-0-1.html');
insert into forum (fsrcid, prev, fname, site, url) value ('n82','0','地方论坛','news','http://forum.home.news.cn/list/82-0-0-1.html');
insert into forum (fsrcid, prev, fname, site, url) value ('n376','0','熊猫社区','news','http://forum.home.news.cn/list/376-0-0-1.html');
insert into forum (fsrcid, prev, fname, site, url) value ('n484','0','民声江苏','news','http://forum.home.news.cn/list/82-484-0-1.html');
insert into forum (fsrcid, prev, fname, site, url) value ('n373','0','黄鹤论坛','news','http://forum.home.news.cn/list/82-373-0-1.html');
insert into forum (fsrcid, prev, fname, site, url) value ('n368','0','吉人吉事','news','http://forum.home.news.cn/list/82-368-0-1.html');
insert into forum (fsrcid, prev, fname, site, url) value ('n538','0','无人机','news','http://forum.home.news.cn/list/538-0-0-1.html');
insert into forum (fsrcid, prev, fname, site, url) value ('n224','0','新科技','news','http://forum.home.news.cn/list/50-224-0-1.html');

-- 
-- 针对南方网论坛, 采用手动插入.
-- 
insert into forum (fsrcid, prev, fname, site, url) value ('scn13','0','广东发展论坛','southcn','http://bbs.southcn.com/forum-13-1.html');
insert into forum (fsrcid, prev, fname, site, url) value ('scn2','0','岭南茶馆','southcn','http://bbs.southcn.com/forum-2-1.html');
insert into forum (fsrcid, prev, fname, site, url) value ('scn25','0','灌水专区','southcn','http://bbs.southcn.com/forum-25-1.html');
insert into forum (fsrcid, prev, fname, site, url) value ('scn45','0','新闻跟贴','southcn','http://bbs.southcn.com/forum-45-1.html');
insert into forum (fsrcid, prev, fname, site, url) value ('scn213','scn13','东莞论坛','southcn','http://bbs.southcn.com/forum-213-1.html');
insert into forum (fsrcid, prev, fname, site, url) value ('scn152','scn13','文明论坛•道德观察','southcn','http://bbs.southcn.com/forum-152-1.html');
insert into forum (fsrcid, prev, fname, site, url) value ('scn212','scn25','谈股论金','southcn','http://bbs.southcn.com/forum-212-1.html');

-- 
-- 针对驴友论坛, 采用手动插入.
-- 
insert into crawier.forum (fsrcid, fname, site, url, prev) value('12', '户外大厅', '8264', 'http://bbs.8264.com/forum-12-1.html', '0');
insert into crawier.forum (fsrcid, fname, site, url, prev) value('5', '商业活动|线路', '8264', 'http://bbs.8264.com/forum-5-1.html', '0');
insert into crawier.forum (fsrcid, fname, site, url, prev) value('161', 'AA相约', '8264', 'http://bbs.8264.com/forum-161-1.html', '0');
insert into crawier.forum (fsrcid, fname, site, url, prev) value('52', '游记攻略', '8264', 'http://bbs.8264.com/forum-52-1.html', '0');
insert into crawier.forum (fsrcid, fname, site, url, prev) value('39', '户外摄影', '8264', 'http://bbs.8264.com/forum-39-1.html', '0');
insert into crawier.forum (fsrcid, fname, site, url, prev) value('56', '我秀我户外', '8264', 'http://bbs.8264.com/forum-56-1.html', '0');
insert into crawier.forum (fsrcid, fname, site, url, prev) value('69', '走出国门', '8264', 'http://bbs.8264.com/forum-69-1.html', '0');
insert into crawier.forum (fsrcid, fname, site, url, prev) value('60', '户外资料', '8264', 'http://bbs.8264.com/forum-60-1.html', '0');
insert into crawier.forum (fsrcid, fname, site, url, prev) value('56', '山伍成群', '8264', 'http://bbs.8264.com/forum-24-1.html', '0');
insert into crawier.forum (fsrcid, fname, site, url, prev) value('69', '攀岩|探洞|绳降', '8264', 'http://bbs.8264.com/forum-135-1.html', '0');
insert into crawier.forum (fsrcid, fname, site, url, prev) value('60', '骑行天下', '8264', 'http://bbs.8264.com/forum-88-1.html', '0');
insert into crawier.forum (fsrcid, fname, site, url, prev) value('56', '钓鱼|水上', '8264', 'http://bbs.8264.com/forum-179-2.html', '0');
insert into crawier.forum (fsrcid, fname, site, url, prev) value('69', '滑雪', '8264', 'http://bbs.8264.com/forum-186-1.html', '0');
insert into crawier.forum (fsrcid, fname, site, url, prev) value('60', '自驾|摩托车', '8264', 'http://bbs.8264.com/forum-66-1.html', '0');
insert into crawier.forum (fsrcid, fname, site, url, prev) value('56', '跑步|越野跑', '8264', 'http://bbs.8264.com/forum-495-1.html', '0');
insert into crawier.forum (fsrcid, fname, site, url, prev) value('69', '装备天下', '8264', 'http://bbs.8264.com/forum-7-1.html', '0');
insert into crawier.forum (fsrcid, fname, site, url, prev) value('60', '装备交易', '8264', 'http://bbs.8264.com/forum-53-1.html', '0');
insert into crawier.forum (fsrcid, fname, site, url, prev) value('158', '安徽', '8264', 'http://bbs.8264.com/forum-158-1.html', '0');
insert into crawier.forum (fsrcid, fname, site, url, prev) value('101', '北京', '8264', 'http://bbs.8264.com/forum-101-1.html', '0');
insert into crawier.forum (fsrcid, fname, site, url, prev) value('166', '重庆', '8264', 'http://bbs.8264.com/forum-166-1.html', '0');
insert into crawier.forum (fsrcid, fname, site, url, prev) value('113', '福建', '8264', 'http://bbs.8264.com/forum-113-1.html', '0');
insert into crawier.forum (fsrcid, fname, site, url, prev) value('110', '甘肃', '8264', 'http://bbs.8264.com/forum-110-1.html', '0');
insert into crawier.forum (fsrcid, fname, site, url, prev) value('112', '广东', '8264', 'http://bbs.8264.com/forum-112-1.html', '0');
insert into crawier.forum (fsrcid, fname, site, url, prev) value('108', '广西', '8264', 'http://bbs.8264.com/forum-108-1.html', '0');
insert into crawier.forum (fsrcid, fname, site, url, prev) value('176', '贵州', '8264', 'http://bbs.8264.com/forum-176-1.html', '0');
insert into crawier.forum (fsrcid, fname, site, url, prev) value('117', '海南', '8264', 'http://bbs.8264.com/forum-117-1.html', '0');
insert into crawier.forum (fsrcid, fname, site, url, prev) value('104', '河北', '8264', 'http://bbs.8264.com/forum-104-1.html', '0');
insert into crawier.forum (fsrcid, fname, site, url, prev) value('106', '河南', '8264', 'http://bbs.8264.com/forum-106-1.html', '0');
insert into crawier.forum (fsrcid, fname, site, url, prev) value('164', '装湖北备交易', '8264', 'http://bbs.8264.com/forum-164-1.html', '0');
insert into crawier.forum (fsrcid, fname, site, url, prev) value('114', '湖南', '8264', 'http://bbs.8264.com/forum-114-1.html', '0');
insert into crawier.forum (fsrcid, fname, site, url, prev) value('159', '黑龙江', '8264', 'http://bbs.8264.com/forum-159-1.html', '0');
insert into crawier.forum (fsrcid, fname, site, url, prev) value('116', '吉林', '8264', 'http://bbs.8264.com/forum-116-1.html', '0');
insert into crawier.forum (fsrcid, fname, site, url, prev) value('109', '江苏', '8264', 'http://bbs.8264.com/forum-109-1.html', '0');
insert into crawier.forum (fsrcid, fname, site, url, prev) value('111', '江西', '8264', 'http://bbs.8264.com/forum-111-1.html', '0');
insert into crawier.forum (fsrcid, fname, site, url, prev) value('115', '辽宁', '8264', 'http://bbs.8264.com/forum-115-1.html', '0');
insert into crawier.forum (fsrcid, fname, site, url, prev) value('170', '内蒙古', '8264', 'http://bbs.8264.com/forum-170-1.html', '0');
insert into crawier.forum (fsrcid, fname, site, url, prev) value('143', '宁夏', '8264', 'http://bbs.8264.com/forum-143-1.html', '0');
insert into crawier.forum (fsrcid, fname, site, url, prev) value('177', '青海', '8264', 'http://bbs.8264.com/forum-177-1.html', '0');
insert into crawier.forum (fsrcid, fname, site, url, prev) value('103', '山东', '8264', 'http://bbs.8264.com/forum-103-1.html', '0');
insert into crawier.forum (fsrcid, fname, site, url, prev) value('165', '山西', '8264', 'http://bbs.8264.com/forum-165-1.html', '0');
insert into crawier.forum (fsrcid, fname, site, url, prev) value('107', '陕西', '8264', 'http://bbs.8264.com/forum-107-1.html', '0');
insert into crawier.forum (fsrcid, fname, site, url, prev) value('48', '上海', '8264', 'http://bbs.8264.com/forum-48-1.html', '0');
insert into crawier.forum (fsrcid, fname, site, url, prev) value('102', '四川', '8264', 'http://bbs.8264.com/forum-102-1.html', '0');
insert into crawier.forum (fsrcid, fname, site, url, prev) value('100', '天津', '8264', 'http://bbs.8264.com/forum-100-1.html', '0');
insert into crawier.forum (fsrcid, fname, site, url, prev) value('171', '新疆', '8264', 'http://bbs.8264.com/forum-171-1.html', '0');
insert into crawier.forum (fsrcid, fname, site, url, prev) value('105', '云南', '8264', 'http://bbs.8264.com/forum-105-1.html', '0');
insert into crawier.forum (fsrcid, fname, site, url, prev) value('147', '浙江', '8264', 'http://bbs.8264.com/forum-147-1.html', '0');

-- 
-- 针对铁血网论坛, 采用手动插入.
-- 
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('gbbs24', '0', '警察之家', 'tiexue', 'http://bbs.tiexue.net/gbbs24-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs32', '0', '陆军论坛', 'tiexue', 'http://bbs.tiexue.net/bbs32-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs31', '0', '海军论坛', 'tiexue', 'http://bbs.tiexue.net/bbs31-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs69', '0', '空军论坛', 'tiexue', 'http://bbs.tiexue.net/bbs69-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs171', '0', '火箭军', 'tiexue', 'http://bbs.tiexue.net/bbs171-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs326', '0', '军事动漫', 'tiexue', 'http://bbs.tiexue.net/bbs326-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs124', '0', '军事影评', 'tiexue', 'http://bbs.tiexue.net/bbs124-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs200', '0', '军事书刊', 'tiexue', 'http://bbs.tiexue.net/bbs200-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs216', '0', '军事小说', 'tiexue', 'http://bbs.tiexue.net/bbs216-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs73', '0', '中国历史', 'tiexue', 'http://bbs.tiexue.net/bbs73-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs74', '0', '世界历史', 'tiexue', 'http://bbs.tiexue.net/bbs74-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs172', '0', '一二战史', 'tiexue', 'http://bbs.tiexue.net/bbs172-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs66', '0', '历史贴图', 'tiexue', 'http://bbs.tiexue.net/bbs66-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs33', '0', '环球风云', 'tiexue', 'http://bbs.tiexue.net/bbs33-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs219', '0', '军品鉴赏', 'tiexue', 'http://bbs.tiexue.net/bbs219-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs292', '0', '军品评测', 'tiexue', 'http://bbs.tiexue.net/bbs292-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs260', '0', '龙牙战术', 'tiexue', 'http://bbs.tiexue.net/bbs260-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs79', '0', '军品置换', 'tiexue', 'http://bbs.tiexue.net/bbs79-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs291', '0', '实弹射击', 'tiexue', 'http://bbs.tiexue.net/bbs291-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs294', '0', '生存狂', 'tiexue', 'http://bbs.tiexue.net/bbs294-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs283', '0', '老兵公益', 'tiexue', 'http://bbs.tiexue.net/bbs283-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs68', '0', '社会聚焦', 'tiexue', 'http://bbs.tiexue.net/bbs68-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs155', '0', '国货当自强', 'tiexue', 'http://bbs.tiexue.net/bbs155-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs114', '0', '职场人生', 'tiexue', 'http://bbs.tiexue.net/bbs114-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs214', '0', '学生时代', 'tiexue', 'http://bbs.tiexue.net/bbs214-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs203', '0', '谈股论金', 'tiexue', 'http://bbs.tiexue.net/bbs203-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs173', '0', '娱乐八卦', 'tiexue', 'http://bbs.tiexue.net/bbs173-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs251', '0', '警察故事', 'tiexue', 'http://bbs.tiexue.net/bbs251-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs252', '0', '警用装备', 'tiexue', 'http://bbs.tiexue.net/bbs252-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs253', '0', '警务探讨', 'tiexue', 'http://bbs.tiexue.net/bbs253-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs254', '0', '警事聚焦', 'tiexue', 'http://bbs.tiexue.net/bbs254-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs255', '0', '便民咨询', 'tiexue', 'http://bbs.tiexue.net/bbs255-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs125', '0', '社会贴图', 'tiexue', 'http://bbs.tiexue.net/bbs125-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs10', '0', '幽默贴图', 'tiexue', 'http://bbs.tiexue.net/bbs10-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs206', '0', '图说海外', 'tiexue', 'http://bbs.tiexue.net/bbs206-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs268', '0', '猎奇论坛', 'tiexue', 'http://bbs.tiexue.net/bbs268-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs246', '0', '四海钓鱼', 'tiexue', 'http://bbs.tiexue.net/bbs246-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs247', '0', '人间美食', 'tiexue', 'http://bbs.tiexue.net/bbs247-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs286', '0', '骑行天下', 'tiexue', 'http://bbs.tiexue.net/bbs286-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs248', '0', '网友实拍', 'tiexue', 'http://bbs.tiexue.net/bbs248-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs249', '0', '摄影艺术', 'tiexue', 'http://bbs.tiexue.net/bbs249-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs24', '0', '历史*都市', 'tiexue', 'http://bbs.tiexue.net/bbs24-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs27', '0', '小说讨论', 'tiexue', 'http://bbs.tiexue.net/bbs27-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs216', '0', '军事小说', 'tiexue', 'http://bbs.tiexue.net/bbs216-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs263', '0', '影视讨论', 'tiexue', 'http://bbs.tiexue.net/bbs263-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs239', '0', '男人情感', 'tiexue', 'http://bbs.tiexue.net/bbs239-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs307', '0', '射雕英雄传', 'tiexue', 'http://bbs.tiexue.net/bbs307-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs287', '0', '三十六计', 'tiexue', 'http://bbs.tiexue.net/bbs287-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs257', '0', '铁血战机', 'tiexue', 'http://bbs.tiexue.net/bbs257-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs304', '0', '最佳阵容', 'tiexue', 'http://bbs.tiexue.net/bbs304-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs310', '0', '神道', 'tiexue', 'http://bbs.tiexue.net/bbs310-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs250', '0', '铁骑冲锋', 'tiexue', 'http://bbs.tiexue.net/bbs250-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs303', '0', '热血三国3', 'tiexue', 'http://bbs.tiexue.net/bbs303-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs272', '0', '荣誉之路', 'tiexue', 'http://bbs.tiexue.net/bbs272-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs325', '0', '最后一炮', 'tiexue', 'http://bbs.tiexue.net/bbs325-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs265', '0', '盗墓笔记', 'tiexue', 'http://bbs.tiexue.net/bbs265-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs322', '0', '斗三国', 'tiexue', 'http://bbs.tiexue.net/bbs322-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs323', '0', '女神联盟2', 'tiexue', 'http://bbs.tiexue.net/bbs323-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs321', '0', '传奇盛世', 'tiexue', 'http://bbs.tiexue.net/bbs321-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs318', '0', 'H5游戏中心', 'tiexue', 'http://bbs.tiexue.net/bbs318-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs135', '0', '游戏大杂烩', 'tiexue', 'http://bbs.tiexue.net/bbs135-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs315', '0', '二战1944', 'tiexue', 'http://bbs.tiexue.net/bbs315-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs320', '0', '坦克大全', 'tiexue', 'http://bbs.tiexue.net/bbs320-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs316', '0', '创世兵魂', 'tiexue', 'http://bbs.tiexue.net/bbs316-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs313', '0', '热血三国2', 'tiexue', 'http://bbs.tiexue.net/bbs313-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs290', '0', '我欲封天', 'tiexue', 'http://bbs.tiexue.net/bbs290-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs282', '0', '攻城掠地', 'tiexue', 'http://bbs.tiexue.net/bbs282-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs280', '0', '战争雷霆', 'tiexue', 'http://bbs.tiexue.net/bbs280-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs273', '0', '第一舰队', 'tiexue', 'http://bbs.tiexue.net/bbs273-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs259', '0', '将军', 'tiexue', 'http://bbs.tiexue.net/bbs259-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs222', '0', '傲视天地', 'tiexue', 'http://bbs.tiexue.net/bbs222-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs245', '0', '帝国文明', 'tiexue', 'http://bbs.tiexue.net/bbs245-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs242', '0', '光荣使命OL', 'tiexue', 'http://bbs.tiexue.net/bbs242-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs215', '0', '游戏先锋', 'tiexue', 'http://bbs.tiexue.net/bbs215-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs209', '0', '射击游戏区', 'tiexue', 'http://bbs.tiexue.net/bbs209-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs217', '0', '铁血战队', 'tiexue', 'http://bbs.tiexue.net/bbs217-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs78', '0', '体育天地', 'tiexue', 'http://bbs.tiexue.net/bbs78-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs140', '0', '体育竞猜', 'tiexue', 'http://bbs.tiexue.net/bbs140-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs243', '0', '铁血赛马', 'tiexue', 'http://bbs.tiexue.net/bbs243-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs76', '0', '士兵俱乐部', 'tiexue', 'http://bbs.tiexue.net/bbs76-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs77', '0', '铁血妹妹', 'tiexue', 'http://bbs.tiexue.net/bbs77-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs80', '0', '搞笑幽默', 'tiexue', 'http://bbs.tiexue.net/bbs80-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs10', '0', '幽默贴图', 'tiexue', 'http://bbs.tiexue.net/bbs10-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs268', '0', '猎奇论坛', 'tiexue', 'http://bbs.tiexue.net/bbs268-0-1.html');
insert into crawier.forum (fsrcid, prev, fname, site, url) value ('bbs95', '0', '中国文化', 'tiexue', 'http://bbs.tiexue.net/bbs95-0-1.html');