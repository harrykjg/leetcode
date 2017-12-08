import java.util.HashMap;

//http://www.cnblogs.com/jdflyfly/p/3810685.html

public class IntegertoRoman {
	public static void main(String[] args) {
		IntegertoRoman ir=new IntegertoRoman();
		System.out.println(ir.intToRoman(20));
	}

	public String intToRoman(int num) {
//这题主要就是要知道这样的对应，就好了		
		String[] pool={"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
		int[] val={1000,900,500,400,100,90,50,40,10,9,5,4,1};
		String rs="";
		HashMap<Integer,String> map=new HashMap<Integer,String>();
//		map.put(1000, "M");
//		map.put(900, "CM"); //这题用map还不好取出来
//		map.put(500, "D");
//		map.put(400, "CD");
//		map.put(100, "C");
//		map.put(90, "XC");
//		map.put(50, "L");
//		map.put(40, "XL");
//		map.put(10, "X");
//		map.put(9, "IX");
//		map.put(5, "V");
//		map.put(4, "IV");
//		map.put(1, "I");
		
		
		while(num>0){
			for(int i=0;i<val.length;i++){
				if(num>=val[i]){
					num-=val[i];
					rs=rs+pool[i];
					break;
				}
			}
		}

		return rs;
	}

}
