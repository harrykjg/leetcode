package linkedlist;

public class SwapNodesinPairs {
    //8/8/2021
    /*
   p1
   d  1  2  3  4  5
         p1
   d  2  1  3  4  5
               p1
   d  2  1  4  3  5
     */
    public ListNode swapPairs(ListNode head) {
        ListNode dum=new ListNode(1);
        dum.next=head;
        ListNode cur=dum;
        while (cur.next!=null&&cur.next.next!=null){
            ListNode temp=cur.next.next;
            cur.next.next=cur.next.next.next;
            temp.next=cur.next;
            cur.next=temp;
            cur=cur.next.next;
        }
        return dum.next;
    }
}
