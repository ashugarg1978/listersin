/* todo: CategoryMapping:oldID */

db.items.ensureIndex({'org.ItemID':1});
db.items.ensureIndex({'org.Seller.UserID':1});
db.items.ensureIndex({'deleted':1});

print('Categories');
db.getCollectionNames().forEach(
	function(coll) {
		if (coll.indexOf('Categories') > 0) {
			print('indexing '+coll+' ('+db[coll].count()+' records)');
			db[coll].ensureIndex({CategoryParentID:1});
			db[coll].ensureIndex({CategoryLevel:1});
		}
	}
);

/*
db.getCollectionNames().forEach(
	function(coll) {
		if (coll.indexOf('ShippingServiceDetails') > 0) {
			print('indexing '+coll+' ('+db[coll].count()+' records)');
			db[coll].ensureIndex({CategoryParentID:1});
		}
	}
);
*/

print('CategorySpecifics');
db.getCollectionNames().forEach(
	function(coll) {
		if (coll.indexOf('CategorySpecifics') > 0) {
			print('indexing '+coll+' ('+db[coll].count()+' records)');
			db[coll].ensureIndex({CategoryID:1});
		}
	}
);

print('CategoryFeatures.Category');
db.getCollectionNames().forEach(
	function(coll) {
		if (coll.indexOf('CategoryFeatures.Category') > 0) {
			print('indexing '+coll+' ('+db[coll].count()+' records)');
			db[coll].ensureIndex({CategoryID:1});
		}
	}
);

print('Category2CS');
db.getCollectionNames().forEach(
	function(coll) {
		if (coll.indexOf('Category2CS') > 0) {
			print('indexing '+coll+' ('+db[coll].count()+' records)');
			db[coll].ensureIndex({CategoryID:1});
		}
	}
);
