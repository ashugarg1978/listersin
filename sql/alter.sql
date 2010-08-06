alter table items add ShippingDetails_InternationalShippingServiceOption text; 
\q

/*
create table ShippingDetails_ShippingServiceOptions (
	id bigint,
	ShippingServiceCost double,
	ShippingServicePriority int,
	ShippingService varchar(100)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
*/

alter table items drop ShippingDetails_ShippingServiceOptions_ShippingServiceCost;
alter table items drop ShippingDetails_ShippingServiceOptions_ShippingServicePriority;
alter table items drop ShippingDetails_ShippingServiceOptions_ShippingService;

\q

alter table items add deleted tinyint default 0;

\q

/*
colname | copy | add | revise | serialize | relist
*/		

create index ind_pid on categories_australia       (CategoryParentID);
create index ind_pid on categories_austria         (CategoryParentID);
create index ind_pid on categories_belgium_dutch   (CategoryParentID);
create index ind_pid on categories_belgium_french  (CategoryParentID);
create index ind_pid on categories_canada          (CategoryParentID);
create index ind_pid on categories_canadafrench    (CategoryParentID);
create index ind_pid on categories_ebaymotors      (CategoryParentID);
create index ind_pid on categories_france          (CategoryParentID);
create index ind_pid on categories_germany         (CategoryParentID);
create index ind_pid on categories_hongkong        (CategoryParentID);
create index ind_pid on categories_india           (CategoryParentID);
create index ind_pid on categories_ireland         (CategoryParentID);
create index ind_pid on categories_italy           (CategoryParentID);
create index ind_pid on categories_malaysia        (CategoryParentID);
create index ind_pid on categories_netherlands     (CategoryParentID);
create index ind_pid on categories_philippines     (CategoryParentID);
create index ind_pid on categories_poland          (CategoryParentID);
create index ind_pid on categories_singapore       (CategoryParentID);
create index ind_pid on categories_spain           (CategoryParentID);
create index ind_pid on categories_switzerland     (CategoryParentID);
create index ind_pid on categories_uk              (CategoryParentID);
create index ind_pid on categories_us              (CategoryParentID);

alter table items add ShippingDetails_ShippingServiceOptions text;

\q

alter table items add ApplicationData varchar(20);
alter table items add relist tinyint;
alter table items modify status varchar(20);
alter table users add language char(3) default 'eng';
alter table items modify Title varchar(200);
alter table items add schedule datetime;
alter table items add ScheduleTime datetime;

update items set schedule = date_add(now(), interval id*5-100 minute);
select id, schedule from items limit 10;

alter table items add SellingStatus_ListingStatus varchar(20);
alter table items add Errors_LongMessage text;
alter table items add Errors_ShortMessage text;
alter table items add status int;
alter table items alter status set default 0;
update items set status = 0;
