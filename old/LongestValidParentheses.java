import java.util.LinkedList;
import java.util.Stack;
//http://blog.csdn.net/linhuanmars/article/details/20439613
//http://jixiangsanbao.wordpress.com/2014/04/29/longest-valid-parentheses/
//http://blog.csdn.net/a83610312/article/details/8639790
//http://www.cnblogs.com/lichen782/archive/2013/07/06/leetcode_Longest_Valid_Parentheses.html
//"()(()"����Ҳֻ��2����Ҫ������."(()())"������Ϸ��ģ���6
//�������Ĳ��ã��������ǵĴ𰸹���Ҳ���޸ĸĺܾ�
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
        	   st.push(i);//����ǣ���λ��
           }
           else{
        	   if(st.isEmpty()){//��������ˡ�������ջ���ˣ���û��ƥ��ģ����������Ϸ������Ÿ�һ�����ˣ�
        		   begin=i+1;// ����Ļ���Ҫ��������һ����ʼ���п����ǺϷ���
        	   }
        	   else{//��������ˡ���������ջû��
        		   st.pop();//��popһ������
        		   if(st.isEmpty()){//���ջ���ˣ�˵�����pop���������Ÿպú͡�����ƥ�䣬i-begin+1
        			   max=Math.max(max, i-begin+1);//�����ǰ�滹�кϷ������������ţ�����д
        			                             //����ǰ��ĺ͵�ǰ���ƥ�������Ҳ����������
        		   }else{//���pop����һ��֮��ջ�ﻹ�����ž�˵����ǰ���������ֻ��ƥ�䵽�Ѿ�pop
        			   //������������������������Ѿ�pop�����ˣ�����ֻ������ǰһ������������ջ��peek
        			   //�����������ȷ����󳤶�
        			   //���ﲻ̫����⣬����˵��������������i�������һ����ʱ����2��3�����Ѿ�pop
        			   //�����ˣ��ǿ��Կ�����󳤶Ⱦ��ǵ�һ������λ�ÿ�ʼ��i��
        			   //����ǣ������������Ļ����������һ��������ʱ��һ�����Ѿ�pop�����ˣ���ջΪ
        			   //�գ�����i-begin+1��ȷ����󳤶�
        			   //�ٱ��磨������������������ӣ��������һ��������ʱst��Ϊ�գ����õڶ�������
        			   //λ����ȷ����󳤶ȡ��ٱ���
        			   max=Math.max(max, i-st.peek());
        		   }
               }
           }
           
        }
        return max;
    }

	//�ڶ��λ��ǲ����
	//�����ֻ��ǲ���
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
