#!/bin/sh

PATH=$PATH:/usr/local/php/bin /var/www/dev.xboo.st/cake/console/cake \
	-app /var/www/dev.xboo.st/app daemon addscheduleditems
