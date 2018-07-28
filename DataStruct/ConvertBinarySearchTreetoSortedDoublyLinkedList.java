package DataStruct;

import java.util.Stack;

/**
 * Created by yufengzhu on 7/26/18.
 */
public class ConvertBinarySearchTreetoSortedDoublyLinkedList {
    //还是很难写
    //https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/discuss/149151/Concise-Java-solution-Beats-100
    Node head;
    Node end;
    public Node treeToDoublyList(Node root) {
        if(root==null){
            return head;
        }
        Stack<Node> st=new Stack<>();
        Node successor=null;
        st.push(root);
        while (!st.isEmpty()){
            Node cur=st.peek();
            if(cur.left!=null){
                successor=cur;
                st.push(cur.left);
                cur.left=null;
            }else{
                st.pop();
                if(cur.right==null){
                    cur.right=successor;
                    cur.right.left=cur;
                }else {
                    st.push(cur.right);
                }
            }

        }

    }
    Node leftmost(Node n){
        Node nn=n;
        while (nn.left!=null){
            nn=nn.left;
        }
        return nn;
    }
    class Node {
        public int val;
        public Node left;
        public Node right;

        public Node() {}

        public Node(int _val,Node _left,Node _right) {
            val = _val;
            left = _left;
            right = _right;
        }
    }
}
