import java.util.LinkedList;
import java.util.Stack;
//http://blog.csdn.net/linhuanmars/article/details/20439613
//http://jixiangsanbao.wordpress.com/2014/04/29/longest-valid-parentheses/
//http://blog.csdn.net/a83610312/article/details/8639790
//http://www.cnblogs.com/lichen782/archive/2013/07/06/leetcode_Longest_Valid_Parentheses.html
//"()(()"这样也只算2，即要连续的."(()())"这样算合法的，即6
//这题做的不好，不看他们的答案估计也修修改改很久
public class LongestValidParentheses {
	public static void main(String[] args) {
		LongestValidParentheses lv=new LongestValidParentheses();
		System.out.println(lv.longestValidParentheses("))(((()(()))"));
	}
	public int longestValidParentheses(String s) {
        if(s.length()==0){
            return 0;
        }
        int begin=0;
        LinkedList<Integer> st=new LinkedList<Integer>();
        int max=0;
        for(int i=0;i<s.length();i++){
           if(s.charAt(i)=='('){
        	   st.push(i);//存的是（的位置
           }
           else{
        	   if(st.isEmpty()){//如果遇到了“）”且栈空了，即没有匹配的（，即连续合法的括号告一段落了，
        		   begin=i+1;// 后面的话就要从他的下一个开始才有可能是合法的
        	   }
        	   else{//如果遇到了“）”，且栈没空
        		   st.pop();//先pop一个出来
        		   if(st.isEmpty()){//如果栈空了，说明这个pop出来的括号刚好和“）”匹配，i-begin+1
        			   max=Math.max(max, i-begin+1);//即如果前面还有合法的连续的括号，这样写
        			                             //即把前面的和当前这个匹配的括号也包括进来了
        		   }else{//如果pop出来一个之后，栈里还有括号就说明当前这个“）”只能匹配到已经pop
        			   //出来的这个“（”，但是他已经pop出来了，所以只能用他前一个“（”，即栈里peek
        			   //出来的这个来确定最大长度
        			   //这里不太好理解，比如说（（（）），等i到了最后一个）时，第2和3个（已经pop
        			   //出来了，那可以看到最大长度就是第一个（的位置开始到i。
        			   //如果是））（（））的话，到了最后一个），此时第一个（已经pop出来了，即栈为
        			   //空，则用i-begin+1来确定最大长度
        			   //再比如（）（（（））这个例子，到了最后一个），此时st不为空，则用第二个（的
        			   //位置来确定最大长度。再比如
        			   max=Math.max(max, i-st.peek());
        		   }
               }
           }
           
        }
        return max;
    }

	//第二次还是不会的
	//第三轮还是不会
	public int longestValidParentheses2(String s) {
		if(s.length()==0){
            return 0;
        }
		int start=0;
		int max=0;
		LinkedList<Integer> st=new LinkedList<Integer>();
		for(int i=0;i<s.length();i++){
			if(s.charAt(i)=='('){
				st.push(i);
			}else{
				if(st.isEmpty()){
					start=i+1;
					continue;
				}
				int temp=st.pop();
				if(st.isEmpty()){
					max=Math.max(max, i-start+1);
				}else{
					max=Math.max(max, i-st.peek());
				}
			}
		}
		return max;
		
	}

}
