package tree;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class SmallestSubtreewithalltheDeepestNodes {
    //10/11/2021 和LowestCommonAncestorofDeepestLeaves 一样
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        Queue<TreeNode> q=new LinkedList<>();
        q.offer(root);
        HashSet<TreeNode> set=new HashSet<>();
        while(!q.isEmpty()){
            int size=q.size();
            for (int i=0;i<size;i++){
                TreeNode cur=q.poll();
                set.add(cur);
                if (cur.left!=null){
                    q.offer(cur.left);
                }
                if (cur.right!=null){
                    q.offer(cur.right);
                }
            }
            if (q.isEmpty()){
                break;
            }else{
                set.clear();
            }
        }
        return find(root,set);
    }
    TreeNode find(TreeNode root, Set<TreeNode> set){
        if (root==null){
            return root;
        }
        if (set.contains(root)){
            return root;
        }
        TreeNode l=find(root.left,set);
        TreeNode r=find(root.right,set);
        if (l!=null&&r!=null){
            return root;
        }
        if (l!=null){
            return l;
        }
        return r;
    }

    class TreeNode{
        TreeNode left;
        TreeNode right;
    }
}
