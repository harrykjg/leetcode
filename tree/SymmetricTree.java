package tree;

public class SymmetricTree {
    //8/7/2021.就是那一个点的右和另一个的左比。比用level order好写多了
    public boolean isSymmetric(TreeNode root) {
        if (root==null){
            return true;
        }
        return helper(root.left,root.right);
    }
    boolean helper(TreeNode a,TreeNode b){
        if (a==null&&b==null){
            return true;
        }else if (a==null){
            return false;
        }else if (b==null){
            return false;
        }
        if (a.val!=b.val){
            return false;
        }
        return helper(a.left,b.right)&&helper(a.right,b.left);

    }
}
