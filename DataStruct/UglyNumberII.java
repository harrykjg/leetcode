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
        System.out.print(nthUglyNumber(11));
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
}
