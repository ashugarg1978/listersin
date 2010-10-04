#!/bin/sh

export PATH=/usr/local/apache-maven-2.2.1/bin:$PATH
export JAVA_HOME=/usr/local/jdk1.6.0_21


cd /var/work
mvn archetype:generate

exit

cd /var/www/dev.xboo.st/
mvn archetype:generate -B \
    -DgroupId=tutorial \
    -DartifactId=tutorial \
    -DarchetypeGroupId=org.apache.struts \
    -DarchetypeArtifactId=struts2-archetype-convention \
    -DarchetypeVersion=2.2.1
