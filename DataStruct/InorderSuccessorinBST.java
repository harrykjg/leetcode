package DataStruct;

import java.util.Stack;

/**
 * Created by yufengzhu on 7/25/18.
 */
public class InorderSuccessorinBST {
    //inorder 再找就不写了，好的方法还写不出来,想用stack但是写不出来,记不用stack的方法
    //https://blog.csdn.net/fightforyourdream/article/details/19860143
    //https://segmentfault.com/a/1190000005797856
    //https://www.cnblogs.com/reboot329/articles/6009917.html
    //12/19/2020想了下觉得不太难，中序的下一个就是该点的父节点，或者右子树的最左节点
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if(root==null){
            return null;
        }
        if(p.right!=null){
            return leftMost(p.right);
        }

        TreeNode rs=null;
        while (root!=null){//真不好写，要被
            if(root.val>p.val){
                rs=root;
                root=root.left;
            }else if(root.val<p.val){
                root=root.right;
            }else{
                break;
            }

        }
        return rs;

    }
    TreeNode leftMost(TreeNode n){
        TreeNode rs=n;
        while (rs.left!=null){
            rs=rs.left;

        }
        return rs;
    }

    //9/19/2021  自己想的，就是用stack inorder。找到p之后，设一个flag=true，则下一个pop出来的就是successor。这样应该是没利用bst，所以会慢一些
    public TreeNode inorderSuccessor2(TreeNode root, TreeNode p) {
        Stack<TreeNode> st=new Stack<>();
        if (root==null){
            return null;
        }
        boolean found=false;
        while (root!=null){
            st.push(root);
            root=root.left;
        }
        while (!st.isEmpty()){
            TreeNode cur=st.pop();
            if (found==true){
                return cur;
            }
            if (cur.val==p.val){
                found=true;
            }
            if (cur.right!=null){
                TreeNode next=cur.right;
                while (next!=null){
                    st.push(next);
                }
            }
        }
        return null;
    }
}
