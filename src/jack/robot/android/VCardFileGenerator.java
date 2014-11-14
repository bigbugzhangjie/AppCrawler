package jack.robot.android;

import jack.utility.FileTools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class VCardFileGenerator {
	HashSet<Contact> contacts;
	File out;
	int max=Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		int MaxNumber = 400; //每个vcf文件中最多的联系人数目；
		VCardFileGenerator gen = new VCardFileGenerator();
//		File in = new File("/home/bigbug/adt-workspace/data/phone-10k-congying.txt");
		File in = new File("/home/bigbug/adt-workspace/data/zhangyun/test-1"); //test400");  //寿险1.tsv");
		String path = "/home/bigbug/adt-workspace/vcards/test"; // all  test
		File out = new File(path);
		if(args.length==2){
			in = new File(args[0]);
			out= new File(args[1]);
		}else{
			System.err.println("Using default input/output files: \n");
		}
		if(!out.exists() || !out.isDirectory()){
			out.mkdir();
		}
		System.out.println("\tinput file: "+in.toString()
				+ "\n\toutput file: "+out.toString()
				);
		
//		HashSet<String> phones = VCardFileGenerator.getColumnSet(in, 0, "\t",true); 
				
		List<String> phones = FileTools.getColumnList(in, 0, "\t", true);
		Collections.shuffle(phones);
		
		gen.contacts = new HashSet<Contact>();
		for(String s :phones){
			try{
				long cell = Long.parseLong(s);
				Contact c = new Contact(cell);
				gen.contacts.add(c);
			}catch(Exception e){
				System.err.println("ignore cellphone format error:"+s);
			}
		}
		
		// 分为多个文件；
		Contact.toVCard(gen.contacts, out,MaxNumber);
		
		// 全部写入一个文件；
		Contact.toVCard(gen.contacts, new File(path+File.separator+"all.vcf"));
	}
	
	public static HashSet<String> getColumnSet(File file, int index, String sep,boolean ignore) {
		HashSet<String> ret = new HashSet<String>();
		BufferedReader br = null;

		try {
			// 构造BufferedReader对象
			br = new BufferedReader(new FileReader(file));

			String line = null;
			while ((line = br.readLine()) != null) {
				String[] cols = line.split(sep);
				if(cols.length>=(index+1)){
					String col = cols[index];
					ret.add(col);
				}else{
//					 //将出错line文本打印到控制台
					System.err.println("index overflow error: "+line);
					if (!ignore){
						ret.add(null);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭BufferedReader
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return ret;
	}

}
