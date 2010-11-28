#!/bin/sh

CP=/var/www/ebaytool/struts2/target/classes/

for jar in `ls /usr/local/apache-tomcat-7.0.2/webapps/ROOT/WEB-INF/lib/`
do
	CP=$CP:/usr/local/apache-tomcat-7.0.2/webapps/ROOT/WEB-INF/lib/$jar
done

#/usr/local/jdk1.6.0_21/bin/java -cp $CP ebaytool.apicall.ThreadPool $1
/usr/local/jdk1.6.0_21/bin/java -cp $CP ebaytool.apicall.Daemon

