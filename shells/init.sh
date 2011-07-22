#!/bin/sh

/usr/local/mongodb/bin/mongo ebay --eval 'db.dropDatabase();'

/var/www/ebaytool.jp/shells/callapi.sh GeteBayDetails
/var/www/ebaytool.jp/shells/callapi.sh GetCategories
/var/www/ebaytool.jp/shells/callapi.sh GetCategoryFeatures
