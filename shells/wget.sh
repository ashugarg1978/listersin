#!/bin/sh

BASEDIR="$( dirname "$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )" )"

wget -q -O $BASEDIR/data/ameblo.xml http://feedblog.ameba.jp/rss/ameblo/listersin/rss20.xml
wget -q -O $BASEDIR/data/blog.xml   http://listers.in/blog/feed/
wget -q -O $BASEDIR/data/github.xml https://github.com/listersin/listersin/commits/master.atom
