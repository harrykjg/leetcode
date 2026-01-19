package 灵神.链表二叉树回溯.树;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinaryTreeInorderTraversal94 {
    //12/24/2025 不要看以前的，都是把叶子设成null的
    //可以看看别人更规整的写法
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> rs=new ArrayList<>();
        if(root==null){
            return rs;
        }
        Stack<TreeNode> st=new Stack<>();
        TreeNode cur=root;
        while(cur!=null){//不知道为啥这次感觉印象中是先直接把左边一溜全都放进st里
            st.push(cur);
            cur=cur.left;
        }
        while(!st.isEmpty()){

            cur=st.pop();
            rs.add(cur.val);

            TreeNode next=cur.right;
            while(next!=null){
                st.push(next);
                next=next.left;
            }

        }


        return rs;
    }

}
