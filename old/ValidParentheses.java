package old;
//http://blog.csdn.net/linhuanmars/article/details/19800789
import java.util.LinkedList;
//���У������������ģ����򶼲���stack��
public class ValidParentheses {
	
	public boolean isValid(String s) {
		if(s==null){
			return true;
		}
		if(s.length()%2!=0){
		    return false;
		}
		LinkedList<Character> stack=new LinkedList<Character>();
		for(int i=0;i<s.length();i++){
			if(s.charAt(i)=='('||s.charAt(i)=='['||s.charAt(i)=='{'){
			    stack.push(s.charAt(i));
			    continue;
			}
			if(s.charAt(i)==')'){
				if(stack.isEmpty()||stack.pop()!='('){
					return false;
				}
			}
			if(s.charAt(i)==']'){
				if(stack.isEmpty()||stack.pop()!='['){
					return false;
				}
			}
			if(s.charAt(i)=='}'){
				if(stack.isEmpty()||stack.pop()!='{'){
					return false;
				}
			}
		}
		if(!stack.isEmpty()){
		    return false;
		}
		return true;

	}
	//�����֣�
	 public boolean isValid2(String s) {
		 if(s.length()==0){
			 return true;
		 }
		 LinkedList<Character> st=new LinkedList<Character>();
		 for(int i=0;i<s.length();i++){
			 if(s.charAt(i)==')'){
				 if(st.isEmpty()||st.peek()!='('){
					 return false;
				 }
				 st.pop();
				 continue;
			 }
             if(s.charAt(i)==']'){
            	 if(st.isEmpty()||st.peek()!='['){
					 return false;
				 }
				 st.pop();
				 continue;
			 }
             if(s.charAt(i)=='}'){
            	 if(st.isEmpty()||st.peek()!='{'){
					 return false;
				 }
				 st.pop();
				 continue;
             }
            
            	 st.push(s.charAt(i));
             
			 
		 }
		 
		 return st.isEmpty();
	    }
	
      
}
