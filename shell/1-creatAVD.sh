export SDK=$HOME/local/adt-bundle-linux-x86_64-20140702/sdk
export WSPACE=$HOME/adt-workspace
export TEMPLATE=$WSPACE/template
export AVDPATH=$HOME/.android/avd

# prepare data
cd $WSPACE
cp ./vcards/$1.vcf contact.vcf

# mksdcard
cd $SDK/tools/
./mksdcard -l sdcard 100M $WSPACE/sdcard.img

# create AVD
cp -rf $TEMPLATE/* $AVDPATH/
# 不再重新创建AVD：  ./android create avd -n '1' -t 2 -c $WSPACE/sdcard.img -s HVGA -b armeabi-v7a ;sleep 1m

# starting emulator
$SDK/tools/emulator @1 &
sleep 3m

# import vcards
cd $SDK/platform-tools
./adb push  $WSPACE/contact.vcf  /sdcard/
sleep 1m

# install apk
./adb install $WSPACE/com.sina.weibo*.com.apk;sleep 2m
echo "Finished starting AVD"

