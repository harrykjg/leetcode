package 灵神.贪心.区间;

import java.util.Arrays;

public class TwoBestNonOverlappingEvents2054 {
    static void main() {
        int[][] e={{1,3,2},{4,5,2},{1,5,5}};
        System.out.println(maxTwoEvents(e));
    }
    //1/3/2026，难。注意只能最多选2个活动.先写dfs暴力，再推出memorization，问的gpt。就算这样也是超时的。
    /*
    正确做法，看gpt的解释，由于是只找2个event，所以正常暴力法应该是枚举一个，再找不重叠的第二个，那样就是n方，现在转变思维，
    枚举第二个i，然后只要找0-i中间不想交（即最后一个end小于i_start的那个）而且最大的那个就行了，而怎么快速找呢，能用二分法，那按什么
    排序呢？由于要找end所以把event先按end day排序。

class Solution {
    public int maxTwoEvents(int[][] events) {
        // 按 end 升序
        Arrays.sort(events, (a, b) -> a[1] - b[1]);
        int n = events.length;

        int[] ends = new int[n];
        int[] best = new int[n]; // best[i]：0..i 里单个活动最大 value

        int curBest = 0;
        for (int i = 0; i < n; i++) {
            ends[i] = events[i][1];
            curBest = Math.max(curBest, events[i][2]);
            best[i] = curBest;
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            int start = events[i][0];
            int val = events[i][2];

            // 找到最后一个 end < start 的下标 j
            int j = lowerBound(ends, start) - 1; // lowerBound: 第一个 >= start
            ans = Math.max(ans, val); // 只选当前
            if (j >= 0) {
                ans = Math.max(ans, val + best[j]);//找到j了，那么best[j]就是前j位最大的那个
            }
        }
        return ans;
    }

    // 返回第一个 >= target 的下标
    private int lowerBound(int[] a, int target) {
        int l = 0, r = a.length;
        while (l < r) {
            int m = (l + r) >>> 1;
            if (a[m] >= target) r = m;
            else l = m + 1;
        }
        return l;
    }
}

     */
    public static int maxTwoEvents(int[][] events) {
        Arrays.sort(events,(a,b)->{
            if(a[0]==b[0]){
                return a[1]-b[1];
            }
            return a[0]-b[0];
        });
        int[][] memo=new int[events.length][3];
        for(int i=0;i<memo.length;i++){
            Arrays.fill(memo[i],-1);
        }
         return dfs(0,0,0,events,memo);
    }
    //这里写的dfs加memo是错误的，因为我想象中的memo【i】【pick】的意义是第i个event开始，pick个状态的情况下的最大值，但是问题是
    //这一层dfs是要通过while 来去除重叠event的，所以其实取得的value并不一定是输入的i了，而gpt的写法是对的，因为他把while去重叠
    //这一步写在上一层dfs即caller那里，因此他的memo[i][pick]才是真正代表从第i个event开始取。
    static int dfs(int endDay,int b,int picked,int[][] events,int[][] memo){
        if(b>=events.length){
            return 0;
        }
        if(memo[b][picked]!=-1){
            return memo[b][picked];
        }
        int i=b;
        while (i<events.length&&events[i][0]<=endDay){//这个endday就是上一个event的end day，因此如果当前i小于enday说明会重叠
            i++;               
        }
        if(i>=events.length){
            memo[b][picked]=Math.max(0,memo[b][picked]);
            return memo[b][picked];
        }

        int noPick= dfs(endDay,b+1,picked,events,memo);
        int pick=0;
        if(picked<=1){
            pick=events[i][2]+dfs(events[i][1],i+1,picked+1,events,memo);
        }
        memo[b][picked]=Math.max(noPick,pick);
        return memo[b][picked];
    }
//这个是gpt的对的memorization版本
    static int dfs(int i, int picked, int[][] events, int[][] memo) {
        if (picked == 2 || i == events.length) return 0;
        if (memo[i][picked] != -1) return memo[i][picked];

        // 不选 i
        int best = dfs(i + 1, picked, events, memo);

        // 选 i：找下一个 start > end 的活动
        int end = events[i][1];
        int j = i + 1;
        while (j < events.length && events[j][0] <= end) j++; // 注意 <=（不能相等）
        best = Math.max(best, events[i][2] + dfs(j, picked + 1, events, memo));

        return memo[i][picked] = best;
    }
}
