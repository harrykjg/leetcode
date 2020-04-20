package DataStruct;

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
        if(root==A||root==B){
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
}
