package 灵神.贪心.区间;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountWaystoGroupOverlappingRanges2580 {
    static void main() {
        int[][] a={{5,11},{20,22},{1,3},{21,22},{11,11}};
        System.out.println();
    }
    //1/3/2026 找规律发现就是合并区间，最后得到n个独立区间，则答案是2的n次方。就是组合算法，自己推到一下
    public  int countWays(int[][] ranges) {
        Arrays.sort(ranges,(a,b)->a[0]-b[0]);
        int mod=1_000_000_007;
        int count=ranges.length;
        int[] pre=ranges[0];
        for (int i=1;i<ranges.length;i++){
            if(ranges[i][0]>pre[1]){
                pre=ranges[i];
                continue;
            }else{
                pre[1]=Math.max(pre[1],ranges[i][1]);
                count--;
            }
        }

//        return (int)((Math.pow(2,count))%mod);//原来这样写是对的，但是后面可能double的范围都不够了，因此只能一个一个乘
            return helper(2l,(long)count,mod);
    }

    int helper(long base, long exp, int mod) {
        long res = 1;
        base %= mod;
        while (exp > 0) {
            if ((exp & 1) == 1) res = (res * base) % mod;
            base = (base * base) % mod;
            exp >>= 1;
        }
        return (int) res;
    }
}
