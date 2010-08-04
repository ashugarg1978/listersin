#!/bin/sh

action=$1
opid=$2

ROOT=/var/www/dev.xboo.st

# if mysql error occures, check duplicate php installation.
# below PATH order is important (/usr/local/php/bin/php is first)
PATH=/usr/local/php/bin:$PATH

$ROOT/cake/console/cake -app $ROOT/app daemon $action $opid >> $ROOT/logs/daemon.log
