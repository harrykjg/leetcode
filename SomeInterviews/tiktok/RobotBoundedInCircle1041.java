package SomeInterviews.tiktok;

public class RobotBoundedInCircle1041 {
    //6/14/2026,不会，理解错了，以为只要执行instruction之后不经过已经经过的点就行了。其实不是，这个instruction只是一轮的，应该考虑
    //无限多轮的情况，比如直走+向左，那么这个instruction执行4遍以上就是 bounded的。
    /*
这题的核心判断非常巧：
执行一遍 instructions 后：

如果回到原点：一定 bounded
如果没回到原点，但是方向变了：也一定 bounded
如果没回到原点，而且方向还是北：一定不 bounded
但如果方向变了，比如从北变成西/南/东，那么最多执行 4 轮，方向会转回北，整体轨迹会形成一个循环。
     */
    public boolean isRobotBounded(String instructions) {//gpt代码

            // 0: north, 1: east, 2: south, 3: west
            int[][] dirs = {
                    {0, 1},
                    {1, 0},
                    {0, -1},
                    {-1, 0}
            };

            int x = 0, y = 0;
            int dir = 0; // 初始朝北

            for (char c : instructions.toCharArray()) {
                if (c == 'G') {
                    x += dirs[dir][0];
                    y += dirs[dir][1];
                } else if (c == 'L') {
                    dir = (dir + 3) % 4;
                } else if (c == 'R') {
                    dir = (dir + 1) % 4;
                }
            }

            return (x == 0 && y == 0) || dir != 0;
    }
}
