#!/bin/sh

top -c -H -d 1 -p `pidof java | sed 's/ /,/'`
