update items set schedule = date_add(now(), interval (id-964)*10 minute);
select id, schedule from items;

\q

alter table items add schedule datetime;
alter table items add ScheduleTime datetime;
alter table items add SellingStatus_ListingStatus varchar(20);
alter table items add Errors_LongMessage text;
alter table items add Errors_ShortMessage text;
alter table items add status int;
alter table items alter status set default 0;
update items set status = 0;
