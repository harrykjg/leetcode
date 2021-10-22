\package tree;


import java.util.HashSet;
import java.util.Set;

public class LowestCommonAncestorofaBinaryTreeIV {
    //8/5/2021
    //https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iv/discuss/957727/Java-Time-O(n)-Space-O(n)
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        if (root==null){
            return root;
        }
        Set<TreeNode> set=new HashSet<>();
        for (TreeNode n:nodes){
            set.add(n);
        }
        return helper(root,set);
    }

    TreeNode helper(TreeNode root,Set<TreeNode> set){
        if (set.contains(root)){
            return root;
        }
        if (root==null){
            return null;
        }
        TreeNode left=helper(root.left,set);
        TreeNode right=helper(root.right,set);
        if (left!=null&&right!=null){
            return root;
        }
        if (left!=null){
            return left;
        }
        if (right!=null){
            return right;
        }
        return null;
    }
}
