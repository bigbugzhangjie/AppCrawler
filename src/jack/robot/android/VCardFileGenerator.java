package jack.robot.android;

import jack.utility.FileTools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class VCardFileGenerator {
	HashSet<Contact> contacts;
	File out;
	int max=Integer.MAX_VALUE;

	public static void main(String[] args) throws IOException {
		//读取手机号码列表，将其转换成vcf文件
////		File in = new File("/home/bigbug/adt-workspace/data/phone-10k-congying.txt");
//		File in = new File("/home/bigbug/adt-workspace/data/zhangyun/test-1"); //test400");  //寿险1.tsv");
//		String path = "/home/bigbug/adt-workspace/vcards/test"; // all  test
//		File out = new File(path);
//		int MaxNumber = 800; //每个vcf文件中最多的联系人数目；  400
//		if(args.length==2){
//			in = new File(args[0]);
//			out= new File(args[1]);
//		}else{
//			System.err.println("Using default input/output files: \n");
//		}
//		cells2vcf(in,out,MaxNumber);
		
		
		
		//读取一组vcf文件，按照指定的最大数目，重新生成一组vcf文件
		//刚开始的时候，每个vcf中分配400个手机号。后来将没有跑完的vcf重新合并，改为每个vcf分配800个手机号；
		File inDir = new File("/home/bigbug/adt-workspace/result/103/vcards/");
		File outDir = new File("/home/bigbug/adt-workspace/result/103/vcards/800perVcf");
		int MaxNumber = 800;
		
		rewrite(FileTools.filter(inDir, "vcf", 2),outDir,MaxNumber);
		
		System.out.println("=========Finished!========");
		
	}
	
	public static void rewrite(File[] vcfs, File outDir, int max) throws IOException {
		System.out.println("Found "+ vcfs.length+" files in input directory.");
		if(!outDir.exists() || !outDir.isDirectory()){
			outDir.mkdir();
		}
		ArrayList<Contact> list = new ArrayList<Contact>();
		for(File f : vcfs){
			list.addAll(Contact.loadVCard(f));
		}
		Contact.toVCard(list, outDir,max);
	}
	
/**
 * 读取手机号码列表，将其转换成vcf文件
 * @param cellFile	每行一个手机号
 * @param outDir	目标vcf所在目录
 * @param max	每个vcf文件中包含的contact最大数目
 * @throws IOException
 */
	public static void cells2vcf(File cellFile,File outDir,int max) throws IOException {
		VCardFileGenerator gen = new VCardFileGenerator();

		if(!outDir.exists() || !outDir.isDirectory()){
			outDir.mkdir();
		}
		System.out.println("\tinput file: "+cellFile.toString()
				+ "\n\toutput file: "+outDir.toString()
				);
		
		HashSet<String> phones = VCardFileGenerator.getColumnSet(cellFile, 0, "\t",true); 
// 				
//		List<String> phones = FileTools.getColumnList(inFile, 0, "\t", true);
//		Collections.shuffle(phones);
		
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
		Contact.toVCard(gen.contacts, outDir,max);
		
//		// 全部写入一个文件；
//		Contact.toVCard(gen.contacts, new File(outDir.getAbsolutePath()+File.separator+"all.vcf"));
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
