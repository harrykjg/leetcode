package DataStruct.Stack;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by yufengzhu on 8/14/18.
 */
public class BasicCalculatorII {
    public static void main(String[] args){
        BasicCalculatorII bc=new BasicCalculatorII();
        System.out.print(bc.calculate4("3+2*2"));
    }
    //https://segmentfault.com/a/1190000003796804  这个解法貌似比较好
    //https://leetcode.com/problems/basic-calculator-ii/discuss/63003/Share-my-java-solution  这个也不错
    //自己想的，用两个dequeue，用dequeue的原因是他可以前后两端操作，即可以当作queue也可以当作stack
    //算法有点麻烦，有2个queue，q1放数字，q2放操作符。如1-3*4／2-1。 先把1放q1，再把'-'放q2，当遇到3时，先看q2。peaklast的符号是不是乘号或者除号，是的话就先计算1*3再放回q1里，否则就不管，继续把3放进q1里
    public int calculate(String s) {    //然后到了'*'放进q2，然后遇到4，发现q2里是乘号，则计算3*4再放入q1里，同理计算12／2放进q1里，最后q1里有1，6，1，q2里有'-'，'-'。则从头开始poll q1和q2，1先单独处理了，
        int rs=0;                  //则q1有6，1，q2有'-','-'然后就是rs-=6,rs-=1了
        if(s.length()==0){
            return rs;
        }
        s=s.replace(" ","");
        char[] ch=s.toCharArray();
        Deque<Integer> q1=new LinkedList<>();
        Deque<Character> q2=new LinkedList<>();
        int sign=1;
        int i=0;
        if(ch[0]=='-'){//如果第一个数字是负数，则单独处理，并且最后计算q里剩下的数字时也要单独处理第一个数字
            sign=-1;
            i=1;
        }

        while (i<ch.length){
            if(ch[i]=='+'){
                q2.offer('+');

                i++;
                continue;
            }
            if(ch[i]=='-'){
                q2.offer('-');

                i++;
                continue;
            }
            if(ch[i]=='*'){
                q2.offer('*');
                i++;
                continue;
            }
            if(ch[i]=='/'){
                q2.offer('/');
                i++;
                continue;
            }
            int temp=0;
            while (i<ch.length&&Character.isDigit(ch[i])){
                temp=temp*10+ch[i]-'0';
                i++;
            }
            if(!q2.isEmpty()&&q2.peekLast()=='*'){
                q1.offerLast(q1.pollLast()*temp);
                q2.pollLast();
            }else if(!q2.isEmpty()&&q2.peekLast()=='/'){
                q1.offerLast(q1.pollLast()/temp);
                q2.pollLast();
            }else{
                q1.offerLast(temp);
            }
        }
        if(!q1.isEmpty()){
            rs+=sign*q1.pollFirst();
        }
        while (!q1.isEmpty()&&!q2.isEmpty()){
            int a=q1.pollFirst();
            char b=q2.pollFirst();
            if(b=='+'){
                rs+=a;
            }else{
                rs-=a;
            }
        }
        return rs;
    }

    //以前的代码
    public static int calculate2(String s) {
        if(s==null){
            return -1;
        }
        s=s.replace(" ", "");
        LinkedList<String> st=new LinkedList<String>();
        int i=0;
        while(i<s.length()){
            if(s.charAt(i)=='*'){
                int one=Integer.parseInt(st.pop());
                int two=s.charAt(i+1)-'0';
                i++;
                while(i+1<s.length()&&Character.isDigit(s.charAt(i+1))){
                    two=two*10+s.charAt(i+1)-'0';
                    i++;
                }
                st.push(String.valueOf(one*two));

                i++;
                continue;
            }
            if(s.charAt(i)=='/'){

                int one=Integer.parseInt(st.pop());
                int two=s.charAt(i+1)-'0';
                i++;
                while(i+1<s.length()&&Character.isDigit(s.charAt(i+1))){
                    two=two*10+s.charAt(i+1)-'0';
                    i++;
                }
                st.push(String.valueOf(one/two));
                i++;
                continue;
            }
            if(s.charAt(i)=='+'){
                st.push(s.substring(i,i+1));
                i++;
                continue;
            }
            if(s.charAt(i)=='-'){
                st.push(s.substring(i,i+1));
                i++;
                continue;
            }
            int num=s.charAt(i)-'0';
            while(i+1<s.length()&&Character.isDigit(s.charAt(i+1))){
                num=num*10+s.charAt(i+1)-'0';
                i++;
            }
            i++;
            st.push(String.valueOf(num));
        }


        while(!st.isEmpty()){
            int one=Integer.valueOf(st.removeLast());
            if(st.isEmpty()){
                return one;
            }
            if(st.getLast().equals("+")){
                st.removeLast();
                int two=Integer.valueOf(st.removeLast());
                st.addLast(String.valueOf(two+one));
                continue;
            }
            if(st.getLast().equals("-")){
                st.removeLast();
                int two=Integer.valueOf(st.removeLast());
                st.addLast(String.valueOf(one-two));
            }

        }
        return Integer.valueOf(s);

    }

    //8／14／2018再写一遍别人的方法  https://segmentfault.com/a/1190000003796804  记这一个

        public int calculate3(String s) {
            if(s.length()==0){
                return 0;
            }
            s=s.replace(" ","");
            char[] ch=s.toCharArray();
            char sign='+';
            Stack<Integer> st=new Stack<>();
            int i=0;
            if(ch[0]=='-'){
                sign='-';
                i=1;
            }
            int first=0;
            while (i<s.length()&&Character.isDigit(ch[i])){
                first=first*10+ch[i]-'0';
                i++;
            }
            if(sign=='-'){
                st.push(-first);
            }else{
                st.push(first);
            }


            while (i<ch.length){
                char c=ch[i];
                i++;
                int temp=0;
                while (i<s.length()&&Character.isDigit(ch[i])){
                    temp=temp*10+ch[i]-'0';
                    i++;
                }
                //这里写法有点不elegent，先弄出一个符号，再搞出数字才能判断
                if(c=='+'){

                    st.push(temp);

                }
                else if(c=='-'){

                    st.push(-temp);

                }
                else if(c=='*'){
                    st.push(st.pop()*temp);

                }
                else if(c=='/'){
                    st.push(st.pop()/temp);

                }
            }
            int rs=0;
            while (!st.isEmpty()){
                rs+=st.pop();
            }
            return rs;

        }
//9/16/2018 记不清了,要记清楚别人用1个stack的,并且sign是char，然后写的不那么好看居然一次过了
    public int calculate4(String s) {
        int rs=0;
        if(s.length()==0){
            return 0;
        }
        s=s.replace(" ","");
        char[] ch=s.toCharArray();
        char sign='+';
        int i=0;
        Stack<Integer> st=new Stack<>();
        boolean neg=false;
        if(ch[0]=='-'){
            neg=true;
            i++;
        }
        int first=0;

        while (i<ch.length&&Character.isDigit(ch[i])){
            first=first*10+ch[i]-'0';
            i++;
        }
        if(neg){
            st.push(-1*first);
        }else{
            st.push(first);
        }

        while (i<ch.length){
            if(ch[i]=='+'){
                sign='+';
                i++;
                continue;
            }
            if(ch[i]=='-'){
                sign='-';
                i++;
                continue;
            }
            if(ch[i]=='*'){
                i++;
                int a=0;
                while (i<ch.length&&Character.isDigit(ch[i])){
                    a=a*10+ch[i]-'0';
                    i++;
                }
                st.push(st.pop()*a);
                continue;
            }
            if(ch[i]=='/'){
                i++;
                int a=0;
                while (i<ch.length&&Character.isDigit(ch[i])){
                    a=a*10+ch[i]-'0';
                    i++;
                }
                st.push(st.pop()/a);
                continue;
            }
            int a=0;
            while (i<ch.length&&Character.isDigit(ch[i])){
                a=a*10+ch[i]-'0';
                i++;
            }
            if(sign=='+'){
                st.push(a);
//                sign='+';//不需要reset
            }else if(sign=='-'){
                st.push(-1*a);
//                sign='+';
            }
        }
        while (!st.isEmpty()){
            rs+=st.pop();
        }

        return rs;
    }
    //9/29/2018，还行
    public int calculate5(String s) {
        int rs=0;
        if(s.length()==0){
            return 0;
        }
        s=s.replace(" ","");
        char[] ch=s.toCharArray();
        Stack<Integer> st=new Stack<>();
        int i=0;
        int first=0;
        while (i<s.length()&&Character.isDigit(ch[i])){
            first=first*10+ch[i]-'0';
            i++;
        }
        st.push(first);
        char sign=' ';
        while (i<ch.length){
            sign=ch[i];
            i++;
            int temp=0;
            while (i<s.length()&&Character.isDigit(ch[i])){
                temp=temp*10+ch[i]-'0';
                i++;
            }
            if(sign=='+'){//下面的所有情况多谢了i++的话就错了
                st.push(temp);

                continue;
            }
            if(sign=='-'){
                st.push(-temp);

                continue;
            }
            if(sign=='*'){
                st.push(st.pop()*temp);

                continue;
            }
            if(sign=='/'){
                st.push(st.pop()/temp);

                continue;
            }
        }
        while (!st.isEmpty()){
            rs+=st.pop();
        }
        return rs;
    }

    //7/5/2021,还是挺难，搞对规则就好了。 看以前的想法写法把。不会存在-2+1，或者1*-2。但是会有3*2*2这种
    //先搞出第一个数字，由于开头不会是-2这样的，所以没毛病。如3-5/2/2+1*2-1=3。搞出第一个数字之后，每遇到一个符号，就把它后面的数字也搞出来，
    //如果遇到+/-，由于优先级低，所以只能先把读出来的数字乘以1或者-1（based on+或-）放进st里，如果遇到*/，则把stack里的东西（已经带了正负）pop出来，
    //与现在的*/和sign做运算
    public int calculate6(String s) {
        if (s==null||s.length()==0){
            return 0;
        }
        s=s.replace(" ","");
        int first=0;
        int i=0;
        while (i<s.length()&&Character.isDigit(s.charAt(i))){
            first=10*first+(s.charAt(i)-'0');
            i++;
        }
        Stack<Integer> st=new Stack<>();
        st.push(first);
        int sign=1;
        int rs=0;
        while (i<s.length()){
            if (s.charAt(i)=='+'){
                i++;
                int temp=0;
                sign=1;
                while (i<s.length()&&Character.isDigit(s.charAt(i))){
                    temp=temp*10+s.charAt(i)-'0';
                    i++;
                }
                st.push(sign*temp);
            }else if (s.charAt(i)=='-'){
                i++;
                int temp=0;
                sign=-1;//只有减号是-1，别的都是1，因为不会有*-2这种
                while (i<s.length()&&Character.isDigit(s.charAt(i))){
                    temp=temp*10+s.charAt(i)-'0';
                    i++;
                }
                st.push(sign*temp);
            }else if (s.charAt(i)=='*'){
                i++;
                int temp=0;
                sign=1;
                while (i<s.length()&&Character.isDigit(s.charAt(i))){
                    temp=temp*10+s.charAt(i)-'0';
                    i++;
                }
                st.push(st.pop()*(sign*temp));
            }else if (s.charAt(i)=='/'){
                i++;
                int temp=0;
                sign=1;
                while (i<s.length()&&Character.isDigit(s.charAt(i))){
                    temp=temp*10+s.charAt(i)-'0';
                    i++;
                }
                st.push(st.pop()/(sign*temp));
            }
        }
        while (!st.isEmpty()){
            rs+=st.pop();
        }
        return rs;

    }

    //8/23/2021 居然写的很顺几乎一次过。比以前的都好，记这个
    // 不用先搞出第一个数字。也不用带sign，只需要见到减号的时候push进去负数就行了，因为pop出来已经带正负号了，怎么运算都行
    public int calculate7(String s) {
        s=s.replace(" ","");
        char[] ch=s.toCharArray();
        Stack<Integer> st=new Stack<>();
        int i=0;
        while(i<ch.length){
            if(ch[i]=='+'){
                i++;
                int temp=0;
                while(i<ch.length&&Character.isDigit(ch[i])){
                    temp=temp*10+ch[i]-'0';
                    i++;
                }
                st.push(temp);
                continue;
            }
            if(ch[i]=='-'){
                i++;
                int temp=0;
                while(i<ch.length&&Character.isDigit(ch[i])){
                    temp=temp*10+ch[i]-'0';
                    i++;
                }
                st.push(-temp);
                continue;
            }
            if(ch[i]=='*'){
                i++;
                int pre=st.pop();
                int temp=0;
                while(i<ch.length&&Character.isDigit(ch[i])){
                    temp=temp*10+ch[i]-'0';
                    i++;
                }
                st.push(pre*temp);
                continue;
            }
            if(ch[i]=='/'){
                i++;
                int pre=st.pop();
                int temp=0;
                while(i<ch.length&&Character.isDigit(ch[i])){
                    temp=temp*10+ch[i]-'0';
                    i++;
                }
                st.push(pre/temp);
                continue;
            }
            int temp=0;
            while(i<ch.length&&Character.isDigit(ch[i])){
                temp=temp*10+ch[i]-'0';
                i++;
            }
            st.push(temp);
        }
        int rs=0;
        while(!st.isEmpty()){
            rs+=st.pop();
        }
        return rs;
    }


}
