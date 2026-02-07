package linkedin;

import 灵神.链表二叉树回溯.树.TreeNode;

public class DeleteNodeinaBST450 {
    static void main() {

    }
    //写的很烂，看回以前的应该用recursion写。我想的删除一个点时，要先找到父节点再判断是左边该删还是右边该删，很麻烦，应该不用通过父节点，直接
    //调用递归就完事,还是容易错
    //https://leetcode.cn/problems/delete-node-in-a-bst/solutions/1529700/shan-chu-er-cha-sou-suo-shu-zhong-de-jie-n6vo/
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root==null){
            return null;
        }
        if (root.val==key){
            if(root.right==null){
                return root.left;
            }
            if (root.left==null){
                return root.right;
            }
            TreeNode suc=findSuccessor(root.right);
            suc.left=root.left;
            if(root.right!=suc){//不用if条件也行，但是你要先deleteNode(root.right,suc.val);否则就死循环
                suc.right=root.right;
            }
            root.right=deleteNode(root.right,suc.val);
            return suc;
        }else if(root.val<key){
            root.right=deleteNode(root.right,key);
            return root;//这里开始写的是return deleteNode(root.right,key)，是不行的
        }else {
             root.left=deleteNode(root.left,key);
             return root;
        }
    }
    TreeNode findSuccessor(TreeNode node){
        if (node==null){
            return null;
        }
        while (node!=null&&node.left!=null){//容易漏了node.left!=null
            node=node.left;
        }
        return node;
    }
}
