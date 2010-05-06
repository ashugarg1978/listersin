DROP TABLE IF EXISTS items2;

CREATE TABLE items2
(
	id			serial PRIMARY KEY,
	created		datetime,
	updated		timestamp,
	
	ItemID							bigint,
	ListingDetails_EndTime			datetime,
	ListingDetails_StartTime		datetime,
	Title							varchar(55),
	Description						text,
	StartPrice						double,
	ListingDetails_ViewItemURL		varchar(500),
	PrimaryCategory_CategoryID		bigint,
	PrimaryCategory_CategoryName	varchar(200),
	Quantity						int
);

DESC items2;
