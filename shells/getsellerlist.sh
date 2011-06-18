#!/bin/sh

#echo 'GeteBayDetails' | nc localhost 8181
echo 'GetCategories' | nc localhost 8181 &
echo 'GetCategoryFeatures' | nc localhost 8181 &

getsellerlist() {
	echo "GetSellerList fd3s.boost@gmail.com $1 Start 2011-05-01 2011-08-01" \
		| nc localhost 8181 &
}

getsellerlist testuser_hal
getsellerlist testuser_tokyo
getsellerlist testuser_kanagawa
getsellerlist testuser_chiba
getsellerlist testuser_docomo
