#!/bin/sh

ps auxw | grep java | grep Daemon | sed 's/java.*$//'
