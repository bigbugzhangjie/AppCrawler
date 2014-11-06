package jack.robot.android;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import jack.utility.FileTools;;

public abstract class Robot {
	ArrayList<WeiboAccount> accounts ;//key：用户名， value:密码
	public Robot(){
		File file = new File("data/account.txt");
		init(file); 
	}
	public Robot(ArrayList<WeiboAccount> weiboAccounts){
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
		accounts = new ArrayList<WeiboAccount>();
		ArrayList<Integer> idx = new ArrayList<Integer> ();
		idx.add(0);
		idx.add(1);
		HashMap<Integer,ArrayList<String>> map = FileTools.getMultiColumn(f, idx, "\t", true);
		for(ArrayList<String> entry : map.values()){
			if(entry.size()!=2){
				continue;
			}
			String user = entry.get(0);
			String pwd = entry.get(1);
			if(user!=null && user.length()>0 && pwd!=null && pwd.length()>0 ){
				accounts.add(new WeiboAccount(user, pwd));
			}
		}
	}
	
	public abstract void run() throws InterruptedException, IOException;

}

