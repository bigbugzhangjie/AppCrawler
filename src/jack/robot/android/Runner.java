package jack.robot.android;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import jack.utility.FileTools;;

public class Runner extends Robot{
	public Runner(File f){
		super(f);
	}
	public void run(){
	//TODO	
	}

	public static void main(String[] args) throws IOException {
		Runner runner = new Runner(new File("/home/bigbug/workspace/WeiboCrawler/doc/accounts.txt"));
		runner.run();
	}
}
