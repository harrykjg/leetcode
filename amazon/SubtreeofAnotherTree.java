package amazon;

/**
 * Created by zhuyu on 12/31/19.
 */
public class SubtreeofAnotherTree {
    //写的烂，要再练
    public static void main (String[] a){
        TreeNode n=new TreeNode(3);
        n.left=new TreeNode(4);
        n.right=new TreeNode(5);
        n.left.left=new TreeNode(1);
        n.right.right=new TreeNode(2);
        TreeNode n2=new TreeNode(3);
        n2.left=new TreeNode(1);
        n2.right=new TreeNode(2);
        System.out.println(isSubtree(n,n2));

    }
    public static boolean isSubtree(TreeNode s, TreeNode t) {
        if(s==null&&t==null){
            return true;
        }
        if(t==null){
            return true;
        }
        if(s==null){
            return false;
        }
        if(helper(s,t)){
            return true;
        }

        return isSubtree(s.left,t)||isSubtree(s.right,t);

    }
    static boolean  helper(TreeNode s,TreeNode t){
        if (s == null && t == null){
            return true;
        }
        if (s == null || t == null) {
            return false;
        }
        if(s.val!=t.val){
            return false;
        }

        return helper(s.left,t.left)&&helper(s.right,t.right);
    }
}
class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
    }