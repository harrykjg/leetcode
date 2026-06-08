package SomeInterviews.snowflake;

import java.util.*;

public class CakeProblem {
    /*
    https://www.1point3acres.com/interview/problems/post/7100187

In Task 1:
0 represents an empty spot.
1 represents a cake.

Task 2: Global Matching
Requirement
Now the input array contains both persons and cakes:
1 = person
2 = cake
0 = empty
You must pair each person with exactly one unique cake. The goal is to make the sum of distances for all pairs as small as possible.
Rules:

Minimize the total distance sum.
If there are more persons than cakes, return an error (impossible).
If asked about a specific person, return the index of the cake assigned to them in this best-case scenario.
Note: You cannot simply pick the nearest cake for each person individually. One person's choice might force someone else to walk much further, increasing the total cost. You must look at the global picture.

Sample Scenario
line = [1, 2, 0, 1, 0, 0, 2]
# Persons are at indices [0, 3]
# Cakes are at indices [1, 6]
     */
    //第二问很难，直接看gpt的答案
    public Map<Integer, Integer> assignCakes(int[] line) {
        List<Integer> people = new ArrayList<>();//先找出person和cake的位置
        List<Integer> cakes = new ArrayList<>();
        // 1 = person, 2 = cake
        for (int i = 0; i < line.length; i++) {
            if (line[i] == 1) {
                people.add(i);
            } else if (line[i] == 2) {
                cakes.add(i);
            }
        }
        int p = people.size();
        int c = cakes.size();
        if (p == 0) {
            return new HashMap<>();
        }
        if (p > c) {
            throw new IllegalArgumentException("Not enough cakes");
        }
        int INF = 1_000_000_000;
        // dp的意义，前i个人和前j个人的最小总距离
        //画图可能好理解一些
        int[][] dp = new int[p + 1][c + 1];
        // take[i][j] = true 表示：
        // 在 dp[i][j] 这个状态下，person i-1 使用了 cake j-1
        boolean[][] take = new boolean[p + 1][c + 1];
        // 初始化
        for (int i = 0; i <= p; i++) {
            Arrays.fill(dp[i], INF);
        }
        // 匹配 0 个 person，cost 永远是 0
        for (int j = 0; j <= c; j++) {
            dp[0][j] = 0;
        }
        for (int i = 1; i <= p; i++) {
            for (int j = 1; j <= c; j++) {
                // 选择 1：跳过第 j 个 cake
                // 也就是 cake[j - 1] 不分给任何人
                dp[i][j] = dp[i][j - 1];
                // 选择 2：用第 j 个 cake 匹配第 i 个 person
                int cost = Math.abs(people.get(i - 1) - cakes.get(j - 1));
                if (dp[i - 1][j - 1] + cost < dp[i][j]) {//dp[i - 1][j - 1]是有可能是INF的
                    dp[i][j] = dp[i - 1][j - 1] + cost;
                    take[i][j] = true;//这里如果取了j代表第i个人必然取了第j个cake，为什么不用检查这个j是不是被别人用了，
                    //因为这个dp的意义就是不断右扩j，上一个dp[i][j-1]的意义是前i个人用了前j-1个cake的最小值，必然是没有取j的。
                }
            }
        }
        //反向匹配
        Map<Integer, Integer> assignment = new HashMap<>();
        int i = p;
        int j = c;
        //这里也很巧妙，take[i][j]肯定是说明第i个人拿了第j个cake。例如题目问第一个人拿了哪个cake，那么
        // 答案并不是单看take[1][3]或者take[1][2]而是要从最后开始回溯，才能找到。
        while (i > 0 && j > 0) {
            if (take[i][j]) {
                // person i-1 匹配 cake j-1
                int personIndex = people.get(i - 1);
                int cakeIndex = cakes.get(j - 1);
                assignment.put(personIndex, cakeIndex);
                i--;
                j--;
            } else {// cake j-1 被跳过
                j--;
            }
        }
        return assignment;
    }
    public int minTotalDistance(int[] line) {//算谁分配给谁之后的总的距离
        Map<Integer, Integer> assignment = assignCakes(line);
        int total = 0;
        for (Map.Entry<Integer, Integer> entry : assignment.entrySet()) {
            total += Math.abs(entry.getKey() - entry.getValue());
        }
        return total;
    }
}