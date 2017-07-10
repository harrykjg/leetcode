package linkedlist;

/**
 * Created by 502575560 on 7/8/17.
 */
public class RemoveDuplicatesfromSortedII {
    public static ListNode deleteDuplicates(ListNode head) {
        // write your code here
        ListNode dum=new ListNode(1);
        dum.next=head;
        ListNode cur=dum;
        while(cur!=null&&cur.next!=null){
            ListNode n=cur.next;
            boolean flag=false;
            while(n!=null&&n.next!=null&&n.val==n.next.val){
                n=n.next;
                flag=true;
            }
            if(!flag){
                cur=cur.next;
            }else{
                cur.next=n.next;
                //这里开始写了cur。next＝n。next就错了
            }

        }
        return dum.next;
    }
}
class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}