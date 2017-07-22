package linkedlist;

/**
 * Created by 502575560 on 7/10/17.
 */
public class PartitionList {
    public ListNode partition(ListNode head, int x) {
        // write your code here
        //看回老leetcode解释把
        if(head==null||head.next==null){
            return head;
        }
        ListNode dum=new ListNode(1);
        dum.next=head;
        ListNode first=dum;
        while(first.next!=null&&first.next.val<x){
            first=first.next;
        }
        if(first.next==null){
            return dum.next;
        }
        ListNode cur=first.next;
        while(cur.next!=null){
            if(cur.next.val>=x){
                cur=cur.next;
            }else{
                ListNode temp=cur.next.next;
                cur.next.next=first.next;
                first.next=cur.next;
                cur.next=temp;
                first=first.next;
            }
        }
        return dum.next;
    }
}
