sleep 3m
export SDK=$HOME/local/adt-bundle-linux-x86_64-20140702/sdk
export WSPACE=$HOME/adt-workspace
cd $SDK/platform-tools
./adb push  $WSPACE/contact.vcf  /sdcard/
sleep 1m
./adb install $WSPACE/com.sina.weibo*.com.apk
sleep 2m

#TODO avd窗口移动到左上角
xdotool 

#TODO import contacts from sdcard
xdotool


