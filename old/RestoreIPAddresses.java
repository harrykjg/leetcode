import java.util.ArrayList;
//http://blog.csdn.net/linhuanmars/article/details/24683699
//大概想到是隔1,2,3个数加一个点，剩下的继续加的样子，这样就是dfs，有点像Palindrome Partitioning
//大体是按自己的想法写的，一些细节，比如000和00不应该算数（只有0算数）如何判断是用了code ganker的
//方法。写出来比他们的代码都短
public class RestoreIPAddresses {
	public static void main(String[] args) {
		RestoreIPAddresses ri=new RestoreIPAddresses();
		ri.restoreIpAddresses2("010010");
		System.out.println();
		
	}

	ArrayList<String> al=new ArrayList<String>();
	public ArrayList<String> restoreIpAddresses(String s) {

		if(s.length()<4||s.length()>12){
			return al;
		}
		int len=s.length();
		dfs(s,len,4,"");
		return al;
		
	}
	public void dfs(String s,int left1,int left2,String cur){
		if(left1<=0&&left2<=0){
			cur=cur.substring(0,cur.length()-1);//最后一个是点，把他去掉
			al.add(cur);
		}
		int b=s.length()-left1;
		for(int i=1;i<=3&&i<=left1;i++){
			String s1=s.substring(b,i+b);
			if(left1-i>(left2-1)*3||(s1.length()>1&&s1.charAt(0)=='0')){//如果去了i个数作为
				//这一段ip，如果剩下的数字比剩下的段数乘以3都要多的话，则不行。或者这个s1的长度
				//如果大于1的话，并且第一个值是0，则略去，因为000或00都用0来代表了，如果是001或者
		//01这样的也不行，应为ip就不能这样显示.这里应该还少写了left1<（left2-1）*1这个条件
				continue;
			}
			if(Integer.parseInt(s1)<=255){
				dfs(s,left1-i,left2-1,cur+s1+".");
			}
		}
	}
//第二次写，是自己想出来的,思路ok，就是实现上有些细节问题，第一次是用元字符串剩下多少位和还要分几个
	//区来作为参数的，我我这是用了一个rs字符串，记录当前得到的现阶段的结果，由于是多了“.”的，造成
	//判断长度上的很多麻烦
	public ArrayList<String> restoreIpAddresses2(String s) {
		ArrayList<String> al=new ArrayList<String>();
		if(s.length()<4||s.length()>12){
			return al;
		}
		go(1,s,0,"",al);
		return al;
	}
	private void go(int cur,String s,int begin,String rs,ArrayList<String> al){
		if(begin==s.length()&&cur==5){
			al.add(rs.substring(0,rs.length()-1));
			return;
		}
		for(int i=1;i<=3&&i+begin<=s.length();i++){//这个i+begin<s.length漏了
			if(i>1&&s.charAt(begin)=='0'){
				continue;
			}
			String temp=s.substring(begin,begin+i);
			rs=rs+temp+".";//由于这里rs是加了cur个点的，所以所有涉及rs的长度的都要减去cur
			if(Integer.parseInt(temp)<=255&&(s.length()-(rs.length()-cur))>=(4-cur)&&
					(s.length()-rs.length()-cur)<=(4-cur)*3&&cur<5){
				go(cur+1,s,rs.length()-cur,rs,al);
			}
			rs=rs.substring(0,rs.length()-i-1);//这里rs.length()-i-1的意思是比如当前加了
			//2位数，则缩回2位且减去1个点
		}
	}
}
