#!/bin/sh

export PATH=/usr/local/apache-maven-2.2.1/bin:$PATH
export JAVA_HOME=/usr/local/jdk1.6.0_21

/etc/init.d/ebaytoold stop

cd /var/www/ebaytool.jp/struts2
mvn clean package
cp target/ebaytool.war /usr/local/apache-tomcat-7.0.2/webapps/ROOT.war

/etc/init.d/ebaytoold start

