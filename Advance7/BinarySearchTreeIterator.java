package Advance7;

import java.util.Stack;

/**
 * Created by 502575560 on 8/16/17.
 */
public class BinarySearchTreeIterator {
    //自己写的,改了几次,一是忘了把left加进去之后要把left设成null,而是忘了pop出来之后要看这个node是否有right,有则加进st里
    Stack<TreeNode> st=new Stack<>();
    public BinarySearchTreeIterator(TreeNode root) {
        if(root==null){
            return;
        }
        st.push(root);
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        while(!st.isEmpty()){
            TreeNode node=st.peek();
            if(node.left==null){
                return true;
            }else{
                st.push(node.left);
                node.left=null;
            }
        }
        return false;

    }

    /** @return the next smallest number */
    public int next() {
        TreeNode n=st.pop();
        if(n.right!=null){
            st.push(n.right);
        }
        return n.val;
    }
}
class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
 }
