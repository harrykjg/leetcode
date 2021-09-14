package tree;

import java.util.Stack;

public class BinaryTreeUpsideDown {
    //7/16/2021,居然一次过，关键先理解题目，其实就是只要操作最左边一条树路径就行了，右边不用管
    //https://leetcode.com/problems/binary-tree-upside-down/discuss/49406/Java-recursive-(O(logn)-space)-and-iterative-solutions-(O(1)-space)-with-explanation-and-figure
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root==null||root.left==null){
            return root;
        }
        TreeNode rs=findLeftMost(root);//先找到最左边的点作为结果，那样helper方法就不用返回任何东西了
        helper(root);

        return rs;
    }
    void helper(TreeNode root){
        if (root==null||root.left==null){
            return ;
        }
        helper(root.left);
        root.left.left=root.right;
        root.left.right=root;
        root.left=null;
        root.right=null;
    }
    TreeNode findLeftMost(TreeNode root){
        if (root==null){
            return root;
        }
        if (root.left==null){
            return root;
        }
        return findLeftMost(root.left);
    }
    //8/12/2021自己这次想的是用stack居然也是一次过。就是用stack装左边的路径，然后2个2个node操作，先pop出来最左边的node，然后和stack的头上node操作，
    //完了stack头上的pop出来，和下一个stack头上的node操作
    public TreeNode upsideDownBinaryTree2(TreeNode root) {
        if(root==null){
            return null;
        }
        Stack<TreeNode> st=new Stack<>();
        TreeNode cur=root;
        TreeNode rs=null;
        while(cur!=null){
            st.push(cur);
            cur=cur.left;
        }
        rs=st.pop();
        cur=rs;
        while(!st.isEmpty()){
            TreeNode temp=st.pop();
            cur.left=temp.right;
            cur.right=temp;
            temp.left=null;
            temp.right=null;
            cur=temp;
        }
        return rs;

    }
}
