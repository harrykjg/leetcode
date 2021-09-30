package tree;

import java.util.*;

public class FindLargestValueinEachTreeRow {
    //9/16/2021根本不需要pq。直接每一层找最大就完了。
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> rs=new ArrayList<>();
        if (root==null){
            return rs;
        }
        Queue<TreeNode> q=new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()){
            int size=q.size();
            int max=Integer.MIN_VALUE;
            for (int i=0;i<size;i++){
                TreeNode cur=q.poll();
                max=Math.max(max,cur.val);
                if (cur.left!=null){
                    q.offer(cur.left);
                }
                if(cur.right!=null){
                    q.offer(cur.right);
                }
            }
            rs.add(max);

        }
        return rs;
    }
}
