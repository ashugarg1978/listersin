#!/bin/sh

export PATH=/usr/local/apache-maven-2.2.1/bin:$PATH
export JAVA_HOME=/usr/local/jdk1.6.0_21

cd /var/www/dev.xboo.st/struts2
mvn clean package
cp target/ebaytool.war /usr/local/apache-tomcat-7.0.2/webapps/ROOT.war
