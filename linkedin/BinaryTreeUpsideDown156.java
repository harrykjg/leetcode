package linkedin;

import 灵神.链表二叉树回溯.树.TreeNode;

public class BinaryTreeUpsideDown156 {
    static void main() {

    }
    /*
    不会了，看回以前的
     不会是 这样的树，说了右节点没有孩子
           1
         2    3
       4   5
          6  7
     */
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if(root==null||root.left==null){
            return root;
        }
        TreeNode l=upsideDownBinaryTree(root.left);

        root.left.left=root.right;//一开始想的是l.left=root.right 为啥就不行呢，因为操作l的话这个l在最后一层是对的，但是返回上层
        root.left.right=root;   //之后他还是最后那一层的l，因此那样操作就错了
        root.left=null;
        root.right=null;

        return l;
    }
}
