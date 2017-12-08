import java.util.ArrayList;


/*Given a string s, partition s such that every substring of the partition is a palindrome. 

Return all possible palindrome partitioning of s. 

For example, given s = "aab",
Return 

  [
    ["aa","b"],
    ["a","a","b"]
  ]


*/
//不知道我写的是啥。。可能是个试验？
public class palindrome2 {
	
	
	public static void main(String[] args) {
		
		
		String s="abaabacdck";
		Solution aa=new Solution();
		
		ArrayList<ArrayList<String>>bb=aa.partition(s);
		while(!bb.isEmpty()){
			System.out.println(bb.remove(0));
		}
		
		
	}

	
}
class Solution{

    public ArrayList<ArrayList<String>> partition(String s) {
        
        ArrayList<ArrayList<String>> a=new ArrayList<ArrayList<String>>();
        int i=0;
        int j=s.length();
        
        while(i<j){
            if(null!=xx(s.substring(i,j))){
               ArrayList<String> b=xx(s.substring(i,j));
               a.add(b);
            }
            i++;
           
        }
        return a;
    }
    
    private ArrayList<String> xx(String s){
    	ArrayList<String> a1=new ArrayList<String>();
        int i=0;
        int j=s.length();
        boolean flag=false;
        while(i<j){
        	if(j==1){
        		String s1=s.substring(i,j);
                a1.add(s1);
                break;
        	}
            
            
           for(int k=0;k<(j/2);k++){
               if(s.charAt(k)!=s.charAt(j-1-k)){
                   flag=false;
                   break;
               }
               else{
                   flag=true;
               }
            
           }
           
           if(flag==true){
               
               String s1=s.substring(i,j);
               a1.add(s1);
           }
           
           j--;
        }
        return a1;
    }
    

}