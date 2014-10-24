#!/bin/bash
for i in {1..3}
  do
    cd /home/bigbug/workspace/AppCrawler/shell
	bash 1-*.sh i && bash 2-*.sh  && bash 3-*.sh  && bash 4-*.sh 
	sleep 10m

	for j in {1..3}
		bash ./5-*.sh
	done

	bash 6-*.sh
done
