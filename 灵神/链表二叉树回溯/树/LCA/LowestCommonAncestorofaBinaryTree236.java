package 灵神.链表二叉树回溯.树.LCA;

public class LowestCommonAncestorofaBinaryTree236 {
    static void main() {

    }
    //12/24/2025
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root==null){
            return null;
        }
        if(root==p||root==q){
            return root;
        }
        TreeNode left=lowestCommonAncestor(root.left,p,q);
        TreeNode right=lowestCommonAncestor(root.right,p,q);
        if(left!=null&&right!=null){
            return root;
        }
        if(left==null&&right!=null){
            return right;
        }
        if(right==null&&left!=null){
            return left;
        }
        return null;
    }
}
