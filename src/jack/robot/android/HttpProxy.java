package jack.robot.android;

import test.HttpRequest;

public class HttpProxy {
	// 提供代理池服务的机器所在的IP地址,如：  10.10.232.101:20000/get/weiboapp
	public static String POOLADDRESS = "10.10.232.101:20000/get/weiboapp";

	/**
	 * 返回一个可用的代理服务器IP，出错时返回空字符:""
	 * @return
	 */
	public static String getOne(){
		String ret = "";
		String resp = HttpRequest.sendGet(POOLADDRESS, "");
		//根据协商好的格式协议出代理服务器的IP
		if(resp.contains("\"data\"")){
			try{
				ret = resp;
				ret=ret.substring(ret.indexOf("\"data\"")+6);
				ret=ret.substring(ret.indexOf("\"")+1);
				ret=ret.substring(0,ret.indexOf("\""));
			}catch(Exception e){
				e.printStackTrace();
				ret = "";
			}
		}
		return ret;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
