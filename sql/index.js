db.items.ensureIndex({'ext.UserID':1});
db.items.ensureIndex({ItemID:1});
db.items.ensureIndex({'ext.deleted':1});

db.US.Categories.ensureIndex({CategoryParentID:1});
db.US.eBayDetails.ShippingServiceDetails.ensureIndex({ValidForSellingFlow:1});

db.Canada.Categories.ensureIndex({CategoryParentID:1});
db.Canada.eBayDetails.ShippingServiceDetails.ensureIndex({ValidForSellingFlow:1});

db.getCollectionNames().forEach(
	function(coll) {
		if (coll.indexOf('Categories') > 0) {
			print(db[coll].count()+' : '+coll);
			db[coll].ensureIndex({CategoryParentID:1});
		}
	}
);
