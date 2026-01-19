package 灵神.链表二叉树回溯.树.插入删除;

import 灵神.链表二叉树回溯.树.TreeNode;

public class TrimaBinarySearchTree669 {
    //12/25/2025,题目看起来不好想，写起来挺容易
    public TreeNode trimBST(TreeNode root, int low, int high) {
        if(root==null){
            return root;
        }
        if(root.val>high){
            return trimBST(root.left,low,high);
        }
        if(root.val<low){
            return trimBST(root.right,low,high);
        }
        root.left=trimBST(root.left,low,high);
        root.right=trimBST(root.right,low,high);
        return root;
    }
}
