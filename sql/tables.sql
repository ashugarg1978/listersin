DROP TABLE IF EXISTS users;
CREATE TABLE users
(
	userid		serial,
	username	varchar(255),
	password	varchar(40),
	created		datetime,
	updated		timestamp
);

INSERT INTO users VALUES (1, 'hal9000dev@gmail.com', 'qwer', NOW(), NOW());
INSERT INTO users VALUES (2, 'fd3s.boost@gmail.com', 'qwer', NOW(), NOW());
INSERT INTO users VALUES (3, 'fd3s.boost@emnet.ne.jp', 'qwer', NOW(), NOW());
INSERT INTO users VALUES (4, 'eeeback@gmail.com', 'qwer', NOW(), NOW());

DROP TABLE IF EXISTS accounts;
CREATE TABLE accounts
(
	accountid		serial,
	userid			int,
	ebayuserid  	varchar(20),
	ebaytoken		text,
	ebaytokenexp	datetime,
	created			datetime,
	updated			timestamp
);

DROP TABLE IF EXISTS items;
CREATE TABLE items
(
	itemid		serial,
	accountid	int,
	ebayitemid	varchar(12),
	starttime	datetime,
	endtime		datetime,
	title		varchar(50),
	description text,
	created		datetime,
	updated		timestamp
);

INSERT INTO items VALUES (1, 1, '', 'BlackBerry Bold 9000a', 'test', NOW(), NOW());
INSERT INTO items VALUES (2, 1, '', '日本語テストあいうえお', 'test', NOW(), NOW());
INSERT INTO items VALUES (3, 1, '', 'BlackBerry Bold 9000c', 'test', NOW(), NOW());
INSERT INTO items VALUES (4, 2, '', 'かきくけこ Bold 9000d', 'test', NOW(), NOW());
INSERT INTO items VALUES (5, 2, '', 'BlackBerry Bold 9000e', 'test', NOW(), NOW());

select * from users;
select * from items;
