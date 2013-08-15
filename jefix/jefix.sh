#!/bin/bash

DIR="`dirname $0`"

CLASSPATH=$DIR/lib/com.gzoltar-0.0.3-jar-with-dependencies.jar
CLASSPATH=$CLASSPATH:$DIR/src/main/resources
CLASSPATH=$CLASSPATH:$DIR/target/jefix-0.0.1-SNAPSHOT-jar-with-dependencies.jar
MAINCLASS=fr.inria.lille.jefix.Main

COMMAND="java -cp $CLASSPATH $MAINCLASS $*"

echo "Running: $COMMAND"

$COMMAND

