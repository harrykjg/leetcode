package 灵神.常用数据结构.并查集;

public class RedundantConnectionII685 {
    static void main() {

    }
    //12/10/2025,开始想是不是拓扑排序也能检测环？gpt说不适合
/*
拓扑排序能干什么？
对有向图：
Kahn 拓扑排序算法：
每次把入度为 0 的点丢进队列，依次弹出并删掉它的出边
最后如果能弹出 n 个点 → 无环
如果弹出 < n 个 → 有环（剩下的点都在某个环里）

小结论：
无向图：检测环 → 并查集 / DFS
有向图：检测环 → 拓扑排序 / DFS（找回边或 SCC）
“树 + 一条额外边，要求删一条恢复树” → 并查集通常最简洁
（因为“加入一条边时如果两个端点已连通，那这条边就是形成环的那条”）

我自己记得好像是可以看一个节点是否重复被访问就可以知道是有环，gpt说那是用在无向图的dfs。在有向图里，一个节点可能合法地被不同路径多次访问，而不形成环。
如1 → 2 ← 3
无向图（DFS 中发现访问过且不是父节点 → 有环）
有向图中，用 3 种颜色 DFS（白/灰/黑）检测环
 */
    //这题还是挺难的，不是简单的检测环或者看谁有两个parent就是需要删除的边。
    /*
    一共三种情况，1：只有入度为2的 2：只有环，3：有环且有入度为2的
    如[[2,1],[3,1],[4,2],[1,4]] 这个例子，是同事存在某个节点有两个父亲，且有环的情况，答案是删除【2，1】
     */
    //这个是gpt的代码
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int n = edges.length;
        int[] parent = new int[n + 1];
        // 初始化 parent 数组
        for (int i = 1; i <= n; i++) {
            parent[i] = 0;
        }

        int[] cand1 = null; // 第一条指向某节点的边（该节点入度为2时）
        int[] cand2 = null; // 第二条指向该节点的边

        // 第一步：统计每个节点的父亲，寻找是否有节点入度为2
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            if (parent[v] == 0) {
                parent[v] = u;
            } else {
                // v 已经有一个父亲 parent[v]，现在又来一个 u
                cand1 = new int[]{parent[v], v}; // 第一条
                cand2 = new int[]{u, v};         // 第二条
                // 暂时把第二条边“删掉”
                e[1] = 0; // 把 v 改为 0，使后面并查集时跳过这条边
            }
        }

        // 第二步：在“忽略 cand2 的前提下”，用并查集检查有没有环
        UnionFind uf = new UnionFind(n + 1);
        for (int[] e : edges) {
            int u = e[0], v = e[1];
            if (v == 0) continue; // 无效边，跳过（可能是 cand2）
            if (!uf.union(u, v)) {
                // union 失败，说明有环
                if (cand1 == null) {
                    // 情况1：没有入度为2，只是单纯有环 → 返回这条成环的边
                    return e;
                } else {
                    // 情况3：有入度2 + 有环 → 应删除第一条边 cand1
                    return cand1;
                }
            }
        }

        // 如果走到这里，说明没有在并查集中发现环
        // 若存在入度为2的情况，则删除第二条边即可（情况2）
        return cand2;
    }

    // 基本并查集模板（用于检测无向环）
    static class UnionFind {
        int[] parent;

        UnionFind(int n) {
            parent = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // 路径压缩
            }
            return parent[x];
        }

        // 返回 false 表示这次 union 发现两个点已经联通 → 出现环
        boolean union(int a, int b) {
            int ra = find(a);
            int rb = find(b);
            if (ra == rb) return false;
            parent[ra] = rb;
            return true;
        }
    }
}
