tdir=$1
machine=$2
cd /home/bigbug/adt-workspace/vcards/done
scp ./* 10.10.232.101:/bigdata/share/appCrawler/$tdir/$machine/vcards-done/
cd /home/bigbug/adt-workspace/image/
scp ./* 10.10.232.101:/bigdata/share/appCrawler/$tdir/$machine/image/

