package tree;

import java.util.HashSet;
import java.util.Set;

public class LowestCommonAncestorofaBinaryTreeIII {
    //8/5/2021看别人答案的
    //https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree-iii/discuss/950242/Multiple-solution-approaches-in-Java-(with-comments-and-explanation)
    public Node lowestCommonAncestor(Node p, Node q) {//用set的方法
        Set<Node> set=new HashSet<>();
        Node cur1=p;
        Node cur2=q;
        while (cur1!=null){
            set.add(cur1);
            cur1=cur1.parent;
        }
        while (cur2!=null){
            if (set.contains(cur2)){
                return cur2;
            }
            cur2=cur2.parent;
        }
        return null;
    }

    public Node lowestCommonAncestor2(Node p, Node q) {//用depth的方法
        int depth1=getDepth(p);
        int depth2=getDepth(q);
        while (depth1>depth2){
            p=p.parent;
            depth1--;
        }
        while (depth2>depth1){
            q=q.parent;
            depth2--;
        }
        while (p!=q){
            p=p.parent;
            q=q.parent;
        }
        return p;
    }
    int getDepth(Node n){
        int i=0;
        while (n!=null){
            n=n.parent;
            i++;
        }
        return i;
    }


    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }
}
