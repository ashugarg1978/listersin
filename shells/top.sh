#!/bin/sh

PATH=$PATH:/sbin

top -c -H -d 1 -p `pidof java | sed 's/ /,/'`
