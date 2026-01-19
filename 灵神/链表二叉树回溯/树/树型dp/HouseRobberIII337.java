package 灵神.链表二叉树回溯.树.树型dp;





import java.util.HashMap;
import java.util.Map;

public class HouseRobberIII337 {
    static void main() {
        TreeNode n=new TreeNode(3);
        n.left=new TreeNode(4);
        n.right=new TreeNode(5);
        n.left.right=new TreeNode(3);
        n.left.left=new TreeNode(1);
        n.right.right=new TreeNode(1);
        System.out.println(rob(n));
    }
    //12/26/2025,不会，自己写的到头来还是和以前的第一个方法一样，需要map里再加一个map去装取和不取当前节点的状态。他们不用这样
    //只看到解法2
    //https://leetcode.cn/problems/house-robber-iii/solutions/47828/san-chong-fang-fa-jie-jue-shu-xing-dong-tai-gui-hu/
    public static int rob(TreeNode root) {
        if(root==null){
            return 0;
        }
        Map<TreeNode,Integer> memo=new HashMap<>();
        //自己想的是这里分别dfs 这个节点取还是不取的情况，这样会导致取的这个dfs更新了memo，导致不取的不会被计算了
//        return Math.max(dfs(root,memo,true),dfs(root,memo,false));
        //答案的写法是这个dfs不接受true/false 参数,而这个dfs分别讨论当前点是否要取。这样居然会导致和上面的写法不一样，很神奇
        dfs(root,memo);
        return memo.get(root);
    }

   static int dfs(TreeNode root,Map<TreeNode,Integer> memo){
        if(memo.containsKey(root)){
            return memo.get(root);
        }
        if(root==null){
            return 0;
        }
        int take=root.val;
        //这里不好想，怎么分别讨论取不取当前节点呢？就是算这点取+下下层（left/right的下一层左+右），和这点不取加下层。这两个值取大者
        if(root.left!=null){
            take+=dfs(root.left.left,memo)+dfs(root.left.right,memo);
        }
        if(root.right!=null){
            take+=dfs(root.right.left,memo)+dfs(root.right.right,memo);
        }
        int noTake=dfs(root.left,memo)+dfs(root.right,memo);
        int max=Math.max(noTake,take);
        if(!memo.containsKey(root)){
            memo.put(root,max);
        }
        return max;
    }
    static class TreeNode {
        public int val;
        public TreeNode left;
        public TreeNode right;
        public TreeNode() {}
        public TreeNode(int val) { this.val = val; }
        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
