#!/bin/sh

/usr/local/mongodb/bin/mongo ebay --eval 'db.dropDatabase();'

echo 'GeteBayDetails' | nc localhost 8181
echo 'GetCategories' | nc localhost 8181
echo 'GetCategoryFeatures' | nc localhost 8181
