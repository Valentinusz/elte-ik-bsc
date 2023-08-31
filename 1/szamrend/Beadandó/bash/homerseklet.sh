#!/bin/sh
cat adatok.txt | cut -f3,2,1 -d' ' | sort -n | uniq | cut -f1,2 -d' ' | uniq -c | grep "$1 $2" | sed 's/^\s*//' | cut -f1 -d' '
