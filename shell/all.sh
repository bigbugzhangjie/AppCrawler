#!/bin/bash
for i in {1..30}
  do
    WID=`xdotool search --title "Mozilla Firefox" | head -1`
    xdotool windowfocus $WID
    xdotool key ctrl+l
    xdotool key Tab
    SENTENCE="$(fortune | cut -d' ' -f1-3 | head -1)"
    xdotool type $SENTENCE
    xdotool key "Return"
    sleep 4
done