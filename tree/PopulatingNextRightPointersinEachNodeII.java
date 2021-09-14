package tree;

public class PopulatingNextRightPointersinEachNodeII {
    //7/4/2021，做了第一题之后，知道要利用好next,写了很久，画图，
    public Node connect(Node root) {
        if (root==null){
            return root;
        }
        Node rs=root;
        Node cur=root;
        Node nextLevl=null;//记录下一层的一个节点
        while (cur!=null){//对于每一个node，它将负责把它的两个子树连一起，并且负责把它的左或右子树连到它的next的左或者右子树
            Node local=cur;//由于对于每一层都是要从左到右的，所以要设一个local从左到右挪，而cur到后面要更新到下一层的最左的节点

            while (local!=null){
                System.out.println(local.val);
                if (local.left!=null&&local.right!=null){//如果有左右子树，则连一起，并且把右子树的next也连上
                    if (nextLevl==null){
                        nextLevl=local.left;
                    }
                    local.left.next=local.right;
                    Node next=findNext(local.next);
                    if (next!=null&&next.left!=null){
                        local.right.next=next.left;
                    }else if(next!=null&&next.right!=null){
                        local.right.next=next.right;
                    }

                }else if (local.left!=null){//如果只有左子树，则左的next要连上
                    if (nextLevl==null){
                        nextLevl=local.left;
                    }
                    Node next=findNext(local.next);
                    if (next!=null&&next.left!=null){
                        local.left.next=next.left;
                    }else if(next!=null&&next.right!=null){
                        local.left.next=next.right;
                    }
                }else if (local.right!=null){//只有右子树，它的next也要连上
                    if (nextLevl==null){
                        nextLevl=local.right;
                    }
                    Node next=findNext(local.next);
                    if (next!=null&&next.left!=null){
                        local.right.next=next.left;
                    }else if(next!=null&&next.right!=null){
                        local.right.next=next.right;
                    }
                }
                local=local.next;
            }

            cur=nextLevl;
            nextLevl=null;//这里别忘了
        }
        return rs;

    }
    Node findNext(Node n){
        if (n==null){
            return n;
        }
        if (n.left!=null||n.right!=null){
            return n;
        }
        return findNext(n.next);
    }

    //递归的
    public  Node connect2(Node root) {
        if (root==null){
            return null;
        }
        Node rs=root;
        Node cur=root;
        Node next=findNext(cur.next);
        if (cur.left!=null&&cur.right!=null){
            cur.left.next=cur.right;
            if (next!=null){
                if (next.left!=null){
                    cur.right.next=next.left;
                }else{
                    cur.right.next=next.right;
                }
            }
        }else if (cur.left!=null){
            if (next!=null){
                if (next.left!=null){
                    cur.left.next=next.left;
                }else{
                    cur.left.next=next.right;
                }
            }
        }else if (cur.right!=null){
            if (next!=null){
                if (next.left!=null){
                    cur.right.next=next.left;
                }else{
                    cur.right.next=next.right;
                }
            }
        }
        connect2(root.right);
        connect2(root.left);
        return rs;
    }
//8/25/2021这个递归比上面的好。答案1的层次遍历也可以。就是要空间。像bfs那样while里弄出q的size，然后只要这个poll出来的node不是当前层的最后一个，则他的next
    //就是q里的下一个，peek出来就行了，level还是从左到右。
    public  Node connect3(Node root) {
        if(root==null){
            return null;
        }
        helper3(root);
        return root;
    }
    void helper3(Node root){
        if (root==null){
            return;
        }
        if (root.right!=null){
            Node next=find3(root);
            root.right.next=next;
        }
        if (root.left!=null){
            if (root.right!=null){
                root.left.next=root.right;
            }else {
                Node next=find3(root);
                root.left.next=next;
            }
        }
        helper3(root.right);//这个就一定要右边先搞了
        helper3(root.left);
    }
    Node find3(Node node){
        if (node==null){
            return null;
        }
        Node cur=node;
        while (cur!=null){
            if (cur.next!=null){
                if (cur.next.left!=null){
                    return cur.next.left;
                }
                if (cur.next.right!=null){
                    return cur.next.right;
                }
            }
            cur=cur.next;
        }
        return null;
    }
    class Node {
        public int val;
        public PopulatingNextRightPointersinEachNode.Node left;
        public PopulatingNextRightPointersinEachNode.Node right;
        public PopulatingNextRightPointersinEachNode.Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, PopulatingNextRightPointersinEachNode.Node _left, PopulatingNextRightPointersinEachNode.Node _right, PopulatingNextRightPointersinEachNode.Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}