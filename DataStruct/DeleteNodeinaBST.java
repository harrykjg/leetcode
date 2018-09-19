package DataStruct;

import apple.laf.JRSUIUtils;

/**
 * Created by yufengzhu on 9/3/18.
 */
public class DeleteNodeinaBST {
    public static void main(String[] args){
        DeleteNodeinaBST dn=new DeleteNodeinaBST();
        TreeNode n=new TreeNode(5);
        n.left=new TreeNode(3);
        n.right=new TreeNode(6);
        n.left.left=new TreeNode(2);
        n.left.right=new TreeNode(4);
        dn.deleteNode2(n,3);
    }
    //写的不好，要练,我想的是要把要删除节点地址替换的，他们都是直接换个val就算了
    //https://blog.csdn.net/byamao1/article/details/53067478
    //https://leetcode.com/problems/delete-node-in-a-bst/discuss/93378/an-easy-understanding-oh-time-o1-space-java-solution
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root==null){
            return null;
        }

        if(root.val>key){
            root.left=deleteNode(root.left,key);
            return root;
        }
        if(root.val<key){
            root.right=deleteNode(root.right,key);
            return root;
        }
        if(root.right!=null){
            TreeNode replacement=helper(root.right);
            replacement.right=deleteNode(root.right,replacement.val);//这个之前写的先replacement.left=root.left就错了，debug才能发现，容易错
            replacement.left=root.left;
            return replacement;
        }else{
            return root.left;
        }
    }
    TreeNode helper(TreeNode root){
        if(root==null){
            return null;
        }
        TreeNode cur=root;
        while (cur.left!=null){
            cur=cur.left;
        }
        return cur;

    }
//9/11/2018，写的还凑活，就是重复写了个方法，应该可以和deleteNode2融合成一个
    public TreeNode deleteNode2(TreeNode root, int key) {
        if(root==null){
            return root;
        }
        if(root.val==key){
            if(root.right==null){
                root=root.left;
                return root;
            }else{
                TreeNode next=successor(root.right);
                root.val=next.val;
                root.right=deleteNode2(root.right,next.val);
                return root;
            }
        }
        if(root.val>key){
            root.left=deleteNode2(root.left,key);
        }else{
            root.right=deleteNode2(root.right,key);
        }
        return root;
    }

    TreeNode successor(TreeNode root){
        while (root.left!=null){
            root=root.left;
        }
        return root;
    }
}
