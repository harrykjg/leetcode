package tree;

public class PopulatingNextRightPointersinEachNode {
    //7/4/2021写的很烂。但是写出来却挺神奇的
    //https://leetcode.com/problems/populating-next-right-pointers-in-each-node/discuss/37473/My-recursive-solution(Java)
    public Node connect(Node root) {
        if (root==null){
            return root;
        }
        helper(root.left,root.right);

        return root;
    }
    void helper(Node n1,Node n2){
        if (n1==null){
            return;
        }
        n1.next=n2;
        helper(n1.left,n1.right);
        helper(n2.left,n2.right);
        helper(n1.right,n2.left);//这里居然是三个helper
    }
}
class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}

