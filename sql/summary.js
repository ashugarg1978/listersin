var now = new Date();

/* Each Users */
db.users.find(
  
  /*
  {
    email: 'tarotarotantan@hotmail.com'
  }
  */
  
).sort(
  
  {
    _id: -1
  }
  
).forEach(
  
  function(row) {
    
    var email = row.email;
    
    /* Each eBay IDs */
    for (idx in row.userids2) {
      
      var user = row.userids2[idx];
      
      print(email + ' : ' + user.username);
      
      /* Aggregate */
			var result = db['items.' + row._id].aggregate([
        {
          $match: {
            UserID: user.username
          }
        },
        {
          $project: {
            _id: 0,
            endtime: "$org.ListingDetails.EndTime",
						listingstatus: "$org.SellingStatus.ListingStatus",
            ended: {"$lt": ["$org.ListingDetails.EndTime", now]}
          },
        },
        {
          $group: {
            _id: {
							listingstatus: "$listingstatus",
              ended: "$ended"
            },
            items: {
              $sum: 1
            },
          }
        }
      ]);
      
      /* Create summary object */
      var summary = {};
      for (i in result.result) {
        var resrow = result.result[i];
        
        var listingstatus = resrow._id.listingstatus;
        var ended = resrow._id.ended + 'z';
        var items = resrow.items;
        
        if (listingstatus == undefined) {
          listingstatus = 'empty';
        }
        
        if (summary[listingstatus] == undefined) {
          summary[listingstatus] = {};
        }
        summary[listingstatus][ended] = NumberInt(items);
      }
      
      /* Update */
      var updateresult = db.users.update(
        {
          email: email,
          'userids2.username': user.username
        },
        {
          $set: {
            'userids2.$.summary': summary
          }
        }
      );
      
    }
  }
);
