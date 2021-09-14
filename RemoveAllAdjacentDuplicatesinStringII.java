import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

public class RemoveAllAdjacentDuplicatesinStringII {
    public static void main(String[] args){
        RemoveAllAdjacentDuplicatesinStringII ra=new RemoveAllAdjacentDuplicatesinStringII();
        System.out.println(ra.removeDuplicates("deeedbbcccbdaa",3));
    }
    //8/19/2021 直接按k为窗口移动，然后递归的就超时了。
    //https://www.youtube.com/watch?v=bJOA_OoWSg4  居然是用stack的
    //https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/discuss/392939/PythonC%2B%2BJava-Stack-Based-Solution-Clean-and-Concise 看他下面第一个回复的写法
    public String removeDuplicates(String s, int k) {//这个是暴力法超时
        if (k>s.length()){
            return s;
        }
        Map<Character,Integer> map=new HashMap<>();
        char[] ch=s.toCharArray();
        StringBuilder sb=new StringBuilder(s);
        for (int i=0;i+k-1<s.length();i++){
            int j=i;
            for (j=i;j<i+k;j++){
                map.put(ch[j],j);
            }
            if (map.size()==1){
                sb=sb.delete(i,i+k);
                return removeDuplicates(sb.toString(),k);
            }else {
                map.clear();
            }
        }
        return s;
    }
//用stack的
    public String removeDuplicates2(String s, int k) {
        if (k>s.length()){
            return s;
        }
        Stack<Pair> st=new Stack<>();
        char[] ch=s.toCharArray();
        for (int i=0;i<ch.length;i++){//stack记录的是某个字符及其连续出现的次数
            if (st.isEmpty()||st.peek().c!=ch[i]){
                st.push(new Pair(ch[i],1));
            }else{
                st.peek().count++;
            }
            if (st.peek().count==k){
                st.pop();
            }
        }
        StringBuilder sb=new StringBuilder();
        while (!st.isEmpty()){
            Pair p=st.pop();
            for (int i=0;i<p.count;i++){
                sb.append(p.c);
            }
        }
        return sb.reverse().toString();
    }
    class Pair{
        char c;
        int count;
        public Pair(char c,int count){
            this.c=c;
            this.count=count;
        }
    }
}
