package 灵神.DP.经典线性dp.LongestIncreasingSubsequence;

import java.util.Arrays;

public class RussianDollEnvelopes354 {
    public static void main(String[] args) {

    }
    //看回以前说的，先排序再LIS，发现不排序会错，问了GPT说必须保证前面的状态是算好的并且有意义的才行，如[5,4], [6,7], [2,3]，这样【2,3】就没算进去了。那为啥300题的不用？是因为这题
    //说可以打乱算许装信封，300题说了只能按那个顺序求LIS
    public int maxEnvelopes(int[][] envelopes) {
        Arrays.sort(envelopes,(a,b)->a[0]+a[1]-b[0]-b[1]);//不排序的话会错，不知道为啥
        int[] dp=new int[envelopes.length];
        Arrays.fill(dp,1);
        int rs=1;
        for(int i=1;i<dp.length;i++){
            for(int j=0;j<i;j++){//不超时的算法就是得二分法找第一个高大于第i个信封的那个，见下面gpt的解释
                if(envelopes[i][0]>envelopes[j][0]&&envelopes[i][1]>envelopes[j][1]){
                    dp[i]=Math.max(dp[j]+1,dp[i]);
                }
            }
            rs=Math.max(dp[i],rs);
        }
        return rs;

    }
    /*
    GPT:
    // 先按宽度升序排，如果宽度相同，就按高度**降序**排
Arrays.sort(envelopes, (a, b) -> {
    if (a[0] == b[0]) {
        return b[1] - a[1]; // 宽相同时，高度降序
    } else {
        return a[0] - b[0]; // 宽度升序
    }
});
为什么宽相同时要按高度降序？

因为：

套娃条件要求 w 严格大于、h 严格大于 才能套

宽相等的两个信封无论如何都不能互相套

如果我们对高度也按升序排，那么在求 LIS(h) 时，相等宽度的一组里高度是 升序，这样 LIS 会认为他们可以接在一起（因为 h[i] >= h[j]），产生错误。

举个例子：

[2,3], [2,4]


这两个宽度一样，严格比较下一个不能套另一个

但如果排成 [2,3], [2,4]，高度是 3,4

对高度做 LIS（非严格）的时候，会得到长度 2，这是错的数量。

所以我们把宽相同的一组按高度降序排：

[2,4], [2,3]   // 高度 4,3


这样在对高度求严格 LIS（或非减 LIS）时，不会把这两个算进同一个递增序列。

class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        int n = envelopes.length;
        if (n == 0) return 0;

        // 1. 排序：宽度升序；若宽度相同，高度降序
        Arrays.sort(envelopes, (a, b) -> {
            if (a[0] == b[0]) {
                return b[1] - a[1]; // 高度降序
            } else {
                return a[0] - b[0]; // 宽度升序
            }
        });

        // 2. 对“高度”做 LIS（严格递增）
        int[] tails = new int[n]; // tails[len-1] = 长度为 len 的递增子序列的最小末尾
        int size = 0;

        for (int i = 0; i < n; i++) {
            int h = envelopes[i][1];

            // 在 tails[0..size) 中找第一个 >= h 的位置，替换掉
            int l = 0, r = size;
            while (l < r) {
                int m = (l + r) >>> 1;
                if (tails[m] < h) {
                    l = m + 1;
                } else {
                    r = m;
                }
            }
            tails[l] = h;
            if (l == size) size++;
        }

        return size;
    }
}
     */

}
