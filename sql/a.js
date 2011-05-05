db.items.group({key: {"ext.importstatus":1}, reduce:function(o,p){p.csum++;}, initial:{csum:0}});
//db.items.find({PostalCode:''},{PostalCode:1})
//db.items.find({status:{$exists:true}},{status:1})
