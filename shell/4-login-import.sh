echo "click Login button"
xdotool search "5554" windowactivate --sync mousemove --sync 189 368 click 1
sleep 1m

echo "click 开始我的旅程"
xdotool search "5554" windowactivate --sync mousemove --sync 189 454 click 1
sleep 15s

echo "update later"
xdotool search "5554" windowactivate --sync mousemove --sync 122 412 click 1
sleep 20s

echo "click 添加好友"
xdotool search "5554" windowactivate --sync mousemove --sync 56 100 click 1
sleep 30s

echo "从下向上拖拽"
#方案一：  写在一行时操作出错，屏幕会惯性滚动
	#xdotool search "5554" windowactivate --sync mousemove --sync 179 524 mousedown 1 mousemove --sync 184 125 mouseup 1
#方案二：  写在多行时也出错，mousemove的速度太快，屏幕仍会惯性滚动
	#xdotool search "5554" windowactivate --sync mousemove --sync 179 524 mousedown 1 
	#sleep 1s
	#xdotool mousemove --sync 184 125 
	#sleep 3s
	#xdotool search "5554" windowactivate --sync mouseup 1
#方案三： 可行
xdotool search "5554" windowactivate --sync mousemove --sync 189 504 click --repeat 12 --delay 500 5
sleep 2s

echo "click 通讯录好友关注"
xdotool search "5554" windowactivate --sync mousemove --sync 189 504 click 1
sleep 15s

echo "click 通讯录联系人"
xdotool search "5554" windowactivate --sync mousemove --sync 180 152 click 1
sleep 3s

echo "enable contact matching"
xdotool search "5554" windowactivate --sync mousemove --sync 185 496 click 1
sleep 1m


