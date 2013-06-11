db.getCollection('US.CategoryFeatures.Category').find(
  
  {
    ListingDuration: {
      $exists: true
    }
  },
  
  {
    ListingDuration: true
  }
  
).forEach(printjson);
