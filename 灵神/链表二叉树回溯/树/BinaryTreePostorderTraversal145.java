package 灵神.链表二叉树回溯.树;

import java.util.*;

public class BinaryTreePostorderTraversal145 {
    //12/24/2025 这次想的post order要rever才行的，也是可以的，以前的更不对，是把左右子树设成null的
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> rs=new ArrayList<>();
        TreeNode cur=root;
        Stack<TreeNode> st=new Stack<>();
        if(root==null){
            return rs;
        }
        st.push(root);
        while(!st.isEmpty()){
            cur=st.pop();
            rs.add(cur.val);
            if(cur.left!=null){
                st.push(cur.left);
            }
            if(cur.right!=null){
                st.push(cur.right);
            }

        }

        Collections.reverse(rs);
        return rs;
    }
}
