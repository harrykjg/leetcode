package tree;

import java.util.LinkedList;
import java.util.Queue;

public class CompleteBinaryTreeInserter {
//9/12/2021  就是level order，先把初始的root按level order放进q里，这里得借助另一个q2来做。然后insert的时候就判断q里peek头上的node是否有左右孩子，
    //没有则插入，有左右说明满了，把他poll出来，看下一个q里的头上可否插入。实际上就是下面帖子说的init o（n），插入o（1）
    //https://leetcode.com/problems/complete-binary-tree-inserter/discuss/178427/JavaPython-3-2-BFS-straightforward-codes-Initialization-and-insert-time-O(1)-respectively.
    Queue<TreeNode> q=new LinkedList<>();
    TreeNode root;
    public void CBTInserter(TreeNode root) {
        Queue<TreeNode> q2=new LinkedList<>();
        q2.offer(root);
        while (!q2.isEmpty()){
            TreeNode cur=q2.poll();
            q.offer(cur);
            if (cur.left!=null){
                q2.offer(cur.left);
                q.offer(cur.left);
            }
            if(cur.right!=null){
                q2.offer(cur.right);
                q.offer(cur.right);
            }
        }
        this.root=root;
    }

    public int insert(int val) {
        while (!q.isEmpty()){
            TreeNode cur=q.peek();
            if (cur.left==null){
                cur.left=new TreeNode(val);
                q.offer(cur.left);
                return cur.val;
            }else if (cur.right==null){
                cur.right=new TreeNode(val);
                q.offer(cur.right);
                q.poll();
                return cur.val;
            }else {
                q.poll();
                return insert(val);
            }
        }
        return 1;
    }

    public TreeNode get_root() {
        return root;
    }
}
