#!/bin/bash
mydir=$(readlink -f $(dirname $0))
$mydir/install.sh \
  --uninstall \
  --catalogManager=/home/nabe/.resolver/CatalogManager.properties \
  --dotEmacs= \
  $@
