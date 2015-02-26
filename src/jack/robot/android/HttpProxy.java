package jack.robot.android;

import jack.example.HttpRequest;

public class HttpProxy {
	// 提供代理池服务的机器所在的IP地址,如：  http://10.10.232.101:30000/get/weiboapp
	public static String POOLADDRESS = "http://10.10.232.101:12580/get/weiboapp";

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
//				// for debug:
//				System.out.println("============HTTP Response===========");
//				System.out.println(resp);
//				System.out.println("============Proxy IP===========");
//				System.out.println(ret);
//				System.out.println("=======================");
			}catch(Exception e){
				e.printStackTrace();
				ret = "";
			}
		}
		return ret;
	}

	public static void main(String[] args) throws Exception{
		for(int i=0;i<3;i++){
			System.out.println(HttpProxy.getOne());
			Thread.sleep(2000);
		}
//		http://183.207.224.52:80
//		http://183.207.228.57:80
	}
}
