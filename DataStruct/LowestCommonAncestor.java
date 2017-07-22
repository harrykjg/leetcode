package DataStruct;

/**
 * Created by 502575560 on 7/14/17.
 */
public class LowestCommonAncestor {
    //真的面试时候还是写不对!我总是想着是contains左边或右边那个想法是不行的
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode A, TreeNode B){
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
        if(left!=null){
            return left;
        }
        return right;
}
class TreeNode {
    public int val;
    public TreeNode left, right;
    public TreeNode(int val) {
         this.val = val;
         this.left = this.right = null;
     }
}
}
