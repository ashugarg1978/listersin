#!/bin/sh

BASEDIR="$( dirname "$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" )"
BASENAME=${BASEDIR/*\//}

echo "basedir [$BASEDIR]"
echo "basename [$BASENAME]"

if [ $BASENAME = "ebaytool.jp" ]
then
	echo "!!! PRODUCTION !!!"
fi

export JAVA_HOME=/usr/local/jdk

$BASEDIR/shells/ebaytoold stop

cd $BASEDIR
/usr/local/apache-ant/bin/ant

cp ./dist/$BASENAME.war /usr/local/apache-tomcat/webapps/

$BASEDIR/shells/ebaytoold start
