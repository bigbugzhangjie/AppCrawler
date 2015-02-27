package jack.robot.android;

import jack.utility.FileTools;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

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
	 * @throws FileNotFoundException 
	 */
	public Robot(File accountfile) throws FileNotFoundException{
		if(accountfile.exists()&&accountfile.isFile()){
			init(accountfile);
		}else{
			throw new FileNotFoundException("Can NOT found file: "+accountfile.toString());
		}
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
		Collections.shuffle(accounts); 
	}
	
	public abstract void run() throws InterruptedException, IOException;

}

