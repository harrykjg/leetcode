package DataStruct;

import java.util.*;

/**
 * Created by yufengzhu on 7/26/18.
 */
public class AverageofLevelsinBinaryTree {
    //改了一次过了
    //https://leetcode.com/problems/average-of-levels-in-binary-tree/solution/  还有dfs的解法
    public List<Double> averageOfLevels(TreeNode root) {
        Queue<TreeNode> q=new LinkedList<>();
        q.offer(root);
        int count1=1;
        int count2=0;
        double curSum=0;
        int num=count1;
        ArrayList<Double> rs=new ArrayList<>();
        while (!q.isEmpty()){
            TreeNode n=q.poll();
            curSum+=n.val;
            count1--;
            if(n.left!=null){
                q.offer(n.left);
                count2++;
            }
            if(n.right!=null){
                q.offer(n.right);
                count2++;
            }
            if(count1==0){
                rs.add(curSum/num);
                count1=count2;
                num=count1;
                count2=0;
                curSum=0;
            }

        }
        return rs;
    }
}
