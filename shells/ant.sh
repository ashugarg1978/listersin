#!/bin/sh

export JAVA_HOME=/usr/local/jdk

cd /var/www/ebaytool.jp
/usr/local/apache-ant/bin/ant

cp ./dist/ebaytool.jp.war /usr/local/apache-tomcat/webapps/ROOT.war
