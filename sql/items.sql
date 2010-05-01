DROP TABLE IF EXISTS items;

CREATE TABLE items
(
	id			serial PRIMARY KEY,
	created		datetime,
	updated		timetamp,
	
	ItemID						bigint,
	ListingDetails_EndTime		datetime,
	ListingDetails_StartTime	datetime,
	Title						varchar(55),
	Description					text,
	StartPrice					double,
	ListingDetails_ViewItemURL	varchar(500),
	PrimaryCategory_CategoryID		bigint,
	PrimaryCategory_CategoryName	varchar(200),
);
