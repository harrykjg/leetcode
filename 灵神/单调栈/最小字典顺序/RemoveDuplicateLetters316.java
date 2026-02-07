package 灵神.单调栈.最小字典顺序;

import java.util.*;

public class RemoveDuplicateLetters316 {
    public static void main(String[] args) {
        System.out.println(removeDuplicateLetters2("abacb"));
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
    //1/22/2026还是没想到怎么做，思想就是维护一个单调栈，目的就是可以比较当前字符和栈顶元素，如果栈顶大的就pop掉，维持一个尽可能小的顺序，
    //但是凭啥可以pop呢，如果pop了之后少了这个字符咋办，因此就要预先统计一下这个将被pop的字符在后面还有没有出现，有的话就可以pop，那么
    //如何记录一个字符在某个位置之后还有没有呢，那就用一个map记录出现的次数，遍历到的时候就减减.并且用了单调栈，后面再进来一个已经在栈里的元素
    //也不让他进了，比如bcb，遇到第二个b时，用一个set记录他已经出现过了，并且由于是单调栈，说明他第一次出现的时候已经是在字典顺序小的位置了
    public static String removeDuplicateLetters2(String s) {
        Stack<Character> st=new Stack<>();
        char[] ch=s.toCharArray();
        Map<Character,Integer> map=new TreeMap<>();
        for (int i=0;i<ch.length;i++){
            map.put(ch[i],map.getOrDefault(ch[i],0)+1);
        }
        Set<Character> set=new HashSet<>();
        for (int i=0;i<ch.length;i++){
            if(st.isEmpty()||(st.peek()<ch[i]&&!set.contains(ch[i]))){
                st.push(ch[i]);
                set.add(ch[i]);
                map.put(ch[i],map.get(ch[i])-1);
                continue;
            }
            map.put(ch[i],map.get(ch[i])-1);//这个容易漏
            if(set.contains(ch[i])){
                continue;
            }
            while (!st.isEmpty()&&st.peek()>ch[i]&&map.get(st.peek())>0){
                set.remove(st.pop());
            }
//            if(!set.contains(ch[i])){ //这里检测已经在set里的必须放在while前面,否则看abacb这个例子，第二个a时会运行while循环把b弹出去
            st.push(ch[i]);          //这样的话答案变成acb，其实答案应该是abc，这个例子很难想到
            set.add(ch[i]);
//            }
        }
        StringBuilder sb=new StringBuilder();
        while (!st.isEmpty()){
            sb.append(st.pop());
        }
        return sb.reverse().toString();
    }
}
