#!/bin/sh

export JAVA_HOME=/usr/local/jdk1.6.0_21

/usr/local/apache-tomcat-7.0.2/bin/shutdown.sh

sleep 5

/usr/local/apache-tomcat-7.0.2/bin/startup.sh
