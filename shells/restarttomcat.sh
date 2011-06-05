#!/bin/sh

export JAVA_HOME=/usr/local/jdk

/usr/local/apache-tomcat/bin/shutdown.sh

sleep 5

/usr/local/apache-tomcat/bin/startup.sh
