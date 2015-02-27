### define VCFFILE VCFNAME ###
SDK=/bigdata/local/adt-bundle-linux-x86_64-20140702/sdk
WSPACE=$HOME/adt-workspace
TEMPLATE=$WSPACE/template
AVDPATH=$HOME/.android/avd

	date
	################################# 【step1:create AVD】 ####################
	# prepare data
	cd $WSPACE
	cp $VCFFILE contact.vcf

	# mksdcard
	cd $SDK/tools/
	./mksdcard -l sdcard 100M $WSPACE/sdcard.img

	# create AVD
	rm -rf $AVDPATH/1.ini
	rm -rf $AVDPATH/1.avd
	cp -rf $TEMPLATE/* $AVDPATH/
	# 不再重新创建AVD：  ./android create avd -n '1' -t 2 -c $WSPACE/sdcard.img -s HVGA -b armeabi-v7a ;sleep 1m

	# starting emulator 【模拟器到名字叫“1”】
	echo "starting AVD. sleep 3m"
#	$SDK/tools/emulator @1 & emuid=$!  #不通过http代理上网
#	$SDK/tools/emulator -http-proxy $PROXY @1 & emuid=$!  # 为了防止被封IP，让模拟器使用http代理上网
###start emulator###
	sleep 3m

	# import vcards
	cd $SDK/platform-tools
	echo "pushing vcard file. sleep 15s"
	./adb push  $WSPACE/contact.vcf  /sdcard/
	sleep 15s

	# install apk
	echo "installing weibo apk. sleep 10s"
	./adb install $WSPACE/com.sina.weibo*.com.apk
	sleep 5s



	############################### 【step2:import Contact from sdcard】#############
	# example: 
	# xdotool search "Mozilla Firefox" windowactivate --sync key --clearmodifiers ctrl+l type "www.baidu.com";xdotool search "Mozilla Firefox" windowactivate --sync key --clearmodifiers Return

	# avd窗口移动到左上角
	echo "move window to top-left"
	xdotool search "5554" windowactivate --sync windowmove 0 0
	sleep 5s

	# 仅在第一次启动此AVD时会出现OK按钮！！
	echo "click OK button"
	xdotool search "5554" windowactivate --sync mousemove --sync 295 493 click 1
	sleep 10s

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
	echo "waiting to import. sleep 17m" 
	sleep 17m

	#502,268	模拟键盘的HOME钮
	echo "click HOME button" 
	xdotool search "5554" windowactivate --sync  mousemove --sync 502 268 click 1
	sleep 8s
	 
	############################### 【step3:login weibo apk 】######################
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

	echo "click weibo icon. sleep 90s"
	xdotool search "5554" windowactivate --sync mousemove --sync 308 163 click 1
	sleep 90s
	
	echo "click login. sleep 20s"
	xdotool search "5554" windowactivate --sync mousemove --sync 320 101 click 1
	sleep 20s

	echo "click email textarea"
	xdotool search "5554" windowactivate --sync mousemove --sync 137 244 click 1
	sleep 5s

###type username here###

	echo "click passwd textarea"
	xdotool search "5554" windowactivate --sync mousemove --sync 118 298 click 1
	sleep 2s

###type passwd here###

	echo "click Login button. sleep 90s"
	xdotool search "5554" windowactivate --sync mousemove --sync 189 368 click 1
	sleep 90s

	echo "click 开始我的旅程. sleep 35s"
	xdotool search "5554" windowactivate --sync mousemove --sync 189 454 click 1
	sleep 35s

	echo "update later. sleep 20s"
	xdotool search "5554" windowactivate --sync mousemove --sync 124 364 click 1
	sleep 1s
#	有时候，弹出的这个对话框中文字较多，出现换行，会多出一行，导致button会下移。
	xdotool search "5554" windowactivate --sync mousemove --sync 124 391 click 1
	sleep 20s

	echo "点击左上角的[添加好友]图标. sleep 90s"
	xdotool search "5554" windowactivate --sync mousemove --sync 56 100 click 1
	sleep 90s

	echo "向下滚动17下，寻找[通讯录好友关注]. sleep 5s"
#	xdotool search "5554" windowactivate --sync mousemove --sync 264 97 click 1
	#xdotool search "5554" windowactivate --sync mousemove --sync 189 504 click --repeat 12 --delay 1500 5
	xdotool search "5554" windowactivate --sync mousemove --sync 189 504 click --repeat 17 --delay 1500 5
	sleep 5s

	echo "click 通讯录好友关注  sleep 15s"
	xdotool search "5554" windowactivate --sync mousemove --sync 189 504 click 1
	sleep 15s

#	echo "click 通讯录联系人. sleep 15s"
#	xdotool search "5554" windowactivate --sync mousemove --sync 180 152 click 1
#	sleep 15s

	echo "enable contact matching. sleep 12m"
	xdotool search "5554" windowactivate --sync mousemove --sync 185 496 click 1
	sleep 12m

	################################### 【step4:printscreeen and save image 】#####
for j in {1..9}
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
echo "Saved image:" $VCFNAME-$j
done

############################# 【step5:clear and quit 】###################
echo closing apk
kill -9 $emuid
#timeout 2s xdotool search "5554" windowactivate --sync mousemove --sync 781 15 click 1 
#xdotool search "5554" windowactivate --sync mousemove --sync 781 15 click 1 & closepid=$!;sleep 3;kill -9 $closepid

	# save image
	mkdir $WSPACE/image/$VCFNAME
	mv $HOME/Desktop/*.png $WSPACE/image/$VCFNAME/

	# mv finished data
	cd $WSPACE/vcards
	mv $VCFFILE ./done/$VCFNAME.vcf

	# delete existing sdcard/vcard/AVD
	cd $WSPACE
	rm -f sdcard.img
	rm -f contact.vcf
	cd $AVDPATH
	rm -f 1.ini
	rm -rf 1.avd
	rm /tmp/android-bigbug/*

echo ============ Ending program =============
date
