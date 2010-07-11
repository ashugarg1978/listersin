#!/bin/sh

filename=/var/www/dev.xboo.st/sql/ebay`date +%Y%m%d`.dump

/usr/local/mysql/bin/mysqldump -u root ebay > $filename

bzip2 $filename
