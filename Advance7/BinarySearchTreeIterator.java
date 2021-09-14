package Advance7;

import apple.laf.JRSUIUtils;

import java.util.Stack;

/**
 * Created by 502575560 on 8/16/17.
 */
public class BinarySearchTreeIterator {

    //https://leetcode.com/problems/binary-search-tree-iterator/discuss/52526/Ideal-Solution-using-Stack-(Java)  confluent面的时候说不能改变树结构，没写对，这个代码是改树结构的
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

//6/23/2021基本一次过了，但是这是改了树结构的，没啥用，写个不改树结构的
//其实也不难，只要确认st的peek出来的node不管有没有left都正是要返回的node，不需要check是不是左边空才要返回。要做到这样也不难，就是push节点时要一直push完这个节点整个
//左边的path
//https://www.lintcode.com/problem/86/solution/16537
class BinarySearchTreeIterator3 {
    Stack<TreeNode> st=new Stack<>();
    public BinarySearchTreeIterator3(TreeNode root) {
        // do intialization if necessary
        TreeNode cur=root;
        while (cur!=null){
            st.push(cur);
            cur=cur.left;
        }

    }

    /*
     * @return: True if there has next node, or false
     */
    public boolean hasNext() {
        // write your code here
        return !st.isEmpty();
    }

    /*
     * @return: return next node
     */
    public TreeNode next() {
        // write your code here
        TreeNode cur=st.pop();

        if (cur.right!=null){
            addToStack(cur.right);
        }
        return cur;

    }
    void  addToStack(TreeNode node){
        TreeNode cur=node;
        while (cur!=null){
            st.push(cur);
            cur=cur.left;
        }
    }
}

class TreeNode {
     int val;
     TreeNode left;
     TreeNode right;
     TreeNode(int x) { val = x; }
 }
