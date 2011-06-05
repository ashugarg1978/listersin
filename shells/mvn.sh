#!/bin/sh

export PATH=/usr/local/apache-maven/bin:$PATH
export JAVA_HOME=/usr/local/jdk

/etc/init.d/ebaytoold stop

cd /var/www/ebaytool.jp/struts2
mvn clean package
cp target/ebaytool.war /usr/local/apache-tomcat/webapps/ROOT.war

/etc/init.d/ebaytoold start
