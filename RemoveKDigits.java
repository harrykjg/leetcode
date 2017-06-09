import java.util.Stack;

/**
 * Created by 502575560 on 6/8/17.
 */
public class RemoveKDigits {
    //大概记得思路,试着做,就是dfs,试着删掉这个数字,删完k个之后比较最后的结果取最小的一个,貌似大多test都能过,就是string很长的时候会integer.parseInt
    //或long.parseLong越界,后来把rs换成string之后会爆栈当string太长的时候dfs太多
    //http://blog.csdn.net/yeqiuzs/article/details/52574177
    //http://blog.csdn.net/mebiuw/article/details/52576884
    //http://www.cnblogs.com/reboot329/p/5883739.html
    public static void main(String[] args){
        RemoveKDigits rk=new RemoveKDigits();
//        System.out.println(rk.removeKdigits2("13245",3));
        System.out.println(rk.removeKdigits("9999999999991",8));
    }


    public String removeKdigits(String num, int k) {
        if(num.length()<=k){
            return "0";
        }
        Stack<Character> st=new Stack<>();
        st.push(num.charAt(0));
        for(int i=1;i<num.length();i++){
            char cur=num.charAt(i);
            while (!st.isEmpty()&&st.peek()>cur&&st.size()+num.length()-i-1>num.length()-k){//注意这是个while循环,把stack里比当前字母小的都pop掉,条件是有点难想
                st.pop(); //最后这个条件限制的是不能删多了,删多的话就导致剩下的num就算全部加进去都不够用.列个方程,即st里的元素加上剩下数组里的字母数,要大于最后得出的string长度才能pop
            }
            if(st.size()<num.length()-k){
                st.push(cur);
            }
        }
        String rs="";
        while (!st.isEmpty()){
            rs=st.pop()+rs;
        }
        int i=0;
        for( i=0;i<rs.length();i++){
            if(rs.charAt(i)!='0'){
                break;
            }
        }
        rs=rs.substring(i);
        return rs.equals("")?"0":rs;
    }

    //自己写的dfs穷举,会爆栈
    int rs=Integer.MAX_VALUE;
    public String removeKdigits2(String num, int k) {
        if(num.length()<=k){
            return "0";
        }
        helper(0,0,k,num);
        return rs+"";

    }
    public void helper(int b,int c,int k,String curNum){
        if(c>=k){
            int temp=Integer.parseInt(curNum);
            rs=Math.min(temp,rs);
            return;
        }
        int left=curNum.length()-b;
        if(left<(k-c)){//剩下的数已经不够删了
            return;
        }
        for(int i=b;i<curNum.length();i++){
            String s1=curNum.substring(0,i);
            String s2=curNum.substring(i+1);//去掉第i位的哪个数
            String s=s1+s2;
            helper(i,c+1,k,s);
            helper(i+1,c,k,curNum);
        }
    }

}
