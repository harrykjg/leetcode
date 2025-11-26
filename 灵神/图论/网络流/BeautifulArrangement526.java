package 灵神.图论.网络流;

import java.util.Arrays;

public class BeautifulArrangement526 {
    public static void main(String[] args) {

    }
    //写暴力回溯吧，状态压缩dp太难了
    int rs=0;
    public int countArrangement(int n) {
        boolean[] memo=new boolean[n];
        dfs(0,n,memo);//0代表要填第0位，可用1-n个数去试
        return rs;
    }
    void dfs(int begin,int n,boolean[] memo){
        if(begin==n){
            rs++;
            return;
        }
        for (int i=1;i<=n;i++){
            if(((begin+1)%i==0||i%(begin+1)==0)&&!memo[i-1]){
                memo[i-1]=true;
                dfs(begin+1,n,memo);
                memo[i-1]=false;

            }
        }
    }



    //https://leetcode.cn/problems/beautiful-arrangement/solutions/2787839/jiao-ni-yi-bu-bu-si-kao-zhuang-ya-dpcong-c6kd/
    //状态压缩dp的写法加gpt的解释
    class Solution {
        public int countArrangement(int n) {
            // memo 的大小是 2^n，因为 mask 的范围是 0 ~ (1<<n)-1，总共有 2^n 个状态
            // memo[s] 表示状态 s（已经使用了哪些数字）下的答案
            int[] memo = new int[1 << n];

            // 用 -1 表示“还没有计算过”
            Arrays.fill(memo, -1);

            // 初始状态是 s = 0（没有使用任何数字）
            return dfs(0, n, memo);
        }

        private int dfs(int s, int n, int[] memo) {
            // 当 s == (1<<n)-1 时，表示 mask 的 n 位全部是 1
            // 即数字 1..n 都已经用完，形成了一个完整排列
            // 这是一个合法方案，返回 1
            if (s == (1 << n) - 1) {
                return 1;
            }

            // 如果这个状态已经计算过，直接返回记忆化结果
            if (memo[s] != -1) {
                return memo[s];
            }

            int res = 0;

            // 当前要填的位置 i
            // 已经用过多少数字 = bitCount(s)
            // 所以下一位置就是 bitCount(s) + 1
            int i = Integer.bitCount(s) + 1;

            // 枚举所有可能放的数字 j (1..n)
            for (int j = 1; j <= n; j++) {

                // (s >> (j-1) & 1) == 0 判断数字 j 是否还没被使用
                //
                // j 对应 mask 的第 j-1 位（因为 bit 从 0 开始）
                // 如果该位是 0，则 j 还没用，可以考虑尝试放它
                //
                // (i % j == 0 || j % i == 0) 就是题目给的“优美排列”条件
                //
                if ((s >> (j - 1) & 1) == 0 && (i % j == 0 || j % i == 0)) {

                    // 把数字 j 标记为“使用过”
                    // s | (1<<(j-1)) 就是在 mask 中把 j 对应那一位变为 1
                    int nextState = s | (1 << (j - 1));

                    // 进入下一层递归，从 nextState 开始继续填下一个位置
                    res += dfs(nextState, n, memo);
                }
            }

            // 把当前状态的结果存进 memo，以后遇到相同状态可以直接返回
            memo[s] = res;

            return res;
        }
    }

}
