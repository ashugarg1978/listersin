#!/bin/sh

email='fd3s.boost@gmail.com'
cmd='/var/www/ebaytool.jp/shells/callapi.sh'

start=`date +%Y-%m-%d --date '89 day ago'`
end=`date +%Y-%m-%d --date '1 day'`

userids=`/usr/local/mongodb/bin/mongo ebay \
	--eval 'db.users.find({},{"userids":1}).forEach(printjson)' \
	| grep testuser \
	| grep -v CorrelationID \
	| sed 's/	//g' \
	| sed 's/"//' \
	| sed 's/" : {//'`

for userid in $userids
do
	$cmd GetSellerList $email $userid Start $start $end &
done

less /var/www/ebaytool.jp/logs/daemon.log
