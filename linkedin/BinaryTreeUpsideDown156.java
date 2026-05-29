package linkedin;

import 灵神.链表二叉树回溯.树.TreeNode;

import java.util.Stack;

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

    //5/28/2026还是不太好写，想的是用stack装一个pari[parent,node】这样就可以操作了，但是还是没写对
    public TreeNode upsideDownBinaryTree2(TreeNode root) {
        Stack<Pair> st=new Stack<>();
        TreeNode parent=null;
        TreeNode cur=root;
        while (cur!=null){
            st.push(new Pair(parent,cur));
            parent=cur;
            cur=cur.left;
        }
        TreeNode head=null;
        while (!st.isEmpty()){
            Pair p=st.pop();
            if(p.parent==null){
                break;
            }
            cur=p.node;
            if(head==null){
                head=cur;
            }
            cur.left=parent.right;
            cur.right=parent;
        }
        return head;
    }
}
class Pair{
    TreeNode parent;
    TreeNode node;
    public  Pair(TreeNode p, TreeNode n){
        parent=p;
        node=n;
    }
}
