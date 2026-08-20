package SomeInterviews.snap;

import java.util.HashMap;
import java.util.Map;

public class NonconnectedPeoplePairs {
    /*
    In a social network with n people labeled from 0 to n − 1, friendships are mutual and can be direct or connected through others. Friendships are given as a list connections, where each element [a, b] means person a is friends with person b.

Two people are considered to be in the same friend group if there is a sequence of direct friendships connecting them (that is, they belong to the same connected component).

Your task is to determine the number of unordered pairs of people who are not friends with each other, either directly or indirectly through other friends. You should count all distinct pairs (i, j) where 0 ≤ i < j < n and persons i and j are not in the same friend group.

Constraints:

1 ≤ n ≤ 10⁵
0 ≤ connections.length ≤ 10⁵
Each element in connections is a pair of different integers in the range [0, n − 1]
All friendship pairs in connections are unique.
No person is friends with themselves.
Example 1:

Input: n = 5, connections = [[0, 1], [1, 2]]
Output: 7
Explanation: The network forms three friend groups: [0, 1, 2], [3], and [4]. There are 10 total unordered pairs, and 3 pairs within the group [0, 1, 2]. The remaining 7 pairs are not friends.

Example 2:

Input: n = 3, connections = [[0, 1], [1, 2]]
Output: 0

Example 3:

Input: n = 4, connections = []
Output: 6



     */
    //应该就是找出所有联通块，然后组合的公式算出n中取2个数作为总数，再减去每一个group size取2个的组合.下面是直接抄的答案
    public int countPairs(int n, int[][] connections) {
        // Union-Find to group people into connected components
        int[] parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
        // Union all connections
        for (int[] conn : connections) {
            union(parent, conn[0], conn[1]);
        }
        // Count size of each component
        Map<Integer, Integer> sizeMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = find(parent, i);
            int count = sizeMap.getOrDefault(root, 0);
            sizeMap.put(root, count + 1);
        }
        // Calculate total possible pairs
        int totalPairs = n * (n - 1) / 2;
        // Calculate connected pairs within each component
        int connectedPairs = 0;
        for (int size : sizeMap.values()) {
            connectedPairs += size * (size - 1) / 2;
        }
        return totalPairs - connectedPairs;
    }
    private int find(int[] parent, int x) {
        if (parent[x] != x) {
            parent[x] = find(parent, parent[x]);
        }
        return parent[x];
    }
    private void union(int[] parent, int x, int y) {
        int rootX = find(parent, x);
        int rootY = find(parent, y);
        if (rootX != rootY) {
            parent[rootX] = rootY;
        }
    }
}
