package tree;

public class PopulatingNextRightPointersinEachNode {
    //7/4/2021写的很烂。但是写出来却挺神奇的
    //https://leetcode.com/problems/populating-next-right-pointers-in-each-node/discuss/37461/Java-solution-with-O(1)-memory%2B-O(n)-time
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

    //8/25/2021
    public Node connect2(Node root) {
        if(root==null){
            return null;
        }
        helper2(root);
        return root;
    }
    void helper2(Node root){
        if(root==null){
            return;
        }
        if(root.right!=null&&root.next!=null){//这两个if调换也行，
            root.right.next=root.next.left;
        }
        if(root.left!=null){
            root.left.next=root.right;
        }
        helper2(root.right);//还以为这个肯定要right先dfs，结果left先也行。
        helper2(root.left);
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
}


