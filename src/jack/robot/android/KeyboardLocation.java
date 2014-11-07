package jack.robot.android;

import java.util.HashMap;

public class KeyboardLocation {
	public static HashMap<String,KeyboardGeo> map = new HashMap<String,KeyboardGeo>();
	static{
		int x0=420, y0=380, s=37;		
		map.put("1", new KeyboardGeo(x0,y0));
		map.put("2", new KeyboardGeo(x0+1*s,y0));
		map.put("3", new KeyboardGeo(x0+2*s,y0));
		map.put("4", new KeyboardGeo(x0+3*s,y0));
		map.put("5", new KeyboardGeo(x0+4*s,y0));
		map.put("6", new KeyboardGeo(x0+5*s,y0));
		map.put("7", new KeyboardGeo(x0+6*s,y0));
		map.put("8", new KeyboardGeo(x0+7*s,y0));
		map.put("9", new KeyboardGeo(x0+8*s,y0));
		map.put("0", new KeyboardGeo(x0+9*s,y0));
		
		map.put("q", new KeyboardGeo(x0,y0+1*s));
		map.put("w", new KeyboardGeo(x0+1*s,y0+1*s));
		map.put("e", new KeyboardGeo(x0+2*s,y0+1*s));
		map.put("r", new KeyboardGeo(x0+3*s,y0+1*s));
		map.put("t", new KeyboardGeo(x0+4*s,y0+1*s));
		map.put("y", new KeyboardGeo(x0+5*s,y0+1*s));
		map.put("u", new KeyboardGeo(x0+6*s,y0+1*s));
		map.put("i", new KeyboardGeo(x0+7*s,y0+1*s));
		map.put("o", new KeyboardGeo(x0+8*s,y0+1*s));
		map.put("p", new KeyboardGeo(x0+9*s,y0+1*s));
		
		map.put("a", new KeyboardGeo(x0,y0+2*s));
		map.put("s", new KeyboardGeo(x0+1*s,y0+2*s));
		map.put("d", new KeyboardGeo(x0+2*s,y0+2*s));
		map.put("f", new KeyboardGeo(x0+3*s,y0+2*s));
		map.put("g", new KeyboardGeo(x0+4*s,y0+2*s));
		map.put("h", new KeyboardGeo(x0+5*s,y0+2*s));
		map.put("j", new KeyboardGeo(x0+6*s,y0+2*s));
		map.put("k", new KeyboardGeo(x0+7*s,y0+2*s));
		map.put("l", new KeyboardGeo(x0+8*s,y0+2*s));
		map.put("del", new KeyboardGeo(x0+9*s,y0+2*s));
		
		map.put("shift", new KeyboardGeo(x0,y0+3*s));
		map.put("z", new KeyboardGeo(x0+1*s,y0+3*s));
		map.put("x", new KeyboardGeo(x0+2*s,y0+3*s));
		map.put("c", new KeyboardGeo(x0+3*s,y0+3*s));
		map.put("v", new KeyboardGeo(x0+4*s,y0+3*s));
		map.put("b", new KeyboardGeo(x0+5*s,y0+3*s));
		map.put("n", new KeyboardGeo(x0+6*s,y0+3*s));
		map.put("m", new KeyboardGeo(x0+7*s,y0+3*s));
		map.put(".", new KeyboardGeo(x0+8*s,y0+3*s));
		map.put("enter", new KeyboardGeo(x0+9*s,y0+3*s));
		
		map.put("alt_l", new KeyboardGeo(x0,y0+4*s));
		map.put("sym", new KeyboardGeo(x0+1*s,y0+4*s));
		map.put("@", new KeyboardGeo(x0+2*s,y0+4*s));
		map.put("space", new KeyboardGeo(x0+5*s,y0+4*s));
		map.put("/", new KeyboardGeo(x0+7*s,y0+4*s));
		map.put(",", new KeyboardGeo(x0+8*s,y0+4*s));
		map.put("alt_r", new KeyboardGeo(x0+9*s,y0+4*s));
		
	}
	
	/**
	 * 在模拟器上用鼠标点击的方式输入str
	 * @param str
	 * @return
	 */
	public static String typeText(String str) {
		
		String ret = "";
		
		String pre ="xdotool search \"5554\" windowactivate --sync mousemove --sync ";
		String post=" click 1\nsleep 2s\n";
		for(int i=0;i<str.length();i++){
			String c = String.valueOf( str.charAt(i) );
			KeyboardGeo button = map.get(c);
			String loc = button.toString();
			ret += pre+loc+post;
		}
		return ret;
	}
	
	public static void main(String[] args) {
//		String key = "1";
//		String loc = KeyboardLocation.map.get(key).toString();
//		System.out.println(loc);
//		
//		 key = "alt_r";
//		 loc = KeyboardLocation.map.get(key).toString();
//		System.out.println(loc);
//		
//		 key = "8";
//		 loc = KeyboardLocation.map.get(key).toString();
//		System.out.println(loc);
		
		String user = "bigbug05@sina.com";
		System.out.println(typeText(user));

	}
	
}
class KeyboardGeo{
	public int x;
	public int y;
	public KeyboardGeo(int x, int y){
		this.x = x;
		this.y = y;
	}
	/**
	 * x+" "+y
	 */
	public String toString(){
		return x+" "+y;
	}
}

