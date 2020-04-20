package DataStruct;

/**
 * Created by 502575560 on 7/14/17.
 */
public class LowestCommonAncestor {
    //真的面试时候还是写不对!我总是想着是contains左边或右边那个想法是不行的
    //http://blog.csdn.net/chen895281773/article/details/10697575
    //思路：递归的思想，如果当前节点的左子树和右子树各包括一个节点，则该节点就为最近公共祖先；如果当前节点等于其中的一个节点，
    // 则当前节点为最近公共祖先；如果当前节点的左子树或者右子树包括两个节点，则需要递归求该节点的左子树或者有子树。
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
//08/20/2017
//还是不熟,不能很好的理解,靠记忆写的
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode A, TreeNode B){
        if(root==null){
            return null;
        }
        if(root==A||root==B){
            return root;
        }
        TreeNode l=lowestCommonAncestor2(root.left,A,B);
        TreeNode r=lowestCommonAncestor2(root.right,A,B);
        if(l!=null&&r!=null){
            return root;
        }
        if(l==null&&r==null){
            return null;
        }
        return l==null?r:l;

    }
//04/15/2020
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode A, TreeNode B){
        if(root==null){
            return null;
        }
        if(root==A){
            return A;
        }
        if(root==B){
            return B;
        }
        TreeNode left=lowestCommonAncestor3(root.left,A,B);
        TreeNode right=lowestCommonAncestor3(root.right,A,B);
        if (left!=null||right!=null){
            return root;
        }
        if(left!=null){
            return left;
        }
        return right;

    }
}
