package DataStruct.Stack;


import com.sun.tools.javac.util.StringUtils;

import java.util.*;

/**
 * Created by yufengzhu on 7/8/18.
 */
public class NumberofAtoms {
    public static void main(String[] a){
        NumberofAtoms na=new NumberofAtoms();
        System.out.println(na.countOfAtoms2("Mg(H2O)N"));//"K4(ON(SO3)2)2
    }
    //和decodestring很像,输出的排序就是按字母的自然顺序拍出来的，和化学表达式的顺序没关系
    //自己想的，居然还比较顺，改了几次就过了，用K4(ON(SO3)2)2 和"H2O"举例子就行了
    public String countOfAtoms(String formula) {
        Stack<Node> st=new Stack<>();
        int i=0;
        StringBuilder rs=new StringBuilder();
        Map<String,Integer> map=new TreeMap<>();
        char[] ch=formula.toCharArray();
        while (i<formula.length()){
            if(ch[i]=='('){
                st.push(new Node("(",1));//如果是左括号则加入stack，作为一个标志，遇到右括号时stack就要一直弹直到谈到左括号为止
                i++;
                continue;
            }
            if(ch[i]==')'){//开始弹栈
                ArrayList<Node> temp=new ArrayList<>();
                i++;
                int num=0;//num就是这个右括号外面的数字，把他乘以stack里直到左括号的所有的node的count，这一对弹出来的node先放在temp数组里，弹完之后再放回stack里
                while (i<ch.length&&ch[i]-'0'>=0&&ch[i]-'9'<=0){
                    num=num*10+ch[i]-'0';
                    i++;
                }
                while (!st.isEmpty()){
                    Node n=st.pop();
                    if(n.val=="("){
                        break;
                    }
                    n.count*=num;
                    temp.add(n);
                }
                for(Node n:temp){
                    st.push(n);
                }
                continue;
            }
            //是字母的情况
            StringBuilder val=new StringBuilder();
            if(i<ch.length&&ch[i]-'0'<0||ch[i]-'9'>0){//由于input是valid的，所以第一个肯定是字母
                val.append(ch[i]);
                i++;
            }
            //第二个有可能是小写字母或者另一个大写字母，或者是括号，这里只处理是小写字母的情况，容易漏ch[i]!='('&&ch[i]!=')'，因为'（'也是小写字母！
            while (i<ch.length&&(ch[i]-'0'<0||ch[i]-'9'>0)&&Character.isLowerCase(ch[i])&&ch[i]!='('&&ch[i]!=')'){
                val.append(ch[i]);
                i++;
            }

            int count=0;//算这个数字
            while (i<ch.length&&ch[i]-'0'>=0&&ch[i]-'9'<=0){
                count=count*10+ch[i]-'0';
                i++;
            }
            Node n=new Node(val.toString(),count==0?1:count);
            st.push(n);
        }
        while (!st.isEmpty()){
            Node n=st.pop();
            if(!map.containsKey(n.val)){
                map.put(n.val,n.count);
            }else{
                map.put(n.val,n.count+map.get(n.val));//这里容易漏，合并已经有的元素的count
            }
        }
        for(String s:map.keySet()){
            rs.append(s);
            if(map.get(s)!=1){//这里也容易漏，如果count是1就不用写1了
                rs.append(map.get(s));
            }
        }
        return rs.toString();

    }
    class Node{//这个node存的就是这个化学符号和其对应的count
        String val;
        int count;
        public Node(String val, int count){
            this.val=val;
            this.count=count;
        }
    }

    //7/25/2021 还是写不出.参考别人之后自己想的，改了好几次
    // 思路就是st里放的是一个map，存的是这个左括号后面的字符和数量，（A2（N2)2X(A2))2,比如现在遇到第一个左括号，把有A2的map放进stack里了，然后遇到第二个左括号
    //把n2放进stack里，然后遇到第一个右括号，则把n2这个map pop出来，乘以2之后，再于栈顶的存的A2的map融合，这样才对。后遇到x，也得把x加入栈顶的map，栈顶没有map的话
    //才说明是不再括号里的。
    //https://leetcode.com/problems/number-of-atoms/discuss/109345/Java-Solution-using-Stack-and-Map 和他写的不一样 看不太懂
    public String countOfAtoms2(String formula) {
        Stack<Map<String,Integer>> st=new Stack<>();
        Map<String,Integer> map=new TreeMap<>();
        char[] ch=formula.toCharArray();
        int i=0;
        while (i<ch.length){
            if (ch[i]=='('){
                i++;
                Map<String,Integer> m=new HashMap<>();
                while (i<ch.length){
                    if (ch[i]=='('||ch[i]==')'){
                        break;
                    }
                    String key=String.valueOf(ch[i]);
                    i++;
                    while (i<ch.length&&Character.isLowerCase(ch[i])){
                        key+=ch[i];
                        i++;
                    }
                    int val=0;
                    while (i<ch.length&&Character.isDigit(ch[i])){
                        val=val*10+ch[i]-'0';
                        i++;
                    }
                    if (val==0){
                        val=1;
                    }
                    m.put(key,m.getOrDefault(key,0)+val);
                }
                st.push(m);
            }else if (ch[i]==')'){
                i++;
                int num=0;
                while (i<ch.length&&Character.isDigit(ch[i])){
                    num=num*10+ch[i]-'0';
                    i++;
                }
                if (num==0){
                    num=1;
                }
                Map<String,Integer> last=st.pop();
                for (String key:last.keySet()){
                    last.put(key,last.get(key)*num);
                }
                if (!st.isEmpty()){
                    Map<String,Integer> pre=st.peek();
                    for(String key:last.keySet()){
                        if (!pre.containsKey(key)){
                            pre.put(key,last.get(key));
                        }else{
                            pre.put(key,last.get(key)+pre.get(key));
                        }
                    }
                }else {
                    for(String key:last.keySet()){
                        if (!map.containsKey(key)){
                            map.put(key,last.get(key));
                        }else{
                            map.put(key,last.get(key)+map.get(key));
                        }
                    }
                }
            }else {
                String key=String.valueOf(ch[i]);
                i++;
                while (i<ch.length&&Character.isLowerCase(ch[i])){
                    key+=ch[i];
                    i++;
                }
                int val=0;
                while (i<ch.length&&Character.isDigit(ch[i])){
                    val=val*10+ch[i]-'0';
                    i++;
                }
                if (val==0){
                    val=1;
                }
                if (!st.isEmpty()){//还是要检测st里有没map，有的话说明现在遇到的元素是在括号里的
                    Map<String,Integer> pre=st.peek();
                    if (!pre.containsKey(key)) {
                        pre.put(key,val);
                    }else {
                        pre.put(key,pre.get(key)+val);
                    }
                }else {
                    map.put(key,map.getOrDefault(key,0)+val);
                }

            }
        }
        StringBuilder  sb=new StringBuilder();
        for (Map.Entry<String,Integer> entry:map.entrySet()){
            sb.append(entry.getKey());
            if (entry.getValue()>1){
                sb.append(entry.getValue());
            }
        }
        return sb.toString();
    }
}
