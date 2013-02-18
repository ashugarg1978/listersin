#!/bin/sh

NOW=`date --utc '+%Y-%m-%d %H:%M:00'`

BASEDIR="$( dirname "$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" )"
DAEMONPORT=`grep daemonport $BASEDIR/config/config.xml | sed 's/[^0-9]//g'`

echo -e "AddScheduledItems\n$NOW\n$NOW" | nc localhost $DAEMONPORT
