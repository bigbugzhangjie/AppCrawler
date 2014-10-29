package jack.robot.android;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import jack.utility.FileTools;;

public abstract class Robot {
	HashMap<String,String> accounts ;
	public Robot(){
		File file = new File("data/account.txt");
		init(file); 
	}
	public Robot(HashMap<String,String> weiboAccounts){
		this.accounts = weiboAccounts;
	}
	
	/**
	 * 
	 * @param accountfile	第一列为用户名，第二列为密码
	 */
	public Robot(File accountfile){
		init(accountfile);
	}
	private void init(File f){
		accounts = new HashMap<String,String>();
		ArrayList<Integer> idx = new ArrayList<Integer> ();
		idx.add(0);
		idx.add(1);
		HashMap<Integer,ArrayList<String>> map = FileTools.getMultiColumn(f, idx, "\t", true);
		for(ArrayList<String> l : map.values()){
			if(l.size()!=2){
				continue;
			}
			String user = l.get(0);
			String pwd = l.get(1);
			if(user!=null && user.length()>0 && pwd!=null && pwd.length()>0 ){
				accounts.put(user, pwd);
			}
		}
	}
	
	public abstract void run();

}
