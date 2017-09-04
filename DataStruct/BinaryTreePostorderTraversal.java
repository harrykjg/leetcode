package DataStruct;

import apple.laf.JRSUIUtils;

import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by 502575560 on 8/20/17.
 */
public class BinaryTreePostorderTraversal {
    //recursive
    public ArrayList<Integer> postorderTraversal(TreeNode root) {
        ArrayList<Integer> rs=new ArrayList<>();
        helper(root,rs);
        return rs;
    }
    void helper(TreeNode n,ArrayList<Integer> rs){
        if(n==null){
            return;
        }
        helper(n.left,rs);
        helper(n.right,rs);
        rs.add(n.val);

    }

    //iterative
    public ArrayList<Integer> postorderTraversal2(TreeNode root) {
        ArrayList<Integer> rs=new ArrayList<>();
        if(root==null){
            return rs;
        }
        Stack<TreeNode> st=new Stack<>();
        st.push(root);
        while (!st.isEmpty()){
            TreeNode n=st.peek();
            if(n.left==null&&n.right==null){
                st.pop();
                rs.add(n.val);
            }
            if(n.right!=null){
                st.push(n.right);
                n.right=null;
            }
            if(n.left!=null){
                st.push(n.left);
                n.left=null;
            }
        }
        return rs;
    }
}
