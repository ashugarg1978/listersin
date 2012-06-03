#!/bin/sh

VERSION=`date +%Y%m%d_%H%M%S`

BASEDIR="$( dirname "$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" )"
BASENAME=${BASEDIR/*\//}

echo "basedir [$BASEDIR]"
echo "basename [$BASENAME]"

if [ $BASENAME = "listers.in" ]
then
	echo "!!! PRODUCTION !!!"
fi

export JAVA_HOME=/usr/local/jdk

$BASEDIR/shells/ebaytoold stop

cd $BASEDIR
/usr/local/apache-ant/bin/ant

cp ./dist/$BASENAME.war /usr/local/apache-tomcat/webapps/$BASENAME##$VERSION.war

$BASEDIR/shells/ebaytoold start

# delete old war files
warfiles=`ls /usr/local/apache-tomcat/webapps/$BASENAME*war | wc -l`
wardeletecount=`expr $warfiles - 1`
wardeletefiles=`ls /usr/local/apache-tomcat/webapps/$BASENAME*war | head -n $wardeletecount`
rm -rf $wardeletefiles
