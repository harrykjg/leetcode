package Advance7;

import apple.laf.JRSUIUtils;

import java.util.Stack;

/**
 * Created by 502575560 on 8/16/17.
 */
public class BinarySearchTreeIterator {

    //https://leetcode.com/problems/binary-search-tree-iterator/discuss/52526/Ideal-Solution-using-Stack-(Java)  confluent面的时候说不能改变树结构，没写对，这个代码是不改树结构的
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
    public TreeNode next() {
        TreeNode n=st.pop();
        if(n.right!=null){
            st.push(n.right);
        }
        return n;
    }
}
//6／28／2018看到空间要O(h）记得是用stack装左子树,还是写的不好,有点乱但是也过了
class BinarySearchTreeIterator2 {
    Stack<TreeNode> st=new Stack<>();
    public BinarySearchTreeIterator2(TreeNode root) {
        if(root==null){
            return;
        }
        st.push(root);

    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !st.isEmpty();

    }

    /** @return the next smallest number */
    public TreeNode next() {
        TreeNode temp=st.peek();

        while (temp.left!=null){
            TreeNode temp2=temp.left;
            temp.left=null;
            st.push(temp2);
            temp=temp2;
        }
        TreeNode rs=st.pop();
        if(rs.right!=null){
            st.push(rs.right);
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
