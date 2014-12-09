# example: 
# xdotool search "Mozilla Firefox" windowactivate --sync key --clearmodifiers ctrl+l type "www.baidu.com";xdotool search "Mozilla Firefox" windowactivate --sync key --clearmodifiers Return

# avd窗口移动到左上角
echo "move window to top-left"
xdotool search "5554" windowactivate --sync windowmove 0 0
sleep 5s

# 仅在第一次启动此AVD时会出现OK按钮！！
echo "click OK button"
xdotool search "5554" windowactivate --sync mousemove --sync 295 493 click 1
sleep 8s

#125,502	联系人
echo "click Contact"
xdotool search "5554" windowactivate --sync  mousemove --sync 125 502 click 1
sleep 8s

echo "click import contact"
xdotool search "5554" windowactivate --sync  mousemove --sync 192 392 click 1
sleep 8s

echo "click import from storage"
xdotool search "5554" windowactivate --sync  mousemove --sync 192 363 click 1

# cost many seconds
echo "waiting to import" 
sleep 1m

#502,268	模拟键盘的HOME钮
echo "click HOME button" 
xdotool search "5554" windowactivate --sync  mousemove --sync 502 268 click 1
sleep 8s
 
