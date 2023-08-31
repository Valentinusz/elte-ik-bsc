#!/bin/sh

max=-100
hol=""
mikor=""

while read -r line;
do
	homerseklet=$(echo $line | cut -f5 -d' ')
	if [ "$homerseklet" -gt "$max" ]
	then
		max=$homerseklet
		mikor="$(echo $line | cut -f3 -d' ') $(echo $line | cut -f4 -d' ')"
		hol="$(echo $line | cut -f1 -d' ') $(echo $line | cut -f2 -d' ')"
	fi
done < adatok.txt
echo "$hol $mikor"

