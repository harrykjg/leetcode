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
}
