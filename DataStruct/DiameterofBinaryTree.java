package DataStruct;

/**
 * Created by yufengzhu on 7/26/18.
 */
public class DiameterofBinaryTree {
    public static void main(String[] a){
        DiameterofBinaryTree db=new DiameterofBinaryTree();
        TreeNode n=new TreeNode(1);
        n.left=new TreeNode(2);
        n.right=new TreeNode(3);
        n.left.left=new TreeNode(4);
        n.left.right=new TreeNode(5);
        db.diameterOfBinaryTree(n);
    }
    //还写的不顺，要debug才写对，要记
    int rs=0;
    public int diameterOfBinaryTree(TreeNode root) {
        if(root==null){
            return 0;
        }
        helper(root);
        return rs;
    }
    int helper(TreeNode n){//一开始还写了另一个参数用来记录深度，其实根本用不着，还是不熟
        if(n==null){
            return 0;
        }
        int left=helper(n.left);
        int right=helper(n.right);
        rs=Math.max(left+right,rs);
        return Math.max(left+1,right+1);//这里开始少了left+1就不行了

    }

}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TreeNode treeNode = (TreeNode) o;

        if (val != treeNode.val) return false;
        if (!left.equals(treeNode.left)) return false;
        return right.equals(treeNode.right);

    }

}
