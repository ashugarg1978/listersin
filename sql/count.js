/*
db.getCollectionNames().forEach(
	function(coll) {
		print('count '+coll+' ('+db[coll].count()+' records)');
	}
);

db.getCollectionNames().forEach(
	function(coll) {
		print('count '+coll+' ('+db[coll].count()+' records)');
	}
);
*/

db.items.group(
	{key: {
		"ext.status": 1
	},
	 reduce: function(o,p) {
		 p.csum++;
	 },
	 initial: {
		 csum:0}
	}
).forEach(printjson);
