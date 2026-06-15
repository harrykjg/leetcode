package SomeInterviews.snowflake;

import java.util.*;

public class TreeHeightWithDeleteNode {
    public static void main(String[] args) {
        System.out.println("===== Test 1 =====");
        // Tree structure:
        //    1
        //   /|\
        //  2 3 4
        // /|
        // 5 6
        TreeNode root1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        root1.children = Arrays.asList(node2, node3, node4);
        node2.children = Arrays.asList(node5, node6);
        int res1 = (new TreeHeightWithDeleteNode()).calculateHeight(root1, Arrays.asList(2));
        System.out.println(res1); // Expected: 2

        System.out.println("===== Test 2 =====");
        // Tree structure:
        // 1
        // |
        // 2
        // |
        // 3
        // |
        // 4
        TreeNode root2 = new TreeNode(1);
        TreeNode c2 = new TreeNode(2);
        TreeNode c3 = new TreeNode(3);
        TreeNode c4 = new TreeNode(4);
        root2.children = Arrays.asList(c2);
        c2.children = Arrays.asList(c3);
        c3.children = Arrays.asList(c4);
        int res2 = (new TreeHeightWithDeleteNode()).calculateHeight(root2, new ArrayList<>());
        System.out.println(res2); // Expected: 4

        System.out.println("===== Test 3 =====");
        // Tree structure:
        // 1
        // |
        // 2
        // / \
        // 3 4
        TreeNode root3 = new TreeNode(1);
        TreeNode p2 = new TreeNode(2);
        TreeNode p3 = new TreeNode(3);
        TreeNode p4 = new TreeNode(4);
        root3.children = Arrays.asList(p2);
        p2.children = Arrays.asList(p3, p4);
        int res3 = (new TreeHeightWithDeleteNode()).calculateHeight(root3, Arrays.asList(3));
        System.out.println(res3); // Expected: 3

        System.out.println("===== Test 4 =====");
        // Tree structure:
        // 1
        // |
        // 2
        // |
        // 3
        // |
        // 4
        // |
        // 5
        TreeNode chain1 = new TreeNode(1);
        TreeNode chain2 = new TreeNode(2);
        TreeNode chain3 = new TreeNode(3);
        TreeNode chain4 = new TreeNode(4);
        TreeNode chain5 = new TreeNode(5);
        chain1.children = Arrays.asList(chain2);
        chain2.children = Arrays.asList(chain3);
        chain3.children = Arrays.asList(chain4);
        chain4.children = Arrays.asList(chain5);
        int res4 = (new TreeHeightWithDeleteNode()).calculateHeight(chain1, Arrays.asList(2, 4));
        System.out.println(res4); // Expected: 3
        System.out.println("===== Test 5 =====");
        // Tree structure:
        //      1
        //      |
        //      2
        //  / / /\ \ \ \ \
        // 3 4 5 6 7 8 9 10
        TreeNode wide1 = new TreeNode(1);
        TreeNode wide2 = new TreeNode(2);
        List<TreeNode> wideChildren = new ArrayList<>();
        for (int i = 3; i <= 10; i++) {
            wideChildren.add(new TreeNode(i));
        }
        wide1.children = Arrays.asList(wide2);
        wide2.children = wideChildren;
        int res5 = (new TreeHeightWithDeleteNode()).calculateHeight(wide1, Arrays.asList(2));
        System.out.println(res5); // Expected: 2

        System.out.println("===== Test 6 =====");
        // Tree structure:
        //      1
        //    / | \
        //    2 3  4
        //  /| /\  | \
        // 5 6 7 8 9 10
        TreeNode branch1 = new TreeNode(1);
        TreeNode branch2 = new TreeNode(2);
        TreeNode branch3 = new TreeNode(3);
        TreeNode branch4 = new TreeNode(4);
        TreeNode branch5 = new TreeNode(5);
        TreeNode branch6 = new TreeNode(6);
        TreeNode branch7 = new TreeNode(7);
        TreeNode branch8 = new TreeNode(8);
        TreeNode branch9 = new TreeNode(9);
        TreeNode branch10 = new TreeNode(10);
        branch1.children = Arrays.asList(branch2, branch3, branch4);
        branch2.children = Arrays.asList(branch5, branch6);
        branch3.children = Arrays.asList(branch7, branch8);
        branch4.children = Arrays.asList(branch9, branch10);
        int res6 = (new TreeHeightWithDeleteNode()).calculateHeight(branch1, Arrays.asList(3, 7));
        System.out.println(res6); // Expected: 3

        System.out.println("===== Test 7 =====");
        // Tree structure:
        // 1
        TreeNode single = new TreeNode(1);
        int res7 = (new TreeHeightWithDeleteNode()).calculateHeight(single, new ArrayList<>());
        System.out.println(res7); // Expected: 1

        System.out.println("===== Test 8 =====");
        // Tree structure:
        //      1
        // / / / |\ \ \ \
        // 2 3 4 5 6 7 8 9
        TreeNode star1 = new TreeNode(1);
        TreeNode star2 = new TreeNode(2);
        TreeNode star3 = new TreeNode(3);
        TreeNode star4 = new TreeNode(4);
        TreeNode star5 = new TreeNode(5);
        star1.children = Arrays.asList(star2, star3, star4, star5);
        int res8 = (new TreeHeightWithDeleteNode()).calculateHeight(star1, Arrays.asList(2, 3, 4, 5));
        System.out.println(res8); // Expected: 1
    }
    //https://www.hack2hire.com/companies/snowflake/coding-questions/69bc55471cbff60a929be330/practice?questionId=69bc554c1cbff60a929be332&src=eg1
    //这题我自己写的就是真的去删除节点的，那样挺难写的，因为你需要从从parent那里看每个children到底该不该删，
    // 该删的话要该parent的children，挺不好写的。其实看gpt说其实不需要，只需要看当前节点是否被删除，就能判断其是否能贡献1个高度。
    int height=0;
    public int calculateHeight(TreeNode root,List<Integer> deletedIds) {
        Set<Integer> set=new TreeSet<>(deletedIds);
        if(root==null){
            return 0;
        }
        dfs(root,set,0);
        return height;
    }
    //这个dfs就看当前node是否是被删的，不是的话就当前height+1继续dfs children，否则就height不加1dfs children
    void dfs(TreeNode root,Set<Integer> set,int cur){
        if(root==null){
            return;
        }
        if(set.contains(root.val)){
            if(root.children.size()!=0){
                for (TreeNode child: root.children){
                    dfs(child,set,cur);
                }
            }
        }else{
            height=Math.max(cur+1,height);
            if(root.children.size()!=0){
                for (TreeNode child: root.children){
                    dfs(child,set,cur+1);
                }
            }
        }

    }
    /* gpt答案
    public int maxHeightAfterDeletion(Node root, List<Integer> deletedIds) {
        Set<Integer> deleted = new HashSet<>(deletedIds);
        return dfs(root, deleted, 0);
    }

    private int dfs(Node node, Set<Integer> deleted, int depth) {
        if (node == null) {
            return depth;
        }
        // 如果当前 node 被删，它不贡献高度
        // 如果没被删，它贡献 1 层
        int newDepth = deleted.contains(node.id) ? depth : depth + 1;
        // leaf
        if (node.children == null || node.children.isEmpty()) {
            return newDepth;
        }
        int max = newDepth;
        for (Node child : node.children) {
            max = Math.max(max, dfs(child, deleted, newDepth));
        }
        return max;
    }
     */

    //follow-up是反过来：给定一个k，问最少删除多少个节点能让树高度不超过k
    private Map<String, Integer> memo = new HashMap<>();
    private static final int INF = 1_000_000_000;
    public int minDeleteToHeightAtMostK(TreeNode root, int k) {
        if (root == null) {
            return 0;
        }
        // root 不能删，所以如果 k < 1，一定不可能
        if (k < 1) {
            return -1;
        }
        // root 必须保留，占用 1 层高度
        // 所以 root 的 children 最多还能保留 k - 1 个高度
        int ans = 0;
        for (TreeNode child : root.children) {
            ans += dfs(child, k - 1);
        }
        return ans;
    }
    // dfs(node, remain)
    // 含义：
    // 从 node 这个位置开始往下，
    // 在每条 root-to-leaf 路径上，最多还能保留 remain 个节点。
    // 返回：
    // 为了满足这个限制，node 这个 subtree 最少要删除多少个节点。
    private int dfs(TreeNode node, int remain) {
        if (node == null) {
            return 0;
        }
        String key = node.val + "#" + remain;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        // 选择 1：删除当前 node
        // 删除 node 的 cost 是 1。
        // 但是注意：
        // 删除 node 后，它的 children 会被提升到 node 的 parent。
        // 所以 children 仍然要继续处理。
        // 而且因为 node 被删了，没有占用高度，
        // children 还能使用同样的 remain。
        int deleteCost = 1;
        for (TreeNode child : node.children) {
            deleteCost += dfs(child,remain);
        }
        // 选择 2：保留当前 node
        // 如果保留 node，那么 node 本身会占用 1 层高度。
        // 所以 children 只能使用 remain - 1。
        // 如果 remain == 0，说明已经不能再保留任何节点，
        // 那当前 node 不能保留。
        int keepCost = INF;
        if (remain > 0) {
            keepCost = 0;
            for (TreeNode child : node.children) {
                keepCost += dfs(child, remain - 1);
            }
        }
        int ans = Math.min(deleteCost, keepCost);
        memo.put(key, ans);
        return ans;
    }
}

class TreeNode {
    int val;
    List<TreeNode> children;
    TreeNode(int val) {
        this.val = val;
        this.children = new ArrayList<>();
    }
}
