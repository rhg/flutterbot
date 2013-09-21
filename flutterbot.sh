#!/bin/sh

cd /opt/flutterbot
lein run \
--logfile /var/log/flutterbot.log \
--pidfile ${HOME}/flutterbot.pid
