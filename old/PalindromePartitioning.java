//http://jixiangsanbao.wordpress.com/2014/05/22/palindrome-partitioning-5/ ????????????
//http://blog.csdn.net/linhuanmars/article/details/22777711 ????code ganker????·??????????
import java.util.ArrayList;

public class PalindromePartitioning {
	
	public static void main(String[] args) {

		String s="abaabacdck";
		PalindromePartitioning pp=new PalindromePartitioning();
		
		ArrayList<ArrayList<String>>bb=pp.partition3(s);
		while(!bb.isEmpty()){
			System.out.println(bb.remove(0));
	}
	}
	
	ArrayList<ArrayList<String>> al=new ArrayList<ArrayList<String>>();
	 public ArrayList<ArrayList<String>> partition(String s) {
		 if(s.length()==0||s==null){
			 return al;
		 }
		 ArrayList<String> a=new ArrayList<String>();
		 go(s,0,a);
		 return al;
	 }
	 public void go(String s, int begin, ArrayList<String> a){
		//?·?????0?????ÿ?????0????1???2???3??????????????????????????????1,2,3?
		 //?????????????????????????1,2,3?
		 if(begin>=s.length()){
			 al.add(new ArrayList<String>(a));
			 return;
		 }
		 for(int i=begin+1;i<=s.length();i++){
			 String ss=s.substring(begin,i);
			 if(valid(ss)){
				 a.add(ss);
				 go(s,i,a);
				 a.remove(a.size()-1);
			 }
		 } 
	 }
	 public boolean valid(String s){
		 if(s.length()==0||s.length()==1){
			 return true;
		 }
		 int len=s.length();
		 int b=0;
		 int e=len-1;
		 while(b<e){
			 if(s.charAt(b)!=s.charAt(e)){
				 return false;
			 }
			 b++;
			 e--;
		 }
		 return true;  
	 }
//??????,????????????????2???????????¼sliding window??????????¼??????????????????
	 //?????????????????????????substring???????????????????????????????????
	 //???????¼sliding window?????.?ú?????
	 public ArrayList<ArrayList<String>> partition2(String s) {
		 ArrayList<ArrayList<String>> al=new ArrayList<ArrayList<String>>();
		 ArrayList<String> a=new ArrayList<String>();
		 if(s.length()==0){
			 return al;
		 }
		
			 check(0,s,a,al);
		 
		 return al;
	 }
	 private void check(int b,String s,ArrayList<String> a,ArrayList<ArrayList<String>> al){
		
		 if(b>=s.length()){
			 al.add(new ArrayList<String>(a));
		 }
			 for(int i=1;i<=s.length()-b;i++){
				 if(valid2(s.substring(b,b+i))){
					 a.add(s.substring(b,b+i));
					 check(b+i,s,a,al);
                     a.remove(a.size()-1);
                     
				 }
		 } 
	 }
	 private boolean valid2(String s){
		 if(s.length()==1){
			 return true;
		 }
		 int i=0;
		 int j=s.length()-1;
		 while(i<j){
			 if(s.charAt(i)!=s.charAt(j)){
				 return false;
			 }
			 i++;
			 j--;
		 }
		 return true;
	 }
	 
	 //code ganker??
	 public ArrayList<ArrayList<String>> partition3(String s) {  
	    ArrayList<ArrayList<String>> res = new ArrayList<ArrayList<String>>();  
	    if(s==null || s.length()==0)  
	        return res;  
	    helper2(s, getDict(s),0,new ArrayList<String>(), res);  
	    return res;  
	}  
	private void helper(String s, boolean[][] dict, int start, ArrayList<String> item, ArrayList<ArrayList<String>> res)  
	{  
	    if(start==s.length())  
	    {  
	        res.add(new ArrayList<String>(item));  
	        return;  
	    }  
	    for(int i=start;i<s.length();i++)  
	    {  
	        if(dict[start][i])  
	        {  
	            item.add(s.substring(start,i+1));  
	            helper(s,dict,i+1,item,res);  
	            item.remove(item.size()-1);  
	        }  
	    }  
	}  
	//????????????helper,?????,index????????????????????al?????????????????begin???
	//????????a.size???
	private void helper2(String s, boolean[][] memo, int b, ArrayList<String> a, ArrayList<ArrayList<String>> al)  
	{  
	    if(b==s.length()){
	    	al.add(new ArrayList<String>(a));
	    	return;
	    }
	    for(int i=0;i<s.length()-b;i++){//index????????memo??substring????????memo[i][i]
	    	if(memo[b][b+i]){        //???????????????substring??i??i???????
	    		a.add(s.substring(b,b+i+1));
	    		helper2(s,memo,b+i+1,a,al);
	    		a.remove(a.size()-1);
	    	}
	    	
	    }
	}  
	private boolean[][] getDict(String s)  //???????????????longest palindromic substring
	{                                     //????????????????????????????  
	    boolean[][] dict = new boolean[s.length()][s.length()];  
	    for(int i=s.length()-1;i>=0;i--)  
	    {  
	        for(int j=i;j<s.length();j++)  
	        {  
	            if(s.charAt(i)==s.charAt(j) && ((j-i<2)||dict[i+1][j-1]))  
	            {  
	                dict[i][j] = true;  
	            }  
	        }  
	    }  
	    return dict;  
	}  
	
	//30/9
	public ArrayList<ArrayList<String>> partition4(String s) {  
		ArrayList<ArrayList<String>> al = new ArrayList<ArrayList<String>>();
		ArrayList<String> a=new ArrayList<String>();
		if(s.length()==0){
			return al;
		}
		boolean[][] dp=new boolean[s.length()][s.length()];
		for(int i=0;i<s.length();i++){
			dp[i][i]=true;
			if(i>0&&s.charAt(i)==s.charAt(i-1)){
				dp[i-1][i]=true;
			}
		}
		for(int i=3;i<=s.length();i++){
			for(int j=0;j<=s.length()-i;j++){
				int k=i+j;
				if(s.charAt(j)==s.charAt(k-1)&&dp[j+1][k-1-1]){
					dp[j][k-1]=true;
				}
			}
		}
		
		go2(0,s,a,al,dp);
		return al;
		
	}
	private void go2(int b,String s,ArrayList<String> a,
			ArrayList<ArrayList<String>> al,boolean[][] memo){
		if(b==s.length()){
			al.add(new ArrayList<String>(a));
			return;
		}
		for(int i=b;i<s.length();i++){
			if(memo[b][i]){
				a.add(s.substring(b,i+1));
				go2(i+1,s,a,al,memo);
				a.remove(a.size()-1);
			}
		}
		
	}

}
