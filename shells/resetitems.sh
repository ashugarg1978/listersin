#!/bin/sh

email='fd3s.boost@gmail.com'
cmd='/var/www/ebaytool.jp/shells/callapi.sh'

start=`date +%Y-%m-%d --date '89 day ago'`
end=`date +%Y-%m-%d --date '1 day'`
#start=`date +%Y-%m-%d --date '180 day ago'`
#end=`date +%Y-%m-%d --date '90 day ago'`

userids=`/usr/local/mongodb/bin/mongo ebay \
	--eval 'db.users.find({},{"userids":1}).forEach(printjson)' \
	| grep testuser \
	| grep -v CorrelationID \
	| sed 's/	//g' \
	| sed 's/"//' \
	| sed 's/" : {//'`

/usr/local/mongodb/bin/mongo ebay --eval 'db.items.remove()'

for userid in $userids
do
	$cmd GetSellerList $email $userid Start $start $end &
	$cmd GetSellerList $email $userid End $start $end &
done
