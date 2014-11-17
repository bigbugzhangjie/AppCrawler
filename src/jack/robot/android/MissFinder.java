package jack.robot.android;

import jack.utility.FileTools;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * 有些vcard名单处理过程中出现错误，没有生成正确的图片，
 * 需要找出这些名单，重新爬取一遍
 * @author bigbug
 *
 */
public class MissFinder {
	
	/**
	 * 找到done目录中存在，而image目录中不存在的文件夹；
	 * 并将其中的vcf文件剪切到miss目录下；
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		String user = "bigbug";
		
		//待爬取的手机号码vcf文件们所在的文件夹
		String dirstr = "/home/"+user+"/adt-workspace/vcards";
		
		//结果图片所在目录
		File image = new File("/home/"+user+"/adt-workspace/image");
		File[] images = image.listFiles();
		ArrayList<String> pngs = new ArrayList<String>();
		for(File f : images){
			pngs.add(f.getName());
		}
				
		//已处理完毕的vcf文件所在目录
		File done = new File(dirstr+File.separator+"done");
		File[] dones = done.listFiles();
		
		// 
		File miss = new File(dirstr+File.separator+"miss");
		if(!miss.exists() || !miss.isDirectory()){
			miss.mkdir();
		}
		
		// 找到done中存在，而image中不存在的文件夹；
		// 并将其中的vcf文件剪切到miss目录下；
		int cnt = 0;
		for(File f : dones){
			String fn = f.getName().replace(".vcf", "");
			if(!pngs.contains(fn)){
				cnt++;
				FileTools.moveFileToDirectory(f, miss, true);
			}
		}

		System.out.println("Find "+cnt+" missed files.");
	}

}
