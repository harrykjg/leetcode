package DataStruct.Stack;


import com.sun.tools.javac.util.StringUtils;

import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

/**
 * Created by yufengzhu on 7/8/18.
 */
public class NumberofAtoms {
    public static void main(String[] a){
        NumberofAtoms na=new NumberofAtoms();
        System.out.println(na.countOfAtoms("H2O"));//"K4(ON(SO3)2)2
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
}
