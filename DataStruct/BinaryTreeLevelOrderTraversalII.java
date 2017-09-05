package DataStruct;

import java.util.*;

/**
 * Created by 502575560 on 9/4/17.
 */
public class BinaryTreeLevelOrderTraversalII {
    public static void main(String[] args){
        TreeNode n=new TreeNode(3);
        n.left=new TreeNode(9);
        n.right=new TreeNode(20);
        n.right.left=new TreeNode(15);
        n.right.right=new TreeNode(7);
        levelOrderBottom(n);
    }
    //九章答案都是先正序拿出来再反转返回,貌似没有容易的更好的方法
    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        // write your code here
        List<List<Integer>> rs=new ArrayList<>();
        List<Integer> al=new ArrayList<>();
        if(root==null){
            return rs;
        }
        int count1=1;
        int count2=0;
        Queue<TreeNode> q=new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()){

            TreeNode temp=q.poll();
            count1--;
            al.add(temp.val);
            if(temp.left!=null){
                count2++;
                q.offer(temp.left);
            }
            if(temp.right!=null){
                count2++;
                q.offer(temp.right);
            }
            if(count1==0){
                count1=count2;
                count2=0;
                rs.add(al);
                al=new ArrayList<>();
            }
        }
        Collections.reverse(rs);
        return rs;
    }
}
