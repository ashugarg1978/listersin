alter table items modify status varchar(20);
alter table users add language char(3) default 'eng';
alter table items modify Title varchar(200);

\q

alter table items add schedule datetime;
alter table items add ScheduleTime datetime;

update items set schedule = date_add(now(), interval id*5-100 minute);
select id, schedule from items limit 10;
\q


\q

alter table items add SellingStatus_ListingStatus varchar(20);
alter table items add Errors_LongMessage text;
alter table items add Errors_ShortMessage text;
alter table items add status int;
alter table items alter status set default 0;
update items set status = 0;
