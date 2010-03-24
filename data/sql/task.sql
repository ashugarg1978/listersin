create table taskmaster (
	taskid 		serial	primary key,
	category 	int		default 0,
	priority	int		default 0,
	status		int		default 0,
	title		text,
	mod_date	timestamp
);

create table message (
	msgid		serial	primary key,
	taskid		int,
	parentid	int,
	reg_user	varchar(20),
	message		text,
	mod_date	timestamp
);
