alter table items add SellingStatus_ListingStatus varchar(20);
alter table items add Errors_LongMessage text;

alter table items add status int;
alter table items alter status set default 0;
update items set status = 0;
