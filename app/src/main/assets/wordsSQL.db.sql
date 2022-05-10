BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "android_metadata" (
	"locale"	TEXT DEFAULT 'en_US'
);
CREATE TABLE IF NOT EXISTS "words_table" (
	"id"	INTEGER NOT NULL UNIQUE,
	"english_word"	TEXT,
	"russian_word"	TEXT,
	PRIMARY KEY("id" AUTOINCREMENT)
);
INSERT INTO "android_metadata" VALUES ('en_US');
INSERT INTO "words_table" VALUES (1,'abandon','оставить');
INSERT INTO "words_table" VALUES (2,'abate','уменьшаться');
INSERT INTO "words_table" VALUES (3,'abduct','похищать');
INSERT INTO "words_table" VALUES (4,'abide','смириться');
INSERT INTO "words_table" VALUES (5,'able','способный');
INSERT INTO "words_table" VALUES (6,'abnormal','ненормальный');
INSERT INTO "words_table" VALUES (7,'aboard','на борту');
INSERT INTO "words_table" VALUES (8,'abolish','упразднять');
INSERT INTO "words_table" VALUES (9,'abort','прерывать');
INSERT INTO "words_table" VALUES (10,'abound','изобиловать');
INSERT INTO "words_table" VALUES (11,'about','примерно');
INSERT INTO "words_table" VALUES (12,'abroad','за границу');
INSERT INTO "words_table" VALUES (13,'abrupt','крутой');
INSERT INTO "words_table" VALUES (14,'absent','отсутствующий');
INSERT INTO "words_table" VALUES (15,'absorb','поглощать');
INSERT INTO "words_table" VALUES (16,'abundant','обильный');
INSERT INTO "words_table" VALUES (17,'abuse','оскорблять');
INSERT INTO "words_table" VALUES (18,'abuse','злоупотреблять');
INSERT INTO "words_table" VALUES (19,'abuse','насилие');
INSERT INTO "words_table" VALUES (20,'accelerate','ускорять');
INSERT INTO "words_table" VALUES (21,'accept','принять');
INSERT INTO "words_table" VALUES (22,'access','доступ');
INSERT INTO "words_table" VALUES (23,'accessory','аксессуар');
INSERT INTO "words_table" VALUES (24,'accident','случайность');
INSERT INTO "words_table" VALUES (25,'accident','происшествие');
INSERT INTO "words_table" VALUES (26,'acclaim','приветствовать');
INSERT INTO "words_table" VALUES (27,'accommodate','размещать');
INSERT INTO "words_table" VALUES (28,'accompany','сопровождать');
INSERT INTO "words_table" VALUES (29,'accomplish','выполнить');
INSERT INTO "words_table" VALUES (30,'accord','согласие');
INSERT INTO "words_table" VALUES (31,'according to','в соответствии');
INSERT INTO "words_table" VALUES (32,'account','счёт');
INSERT INTO "words_table" VALUES (33,'account','отчёт');
INSERT INTO "words_table" VALUES (34,'account for','объяснить');
INSERT INTO "words_table" VALUES (35,'accurate','точный');
INSERT INTO "words_table" VALUES (36,'accuse','обвинять');
INSERT INTO "words_table" VALUES (37,'accustom','приучить');
INSERT INTO "words_table" VALUES (38,'ache','боль, болеть');
INSERT INTO "words_table" VALUES (39,'achieve','достигать');
INSERT INTO "words_table" VALUES (40,'acid','кислота');
INSERT INTO "words_table" VALUES (41,'acknowledge','признавать');
INSERT INTO "words_table" VALUES (42,'acquaint','знакомить');
INSERT INTO "words_table" VALUES (43,'acquire','обретать');
INSERT INTO "words_table" VALUES (44,'acquit','оправдать');
INSERT INTO "words_table" VALUES (45,'across','за, через');
INSERT INTO "words_table" VALUES (46,'act','действовать');
INSERT INTO "words_table" VALUES (47,'act on','действовать в соответствии');
INSERT INTO "words_table" VALUES (48,'acute','острый');
INSERT INTO "words_table" VALUES (49,'adamant','непреклонный');
INSERT INTO "words_table" VALUES (50,'add to','увеличивать');
INSERT INTO "words_table" VALUES (51,'add up','сходиться');
INSERT INTO "words_table" VALUES (52,'addict','наркоман');
INSERT INTO "words_table" VALUES (53,'addition','добавление');
INSERT INTO "words_table" VALUES (54,'adhere','придерживаться');
INSERT INTO "words_table" VALUES (55,'adjacent','смежный');
INSERT INTO "words_table" VALUES (56,'adjective','прилагательное');
INSERT INTO "words_table" VALUES (57,'adjust','настраивать');
INSERT INTO "words_table" VALUES (58,'admire','восхищаться');
INSERT INTO "words_table" VALUES (59,'admit','признавать');
INSERT INTO "words_table" VALUES (60,'adolescent','подросток');
INSERT INTO "words_table" VALUES (61,'adopt','принимать');
INSERT INTO "words_table" VALUES (62,'adopt','усыновить');
INSERT INTO "words_table" VALUES (63,'adore','обожать');
INSERT INTO "words_table" VALUES (64,'adorn','украшать');
INSERT INTO "words_table" VALUES (65,'adult','взрослый');
INSERT INTO "words_table" VALUES (66,'adultery','прелюбодеяние');
INSERT INTO "words_table" VALUES (67,'advance','продвигаться');
INSERT INTO "words_table" VALUES (68,'advantage','преимущество');
INSERT INTO "words_table" VALUES (69,'adventure','приключение');
INSERT INTO "words_table" VALUES (70,'adverb','наречие');
INSERT INTO "words_table" VALUES (71,'adversary','противник');
INSERT INTO "words_table" VALUES (72,'adverse','неблагоприятный');
INSERT INTO "words_table" VALUES (73,'advertise','рекламировать');
INSERT INTO "words_table" VALUES (74,'advice','совет');
INSERT INTO "words_table" VALUES (75,'advise','советовать');
INSERT INTO "words_table" VALUES (76,'advocate','защищать');
INSERT INTO "words_table" VALUES (77,'aerial','воздушный');
INSERT INTO "words_table" VALUES (78,'aerial','антенна');
INSERT INTO "words_table" VALUES (79,'affair','дело');
INSERT INTO "words_table" VALUES (80,'affair','роман');
INSERT INTO "words_table" VALUES (81,'affect','воздействовать');
INSERT INTO "words_table" VALUES (82,'affection','привязанность');
INSERT INTO "words_table" VALUES (83,'affiliate','филиал');
INSERT INTO "words_table" VALUES (84,'affirm','утверждать');
INSERT INTO "words_table" VALUES (85,'afflict','поражать,');
INSERT INTO "words_table" VALUES (86,'affluent','обеспеченный');
INSERT INTO "words_table" VALUES (87,'afford','позволить себе');
INSERT INTO "words_table" VALUES (88,'afraid','напуганный');
INSERT INTO "words_table" VALUES (89,'after all','всё-таки');
INSERT INTO "words_table" VALUES (90,'aftermath','последствие');
INSERT INTO "words_table" VALUES (91,'age','век');
INSERT INTO "words_table" VALUES (92,'age','возраст');
INSERT INTO "words_table" VALUES (93,'agenda','повестка дня');
INSERT INTO "words_table" VALUES (94,'aggravate','обострять');
INSERT INTO "words_table" VALUES (95,'aggregate','совокупный');
INSERT INTO "words_table" VALUES (96,'agile','проворный');
INSERT INTO "words_table" VALUES (97,'agitate','волновать');
INSERT INTO "words_table" VALUES (98,'agree','соглашаться');
INSERT INTO "words_table" VALUES (99,'agreement','соглашение');
INSERT INTO "words_table" VALUES (100,'agriculture','сельское хозяйство');
COMMIT;
