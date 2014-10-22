export SDK=$HOME/local/adt-bundle-linux-x86_64-20140702/sdk
export WSPACE=$HOME/adt-workspace

cd $SDK/tools/
./mksdcard -l sdcard 100M $WSPACE/sdcard.img
./android create avd -n '0' -t 2 -c $WSPACE/sdcard.img -s HVGA -b armeabi-v7a 

# 后续交互中输入yes，然后不断回车，直到hw.keyboard,输入yes，然后再回车到结束
