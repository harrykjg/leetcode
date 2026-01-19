package 灵神.链表二叉树回溯.树;

import 灵神.链表二叉树回溯.链表.ListNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversal102 {
    static void main() {

    }
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> rs=new ArrayList<>();
        Queue<TreeNode> q=new LinkedList<>();
        if(root==null){
            return rs;
        }
        q.offer(root);
        while (!q.isEmpty()){
            List<Integer> al=new ArrayList<>();
            int size=q.size();
            for(int i=0;i<size;i++){
                TreeNode cur=q.poll();
                al.add(cur.val);
                if(cur.left!=null){
                    q.offer(cur.left);
                }
                if(cur.right!=null){
                    q.offer(cur.right);
                }
            }
            rs.add(al);
        }
        return rs;
    }

}
