package linkedlist;

/**
 * Created by 502575560 on 7/9/17.
 */
public class LinkedListCycle {
    public boolean hasCycle(ListNode head) {
        // 快慢指针,有相等的node就返回true
        if(head==null||head.next==null){
            return false;
        }
        ListNode slow=head;
        ListNode fast=head.next.next;
        while(fast!=null&&fast.next!=null&&fast.next.next!=null){
            if(slow==fast){
                return true;
            }
            slow=slow.next;
            fast=fast.next.next;
        }
        return false;
    }

    //10/3/2018
    public boolean hasCycle2(ListNode head) {
        if(head==null||head.next==null){
            return false;
        }
        ListNode wal=head;
        ListNode run=head;
        while (run!=null&&run.next!=null){

            wal=wal.next;
            run=run.next.next;
            if(wal==run){
                return true;
            }
        }

        return false;
    }
}
