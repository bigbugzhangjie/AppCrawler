export SDK=$HOME/local/adt-bundle-linux-x86_64-20140702/sdk
export WSPACE=$HOME/adt-workspace
WID=xdotool getactivewindow

cd $SDK/tools/
./mksdcard -l sdcard 100M $WSPACE/sdcard.img
./android create avd -n myavd -t 2 -c $WSPACE/sdcard.img -s HVGA -b armeabi-v7a &

# TODO 启动过程中需要输入“no”或者“回车”
xdotool type yes; sleep 10s;
xdotool key 'Return' ; sleep 10s;

# example: 
# xdotool search "Mozilla Firefox" windowactivate --sync key --clearmodifiers ctrl+l type "www.baidu.com";xdotool search "Mozilla Firefox" windowactivate --sync key --clearmodifiers Return

sleep 1m
$SDK/tools/emulator @myavd


