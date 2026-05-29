package SomeInterviews.bloomberg;

public class FlattenaMultilevelDoublyLinkedList430 {
    //4/28/2026 我写的是递归的，看别人貌似还不用递归，直接while搞定。
    public Node flatten(Node head) {
        Node cur=head;
        //找child不为空的点,如果找不到的话，也得找到最后一个节点，用来返回
        while (cur!=null){
            if(cur.child!=null){//找到child，该flatten了
                Node nextLevelFirst=flatten(cur.child);//这个就是child list的第一个节点，把它接插入本层，即头插在cur的后面，尾接着cur.next
                Node curNext=cur.next;

                cur.next=nextLevelFirst;
                nextLevelFirst.prev=cur;
                //现在要走到nextLevel这个链表的最后一个，才能接上curNext
                Node nn=nextLevelFirst;
                while (nn!=null&&nn.next!=null){
                    nn=nn.next;
                }
                //nn就是最后一个节点，接上curNext
                nn.next=curNext;
                if(curNext!=null){//开始漏了这个
                    curNext.prev=nn;
                }
                cur.child=null;//开始忘了这一步
                cur=curNext;
                continue;
            }
            if(cur.next==null){//不用这个也对
                break;
            }
            cur=cur.next;
        }

        return head;
    }

    class Node {
        public int val;
        public Node prev;
        public Node next;
        public Node child;
    };
}
