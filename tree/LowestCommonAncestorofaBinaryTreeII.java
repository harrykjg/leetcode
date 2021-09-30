package tree;

public class LowestCommonAncestorofaBinaryTreeII {
    //9/21/2021 以为可以写出来，和以前的lca，只是找到left或者right的时候再去检测这个left或right子树是否包含2个节点，结果是不行的，因为以前的lca遇到root等于
    //p或q时就返回了，假如root时p或q，则直接返回root而没去检查root是否包含p和q，如果在这里加上检查root是否包含pq的话那么整个逻辑也不对了。
    //https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-ii/discuss/933835/Java.-Difference-from-236-is-you-need-to-search-the-entire-tree.
    //做法是看起来和lca差不多，但是很巧妙，关键是检测root是否等于p或q的判断放在了递归左边和右边的后面而不是前面。而且判断方法不是判断左右是否非空，而是
    //各自判断左右是否等于root.
    boolean pfound=false;
    boolean qfound=false;
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode rs=helper(root,p,q);
        if (pfound&&qfound){
            return rs;
        }
        return null;
    }
    TreeNode helper(TreeNode root, TreeNode p, TreeNode q){
        if(root==null){
            return null;
        }

        TreeNode left=helper(root.left,p,q);
        TreeNode right=helper(root.right,p,q);
        if(root==p){//这里在lca1里是先看左右是不是都是非空，是就返回root，而这里是分别判断root是否等于p或q
            pfound=true;
            return root;
        }
        if(root==q){
            qfound=true;
            return root;
        }
        if (left!=null&&right!=null){
            return root;
        }else if (left!=null){
            return left;
        }
        return right;
    }

    class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
  }
}
