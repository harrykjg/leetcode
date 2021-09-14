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

    //7/7/2021只能想的暴力找出最小的删法再去dfs找所有可能。
    //其实看最上面这个解法也行了，就是bfs，q里poll出来的string如果不valid的话，在它的基础上把删除1个括号的所有可能都弄出来，放到q里，q里poll出
    // 来的每个都检查是否valid，valid的放进result里。若result不为空的话，就算当前poll出来不valid也不会在基于他去再删括号了
    //https://leetcode.com/problems/remove-invalid-parentheses/discuss/75032/Share-my-Java-BFS-solution
    public List<String> removeInvalidParentheses4(String s) {
        List<String> rs=new ArrayList<>();
        Queue<String> q=new LinkedList<>();
        HashSet<String> set=new HashSet<>();
        q.offer(s);
        while (!q.isEmpty()){
            String cur=q.poll();
            if (valid4(cur)){
                rs.add(cur);
            }else if (rs.isEmpty()){
                String temp="";
                for (int i=0;i<cur.length();i++){
                    if (Character.isAlphabetic(cur.charAt(i))){
                        continue;
                    }
                    if (i==0){
                        temp=cur.substring(i+1);
                    }else {
                        temp=cur.substring(0,i)+cur.substring(i+1);
                    }
                    if (!set.contains(temp)){
                        set.add(temp);
                        q.offer(temp);
                    }
                }
            }
        }
        return rs;
    }
    boolean valid4(String s){
        if (s.length()==0){
            return true;
        }
        Stack<Character> st=new Stack<>();
        for (int i=0;i<s.length();i++){
            if (s.charAt(i)=='('){
                st.push('(');
            }
            if (s.charAt(i)=='{'){
                st.push('{');
            }
            if (s.charAt(i)=='['){
                st.push('[');
            }
            if (s.charAt(i)==')'){
                if (st.isEmpty()||st.peek()!='(') {
                    return false;
                }
                st.pop();
            }
            if (s.charAt(i)==']'){
                if (st.isEmpty()||st.peek()!='[') {
                    return false;
                }
                st.pop();
            }
            if (s.charAt(i)=='}'){
                if (st.isEmpty()||st.peek()!='{') {
                    return false;
                }
                st.pop();
            }
        }
        return st.isEmpty();
    }
//8/25/2021 忘了用set去重，想着反正是一个一个删除就不会有重复的，其实不是的，有些string你删这个和删那个得出来的是一样的
    public  List<String> removeInvalidParentheses5(String s) {
        List<String> rs=new ArrayList<>();
        Queue<String> q=new LinkedList<>();
        HashSet<String> set=new HashSet<>();
        q.offer(s);
        set.add(s);
        while (!q.isEmpty()){
            int size=q.size();
            for (int i=0;i<size;i++){
                String cur=q.poll();
                if (valid5(cur)){
                    rs.add(cur);
                }else {
                    for (int j=0;j<cur.length();j++){
                        if (Character.isAlphabetic(cur.charAt(j))){
                            continue;
                        }
                        String next=cur.substring(0,j)+cur.substring(j+1);
                        if (!set.contains(next)){
                            q.offer(next);
                            set.add(next);
                        }
                    }
                }
            }
            if (rs.size()!=0){
                break;
            }
        }
        return rs;
    }
    boolean valid5(String s){
        char[] ch=s.toCharArray();
        int left=0;
        for (int i=0;i<ch.length;i++){
            if (ch[i]=='('){
                left++;
            }else if (ch[i]==')'){
                if (left<=0){
                    return false;
                }
                left--;
            }
        }
        return left==0;
    }

}
