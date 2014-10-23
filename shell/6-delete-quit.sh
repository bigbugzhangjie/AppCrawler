export WSPACE=$HOME/adt-workspace
export AVDPATH=$HOME/.android/avd

# close apk
xdotool search "5554" windowactivate --sync mousemove --sync 781 15 click 1

# save image
mkdir $WSPACE/image/$1
mv $HOME/Desktop/*.png $WSPACE/image/$1/

# mv finished data
cd $WSPACE/vcards
mv $1.vcf ./done/$1.vcf

# delete existing sdcard/vcard/AVD
cd $WSPACE
rm -f sdcard.img
rm -f contact.vcf
cd $AVDPATH
rm -f 1.ini
rm -rf 1.avd

