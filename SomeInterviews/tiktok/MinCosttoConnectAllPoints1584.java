package SomeInterviews.tiktok;

import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class MinCosttoConnectAllPoints1584 {

    //6、14、2026不会，看答案，下面是直接抄的gpt,思路就是设int[] minDist 代表i点被纳入之后最小的成本，然后外层for 循坏n次，每次找
    //当前成本最小的那个点作为起点，试着连所有点的别的点，然后更新别的点的距离。是最小生成树算法
    class Solution {
        public int minCostConnectPoints(int[][] points) {
            int n = points.length;
            boolean[] visited = new boolean[n];
            // minDist[i] 表示：点 i 连接到当前已连通集合的最小成本
            int[] minDist = new int[n];
            Arrays.fill(minDist, Integer.MAX_VALUE);
            // 从点 0 开始
            minDist[0] = 0;
            int ans = 0;
            for (int i = 0; i < n; i++) {
                int cur = -1;
                // 找一个还没加入 MST，并且 minDist 最小的点
                for (int j = 0; j < n; j++) {
                    //这个条件有点绕，j=0时，cur=-1，进入if得cur=0，然后后面的cur就不是-1了，但是这个会
                    if (!visited[j] && (cur == -1 || minDist[j] < minDist[cur])) {
                        cur = j;
                    }
                }
                // 把这个点加入 MST
                visited[cur] = true;
                ans += minDist[cur];//第一个点不是虚拟的0点，是输入的第一个点。但是此时把它纳入的cost是0
                // 用 cur 去更新其他未访问点的连接成本
                for (int next = 0; next < n; next++) {
                    if (!visited[next]) {
                        int dist = Math.abs(points[cur][0] - points[next][0])
                                + Math.abs(points[cur][1] - points[next][1]);
                        minDist[next] = Math.min(minDist[next], dist);
                    }
                }
            }
            return ans;
        }
    }
}

