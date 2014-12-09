	for j in {1..10}
	do
		xdotool search "5554" windowactivate --sync windowmove 0 0
		xdotool search "5554" windowactivate --sync mousemove --sync 264 97 click 1
		xdotool search "5554" windowactivate --sync mousemove --sync 189 504 click --repeat 6 --delay 1000 5
		sleep 2s		
		gnome-screenshot -a &
		sleep 3s
		xdotool mousemove --sync 96 124 mousedown 1 mousemove --sync 290 528 mouseup 1
		sleep 5s
		xdotool key "Return"
		echo "Saved image:" 5 - $j
	done
