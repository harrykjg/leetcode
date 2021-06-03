package DataStruct.tree;

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
}

 class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode() {}
      TreeNode(int val) { this.val = val; }
      TreeNode(int val, TreeNode left, TreeNode right) {
          this.val = val;
          this.left = left;
          this.right = right;
      }
  }
