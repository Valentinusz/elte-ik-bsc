#!/bin/sh
cat adatok.txt | cut -f1,2 -d' ' | sort -n | uniq -c | sort -r | head -1 | sed 's/^\s*//' | cut -f2,3 -d' '
