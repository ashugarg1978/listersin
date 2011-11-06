db.items.ensureIndex({'ext.UserID':1});
db.items.ensureIndex({'ItemID':1});
db.items.ensureIndex({'ext.deleted':1});

db.getCollectionNames().forEach(
	function(coll) {
		if (coll.indexOf('Categories') > 0) {
			print('indexing '+coll+' ('+db[coll].count()+' records)');
			db[coll].ensureIndex({CategoryParentID:1});
		}
	}
);

db.getCollectionNames().forEach(
	function(coll) {
		if (coll.indexOf('ShippingServiceDetails') > 0) {
			print('indexing '+coll+' ('+db[coll].count()+' records)');
			db[coll].ensureIndex({CategoryParentID:1});
		}
	}
);

db.getCollectionNames().forEach(
	function(coll) {
		if (coll.indexOf('CategorySpecifics') > 0) {
			print('indexing '+coll+' ('+db[coll].count()+' records)');
			db[coll].ensureIndex({CategoryID:1});
		}
	}
);
