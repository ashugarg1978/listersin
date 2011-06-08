#!/bin/sh

export JAVA_HOME=/usr/local/jdk

/etc/init.d/ebaytoold stop

cd /var/www/ebaytool.jp
/usr/local/apache-ant/bin/ant

cp ./dist/ebaytool.jp.war /usr/local/apache-tomcat/webapps/ROOT.war

/etc/init.d/ebaytoold start
