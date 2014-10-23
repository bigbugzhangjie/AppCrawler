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

#使用type未调通
# 不行：xdotool search "5554" windowactivate --sync type --clearmodifiers "bigbug05@sina.com"
#修改为使用click ################
echo "type username"
# B
xdotool search "5554" windowactivate --sync mousemove --sync 604 486 click 1
sleep 2s
# i
xdotool search "5554" windowactivate --sync mousemove --sync 677 411 click 1
sleep 2s
# g
xdotool search "5554" windowactivate --sync mousemove --sync 567 447 click 1
sleep 2s
# B
xdotool search "5554" windowactivate --sync mousemove --sync 604 486 click 1
sleep 2s
# u
xdotool search "5554" windowactivate --sync mousemove --sync 640 411 click 1
sleep 2s
# g
xdotool search "5554" windowactivate --sync mousemove --sync 567 447 click 1
sleep 2s
# 0
xdotool search "5554" windowactivate --sync mousemove --sync 753 372 click 1
sleep 2s
# 5
xdotool search "5554" windowactivate --sync mousemove --sync 567 372 click 1
sleep 2s
# @
xdotool search "5554" windowactivate --sync mousemove --sync 494 516 click 1
sleep 2s
# sina.com
xdotool search "5554" windowactivate --sync mousemove --sync 187 297 click 1
sleep 2s

echo "click passwd textarea"
xdotool search "5554" windowactivate --sync mousemove --sync 118 298 click 1
sleep 2s

#使用type未调通
#xdotool search "5554" windowactivate --sync type --clearmodifiers "654123"
#修改为使用click ################
echo "type passwd"
# 6
xdotool search "5554" windowactivate --sync mousemove --sync 604 372 click 1
sleep 2s
# 5
xdotool search "5554" windowactivate --sync mousemove --sync 567 372 click 1
sleep 2s
# @
xdotool search "5554" windowactivate --sync mousemove --sync 530 372 click 1
sleep 2s
# 0
xdotool search "5554" windowactivate --sync mousemove --sync 420 372 click 1
sleep 2s
# 5
xdotool search "5554" windowactivate --sync mousemove --sync 456 372 click 1
sleep 2s
# @
xdotool search "5554" windowactivate --sync mousemove --sync 494 372 click 1
sleep 5s


