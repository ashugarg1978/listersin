#!/bin/sh

#/usr/local/mongodb/bin/mongo ebay --eval 'db.dropDatabase();'

/var/www/listers.in/shells/callapi.sh GeteBayDetails
/var/www/listers.in/shells/callapi.sh GetCategories
/var/www/listers.in/shells/callapi.sh GetCategoryFeatures
/var/www/listers.in/shells/callapi.sh GetDescriptionTemplates
