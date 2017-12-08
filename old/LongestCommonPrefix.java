public class LongestCommonPrefix {
	public static void main(String[] args) {
		LongestCommonPrefix lc=new LongestCommonPrefix();
		String[] strs={"ac","ac","a","a"};
		System.out.println(lc.longestCommonPrefix(strs));
	}
	//这题网上也是直观的想法，好像没什么特别好的方法
	public String longestCommonPrefix(String[] strs) {
		
		if(strs.length==0){
			return "";
		}
		if(strs.length==1){
			return strs[0];
		}
		String rs="";
		int l1=strs[0].length();//现在strs至少有两个元素了
		int l2=strs[1].length();
		if(l1>l2){
			rs=strs[1];
		}else{
			rs=strs[0];
		}
		
		for(int i=0;i<rs.length();i++){//先找到第一，2个的prefix，即rs
			if(strs[0].charAt(i)!=strs[1].charAt(i)&&i==0){
				return "";
			}
			else if(strs[0].charAt(i)!=strs[1].charAt(i)){
				rs=rs.substring(0,i);
				break;
			}
		}
		//再用这个rs和第3个元素开始比较
		for(int i=2;i<strs.length;i++){
			if(strs[i]==""){
				return "";
			}
			int min=Math.min(rs.length(), strs[i].length());
			for(int j=0;j<min;j++){
				if(strs[i].charAt(j)!=rs.charAt(j)&&j==0){
					return "";
				}
				else if(strs[i].charAt(j)!=rs.charAt(j)){
					rs=rs.substring(0,j);
					break;
				}
				else if(j+1==min){//用来处理rs是abcd，而要比较的那个是abc（或更短，但前面的字母都
					//一样的情况）
					rs=rs.substring(0,j+1);
				}
			}
		}
		return rs;

	}
	//第二次写，还是想了一会
	public String longestCommonPrefix2(String[] strs) {
		if(strs.length==0){
			return "";
		}
		if(strs.length==1){
			return strs[0];
		}
		boolean flag=true;
		
		String s="";
		
		for(int i=0;i<strs[0].length();i++){
			char c=strs[0].charAt(i);//其实就拿第一个元素作为检测标准就行了，不管他的长度如何，
			for(int j=0;j<strs.length;j++){//想一下，长了短了都一样。
				//然后拿第一个字符串的第i个字符，和剩下的所有字符串的第i个字符比较，错了的话
				//就肯定不是common prefix，可以break了，或者后面的字符串比较短，也break
				if(i<strs[j].length()&&c!=strs[j].charAt(i)){
					flag=false;
					break;
				}else if(i>=strs[j].length()){
					flag=false;
					break;
				}
			}
			if(flag){
				s=s+c;
			}else{//如果当前第i个字符已经不符合了，那common prefix也到此结束，break
				break;
			}
		}
		return s;
	}

}
