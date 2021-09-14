import java.util.*;

public class MinimumRemovetoMakeValidParentheses {
    //7/24/2021。按RemoveInvalidParentheses写的会超时.由于是只要找一个，所以可以O（n）的解法，见答案解释（ipad上bytedance）。
    //https://www.youtube.com/watch?v=thL70BR3yMA
    public String minRemoveToMakeValid(String s) {
        StringBuilder sb=new StringBuilder();
        char[] ch=s.toCharArray();
        int left=0;
        for (int i=0;i<ch.length;i++){
            if (ch[i]=='('){
                left++;
                sb.append(ch[i]);
            }else if (ch[i]==')'){
                if (left==0){
                    continue;
                }
                sb.append(')');
                left--;
            }else {
                sb.append(ch[i]);
            }
        }
        if(left==0){//可以直接返回了
            return sb.toString();
        }
        String rs=sb.toString();
        sb=new StringBuilder();
        int right=0;
        for (int i=rs.length()-1;i>=0;i--){
            if (rs.charAt(i)==')'){
                right++;
                sb.append(rs.charAt(i));
            }else if (rs.charAt(i)=='('){
                if (right==0){
                    continue;
                }
                sb.append('(');
                right--;
            }else {
                sb.append(rs.charAt(i));
            }
        }
        return sb.reverse().toString();
    }

    //8/27/2021 用的dq加int数组
    public String minRemoveToMakeValid2(String s) {
        Deque<Integer> dq=new LinkedList<>();
        int[] a=new int[s.length()];
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='('){
                dq.push(i);
            }else if(s.charAt(i)==')'&&!dq.isEmpty()){
                dq.pop();
            }else if(s.charAt(i)==')'){
                a[i]=1;
            }
        }

        for(int i=0;i<s.length();i++){
            if(!dq.isEmpty()&&dq.peekLast()==i){
                dq.pollLast();
            }else if(a[i]==0){
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }

}
