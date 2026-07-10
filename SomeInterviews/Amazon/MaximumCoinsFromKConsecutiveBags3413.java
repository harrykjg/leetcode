package SomeInterviews.Amazon;

import java.util.Arrays;

public class MaximumCoinsFromKConsecutiveBags3413 {

    //7/9/2026 定长sliding window
    //https://leetcode.com/problems/maximum-coins-from-k-consecutive-bags/solutions/6232195/javacpython-sliding-window-by-lee215-3418/?envType=company&envId=amazon&favoriteSlug=amazon-thirty-days
    public long maximumCoins(int[][] coins, int k) {
        Arrays.sort(coins,(a,b)->a[0]-b[0]);
        long rs=0;
        //可以想到是不用把coins里的钱都模拟出来到一个arry里再算，但是区间的话又不太好一个一个挪，巧妙的是就看两种情况，1，从每个区间开头开始，
        // 看这个window能cover 多少 2.从每个区间结束点作为这个window的结束点开始，看能cover多少。这个不好证明，只能反证法吧
        long cur=0;
        int j=0;//代表最远的那个interval
        for (int i=0;i<coins.length;i++){
            if (j < i) {//这个也很容易漏，即上一段j没有前进，但这个for loop i前进了，说明第一段interval大于window的情况
                j = i;
                cur = 0;
            }
            int end=coins[i][0]+k-1;//代表最远的那个点，注意-1容易漏
            while (j<coins.length&&coins[j][1]<=end){//能完整包住这个interval j
                cur+= (long) (coins[j][1] - coins[j][0]+1) *coins[j][2];//是inclusive，如[1,3]是有3个bag
                j++;
            }
            long temp=cur;//这里也很容易错，partial interval 只是临时算当前窗口答案，不能永久放进 cur，因此拿个temp来算
            //否则下一轮 i++ 的时候，这个 partial 部分还留在 cur 里，会污染后面的计算。
            if(j<coins.length&&coins[j][0]<=end){//这个j是不完整包括的那个
                temp+= (long) (end - coins[j][0] + 1) *coins[j][2];
            }
            rs=Math.max(rs,temp);
            if(i<j){//这个也很容易错，只有当i<j时才代表i已经整体加进cur里，因此才需要在这里减去
                cur-=(long)(coins[i][1]-coins[i][0]+1)*coins[i][2];//挪到下一个interval，那就把当前interval减掉，
                // 这样下个interval就不需要，并且j放在for loop外面重新从头开始右移算cur了
            }
        }

        cur=0;
        j=coins.length-1;//就是从右往左扩
        for (int i=coins.length-1;i>=0;i--){
            if (j > i) {
                j = i;
                cur = 0;
            }
            long start=coins[i][1]-k+1;
            while (j>=0&&coins[j][0]>=start){
                cur+=(long)(coins[j][1]-coins[j][0]+1)*coins[j][2];
                j--;
            }
            long temp=cur;
            if(j>=0&&coins[j][1]>=start){
                temp+=(long)(coins[j][1]-start+1)*coins[j][2];
            }
            rs=Math.max(rs,temp);
            if(i>j){
                cur-=(long)(coins[i][1]-coins[i][0]+1)*coins[i][2];

            }
        }

        return rs;

    }
}
