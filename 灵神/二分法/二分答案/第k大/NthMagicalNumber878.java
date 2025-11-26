package 灵神.二分法.二分答案.第k大;

import java.util.HashSet;
import java.util.Set;

public class NthMagicalNumber878 {
    public static void main(String[] args) {
        System.out.println(nthMagicalNumber2(4,2,3));
    }
    //只能想到简单的做法，超时
    //https://leetcode.com/problems/nth-magical-number/solutions/1622336/java-binary-search-detailed-explanation-using-image/ 看他的解释比灵神的容易懂
    //https://leetcode.cn/problems/nth-magical-number/solutions/1984641/er-fen-da-an-rong-chi-yuan-li-by-endless-9j34/
    public static int nthMagicalNumber(int n, int a, int b) {
        int cur1=1;
        int cur2=1;
        int rs=1;
        Set<Integer> set=new HashSet<>();//因为重复的数字不能重复算第n个数
        while (n>0){
            if(a*cur1>cur2*b){
                rs=cur2*b;
                if(!set.contains(rs)){

                    set.add(rs);
                    cur2++;
                    n--;
                }else{
                    cur2++;
                }

            }else{
                rs=cur1*a;
                if(!set.contains(rs)){
                    set.add(rs);
                    cur1++;
                    n--;
                }else{
                    cur1++;
                }
            }

        }
        return rs;
    }

    public static int nthMagicalNumber2(int n, int a, int b) {
        int aa=a;
        int bb=b;
        long mod=(long)Math.pow(10,9)+7;
        while (aa!=0){
            int temp=aa;
            aa=bb%aa;
            bb=temp;
        }
        int gcd=bb;
        long lcm=a*b/gcd;
        long left=1;
        long right=(long)n*Math.min(a,b);
        while (left+1<right){
            long m=right-(right-left)/2;
            long count=m/a+m/b-m/lcm;
            if(count>=n){
                right=m;
            }else{
                left=m;
            }
        }
        return (int) (right%mod);//
    }

}
