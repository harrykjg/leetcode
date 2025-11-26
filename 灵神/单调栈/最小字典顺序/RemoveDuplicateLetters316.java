package 灵神.单调栈.最小字典顺序;

import java.util.*;

public class RemoveDuplicateLetters316 {
    public static void main(String[] args) {
        System.out.println(removeDuplicateLetters("abacb"));
    }
    //没想出来,以为最多要一个set或者map就行，结果set和map都要
    //https://leetcode.cn/problems/remove-duplicate-letters/solutions/2381483/gen-zhao-wo-guo-yi-bian-shi-li-2ni-jiu-m-zd6u/
    public static String removeDuplicateLetters(String s) {
        char[] ch=s.toCharArray();
        Map<Character,Integer> map=new HashMap();
        Set<Character> set=new HashSet<>();
        Stack<Character> st=new Stack<>();
        for(int i=0;i<s.length();i++){
            map.put(ch[i],map.getOrDefault(ch[i],0)+1);
        }
        for(int i=0;i<ch.length;i++){
            map.put(ch[i],map.get(ch[i])-1);
            if(st.isEmpty()){
                st.push(ch[i]);
                set.add(ch[i]);
                continue;
            }
            if(set.contains(ch[i])){//这里不太好理解，遇到重复的直接略过，为啥不能是前面的不加而加后面使得整个string更小呢。可能是因为前面大的char会被更小的char pop出来，拿cbacdcbc看
                continue;
            }
            while (!st.isEmpty()&&st.peek()>ch[i]&&map.get(st.peek())>0){
                set.remove(st.pop());
            }

            st.push(ch[i]);
            set.add(ch[i]);

        }
        StringBuilder sb=new StringBuilder();
        while (!st.isEmpty()){
            sb.append(st.pop());
        }
        return sb.reverse().toString();
    }
}
