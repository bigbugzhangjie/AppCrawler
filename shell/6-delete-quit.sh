export WSPACE=$HOME/adt-workspace
export AVDPATH=$HOME/.android/avd

# delete existing sdcard/vcard/AVD
cd $WSPACE
rm -f sdcard.img
rm -f contact.vcf
cd $AVDPATH
rm -f 1.ini
rm -rf 1.avd

# save image
mv $HOME/Desktop/Screenshot.png $WSPACE/image/$1.png

# mv finished data
cd $WSPACE/vcards
mv $1.vcf ./done/$1.vcf
