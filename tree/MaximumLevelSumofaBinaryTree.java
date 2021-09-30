package tree;

import java.util.LinkedList;
import java.util.Queue;

public class MaximumLevelSumofaBinaryTree {
    //9/16/2021
    public int maxLevelSum(TreeNode root) {
        int rs=0;
        if (root==null){
            return rs;
        }
        Queue<TreeNode> q=new LinkedList<>();
        q.offer(root);
        int step=1;
        int max=Integer.MIN_VALUE;
        while (!q.isEmpty()){
            int size=q.size();
            int sum=0;
            for(int i=0;i<size;i++){
                TreeNode cur=q.poll();
                sum+=cur.val;
                if(cur.left!=null){
                    q.add(cur.left);
                }
                if(cur.right!=null){
                    q.offer(cur.right);
                }
            }
            if (sum>max){
                max=sum;
                rs=step;
            }
            step++;
        }
        return rs;
    }
}
