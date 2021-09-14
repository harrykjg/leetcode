package DataStruct;

import java.util.Map;

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
//8/8/2021  想的就是左边depth加右边depth，结果是不对的。其实区别很小，关键是最长的path不一定是经过root，而是可能是在非跟节点的两条子树形成的路径,
    //所以说要在depth里面得到左右深度的时候就检测一下。
    int rs2=0;
    public int diameterOfBinaryTree2(DataStruct.TreeNode root) {
        if (root==null){
            return 0;
        }
        depth(root);
        return rs;
    }
    int depth(DataStruct.TreeNode root ){
        if (root==null){
            return 0;
        }
        int left=depth(root.left);
        int right=depth(root.right);
        rs2= Math.max(rs2,left+right);
        return Math.max(left,right)+1;
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
