package tree;

public class LowestCommonAncestorofDeepestLeaves {
    //9/18/2021只能想到先搞出所有的最深的节点放进一个集合，再找他们的acestor,应该和Lowest Common Ancestor of a Binary Tree IV 一样
    //https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/discuss/334716/No-new-code!-Reusing-codes-from-level-Order-Traversal-and-lowest-common-ancestor-in-binary-tree!
    //上面这个链接的好理解一些，用level order，找到最后一层的第一个和最后一个节点。然后用这两个节点找lca就行了。不管这两个节点中间有没有别的节点，这一头一尾的节点
    //肯定是分的最开的两个。
    //https://leetcode.com/problems/lowest-common-ancestor-of-deepest-leaves/discuss/334577/JavaC%2B%2BPython-Two-Recursive-Solution
    //这个更巧妙，直接求depth的同时也求了lca了；
    int max=0;
    TreeNode rs;
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        helper(root,0);
        return rs;
    }
    int helper(TreeNode node,int depth){
        max=Math.max(max,depth);
        if (node==null){
            return depth;
        }
        int ld=helper(node.left,depth+1);
        int rd=helper(node.right,depth+1);
        if (ld==rd&&ld==max){
            rs=node;
        }
        return Math.max(ld,rd);
    }

    class TreeNode{
        TreeNode left;
        TreeNode right;
    }
}
