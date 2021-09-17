package SomeInterviews.doordash;

import java.util.ArrayList;
import java.util.List;

public class TreeMximumPath {
    public static void main(String[] args){
        TreeNode root=new TreeNode(5,false);
        root.left=new TreeNode(2,false);
        root.left.left=new TreeNode(100,true);
        root.left.right=new TreeNode(5,true);
        root.right=new TreeNode(0,false);
        root.right.right=new TreeNode(55,false);
        root.right.left=new TreeNode(14,true);
        TreeMximumPath tm=new TreeMximumPath();
        System.out.println(tm.maxPath(root));

    }
    //https://www.1point3acres.com/bbs/thread-796259-1-1.html
    int rs=0;
    public int maxPath(TreeNode root){
        if (root==null){
            return rs;
        }
        helper(root);
        return rs;
    }
    TreeNode helper(TreeNode node){
        if (node==null){
            return new TreeNode(0,false);//这里不返回null，而是返回这个，这样就省了上层检测左右是否为空再检测是否active了，直接检测是否active
        }
        TreeNode left=helper(node.left);
        TreeNode right=helper(node.right);

        if (left.active&&right.active){
            rs=Math.max(rs,node.value+left.value+right.value);
            return new TreeNode(Math.max(left.value,right.value)+node.value,true);
        }
        if (left.active){
            rs=Math.max(rs,node.value+left.value);
            return new TreeNode(node.value+left.value,true);
        }
        if (right.active){
            rs=Math.max(rs,node.value+right.value);
            return new TreeNode(node.value+right.value,true);
        }
        if (node.active){
            rs=Math.max(rs,node.value);
        }
        return new TreeNode(node.value,node.active?true:false);
    }


    static class TreeNode {
        Integer value;
        TreeNode left;
        TreeNode right;
        boolean active;
        public TreeNode(Integer value,boolean active) {
            this.value = value;
            this.active=active;
        }
    }
}
