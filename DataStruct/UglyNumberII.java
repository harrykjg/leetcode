package DataStruct;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by 502575560 on 7/12/17.
 */
public class UglyNumberII {
    //记得一点但是还是写不出,leetcode的提示已经写的很多了,主要是每一个ugly number都是从i1*1,i2*2,i3*5来的这里不太好理解
    //http://www.cnblogs.com/aprilcheny/p/4950442.html  看它的解法二
//            (2) 1×2, 2×2, 3×2, 4×2, 5×2, 6×2, 8×2  注意是ugly number分别去乘以2,3,5别想歪了
//            (3) 1×3, 2×3, 3×3, 4×3, 5×3, 6×3, 8×3
//            (5) 1×5, 2×5, 3×5, 4×5, 5×5, 6×5, 8×5
    public static void main(String[] args){
        System.out.print(nthUglyNumber2(10));
    }
    public static int nthUglyNumber(int n) {
        if(n==1){
            return 1;
        }
        LinkedList<Integer> n1=new LinkedList<>();
        LinkedList<Integer> n2=new LinkedList<>();
        LinkedList<Integer> n3=new LinkedList<>();

        int rs=1;
        for(int i=2;i<=n;i++){
            n1.offer(rs*2);
            n2.offer(rs*3);
            n3.offer(rs*5);

            int min=Math.min(n1.peek(),Math.min(n2.peek(),n3.peek()));
            if(n1.peek()==min){
                n1.poll();
            }
            if(n2.peek()==min){
                n2.poll();
            }
            if(n3.peek()==min){
                n3.poll();
            }
            rs=min;
        }
        return rs;
    }
    //http://www.cnblogs.com/aprilcheny/p/4950442.html
    //05/27/2020,还是写不出来，要理解，while循环每次其实只是从*2，*3，*5之中选出最小的那个，剩下的2个留在各自的queue里等下次候选
    public static int nthUglyNumber2(int n) {
        int count=1;
        int base=1;
        Queue<Integer> n1=new LinkedList<>();
        Queue<Integer> n2=new LinkedList<>();
        Queue<Integer> n3=new LinkedList<>();
        while (count<n){
            n1.offer(base*2);
            n2.offer(base*3);
            n3.offer(base*5);
            int min=Math.min(n1.peek(),Math.min(n2.peek(),n3.peek()));
            if(n1.peek()==min){
                n1.poll();
            }
            if(n2.peek()==min){
                n2.poll();
            }
            if(n3.peek()==min){
                n3.poll();
            }
            base=min;
           count++;
        }
        return base;
    }
//6/8/2021.自己想容易想错，以为只要3个变量就可以。注意14不是ugly number，ugly number必须是当前result去乘以2，3，5得到得
    //以前的解法还要3个list，别人的用一个数组即可，不是那么好想像.比如先比较1*2。1*3。1*5，得出rs【1】=2。然后再比较2*2，1*3，1*5，继续可知应该设3个index
    //并且没被选中的index还是指向久的result
    //https://leetcode.com/problems/ugly-number-ii/discuss/69362/O(n)-Java-solution
    public static int nthUglyNumber3(int n) {
        if(n==1){
            return 1;
        }
        int i1=0;
        int i2=0;
        int i3=0;
        int[] rs=new int[n];
        rs[0]=1;
        for(int i=1;i<n;i++){
            int min=Math.min(rs[i1]*2,Math.min(rs[i2]*3,rs[i3]*5));
            rs[i]=min;
            if(rs[i1]*2==min){
                i1++;
            }
            if(rs[i2]*3==min){
                i2++;
            }
            if(rs[i3]*5==min){
                i3++;
            }
        }
        return rs[n-1];
    }
}
