package 灵神.链表二叉树回溯.树.BFS;

import 灵神.链表二叉树回溯.树.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class CheckCompletenessofaBinaryTree958 {
    public boolean isCompleteTree(TreeNode root) {
        Queue<TreeNode> q=new LinkedList<>();
        q.offer(root);
        boolean flag=false;
        while (!q.isEmpty()){
            TreeNode cur=q.poll();
            if(flag&&cur!=null){
                return false;
            }
            if(cur==null){
                flag=true;
            }else{
                q.offer(cur.left);
                q.offer(cur.right);
            }

        }
        return true;
    }
}
