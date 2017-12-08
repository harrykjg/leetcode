import java.util.LinkedList;
//http://blog.csdn.net/linhuanmars/article/details/21058857
//http://jixiangsanbao.wordpress.com/2014/07/22/evaluate-reverse-polish-notation/

//������Ǽٶ�����ȫ���ǺϷ��ģ�û���������ʽ�ļ��
public class EvaluateReversePolishNotation {
	public int evalRPN(String[] tokens) {
		if(tokens.length==0){
			return 0;
		}
		LinkedList<Integer> st=new LinkedList<Integer>();
		for(int i=0;i<tokens.length;i++){
			if(tokens[i].equals("+")){//ע��������string��������equals����
				int one=st.pop();
				int two=st.pop();
				st.push(one+two);
			}
			else if(tokens[i].equals("-")){
				int one=st.pop();
				int two=st.pop();
				st.push(two-one);
			}
			else if(tokens[i].equals("*")){
				int one=st.pop();
				int two=st.pop();
				st.push(one*two);
			}
			else if(tokens[i].equals("/")){
				int one=st.pop();
				int two=st.pop();
				st.push(two/one);
			}
			else{
				st.push(Integer.parseInt(tokens[i]));
			}
		}
		return st.pop();
	}
	//�ڶ���дҲһ��ACCept
	public int evalRPN2(String[] tokens) {
		if(tokens.length==0){
			return 0;
		}
		LinkedList<String> stack=new LinkedList<String>();
		int i=0;
		while(i<tokens.length){
			if(tokens[i].equals("+")){
				int i1=Integer.parseInt(stack.pop());
				int i2=Integer.parseInt(stack.pop());
				stack.push(""+(i1+i2));
				i++;
				continue;
			}
			else if(tokens[i].equals("-")){
				int i1=Integer.parseInt(stack.pop());
				int i2=Integer.parseInt(stack.pop());
				stack.push(""+(i2-i1));
				i++;
				continue;
			}
			else if(tokens[i].equals("*")){
				int i1=Integer.parseInt(stack.pop());
				int i2=Integer.parseInt(stack.pop());
				stack.push(""+(i1*i2));
				i++;
				continue;
			}
			else if(tokens[i].equals("/")){
				int i1=Integer.parseInt(stack.pop());
				int i2=Integer.parseInt(stack.pop());
				stack.push(""+(i2/i1));
				i++;
				continue;
			}
			else{
				stack.push(tokens[i]);
				i++;
			}
		}
		return Integer.parseInt(stack.pop());
	}

}
