#!/bin/sh

ps -ef|grep minion-|grep -v grep|awk '{print $2}'|xargs kill

sleep 1

cd `dirname $0`

java -jar minion-1.0.0-SNAPSHOT.jar --spring.config.additional-location=minion.properties &
