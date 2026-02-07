package linkedin;

import 灵神.链表二叉树回溯.树.TreeNode;

public class LowestCommonAncestorofBinarySearchTree235 {
    //居然一次过
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root==null){
            return null;
        }
        if(root==p||root==q){
            return root;
        }
        if(root.val>p.val&&root.val>q.val){
            return lowestCommonAncestor(root.left,p,q);
        }else if(root.val<p.val&&root.val<q.val){
            return lowestCommonAncestor(root.right,p,q);
        }
        return root;
    }
    //iterative 原来也就差不多
    //https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/solutions/1156398/java-easy-solutionrecursive-iterative-0m-f6ol/
    public TreeNode lowestCommonAncestor2(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode cur=root;
        while (cur!=null){
            if(cur.val<p.val&&cur.val<q.val){
                cur=cur.right;
            }else if(cur.val>p.val&&cur.val>q.val){
                cur=cur.left;
            }
            return cur;
        }
        return cur;
    }
}
