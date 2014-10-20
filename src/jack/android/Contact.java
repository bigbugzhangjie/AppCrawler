package jack.android;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;

/**
 * 
 * @author bigbug
 * @since Oct 20, 2014
 */
public class Contact {
	static String rev = "";
	static{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");  
		java.util.Date date=new java.util.Date();  
		rev = sdf.format(date);
		rev +="T021959Z";
	}
	
	String version = "2.1";
	String name = "";
	String fullname = "";
	long cell = 0L;
	String email = "";

	public Contact(){
		
	}
	/**
	 * 
	 * @param cell	手机号码
	 */
	public Contact(long cell ){
		this.cell = cell;	
		this.name = String.valueOf(cell);
		this.fullname = name;
	}
	public Contact(String email ){
		this.email = email;	
		this.name = email;
		this.fullname = name;
	}
	public Contact(long cell,String email ){
		this.cell = cell;	
		this.email = email;		
		this.name = String.valueOf(cell);
		this.fullname = name;
	}
	
	public static String getRev() {
		return rev;
	}
	public static void setRev(String rev) {
		Contact.rev = rev;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public long getCell() {
		return cell;
	}
	public void setCell(long cell) {
		this.cell = cell;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String toVCard(){
		/*
		BEGIN:VCARD
		VERSION:2.1
		N:zhangjie
		FN:zhangjie
		TEL;CELL;VOICE:15001383841
		EMAIL;PREF;INTERNET:z127513@huawei.com
		REV:20141020T021959Z
		END:VCARD
			 */
		String ret = "";
		ret += "BEGIN:VCARD\n"
				+ "VERSION:"+this.version+"\n"
				+"N:"+name+"\n"
				+"FN:"+fullname+"\n"
				+((cell>0)?("TEL;CELL;VOICE:"+cell+"\n"):"")
				+((email.length()>0)?("EMAIL;PREF;INTERNET:"+email+"\n"):"")
				+"REV:"+rev+"\n"
				+"END:VCARD\n"
				;
		return ret;
	}
	
	/**
	 * 将一组联系人导入到一个指定的文件。【文件扩展名推荐使用*.vcf】
	 * @param list
	 * @param vcf
	 * @throws IOException
	 */
	public static void toVCard(Collection<Contact> list,File vcf) throws IOException{
		FileWriter w = new FileWriter(vcf);
		for(Contact c : list){
			w.write(c.toVCard()+"\n");
		}
		w.close();
	}


	public static void main(String[] args) {
		Contact c = new Contact(18901383841L);
		System.out.println(c.toVCard());
		
		Contact d = new Contact("z127513@126.com");
		System.out.println(d.toVCard());
		
		Contact e = new Contact(18901383841L,"z127513@126.com");
		System.out.println(e.toVCard());

	}

}
