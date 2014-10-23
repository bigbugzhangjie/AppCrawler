echo "move window to top-left"
xdotool search "5554" windowactivate --sync windowmove 0 0
sleep 1s

echo "click HOME button"
xdotool search "5554" windowactivate --sync mousemove --sync 502 268 click 1
sleep 4s

echo "click setting button"
xdotool search "5554" windowactivate --sync mousemove --sync 189 499 click 1
sleep 10s

#仅在第一次点击setting时会出现OK按钮！！
echo "click OK button"
xdotool search "5554" windowactivate --sync mousemove --sync 297 493 click 1
sleep 5s

echo "横向拖拽到下一页"
xdotool search "5554" windowactivate --sync mousemove --sync 271 325 mousedown 1 mousemove --sync 68 323 mouseup 1
sleep 4s

echo "click weibo icon"
xdotool search "5554" windowactivate --sync mousemove --sync 308 163 click 1
sleep 1m
	
echo "click login"
xdotool search "5554" windowactivate --sync mousemove --sync 320 101 click 1
sleep 10s

echo "click email textarea"
xdotool search "5554" windowactivate --sync mousemove --sync 137 244 click 1
sleep 2s

########## TODO：使用type未调通，后续应修改为使用click ################
echo "type username"
xdotool search "5554" windowactivate --sync type --clearmodifiers "bigbug05@sina.com"
sleep 5s

echo "click passwd textarea"
xdotool search "5554" windowactivate --sync mousemove --sync 118 298 click 1
sleep 2s

########## TODO：使用type未调通，后续应修改为使用click ################
echo "type passwd"
xdotool search "5554" windowactivate --sync type --clearmodifiers "654123"
sleep 5s


