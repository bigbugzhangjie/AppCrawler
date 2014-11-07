export SDK=$HOME/local/adt-bundle-linux-x86_64-20140702/sdk
export WSPACE=$HOME/adt-workspace
export TEMPLATE=$WSPACE/template
export AVDPATH=$HOME/.android/avd
# vcf文件数目，每个帐号每天只能上传3次
vcf_num=3 
# 每个虚拟机保存的图片数目（每个page含6个联系人，目前是每个vcf文件含120个联系人）
page_num=20

for i in {1..3}
do
	echo ========== starting $i-th round ===========
	date
	################################# 【step1:create AVD】 ####################
	# prepare data
	cd $WSPACE
	cp ./vcards/$i.vcf contact.vcf

	# mksdcard
	cd $SDK/tools/
	./mksdcard -l sdcard 100M $WSPACE/sdcard.img

	# create AVD
	cp -rf $TEMPLATE/* $AVDPATH/
	# 不再重新创建AVD：  ./android create avd -n '1' -t 2 -c $WSPACE/sdcard.img -s HVGA -b armeabi-v7a ;sleep 1m

	# starting emulator 【模拟器到名字叫“1”】
	echo "starting AVD. sleep 3m"
	$SDK/tools/emulator @1 &
	sleep 3m

	# import vcards
	cd $SDK/platform-tools
	echo "pushing vcard file. sleep 15s"
	./adb push  $WSPACE/contact.vcf  /sdcard/
	sleep 15s

	# install apk
	echo "installing weibo apk. sleep 10s"
	./adb install $WSPACE/com.sina.weibo*.com.apk
	sleep 10s
	# 这2m貌似是可以省的。因为接下来是导入sdcard，可以尝试并行处理。


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
	echo "waiting to import. sleep 5m" 
	sleep 5m

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
	# 4
	xdotool search "5554" windowactivate --sync mousemove --sync 530 372 click 1
	sleep 2s
	# 1
	xdotool search "5554" windowactivate --sync mousemove --sync 420 372 click 1
	sleep 2s
	# 2
	xdotool search "5554" windowactivate --sync mousemove --sync 456 372 click 1
	sleep 2s
	# 3
	xdotool search "5554" windowactivate --sync mousemove --sync 494 372 click 1
	sleep 5s

	echo "click Login button. sleep 90s"
	xdotool search "5554" windowactivate --sync mousemove --sync 189 368 click 1
	sleep 90s

	echo "click 开始我的旅程. sleep 20s"
	xdotool search "5554" windowactivate --sync mousemove --sync 189 454 click 1
	sleep 20s

	echo "update later. sleep 20s"
	xdotool search "5554" windowactivate --sync mousemove --sync 122 412 click 1
	sleep 20s

	echo "click 添加好友. sleep 30s"
	xdotool search "5554" windowactivate --sync mousemove --sync 56 100 click 1
	sleep 30s

#	# 有时候好像会多出一个“找人”页面，需要多点击一次。绝大多数情况下不出现此页面
#	echo "click 添加好友"
#	xdotool search "5554" windowactivate --sync mousemove --sync 185 151 click 1
#	sleep 30s

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

	echo "click 通讯录联系人. sleep 15s"
	xdotool search "5554" windowactivate --sync mousemove --sync 180 152 click 1
	sleep 15s

	echo "enable contact matching. sleep 90s"
	xdotool search "5554" windowactivate --sync mousemove --sync 185 496 click 1
	sleep 90s

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
		echo "Saved image:" $i - $j
	done
	############################# 【step5:clear and quit 】###################
	# close apk
	xdotool search "5554" windowactivate --sync mousemove --sync 781 15 click 1
	sleep 3s

	# save image
	mkdir $WSPACE/image/$i
	mv $HOME/Desktop/*.png $WSPACE/image/$i/

	# mv finished data
	cd $WSPACE/vcards
	mv $i.vcf ./done/$i.vcf

	# delete existing sdcard/vcard/AVD
	cd $WSPACE
	rm -f sdcard.img
	rm -f contact.vcf
	cd $AVDPATH
	rm -f 1.ini
	rm -rf 1.avdl
done
echo ============ Ending program =============
date
