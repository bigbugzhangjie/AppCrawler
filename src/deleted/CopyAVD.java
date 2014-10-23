package deleted;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CopyAVD {
	public static String TARGET="android-18";
	String name="";
	File dir;
	public CopyAVD(String name) throws IOException{
		this.name = name;
		dir = new File("templates/avd");
		init();
	}
	public CopyAVD(String name,File path) throws IOException{
		this.name = name;
		if(path.exists()){
			if(path.isDirectory()){
				dir = path;
			}else{
				path.mkdir();
				dir = path;
			}
		}else{
			path.mkdir();
			dir = path;
		}
		init();
	}
	private void init() throws IOException{
		/*
avd.ini.encoding=ISO-8859-1
path=/home/bigbug/.android/avd/1.avd
path.rel=avd/1.avd
target=android-18
		 */
		
		// 创建 xxx.ini
		FileWriter w = new FileWriter(new File(dir+File.separator+name+".ini"));
		w.write("avd.ini.encoding=ISO-8859-1\n");
		w.write("path="+dir+File.separator+name+".avd\n");
		w.write("path.rel="+dir.getName()+File.separator+name+".avd\n");
		w.write("target="+TARGET+"\n");
		w.close();

		// 创建文件夹，及其里边的文件
		File avddir = new File(dir.getPath()+File.separator+name+".avd");
		avddir.mkdirs();
		//TODO 决定不用java创建avd了，直接用shell脚本cp
//		w = new

	}
	
}
