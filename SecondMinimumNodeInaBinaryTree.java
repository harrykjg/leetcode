public class SecondMinimumNodeInaBinaryTree {
    //7/17/2021 除了暴力法没啥思路
    /* 这是答案
    class Solution {
    int min1;
    long ans = Long.MAX_VALUE;

    public void dfs(TreeNode root) {
        if (root != null) {
            if (min1 < root.val && root.val < ans) {
                ans = root.val;
            } else if (min1 == root.val) {
                dfs(root.left);
                dfs(root.right);
            }
        }
    }
    public int findSecondMinimumValue(TreeNode root) {
        min1 = root.val;
        dfs(root);
        return ans < Long.MAX_VALUE ? (int) ans : -1;
    }
}
     */
    //https://leetcode.com/problems/second-minimum-node-in-a-binary-tree/discuss/107158/Java-divide-and-conquer-solution
    //下面是自己写的
    int min=Integer.MAX_VALUE;
    Integer second;
    public int findSecondMinimumValue(TreeNode root) {//root的value肯定是最小的，然后看左右子树节点是否大于min，是的话则记为second作为可能的答案
        min=root.val;                      //然后这个子树就不用继续往下了，因为往下肯定是大于等于second的，但是如果另一边子树是小于second的话，是要继续
                                            //递归检查，因为那一边可能有更小的数
        helper(root.left);
        helper(root.right);
        return second==null?-1:second;
    }
    void helper(TreeNode root){
        if (root==null){
            return;
        }
        if (root.val>min&&(second==null||root.val<second)){
            second=root.val;
        }else if (root.val==min){
            helper(root.left);
            helper(root.right);
        }//否则就是root大于second，也不用往下走了
    }
}
