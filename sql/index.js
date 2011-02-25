db.items.ensureIndex({'ext.UserID':1});
db.items.ensureIndex({ItemID:1});
db.items.ensureIndex({'ext.deleted':1});

db.Categories_US.ensureIndex({CategoryParentID:1});
db.Categories_Canada.ensureIndex({CategoryParentID:1});
