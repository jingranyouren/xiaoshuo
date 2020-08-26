/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/8/12 20:12:19							*/
/* Update  by :    logytj                                       */
/*==============================================================*/
drop database if exists db_loggty_xiaoshuo_db;
create database db_loggty_xiaoshuo_db;
use db_loggty_xiaoshuo_db;

drop table if exists add_bookshell;

drop table if exists discuss;

drop table if exists read_notes;

drop table if exists tb_logytj_xiaoshuo_book;

drop table if exists tb_logytj_xiaoshuo_book_content;

drop table if exists tb_logytj_xiaoshuo_carousel;

drop table if exists tb_logytj_xiaoshuo_category;

drop table if exists tb_logytj_xiaoshuo_index_config;

drop table if exists tb_logytj_xiaoshuo_root_category;

drop table if exists tb_logytj_xiaoshuo_style;

drop table if exists tb_logytj_xiaoshuo_user;

/*==============================================================*/
/* Table: add_bookshell                                         */
/*==============================================================*/
create table add_bookshell
(
   user_id              int(11) not null,
   book_id              int(11) not null,
   add_time             datetime	default current_timestamp	comment '加入书架的时间',
   primary key (user_id, book_id)
)engine = InnoDB character set = utf8 comment '书架表';

/*==============================================================*/
/* Table: discuss                                               */
/*==============================================================*/
create table discuss
(
   user_id              int(11) not null,
   book_id              int(11) not null,
   discuss_time         datetime  not null default current_timestamp,
   content              varchar(200)	comment '评论内容',
   agreen				int(11)			comment '点赞次数',
   primary key (user_id, book_id, discuss_time)
)engine = InnoDB character set = utf8 comment '评论表';

/*==============================================================*/
/* Table: read_notes                                            */
/*==============================================================*/
create table read_notes
(
   user_id              int(11) not null,
   book_id              int(11) not null,
   last_read_time       datetime	not null default current_timestamp comment '上次阅读时间',
   last_read_content_id int(11)    not null  comment '上次阅读的章节id',
   primary key (user_id, book_id)
)engine = InnoDB character set = utf8 comment '最近阅读记录表';

/*==============================================================*/
/* Table: tb_logytj_xiaoshuo_book                               */
/*==============================================================*/
create table tb_logytj_xiaoshuo_book
(
   book_id              int(11) not null	auto_increment,
   book_name            varchar(32)		not null,
   book_img             varchar(200)	not null  default 'http://127.0.0.1:8080/img/book_default.png' comment '小说图片存放路径 url',
   author               varchar(8)		not null  default '佚名',
   word_num             float(2)	not null default 0 comment	'小说总字数 单位  万字',	
   update_status        tinyint			default 0 comment '小说的状态  0 连载中 ；1 完结',
   intro                varchar(1024)	default '',
   book_file_path       varchar(200)	comment '小说内容存放路径 url',
   free_status          tinyint			default 1		comment '免费状态 ， 0 免费  1 收费 2 限时免费' check(free_status in (0,1,2)),
   free_start_time      datetime		default null comment '限时免费开始时间',
   free_end_time        datetime		default null comment '限时免费结束时间',
   categoty_id          int(11)				comment '小说分类 id',
   primary key (book_id)
)engine = InnoDB auto_increment = 3 character set = utf8 comment '小说表';


/*==============================================================*/
/* Table: tb_logytj_xiaoshuo_book_content                               */
/*==============================================================*/
create table tb_logytj_xiaoshuo_book_content(
	book_id				int(11) not null 	comment '外键',
    book_content_id		int(11) not null 	comment '章节id',
    book_content_name   varchar(120) not null comment '章节名',
    book_content_path	varchar(200) not null	comment '章节内容路径',
    primary key (book_content_id)
    
)engine = InnoDB auto_increment = 3 character set = utf8 comment '小说章节内容表';


/*==============================================================*/
/* Table: tb_logytj_xiaoshuo_carousel                           */
/*==============================================================*/
create table tb_logytj_xiaoshuo_carousel
(
   book_id              int(11),
   carousel_id          int(11)		primary key not null	auto_increment  ,
   carousel_img			varchar(500) comment '轮播图片地址url',
   carousel_sex			char(2) 	check(carousel_sex in('男','女')),
   `rank`                int(11)	comment '排序值',
   is_deleted 			tinyint	 comment '0 未删除；1 删除'	check (is_deleted in(0,1)) 
)engine = InnoDB auto_increment = 3 character set = utf8 comment '轮播图表';

/*==============================================================*/
/* Table: tb_logytj_xiaoshuo_category                           */
/*==============================================================*/
create table tb_logytj_xiaoshuo_category
(
   root_id              tinyint,
   categoty_id          int(11) primary key auto_increment,
   category_name        varchar(8)	not null,
   `rank`                 int(11),
   is_deleted           tinyint check (is_deleted in(0,1)) 
)engine = InnoDB auto_increment = 13 character set = utf8 comment '小说分类表';

/*==============================================================*/
/* Table: tb_logytj_xiaoshuo_index_config                       */
/*==============================================================*/
create table tb_logytj_xiaoshuo_index_config
(
   book_id              int(11),
   config_id            int(11) auto_increment,
   config_sex			char(2)		comment '性别' check (config_sex in('男','女')),
   config_type          tinyint not null comment '0 轮播图配置; 1 本周热门; 2 新书抢鲜；3 精选小说； 4 猜你喜欢' check(config_type in(0,1,2,3,4)),
   `rank`                 int(11),
   is_deleted			 tinyint	check (is_deleted in(0,1)),
   primary key (config_id)
)engine = InnoDB auto_increment = 3 character set = utf8 comment '首页配置表';

/*==============================================================*/
/* Table: tb_logytj_xiaoshuo_root_category                      */
/*==============================================================*/
create table tb_logytj_xiaoshuo_root_category
(
   root_id              tinyint not null,
   root_sex             char(2) check (root_sex in('男','女')) ,
   primary key (root_id)
)engine = InnoDB character set = utf8 comment '男女总分类';

/*==============================================================*/
/* Table: tb_logytj_xiaoshuo_style                              */
/*==============================================================*/
create table tb_logytj_xiaoshuo_style
(
   style_id             int(11) not null auto_increment,
   book_id              int(11),
   style_name           varchar(8)	comment '标签名',
   primary key (style_id)
)engine = InnoDB auto_increment = 3 character set = utf8 comment '小说标签表,如：打脸，扮猪吃虎';

/*==============================================================*/
/* Table: tb_logytj_xiaoshuo_user                               */
/*==============================================================*/
create table tb_logytj_xiaoshuo_user
(
   user_id              int(11) not null auto_increment,
   nick_name            varchar(32),
   header_img           varchar(200)	comment '头像地址',
   sex                  char(2) comment '性别' check (sex in('男','女')),
   create_time          datetime	comment '注册时间',
   primary key (user_id)
)engine = InnoDB auto_increment = 4 character set = utf8;

/****************************************************************/
/*																*/
/*               添加外键约束                                   */
/****************************************************************/
alter table add_bookshell add constraint FK_add_bookshell foreign key (user_id)
      references tb_logytj_xiaoshuo_user (user_id) on delete restrict on update restrict;

alter table add_bookshell add constraint FK_add_bookshell2 foreign key (book_id)
      references tb_logytj_xiaoshuo_book (book_id) on delete restrict on update restrict;

alter table discuss add constraint FK_discuss foreign key (user_id)
      references tb_logytj_xiaoshuo_user (user_id) on delete restrict on update restrict;

alter table discuss add constraint FK_discuss2 foreign key (book_id)
      references tb_logytj_xiaoshuo_book (book_id) on delete restrict on update restrict;

alter table read_notes add constraint FK_read_notes foreign key (user_id)
      references tb_logytj_xiaoshuo_user (user_id) on delete restrict on update restrict;

alter table read_notes add constraint FK_read_notes2 foreign key (book_id)
      references tb_logytj_xiaoshuo_book (book_id) on delete restrict on update restrict;

alter table tb_logytj_xiaoshuo_carousel add constraint FK_map foreign key (book_id)
      references tb_logytj_xiaoshuo_book (book_id) on delete restrict on update restrict;

alter table tb_logytj_xiaoshuo_category add constraint FK_has foreign key (root_id)
      references tb_logytj_xiaoshuo_root_category (root_id) on delete restrict on update restrict;

alter table tb_logytj_xiaoshuo_index_config add constraint FK_config foreign key (book_id)
      references tb_logytj_xiaoshuo_book (book_id) on delete restrict on update restrict;

alter table tb_logytj_xiaoshuo_style add constraint FK_have foreign key (book_id)
      references tb_logytj_xiaoshuo_book (book_id) on delete restrict on update restrict;

alter table tb_logytj_xiaoshuo_book add constraint FK_category foreign key (categoty_id)
	  references tb_logytj_xiaoshuo_category (categoty_id) on delete restrict on update restrict;

alter table tb_logytj_xiaoshuo_book_content add constraint FK_book_content foreign key (book_id)
	  references tb_logytj_xiaoshuo_book (book_id) on delete restrict on update restrict;
   
      
/****************************************************************/
/*																*/
/*               添加数据                                       */
/****************************************************************/

				#tb_logytj_xiaoshuo_root_category
insert into tb_logytj_xiaoshuo_root_category(root_id,root_sex) values(0,'男');
insert into tb_logytj_xiaoshuo_root_category(root_id,root_sex) values(1,'女');


				#tb_logytj_xiaoshuo_category
insert into tb_logytj_xiaoshuo_category(root_id,categoty_id,category_name,`rank`,is_deleted) values(0,0,'都市情感',0,0);
insert into tb_logytj_xiaoshuo_category(root_id,categoty_id,category_name,`rank`,is_deleted) values(0,1,'都市异能',1,0);
insert into tb_logytj_xiaoshuo_category(root_id,categoty_id,category_name,`rank`,is_deleted) values(0,2,'玄幻奇幻',2,0);
insert into tb_logytj_xiaoshuo_category(root_id,categoty_id,category_name,`rank`,is_deleted) values(0,3,'历史军事',3,0);
insert into tb_logytj_xiaoshuo_category(root_id,categoty_id,category_name,`rank`,is_deleted) values(0,4,'仙侠武侠',4,0);
insert into tb_logytj_xiaoshuo_category(root_id,categoty_id,category_name,`rank`,is_deleted) values(0,5,'游戏竞技',5,0);
insert into tb_logytj_xiaoshuo_category(root_id,categoty_id,category_name,`rank`,is_deleted) values(0,6,'科幻末世',6,0);

insert into tb_logytj_xiaoshuo_category(root_id,categoty_id,category_name,`rank`,is_deleted) values(1,7,'总裁豪门',7,0);
insert into tb_logytj_xiaoshuo_category(root_id,categoty_id,category_name,`rank`,is_deleted) values(1,8,'婚恋生活',8,0);
insert into tb_logytj_xiaoshuo_category(root_id,categoty_id,category_name,`rank`,is_deleted) values(1,9,'古代言情',9,0);
insert into tb_logytj_xiaoshuo_category(root_id,categoty_id,category_name,`rank`,is_deleted) values(1,10,'穿越重生',10,0);
insert into tb_logytj_xiaoshuo_category(root_id,categoty_id,category_name,`rank`,is_deleted) values(1,11,'幻想时空',11,0);
insert into tb_logytj_xiaoshuo_category(root_id,categoty_id,category_name,`rank`,is_deleted) values(1,12,'短篇',12,0);

				#tb_logytj_xiaoshuo_book
insert into tb_logytj_xiaoshuo_book(book_id,book_name,book_img,author,word_num,update_status,intro,book_file_path,free_status,free_start_time,free_end_time,categoty_id)
							 values(4,'我在幕后调教大佬','http://127.0.0.1:8080/img/300.jpg','阎ZK',91.67,0,'妖魔，神灵，怪异。云上俯瞰天下的灵，行走在大地上的人。赵离一
                             头载入了这个危险的时代，直接地狱开局。唯一的破局在于他异变的梦境。为了拼命活下去，他不得不走上一条在梦里具现出各大boss，然后想方设法抓人入梦，
                             ‘培养’这批人的道路。在当年蹂躏过第四天灾的boss们残酷的训练之下，赵离得到了自己想要的东西，心满意足地把他们放了出去——于是，这个世界发现，新的
                             ‘boss’们诞生了。','http://127.0.0.1:8080/file/300.txt',1,null,null,2);
insert into tb_logytj_xiaoshuo_book(book_id,book_name,book_img,author,word_num,update_status,intro,book_file_path,free_status,free_start_time,free_end_time,categoty_id)
							 values(5,'法术真理','http://127.0.0.1:8080/img/400.jpg','奇迹祈愿',124.74,0,'　　赵旭重生十年前，回到了《亚瑟》刚刚开服的那一刻。
只有他知道，一年后，地球毁灭，地球人类都会穿越到亚瑟世界里！（群号:191645033）
PS：主角双精英法师卷宗神奥双修，后期超魔／法守／咒文／红玉／潜能／多动／持久／暂效／触发／刷卡套装预备中（参考DND设定）','http://127.0.0.1:8080/file/400.txt',1,null,null,5);

insert into tb_logytj_xiaoshuo_book(book_id,book_name,book_img,author,word_num,update_status,intro,book_file_path,free_status,free_start_time,free_end_time,categoty_id)
							 values(6,'魔卡诸天','http://127.0.0.1:8080/img/500.jpg','威馆长',66.59,0,'　　　　一个卡牌主宰的世界，每个人都能进入到“异世界”获取卡牌。
卫渊发现，为什么这些异世界和我知道的那些影视动漫游戏，有那么点似是而非呢？','http://127.0.0.1:8080/file/500.txt',1,null,null,2);

insert into tb_logytj_xiaoshuo_book(book_id,book_name,book_img,author,word_num,update_status,intro,book_file_path,free_status,free_start_time,free_end_time,categoty_id)
							 values(7,'我成了二周目BOSS','http://127.0.0.1:8080/img/600.jpg','威馆长',78.49,0,'每当他入梦，便会降临某游戏的二周目BOSS身上。他是海底拉莱耶的沉睡之神，是游戏里的旧日支配者，是令人疯狂的梦境之主。因游戏越发火爆，越来越多的狂热信徒呼唤祂的名字。克蒙每次醒来，屋子里奇怪的东西又增加了。身为一名开箱测评UP主，常常为此烦恼。……（前中期开箱测评、献祭流；大后期BOSS流）（群707979802）','http://127.0.0.1:8080/file/600.txt',1,null,null,2);

insert into tb_logytj_xiaoshuo_book(book_id,book_name,book_img,author,word_num,update_status,intro,book_file_path,free_status,free_start_time,free_end_time,categoty_id)
							 values(8,'北方有二哈','http://127.0.0.1:8080/img/800.jpg','感觉挺冷',92.23,0,'【胎穿】【日常】【非典型兽人文】【1V1】北方有二哈，蠢萌会拆家～天生烟熏妆，鼻梁带高光～
                             ……………………………………………………露娜穿越了。自此原本的长发飘飘变成了一身毛。两条腿走路，变成了四条腿着地。外加穿越大神倾情奉送的尾巴一条……本着捡回条小命不易。露娜顶着张蠢萌二哈脸，
                             咬牙下定决心放飞自我，一切从零学起。好不容易适应了从人变狗，啊呸，是从人变狼的日子。下一步却是要琢磨着怎么变回来','http://127.0.0.1:8080/file/800.txt',1,null,null,9);

insert into tb_logytj_xiaoshuo_book(book_id,book_name,book_img,author,word_num,update_status,intro,book_file_path,free_status,free_start_time,free_end_time,categoty_id)
							 values(9,'我全家都是穿越来的','http://127.0.0.1:8080/img/900.jpg','YTT桃桃',194.83,0,'一家三口穿越古代，穿越过来就遇战乱，只能带着油带着酒，
                             浪迹天涯一起走。冷了，没法加外套；饿了，没地买面包；遇到抢劫要人命，没有医院给看病。就这样，即便哭着去逃荒，仍要笑着相信未来能绽放。','http://127.0.0.1:8080/file/900.txt',1,null,null,9);

insert into tb_logytj_xiaoshuo_book(book_id,book_name,book_img,author,word_num,update_status,intro,book_file_path,free_status,free_start_time,free_end_time,categoty_id)
							 values(10,'药植空间有点田','http://127.0.0.1:8080/img/1000.jpg','吴家二小姐',80.54,0,'“娘，二弟家今天盖新房了，是二进的呢！”“娘，娘，二郎中秀才了，二弟好大方，给来报喜的人，一人二钱银子呢！”“娘，娘…..”“你别再说了！”老太太一屁股坐在地上，拍着大腿哭嚎起来：“老天爷你不公啊，别人分家都是越过越穷，凭啥那死丫头家越过越好，早知这样，我还分什么家啊…..！”叶蓁：我不会告诉你，分家是我一手促成的！','http://127.0.0.1:8080/file/10000.txt',1,null,null,9);
insert into tb_logytj_xiaoshuo_book(book_id,book_name,book_img,author,word_num,update_status,intro,book_file_path,free_status,free_start_time,free_end_time,categoty_id)
							 values(11,'表小姐','http://127.0.0.1:8080/img/11000.jpg','吱吱',16.34,0,'王晞的母亲为给她说门体面的亲事，把她送到京城的永城侯府家镀金。可出身蜀中巨贾之家的王晞却觉得京城哪哪儿都不好，只想着什么时候能早点回家。直到有一天，她偶然间发现自己住的后院假山上可以用千里镜看见隔壁长公主府……她顿时眼睛一亮——长公主之子陈珞可真英俊！永城侯府的表姐们可真有趣！京城好好玩！','http://127.0.0.1:8080/file/11000.txt',1,null,null,9);

insert into tb_logytj_xiaoshuo_book(book_id,book_name,book_img,author,word_num,update_status,intro,book_file_path,free_status,free_start_time,free_end_time,categoty_id)
							 values(12,'万族之劫','http://127.0.0.1:8080/img/1200.jpg','威馆长',389.16,0,'我是这诸天万族的劫！已有完本作品《全球高武》《重生之财源滚滚》，没看过的书友可以去看看，新书收藏一下慢慢养。','http://127.0.0.1:8080/file/1200.txt',1,null,null,2);

insert into tb_logytj_xiaoshuo_book(book_id,book_name,book_img,author,word_num,update_status,intro,book_file_path,free_status,free_start_time,free_end_time,categoty_id)
							 values(13,'我师兄实在太稳健了','http://127.0.0.1:8080/img/1300.jpg','言归正传',389.16,0,'　重生在封神大战之前的上古时代，李长寿成了一个小小的炼气士，没有什么气运加身，也不是什么注定的大劫之子，他只有一个想要长生不老的修仙梦。
     为了能在残酷的洪荒安身立命，他努力不沾因果，杀人必扬其灰，凡事谋而后动，从不轻易步入危险之中。
      藏底牌，修遁术，炼丹毒，掌神通，不动稳如老狗，一动石破天惊，动后悄声走人。
     本来李长寿规划中，自己会一直躲在山中平安无事的修行成仙，直到有一年，他的老师父静极思动，又给他……收了个师妹回来','http://127.0.0.1:8080/file/1300.txt',1,null,null,4);
insert into tb_logytj_xiaoshuo_book(book_id,book_name,book_img,author,word_num,update_status,intro,book_file_path,free_status,free_start_time,free_end_time,categoty_id)
							 values(14,'大奉打更人','http://127.0.0.1:8080/img/1400.jpg','卖报小郎君',119.61,0,'这个世界，有儒；有道；有佛；有妖；有术士。警校毕业的许七安幽幽醒来，发现自己身处牢狱之中，三日后流放边陲.....他起初的目的只是自保，顺便在这个没有人权的社会里当个富家翁悠闲度日。......多年后，许七安回首前尘，身后是早已逝去的敌人，以及累累白骨。滚滚长江东逝水，浪花淘尽英雄，是非成败转头空。青山依旧在，几度夕阳红。','http://127.0.0.1:8080/file/1400.txt',1,null,null,4);
insert into tb_logytj_xiaoshuo_book(book_id,book_name,book_img,author,word_num,update_status,intro,book_file_path,free_status,free_start_time,free_end_time,categoty_id)
							 values(15,'霍格沃茨万事皆三','http://127.0.0.1:8080/img/1500.jpg','桐棠',296.24,0,'一、剧情：艾伦·哈里斯重生HP世界，试图改革巫师教育，打破《保密法》——最终boss不是伏地魔，是另一位原著中存在的角色。二、风格：本文非真系统文，依据HP风格，故事剧情的开展一本比一本黑暗。三、特点：万事皆三，JK罗琳用了7作为特别数字，而本文中，3则是一个有魔力的数字。万物归环，事物都是循环的，时间的洪流里，凡事皆有因由。','http://127.0.0.1:8080/file/1500.txt',1,null,null,2);

insert into tb_logytj_xiaoshuo_book(book_id,book_name,book_img,author,word_num,update_status,intro,book_file_path,free_status,free_start_time,free_end_time,categoty_id)
							 values(16,'大梦主','http://127.0.0.1:8080/img/1600.jpg','忘语',43.25,0,'一个从小体弱多病的富商之子，在寻求续命之法时，意外走上了修仙登天之路！大唐盛世，天下安泰，风调雨顺，百姓安居。千年后世，魔物吞天，妖鬼横行，遍野哀鸣。西游再现，大圣斗天，天蓬下凡，卷帘重生。莫名的穿梭与轮回，虚实掩映，真幻交织！是预言中的梦境？还是尚未发生的现实？他能否打破命中注定的魔障，消弭还未发生的三界大劫，挽救苍生于水火？','http://127.0.0.1:8080/file/1600.txt',1,null,null,4);

insert into tb_logytj_xiaoshuo_book(book_id,book_name,book_img,author,word_num,update_status,intro,book_file_path,free_status,free_start_time,free_end_time,categoty_id)
							 values(17,'神魔书','http://127.0.0.1:8080/img/1700.jpg','血红',34.07,0,'我是谁？我在哪？我在做什么？我要做什么？我想做什么？我所见、所闻、所经历的一切，又都是为了什么？眼前的世界，是真实还是虚幻？左边的道路，密布荆棘；右边的道路，鲜花着锦。我要选择哪一条？我为什么要选择这一条？愿绝对的秩序指引我们。愿至高的真理与我同在。','http://127.0.0.1:8080/file/1700.txt',1,null,null,2);

insert into tb_logytj_xiaoshuo_book(book_id,book_name,book_img,author,word_num,update_status,intro,book_file_path,free_status,free_start_time,free_end_time,categoty_id)
							 values(18,'逍遥章','http://127.0.0.1:8080/img/1800.jpg','姚颖怡',18.16,0,'华大小姐很烦恼，她做梦也没有想到，英明神武的她被一个傻子赖上了!这一切还要从一块石头说起……','http://127.0.0.1:8080/file/1800.txt',1,null,null,9);

insert into tb_logytj_xiaoshuo_book(book_id,book_name,book_img,author,word_num,update_status,intro,book_file_path,free_status,free_start_time,free_end_time,categoty_id)
							 values(19,'剑神在星际','http://127.0.0.1:8080/img/1900.jpg','念夫子',406.92,0,'修仙大陆最年轻的剑神渡劫后重生了，来到了星际时代，从此后被不靠谱的爹当成了男孩子养。本文有男主，么么啾','http://127.0.0.1:8080/file/1900.txt',1,null,null,11);

insert into tb_logytj_xiaoshuo_book(book_id,book_name,book_img,author,word_num,update_status,intro,book_file_path,free_status,free_start_time,free_end_time,categoty_id)
							 values(20,'她有一间时空小屋','http://127.0.0.1:8080/img/2000.jpg','蜀椒',255.49,0,'芩谷终于为自己的人生画上一个还算完满的句号，一份时空小屋的契约落到她的手上。面对这间破破烂烂的小木屋，芩谷从此便走上努力修缮小屋和提升自身实力的漫漫征程。——来我的时空小屋吧，绝地逆袭，成就美满人生。——诀窍只有一个：努力，努力，再努力！如果没有成功……那，那就继续努力！！！！（……这本的话，不出意外应该也是无男主的……）','http://127.0.0.1:8080/file/2000.txt',1,null,null,11);

insert into tb_logytj_xiaoshuo_book(book_id,book_name,book_img,author,word_num,update_status,intro,book_file_path,free_status,free_start_time,free_end_time,categoty_id)
							 values(21,'清穿咸鱼攻略','http://127.0.0.1:8080/img/2100.jpg','尤妮丝',78.52,0,'宅女林羡余被宫斗APP绑定，开启快穿之路，本应智斗嫔妃、攻略帝王，然鹅……看看那些渣得各有特色的渣渣，林羡余表示，争宠是不可能争宠的，她宁可当一条混吃等死的咸鱼！谁敢坏老娘享乐大业，咸鱼甩尾抽死之！第一场游戏：董鄂妃她妹第二场游戏：辛者库贱妇第三场游戏：十岁小福晋第四场游戏：福晋凶猛第五场游戏：包衣贵妃第六场游戏：雍正妾妃第七场游戏：固伦公主第八场游戏：未定~','http://127.0.0.1:8080/file/2100.txt',1,null,null,9);

insert into tb_logytj_xiaoshuo_book(book_id,book_name,book_img,author,word_num,update_status,intro,book_file_path,free_status,free_start_time,free_end_time,categoty_id)
							 values(22,'重生八零养狼崽','http://127.0.0.1:8080/img/2200.jpg','伊人为花',83.58,0,'【甜宠文，男强女强，1V1，随身空间】顾锦死后才知道唯一给她收尸，不择手段为她报仇的人，只有当年一饭之恩的小狼崽。重生后她找到正在被人欺辱，瘦成琵琶骨的小崽子，不顾众人的反对收养他。在这个遍地都是商机的年代，顾锦阴差阳错踏入异能世界，手持空间开始了养崽生涯。可不知道什么时候，她一不小心将崽崽养歪了……很多年以后。顾锦望着电视里看似俊雅，实则白切黑叱咤风云的商界帝王，不禁咬牙切齿。她以为养得是贴心小棉衣，却不曾想对方是吃人的小狼狗。————重活一世顾锦逆天改命，成为人人敬重的小九爷。上至世家豪门大族，下至新贵富商巨贾，无人不知。上古之术在手，富贵权势手中握！','http://127.0.0.1:8080/file/2200.txt',1,null,null,8);

insert into tb_logytj_xiaoshuo_book(book_id,book_name,book_img,author,word_num,update_status,intro,book_file_path,free_status,free_start_time,free_end_time,categoty_id)
							 values(23,'暗恋休止符','http://127.0.0.1:8080/img/2300.jpg','夕梨nunu',25.91,0,'我想带你看遍世间最美好的两情相悦我想让你听到暗恋休止后的浪漫心跳我想容你碰触情窦初开时的绯红面庞这是关于每一个人的青春故事，有你渴望过的高冷男神，也有你羡慕过的飒爽姑娘；有穿过流金庭院只为嗅你发香的王子，也有不靠魔法全凭自己努力坐上南瓜马车的灰姑娘；有你一直期待但又困惑着的爱情答案，也有你必经的种种人生选择。翻开书卷，没有虐恋，没有苦楚，只有浓情蜜意，怒放心花。阳光在你的世界久久停留，清风把你带到人间最美的地方，随主人公一起去看马尔代夫的白沙，牵手追寻琉璃浪花，去波士顿荡秋千吃龙虾，迈阿密海滩的冰饮碗口比脸大，在纽约帝国大厦的夕阳里说情话，赞叹迪士尼城堡夜空中的烟花，去凯恩斯的桉树林里喂考拉，在墨尔本黎明的热气球里等待朝霞，在京都鸭川边十指相扣品薄茶，在环球邮轮的顶层露台共赏月华。世界很大爱很浓，和故事里的人一样，你也一定会遇到那个温暖的人，陪你走过青春，走进光和爱里，和你一起，尝遍人间的糖。','http://127.0.0.1:8080/file/2300.txt',1,null,null,12);

insert into tb_logytj_xiaoshuo_book(book_id,book_name,book_img,author,word_num,update_status,intro,book_file_path,free_status,free_start_time,free_end_time,categoty_id)
							 values(24,'突然成仙了怎么办','http://127.0.0.1:8080/img/2400.jpg','欢颜笑语',91.94,0,'这天，林凡与圣女同时穿越，之后更是发现，他们可以无限互穿。随后，两人在不同时空，杠上了。直到某日，林凡穿越归来，却发现自己已经成仙！突然成仙了怎么办？在线等，挺急的。','http://127.0.0.1:8080/file/2400.txt',1,null,null,1);

insert into tb_logytj_xiaoshuo_book(book_id,book_name,book_img,author,word_num,update_status,intro,book_file_path,free_status,free_start_time,free_end_time,categoty_id)
							 values(25,'从山寨npc到大BOSS','http://127.0.0.1:8080/img/2500.jpg','白驹易逝',119.18,0,'“哪怕要当npc，我也绝不当炮灰npc！”凉山寨里，有“人”默默立下了一个flag。——我叫秦书剑。皇皇三十载，书剑两无成。这是一个游戏里面的npc，一步步成为大BOSS的故事。','http://127.0.0.1:8080/file/2500.txt',1,null,null,2);

insert into tb_logytj_xiaoshuo_book(book_id,book_name,book_img,author,word_num,update_status,intro,book_file_path,free_status,free_start_time,free_end_time,categoty_id)
							 values(26,'柯学验尸官','http://127.0.0.1:8080/img/2600.jpg','河流之汪',40.91,0,'　　【这是柯南同人】本书全称：《刚穿越到柯学世界就被名侦探指认为头号犯罪嫌疑人，身为验尸官的我现在一点不慌》','http://127.0.0.1:8080/file/2600.txt',1,null,null,6);

insert into tb_logytj_xiaoshuo_book(book_id,book_name,book_img,author,word_num,update_status,intro,book_file_path,free_status,free_start_time,free_end_time,categoty_id)
							 values(27,'酒名千愁醉','http://127.0.0.1:8080/img/2700.jpg','逐光的乳酸菌',25.90,0,'作为翠烟阁修为最低的云冰卿，却意外得到掌门厚爱，与当世六大天宗的天之骄子一同被送入了神秘遗迹。一年后得到奇遇回到各自宗派的七人，却意外发现各自所属门派已经惨遭灭门，而手握屠刀的，则是这个天下……忘忧酒馆，每晚亥时开业，只等待一位客人，一个故事。而今天，便是一名女子，与天下为敌的故事。--------------------------------------------倾城之姿，深宫侍君，只为深仇，可能如愿？得道高僧，失忆桃源，杀尽人畜，怒为红颜！苗疆圣女，钟情一见，阴差阳错，无语凝咽？邪派公子，正派少年，正邪有别，情深缘浅！------------------剑网三手游征文，背景是剑三手游背景年代，架空宋朝。','http://127.0.0.1:8080/file/2700.txt',1,null,null,9);

insert into tb_logytj_xiaoshuo_book(book_id,book_name,book_img,author,word_num,update_status,intro,book_file_path,free_status,free_start_time,free_end_time,categoty_id)
							 values(29,'师妹且慢','http://127.0.0.1:8080/img/2900.jpg','严歌玲',15.25,0,'桑隐小师妹，下山记忆没；揍人不眨眼，吃饭不给钱。刚被二师兄一脚踢出门派，赵幼菱摸着被踹得生疼的屁股蛋，脑海中想着怎么跟大师姐告状。下一刻，赵幼菱脑袋一空，发出夺命三连，我是谁，我在哪，我要干什么?镇国公府消散的痴情伶人；孤山古寺遗留前朝的佛花；边疆沙场重蹈血腥的覆辙；桑隐不入尘世的秘密……这一桩桩一件件的事儿实在是令人头疼。赵幼菱觉得她被坑了。不过嘛，这位世子爷还挺香的，与我有缘，想吃。','http://127.0.0.1:8080/file/2900.txt',1,null,null,9);

insert into tb_logytj_xiaoshuo_book(book_id,book_name,book_img,author,word_num,update_status,intro,book_file_path,free_status,free_start_time,free_end_time,categoty_id)
							 values(30,'清宫有毒','http://127.0.0.1:8080/img/3000.jpg','夕幼',49.79,1,'一睁眼，回到光绪十四年，我居然成了长叙家的五姑娘——他他拉•子兮，待六个月后嫁入宫中为珍嫔……是光绪皇帝一生中最宠爱的妃子，也是清朝历史上唯一一个敢跟慈禧硬杠的女人。','http://127.0.0.1:8080/file/3000.txt',1,null,null,10);


							
                            #tb_logytj_xiaoshuo_carousel
insert into tb_logytj_xiaoshuo_carousel(book_id,carousel_id,carousel_img,carousel_sex,`rank`,is_deleted)
									values(4,4,'http://127.0.0.1:8080/img/c6b3054532bbd9efde58a449a163bc3e.jpg','男',4,0);
                                    
insert into tb_logytj_xiaoshuo_carousel(book_id,carousel_id,carousel_img,carousel_sex,`rank`,is_deleted)
									values(5,5,'http://127.0.0.1:8080/img/1aee0dd747c4f70fc70c83cace2c0024.jpg','男',5,0);

insert into tb_logytj_xiaoshuo_carousel(book_id,carousel_id,carousel_img,carousel_sex,`rank`,is_deleted)
									values(6,6,'http://127.0.0.1:8080/img/052af333a3e86443a68f179df0d780db.jpg','男',6,0);     

insert into tb_logytj_xiaoshuo_carousel(book_id,carousel_id,carousel_img,carousel_sex,`rank`,is_deleted)
									values(7,7,'http://127.0.0.1:8080/img/f60390d2ab8d7716571ee34b14a768f7.jpg','男',7,0);
                                    
insert into tb_logytj_xiaoshuo_carousel(book_id,carousel_id,carousel_img,carousel_sex,`rank`,is_deleted)
									values(8,8,'http://127.0.0.1:8080/img/116eb9a0d22dae90ba2ea2b8107e8e06.jpg','女',8,0); 

insert into tb_logytj_xiaoshuo_carousel(book_id,carousel_id,carousel_img,carousel_sex,`rank`,is_deleted)
									values(9,9,'http://127.0.0.1:8080/img/3243d1bf000cfbdb374971b26bdf7355.jpg','女',9,0); 


insert into tb_logytj_xiaoshuo_carousel(book_id,carousel_id,carousel_img,carousel_sex,`rank`,is_deleted)
									values(10,10,'http://127.0.0.1:8080/img/b5985b3899d35dc41b8118e7d99b54d2.jpg','女',10,0); 

insert into tb_logytj_xiaoshuo_carousel(book_id,carousel_id,carousel_img,carousel_sex,`rank`,is_deleted)
									values(11,11,'http://127.0.0.1:8080/img/11647a4b046178951b0e9d623cc50c60.jpg','女',11,0); 
                                    
									#tb_logytj_xiaoshuo_index_config
insert into tb_logytj_xiaoshuo_index_config(book_id,config_id,config_sex,config_type,`rank`,is_deleted) values(12,12,'男',1,12,0);
insert into tb_logytj_xiaoshuo_index_config(book_id,config_id,config_sex,config_type,`rank`,is_deleted) values(13,13,'男',1,13,0);
insert into tb_logytj_xiaoshuo_index_config(book_id,config_id,config_sex,config_type,`rank`,is_deleted) values(14,14,'男',1,14,0);
insert into tb_logytj_xiaoshuo_index_config(book_id,config_id,config_sex,config_type,`rank`,is_deleted) values(15,15,'男',1,15,0);
insert into tb_logytj_xiaoshuo_index_config(book_id,config_id,config_sex,config_type,`rank`,is_deleted) values(16,16,'男',1,16,0);
insert into tb_logytj_xiaoshuo_index_config(book_id,config_id,config_sex,config_type,`rank`,is_deleted) values(17,17,'男',1,17,0);

insert into tb_logytj_xiaoshuo_index_config(book_id,config_id,config_sex,config_type,`rank`,is_deleted) values(18,18,'女',1,18,0);
insert into tb_logytj_xiaoshuo_index_config(book_id,config_id,config_sex,config_type,`rank`,is_deleted) values(19,19,'女',1,19,0);
insert into tb_logytj_xiaoshuo_index_config(book_id,config_id,config_sex,config_type,`rank`,is_deleted) values(20,20,'女',1,20,0);
insert into tb_logytj_xiaoshuo_index_config(book_id,config_id,config_sex,config_type,`rank`,is_deleted) values(21,21,'女',1,21,0);
insert into tb_logytj_xiaoshuo_index_config(book_id,config_id,config_sex,config_type,`rank`,is_deleted) values(22,22,'女',1,22,0);
insert into tb_logytj_xiaoshuo_index_config(book_id,config_id,config_sex,config_type,`rank`,is_deleted) values(23,23,'女',1,23,0);

insert into tb_logytj_xiaoshuo_index_config(book_id,config_id,config_sex,config_type,`rank`,is_deleted) values(27,27,'女',2,27,0);
insert into tb_logytj_xiaoshuo_index_config(book_id,config_id,config_sex,config_type,`rank`,is_deleted) values(29,29,'女',2,29,0);
insert into tb_logytj_xiaoshuo_index_config(book_id,config_id,config_sex,config_type,`rank`,is_deleted) values(30,30,'女',2,30,0);

insert into tb_logytj_xiaoshuo_index_config(book_id,config_id,config_sex,config_type,`rank`,is_deleted) values(24,24,'男',2,24,0);
insert into tb_logytj_xiaoshuo_index_config(book_id,config_id,config_sex,config_type,`rank`,is_deleted) values(25,25,'男',2,25,0);
insert into tb_logytj_xiaoshuo_index_config(book_id,config_id,config_sex,config_type,`rank`,is_deleted) values(26,26,'男',2,26,0);

						#add_bookshell
insert into add_bookshell(user_id,book_id,add_time) values(4,4,now());
insert into add_bookshell(user_id,book_id,add_time) values(4,5,now());
insert into add_bookshell(user_id,book_id,add_time) values(4,6,now());
insert into add_bookshell(user_id,book_id,add_time) values(4,7,now());
insert into add_bookshell(user_id,book_id,add_time) values(4,8,now());
insert into add_bookshell(user_id,book_id,add_time) values(4,9,now());
insert into add_bookshell(user_id,book_id,add_time) values(4,10,now());
insert into add_bookshell(user_id,book_id,add_time) values(4,11,now());
insert into add_bookshell(user_id,book_id,add_time) values(4,12,now());
insert into add_bookshell(user_id,book_id,add_time) values(4,13,now());
insert into add_bookshell(user_id,book_id,add_time) values(4,26,now());

#read_notes

insert into read_notes(user_id,book_id,last_read_time,last_read_content_id) values(4,5,now(),110826884);
insert into read_notes(user_id,book_id,last_read_time,last_read_content_id) values(4,4,now(),120822981);
insert into read_notes(user_id,book_id,last_read_time,last_read_content_id) values(4,7,now(),116592604);
insert into read_notes(user_id,book_id,last_read_time,last_read_content_id) values(4,6,now(),122864710);


#tb_logytj_xiaoshuo_style

insert into tb_logytj_xiaoshuo_style(style_id,book_id,style_name) values(5,5,'兵王');
insert into tb_logytj_xiaoshuo_style(style_id,book_id,style_name) values(6,5,'杀戮');

insert into tb_logytj_xiaoshuo_style(style_id,book_id,style_name) values(7,6,'兵王');
insert into tb_logytj_xiaoshuo_style(style_id,book_id,style_name) values(8,6,'杀戮');
insert into tb_logytj_xiaoshuo_style(style_id,book_id,style_name) values(9,6,'都市');
insert into tb_logytj_xiaoshuo_style(book_id,style_name) values(7,'兵王');
insert into tb_logytj_xiaoshuo_style(book_id,style_name) values(7,'杀戮');
insert into tb_logytj_xiaoshuo_style(book_id,style_name) values(7,'都市');

insert into tb_logytj_xiaoshuo_style(book_id,style_name) values(8,'兵王');
insert into tb_logytj_xiaoshuo_style(book_id,style_name) values(8,'杀戮');
insert into tb_logytj_xiaoshuo_style(book_id,style_name) values(8,'扮猪吃虎');
insert into tb_logytj_xiaoshuo_style(book_id,style_name) values(9,'兵王');
insert into tb_logytj_xiaoshuo_style(book_id,style_name) values(9,'杀戮');
insert into tb_logytj_xiaoshuo_style(book_id,style_name) values(9,'扮猪吃虎');
insert into tb_logytj_xiaoshuo_style(book_id,style_name) values(10,'兵王');
insert into tb_logytj_xiaoshuo_style(book_id,style_name) values(10,'杀戮');
insert into tb_logytj_xiaoshuo_style(book_id,style_name) values(10,'扮猪吃虎');
insert into tb_logytj_xiaoshuo_style(book_id,style_name) values(11,'兵王');
insert into tb_logytj_xiaoshuo_style(book_id,style_name) values(11,'杀戮');
insert into tb_logytj_xiaoshuo_style(book_id,style_name) values(11,'扮猪吃虎');
insert into tb_logytj_xiaoshuo_style(book_id,style_name) values(12,'兵王');
insert into tb_logytj_xiaoshuo_style(book_id,style_name) values(12,'杀戮');
insert into tb_logytj_xiaoshuo_style(book_id,style_name) values(12,'扮猪吃虎');
insert into tb_logytj_xiaoshuo_style(book_id,style_name) values(13,'兵王');
insert into tb_logytj_xiaoshuo_style(book_id,style_name) values(13,'杀戮');
insert into tb_logytj_xiaoshuo_style(book_id,style_name) values(13,'扮猪吃虎');
insert into tb_logytj_xiaoshuo_style(book_id,style_name) values(14,'兵王');
insert into tb_logytj_xiaoshuo_style(book_id,style_name) values(14,'杀戮');
insert into tb_logytj_xiaoshuo_style(book_id,style_name) values(15,'扮猪吃虎');
insert into tb_logytj_xiaoshuo_style(book_id,style_name) values(15,'兵王');
insert into tb_logytj_xiaoshuo_style(book_id,style_name) values(16,'杀戮');
insert into tb_logytj_xiaoshuo_style(book_id,style_name) values(16,'扮猪吃虎');
insert into tb_logytj_xiaoshuo_style(book_id,style_name) values(17,'兵王');
insert into tb_logytj_xiaoshuo_style(book_id,style_name) values(18,'杀戮');
insert into tb_logytj_xiaoshuo_style(book_id,style_name) values(19,'扮猪吃虎');
insert into tb_logytj_xiaoshuo_style(book_id,style_name) values(19,'兵王');
insert into tb_logytj_xiaoshuo_style(book_id,style_name) values(20,'杀戮');
insert into tb_logytj_xiaoshuo_style(book_id,style_name) values(22,'扮猪吃虎');
insert into tb_logytj_xiaoshuo_style(book_id,style_name) values(22,'兵王');
insert into tb_logytj_xiaoshuo_style(book_id,style_name) values(22,'杀戮');
insert into tb_logytj_xiaoshuo_style(book_id,style_name) values(22,'扮猪吃虎');


#discuss

insert into discuss values(4,5,now(),'坐等更新',8);
insert into discuss values(4,5,now(),'写的很好',4);
insert into discuss values(4,6,now(),'坐等更新',8);
insert into discuss values(4,6,now(),'写的很好',4);
insert into discuss values(4,7,now(),'坐等更新',8);
insert into discuss values(4,7,now(),'写的很好',4);
insert into discuss values(4,8,now(),'坐等更新',8);
insert into discuss values(4,8,now(),'写的很好',4);
insert into discuss values(4,9,now(),'坐等更新',8);
insert into discuss values(4,9,now(),'写的很好',4);
insert into discuss values(4,10,now(),'坐等更新',8);
insert into discuss values(4,10,now(),'写的很好',4);
insert into discuss values(4,11,now(),'坐等更新',8);
insert into discuss values(4,11,now(),'写的很好',4);
insert into discuss values(4,12,now(),'坐等更新',8);
insert into discuss values(4,12,now(),'写的很好',4);
insert into discuss values(4,13,now(),'坐等更新',8);
insert into discuss values(4,13,now(),'写的很好',4);
insert into discuss values(4,14,now(),'坐等更新',8);
insert into discuss values(4,14,now(),'写的很好',4);
insert into discuss values(4,15,now(),'坐等更新',8);
insert into discuss values(4,15,now(),'写的很好',4);
insert into discuss values(4,16,now(),'坐等更新',8);
insert into discuss values(4,16,now(),'写的很好',4);
insert into discuss values(4,17,now(),'坐等更新',8);
insert into discuss values(4,17,now(),'写的很好',4);

