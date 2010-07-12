#!/bin/sh

action=$1
opid=$2

ROOT=/var/www/dev.xboo.st
PATH=$PATH:/usr/local/php/bin 

$ROOT/cake/console/cake -app $ROOT/app daemon $action $opid \
	>> $ROOT/app/tmp/apilogs/daemon.log

