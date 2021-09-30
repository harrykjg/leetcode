package tree;

public class InorderSuccessorinBSTII {
    //9/19/2021 看别人的
    //https://leetcode.com/problems/inorder-successor-in-bst-ii/discuss/231587/Java-find-in-parents-or-find-in-descendents
    //这个节点是有父引用的，因此successor只有2种情况，1，有右节点的话，则是右节点的最左节点。2，没有right的话，则sucessor是大于他的最小的节点，得用
    //父引用一直网上找到第一个比他大的就是了
    public Node inorderSuccessor(Node node) {
        if (node==null){
            return null;
        }
        Node rs=null;
        if (node.right!=null){
            Node next=node.right;
            while (next.left!=null){
                next=next.left;
            }
            return next;
        }
        Node cur=node.parent;
        while (cur!=null&&cur.val<node.val){
            cur=cur.parent;
        }
        return cur;
    }
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }
}
