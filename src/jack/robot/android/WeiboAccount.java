package jack.robot.android;

public class WeiboAccount {
	
		String username;
		String passwd;
		String phone="";
		public WeiboAccount(String un, String pwd){
			username = un;
			passwd = pwd;
		}
		public WeiboAccount(String un, String pwd,String cell){
			username = un;
			passwd = pwd;
			phone = cell;
		}
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPasswd() {
			return passwd;
		}
		public void setPasswd(String passwd) {
			this.passwd = passwd;
		}
		public String getPhone() {
			return phone;
		}
		public void setPhone(String phone) {
			this.phone = phone;
		}
	public String toString(){
		return "["+username+","+passwd+","+phone+"]";
	}
}
