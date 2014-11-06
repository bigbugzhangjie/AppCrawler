package jack.robot.android;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import jack.utility.FileTools;;

public class MatchRobot extends Robot{
	File[] files;//需要处理的vcard文件
	
	/**
	 * 
	 * @param account	微博帐号所在文件。第一列用户名，第二列密码
	 * @param dir	需要处理的vcard文件所在的目录
	 */
	public MatchRobot(File account, File dir){
		super(account);
		files = FileTools.filter(dir, ".vcf", 2);
	}
	
	/**
	 * 使用帐号池里的微博帐号，批量处理联系人文件，进行联系人手机号与微博帐号的匹配；
	 * 
	 */
	public void run(){
	//TODO	
	}

	public static void main(String[] args) throws IOException {
		File acc = new File("/home/bigbug/workspace/WeiboCrawler/doc/accounts.txt");
		File dir = new File("/home/bigbug/adt-workspace/vcards");
		MatchRobot runner = new MatchRobot(acc,dir);
		runner.run();
	}
}
