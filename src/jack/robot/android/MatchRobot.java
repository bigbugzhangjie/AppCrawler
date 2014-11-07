package jack.robot.android;

import jack.utility.FileTools;
import jack.utility.Timer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;
import java.util.List;

public class MatchRobot extends Robot{
	public static File SCRIPT = new File("templates/template.sh"); //shell 脚本文件
	public static boolean debug = true; //是否是debug阶段
	
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
	 * @throws InterruptedException 
	 * @throws IOException 
	 * 
	 */
	public void run() throws InterruptedException, IOException{
		int round = 0;
		int accountIdx = 0;
		for(int i=0;i<files.length;i++){
			System.out.println("");
			File vcard = files[i]; //当前待处理的名单
			
			//如果爬虫帐号用完了，则休息几个小时；
			if(accounts.size()<= accountIdx){
				System.out.print("falling into sleep at: ");				
				Date d = new Date();
				System.out.println(d.toString());
				int hour = d.getHours();
				Thread.sleep((24-hour)*3600*1000);
				accountIdx = 0; //休息完之后，第二天从头开始用帐号；
			}
			WeiboAccount account = this.accounts.get(accountIdx); 
			
			System.out.println("================== round "+i+" =================");
			System.out.println("vcardfile:"+vcard+"\taccount:"+accountIdx+"\t"+account.toString());
			Timer timer = new Timer();
			
			File shell = new File(SCRIPT.getAbsoluteFile().getParent()+File.separator+"temp-f"+i+"-u"+accountIdx+".sh");
			generateScript(account.getUsername(),account.getPasswd(),vcard, shell);
			timer.add("generate script");
			callShell(shell);
			timer.add("run script");
			
			if(!debug){
				FileTools.forceDelete(shell); //debug阶段先注掉；
			}
			
			// 善后工作
			round++;
			if(round%3==0){  //如果遇到休息，第二天重新开始爬，这样可能会浪费1-2轮。
				accountIdx++;	
			}
			timer.printAll();
			
			if(debug){
				if(round==2){
					return;
				}
			}
		}
		
	}
	
	/**
	 * 根据SCRIPT模板脚本，生成新的脚本文件；
	 * @param uname	 微博用户名
	 * @param pwd	微博用户密码
	 * @param vcf	vcard文件
	 * @param script	待生成的脚本文件
	 * @throws IOException 
	 */
	private void generateScript(String uname,String pwd,File vcf,File script) throws IOException{
		String vcfpath = vcf.getAbsolutePath();
		String fn = vcf.getName();
		String vcfname = fn.substring(0, fn.indexOf("."));
		
		List<String> lines = FileTools.getLineList(SCRIPT);
		String typeusername = KeyboardLocation.typeText(uname);
		String typepwd = KeyboardLocation.typeText(pwd);
		FileWriter writer = new FileWriter(script);
		for(String line : lines){
			if(line.equals("### define VCFFILE VCFNAME ###")){
				line = "VCFFILE="+vcfpath+"\nVCFNAME="+vcfname;
			}else if(line.equals("###type username here###")){
				line = typeusername;
			}else if(line.equals("###type passwd here###")){
				line = typepwd;
			}
			writer.write(line+"\n");
		}
		writer.close();
	
	}
	
	
	private void callShell(File f) throws InterruptedException{
		try {
			
//			String[] commands = new String[] { "sh", "test/test.sh" };
			String[] commands = new String[] {"sh",f.getAbsolutePath()};
//			System.out.println("==============before exec");
			Process process = Runtime.getRuntime().exec(commands);
//			System.out.println("==============waitFor");
//			process.waitFor(); //block当前的java进程，等子进程全部执行完毕后统一返回
//			System.out.println("==============before read input stream");
			InputStreamReader ir = new InputStreamReader(
					process.getInputStream());
			BufferedReader input = new BufferedReader(ir);
			String line;
			while ((line = input.readLine()) != null) {
				System.out.println(line);
			}
		} catch (java.io.IOException e) {
			System.err.println("IOException " + e.getMessage());
		}
	}

	public static void main(String[] args) throws Exception {
		MatchRobot.debug = true;
		File acc = new File("/home/bigbug/workspace/WeiboCrawler/doc/accounts.txt");
		File dir = new File("/home/bigbug/adt-workspace/vcards");
		MatchRobot runner = new MatchRobot(acc,dir);
		runner.run();
	}
}
