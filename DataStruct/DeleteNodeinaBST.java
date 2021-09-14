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
//8/13/2021 还是很想的要换地址，那样很麻烦的。貌似一般要删的话都是node有父引用的。这里直接换值就好了。因此也不用需要父节点取操作当前节点，直接到当前节点操作就行
    public TreeNode deleteNode3(TreeNode root, int key) {
        if(root==null){
            return null;
        }
        if (root.val==key){
            if (root.right==null){
                return root.left;
            }
            TreeNode suc=successor3(root.right);
            root.val=suc.val;
            root.right=deleteNode3(root.right,suc.val);//这里不太好想，开始写的直接deleteNode没有再asign给root。right就错了
            return root;
        }
        if(root.val>key){
            root.left=deleteNode3(root.left,key);//开始写的没asign回给left也错了
        }else{
            root.right= deleteNode3(root.right,key);
        }
        return root;

    }
    TreeNode successor3(TreeNode n){
        if (n.left==null){
            return n;
        }
        return successor3(n.left);
    }
}
