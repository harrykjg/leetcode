package tree;

import java.util.LinkedList;
import java.util.Queue;

public class MaximumDepthofBinaryTree {
    public int maxDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        Queue<TreeNode> q=new LinkedList<>();
        q.offer(root);
        int rs=0;
        int count=1;
        int count2=0;
        while (!q.isEmpty()){
            TreeNode node=q.poll();
            count--;

            if(node.left!=null){
                q.offer(node.left);
                count2++;
            }
            if(node.right!=null){
                q.offer(node.right);
                count2++;
            }
            if(count==0){
                rs++;
                count=count2;
                count2=0;
            }

        }
        return rs;

    }
    //8/13/2021以前居然写的那么烂,其实不是，只是用iterative而已！
//https://leetcode.com/problems/maximum-depth-of-binary-tree/discuss/34238/Clean-Java-Iterative-Solution 看他下面第二个评论用的queue的
// iterative方法挺好 求的其实是深度用，像bfs
    public int maxDepth2(TreeNode root) {
        if(root==null){
            return 0;
        }
        int left=maxDepth(root.left);
        int right=maxDepth(root.right);
        return Math.max(left,right)+1;
    }
    class TreeNode {
        protected int val;
        protected TreeNode left;
        protected TreeNode right;
        TreeNode() {}
        protected TreeNode(int val) { this.val = val; }
        protected TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}

