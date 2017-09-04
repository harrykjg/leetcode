package DataStruct;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.TreeMap;

/**
 * Created by 502575560 on 8/20/17.
 */
public class BinaryTreePreorderTraversal {
    //recursive
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> rs=new ArrayList<>();
        helper(root,rs);
        return rs;
    }
    void helper(TreeNode n,List<Integer> rs){
        if(n==null){
            return;
        }
        rs.add(n.val);
        helper(n.left,rs);
        helper(n.right,rs);
    }

    //迭代,想了一下一次过,用stack,并且先push右子树再push左子树
    public List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> rs=new ArrayList<>();
        if(root==null){
            return rs;
        }
        Stack<TreeNode> st=new Stack<>();
        st.push(root);
        while (!st.isEmpty()){
            TreeNode n=st.pop();
            rs.add(n.val);
            if(n.right!=null){
                st.push(n.right);
            }
            if(n.left!=null){
                st.push(n.left);
            }
        }
        return rs;
    }
}
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}