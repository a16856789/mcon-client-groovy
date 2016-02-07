#!/bin/sh

groovyc -d build/classes ./Mcon.groovy
cd build/classes && jar cvf ../mcon-client-groovy.jar *.class && cd ../../
cp build/mcon-client-groovy.jar /usr/local/groovy-2.4.3/user-lib/

groovy demo/test.groovy
