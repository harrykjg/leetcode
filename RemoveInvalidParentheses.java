import java.util.*;

/**
 * Created by yufengzhu on 11/1/18.
 */
public class RemoveInvalidParentheses {
    public static void main(String[] args){
        RemoveInvalidParentheses ri=new RemoveInvalidParentheses();
        ri.removeInvalidParentheses3(")(");
    }
//以前的代码
    public static List<String> removeInvalidParentheses(String s) {
        List<String> rs=new ArrayList<String>();
        if(s==null||s.length()==0){
            rs.add("");
            return rs;
        }
        s=s.trim();
        LinkedList<String> q=new LinkedList<String>();
        Set<String> q2=new HashSet<String>();
        q.add(s);
        while(!q.isEmpty()){
            String temp=q.poll();
            if(valid(temp)){
                rs.add(temp);
            }else if(rs.isEmpty()){//我想的是，如果rs里有结果了，说明不需要再删一个符号再看他们是不是valid了，因为题目要的是
                //Remove the minimum number of invalid parentheses
                for(int i=0;i<temp.length();i++){
                    if(i>0&&temp.charAt(i)==temp.charAt(i-1)){//比如（（（））删第一，2,3个都一样，所以删了第一个的话，第
                        continue;             //2,3个也不用删了
                    }
                    if(Character.isAlphabetic(temp.charAt(i))){//如果是字母的话也不删
                        continue;
                    }
                    String s1=temp.substring(0,i);
                    String s2=temp.substring(i+1);
                    String s3=s1+s2;
                    q2.add(s3);
                }
            }
            if(q.isEmpty()&&rs.isEmpty()){
                q.addAll(q2);//注意写成q=q2然后q2.clear是不行的，q2clear之后q也是空了
                q2.clear();
            }
        }

        return rs;
    }
    //好像题目的括号只有小括号
    public static boolean valid(String s){
        if(s.length()==0){
            return true;
        }
        s=s.trim();
        Stack<Character> st=new Stack<Character>();
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='('){
                st.push('(');
                continue;
            }
            if(s.charAt(i)=='{'){
                st.push('{');
                continue;
            }
            if(s.charAt(i)=='['){
                st.push('[');
                continue;
            }
            if(s.charAt(i)==')'){
                if(st.isEmpty()){
                    return false;
                }
                if(st.pop()!='('){
                    return false;
                }
                continue;
            }
            if(s.charAt(i)==']'){
                if(st.isEmpty()){
                    return false;
                }
                if(st.pop()!='['){
                    return false;
                }
                continue;
            }
            if(s.charAt(i)=='}'){
                if(st.isEmpty()){
                    return false;
                }
                if(st.pop()!='{'){
                    return false;
                }
                continue;
            }

        }
        return st.isEmpty();
    }

    //11／1／2018,这个写法超时，思路是先算出这个字符串最少要删几个，然后按着这个去dfs并找到valid的就行了。复杂度是2的gap次方，还是要看别人的解法
    public List<String> removeInvalidParentheses2(String s) {
        List<String> rs=new ArrayList<>();
        if(s.length()==0){
            rs.add(s);
            return rs;
        }
        char[] ch=s.toCharArray();
        int left=0;
        int right=0;
        int gap=0;
        for(int i=0;i<ch.length;i++){
            if(ch[i]=='('){
                left++;
            }else if(ch[i]==')'){
                if(left>0){
                    left--;
                }else{
                    gap++;
                }
            }
        }
        gap+=left;

        if(gap==0&&valid2(s)){
            rs.add(s);
            return rs;
        }
        HashSet<String> memo=new HashSet<>();

        helper(s,gap,memo,rs);

        return rs;

    }

    void helper(String s,int gap,HashSet<String> memo,List<String> rs){
        if(gap==0&&valid2(s)&&memo.add(s)){
            rs.add(s);
            return;
        }
        if(gap==0){
            return;
        }
        for(int i=0;i<s.length();i++){
            if(i>0&&s.charAt(i-1)==s.charAt(i)){
                continue;
            }
            if(Character.isAlphabetic(s.charAt(i))){
                continue;
            }
            StringBuilder sb=new StringBuilder(s);
            sb.deleteCharAt(i);
            String temp=sb.toString();
            helper(temp,gap-1,memo,rs);
        }
    }
    boolean valid2(String s){
        if(s.length()==0){
            return true;
        }
        Stack<Integer> st=new Stack<>();
        char[] ch=s.toCharArray();
        int i=0;
        while (i<ch.length){
            if(ch[i]=='('){
                st.push(1);
                i++;
                continue;
            }
            if(ch[i]==')'){
                if(st.isEmpty()){
                    return false;
                }
                i++;
                st.pop();
                continue;
            }
            i++;
        }
        return st.isEmpty()?true:false;
    }

    //https://leetcode.com/problems/remove-invalid-parentheses/discuss/75038/Evolve-from-intuitive-solution-to-optimal-a-review-of-all-solutions
    //是用以前的方法改写的，就记这个
    public List<String> removeInvalidParentheses3(String s) {
        List<String> rs=new ArrayList<String>();
        if(s==null||s.length()==0){
            rs.add("");
            return rs;
        }
        s=s.trim();
        LinkedList<String> q=new LinkedList<String>();
        Set<String> set=new HashSet<String>();//开始写的set在while里面每次new一个，那样就不能保证去重了
        q.add(s);
        while(!q.isEmpty()){
            String temp=q.poll();
            if(valid(temp)){
                rs.add(temp);
            }else if(rs.isEmpty()){//我想的是，如果rs里有结果了，说明不需要再删一个符号再看他们是不是valid了，因为题目要的是
                //Remove the minimum number of invalid parentheses
                StringBuilder sb=null;

                for(int i=0;i<temp.length();i++){
                    if(i>0&&temp.charAt(i)==temp.charAt(i-1)){//比如（（（））删第一，2,3个都一样，所以删了第一个的话，第
                        continue;             //2,3个也不用删了。尽管这样还是不能保证能去重
                    }
                    if(Character.isAlphabetic(temp.charAt(i))){//如果是字母的话也不删
                        continue;
                    }
                    sb=new StringBuilder(temp);
                    sb.deleteCharAt(i);
                    if(set.add(sb.toString())){
                        q.add(sb.toString());
                    }
                }
            }
        }

        return rs;
    }


}
