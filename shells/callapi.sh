#!/bin/sh

BASEDIR="$( dirname "$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" )"
BASENAME=${BASEDIR/*\//}
DAEMONPORT=`grep daemonport $BASEDIR/config/config.xml | sed 's/[^0-9]//g'`

echo $@ | sed 's/ /\n/g' | nc localhost $DAEMONPORT
#echo "$@" | nc localhost $DAEMONPORT
