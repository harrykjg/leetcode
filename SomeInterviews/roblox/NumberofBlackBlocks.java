package SomeInterviews.roblox;

public class NumberofBlackBlocks {


    /*
    这题不好理解意思，意思是
    看途中所有可能的 2 x 2 小方块，一共有：(m - 1) * (n - 1)
    你要返回：
    arr[0] = 恰好有 0 个黑格子的 2x2 block 数量
    arr[1] = 恰好有 1 个黑格子的 block 数量
    ...
    arr[4] = 恰好有 4 个黑格子的 block 数量
    关键要想到
    因为一个 2x2 block 由它的左上角决定。
    如果 (x, y) 在某个 block 里，那么这个 block 的左上角只可能是：

    (x, y)         // 黑格子是左上
    (x-1, y)       // 黑格子是左下
    (x, y-1)       // 黑格子是右上
    (x-1, y-1)     // 黑格子是右下
    因此遍历codinates，找则个黑格子属于哪四个2*2block，而2*2block由左上角定，因此可以用i*length+j来作为key定位，这个map就是《long，value》，其中value是出现
    几个黑格子。方向是
    dx={0,-1,0,-1}
    dy={0,0，-1，-1」
    格子是定了的，那么他属于的block的id是有四个可能，（i，j）本身，（i，j-1），（i-1，j），（i-1，j-1）这里想清楚就行了

    class Solution {
    public long[] countBlackBlocks(int m, int n, int[][] coordinates) {
        Map<Long, Integer> map = new HashMap<>();

        for (int[] c : coordinates) {
            int x = c[0], y = c[1];

            for (int dx = -1; dx <= 0; dx++) {
                for (int dy = -1; dy <= 0; dy++) {
                    int r = x + dx;
                    int col = y + dy;

                    if (r >= 0 && r < m - 1 && col >= 0 && col < n - 1) {
                        long key = (long) r * n + col;
                        map.put(key, map.getOrDefault(key, 0) + 1);
                    }
                }
            }
        }

        long[] res = new long[5];
        for (int cnt : map.values()) {
            res[cnt]++;
        }

        long totalBlocks = (long) (m - 1) * (n - 1);
        res[0] = totalBlocks - map.size();

        return res;
    }
}

     */

    public long[] countBlackBlocks(int m, int n, int[][] coordinates) {

    }

}
