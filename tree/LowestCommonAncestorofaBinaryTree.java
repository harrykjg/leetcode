package tree;

/**
 * Created by yufengzhu on 7/23/18.
 */
public class LowestCommonAncestorofaBinaryTree {
    //记得
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode A, TreeNode B) {
        if(root==null){
            return null;
        }
        if(root==A||root==B){
            return root;
        }
        TreeNode left=lowestCommonAncestor(root.left,A,B);
        TreeNode right=lowestCommonAncestor(root.right,A,B);
        if(left!=null&&right!=null){
            return root;
        }
        return left==null?right:left;
    }
//04/15/2020,写的不好，要练
    //https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/discuss/64954/My-Java-Solution 这个用到了bst的性质
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode A, TreeNode B) {
        if (root==null){
            return null;
        }
        if(root==A||root==B){//这是错的，看别人的答案
            return root;
        }
        TreeNode left=lowestCommonAncestor(root.left,A,B);
        TreeNode right=lowestCommonAncestor(root.right,A,B);
        if(left!=null&&right!=null){
            return root;
        }
        if(left!=null){
            return left;
        }
        return right;
    }

    //6/1/2021,BalancedBinaryTree启发，这样是n2复杂度，是top down，下面写bottom up的
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode A, TreeNode B) {
        if(root==null){
            return root;
        }
        if(root.val==A.val||root.val==B.val){
            return root;
        }
        if(exist(root.left,A)&&exist(root.right,B)){
            return root;
        }
        if(exist(root.right,A)&&exist(root.left,B)){
            return root;
        }
        TreeNode n1=lowestCommonAncestor3(root.left,A,B);

        if(n1!=null){
            return n1;
        }
        return lowestCommonAncestor3(root.right,A,B);

    }
    boolean exist(TreeNode root, TreeNode target){
        if(root==null){
            return false;
        }
        if(root.val==target.val){
            return true;
        }
        if(root.val>target.val){
            return exist(root.left,target);
        }
        return exist(root.right,target);
    }
//下面写bottom up的的，注意他的dfs部分是放在中间计算出left/right的时候进行的，然后不会再递归调用lowestCommonAncestor4的，BalancedBinaryTree也是这样，即结尾return那里不会再递归了
    public TreeNode lowestCommonAncestor4(TreeNode root, TreeNode A, TreeNode B) {
        if(root==null){
            return root;
        }
        if(root==A||root==B){
            return root;
        }
        TreeNode left=lowestCommonAncestor4(root.left,A,B);
        TreeNode right=lowestCommonAncestor4(root.right,A,B);
        if(left!=null&&right!=null){
            return root;
        }
        if(left!=null){
            return left;
        }
        return right;
    }

}
