package linkedlist;

import java.util.List;

/**
 * Created by 502575560 on 7/9/17.
 */
public class SortList {
    public static void main(String[]args){
        SortList sl=new SortList();
        ListNode l=new ListNode(0);
        l.next=new ListNode(3);
        l.next.next=new ListNode(1);
        l.next.next.next=new ListNode(1);
        sl.sortList(l);
    }
    //用mergesort,quicksort没写
    public ListNode sortList(ListNode head) {
        // write your code here
        if(head==null||head.next==null){
            return head;
        }//开始还写了dum,其实这里不需要dum,有了dum也可以,只是fast和slow的起点不是head而是dum,while循环也要改一下还是能accept的
        ListNode slow=head;
        ListNode fast=head;
        while (fast.next!=null&&fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        ListNode head2=slow.next;
        slow.next=null;
        ListNode h1=sortList(head);
        ListNode h2=sortList(head2);
        return merge(h1,h2);

    }
    ListNode merge(ListNode l1,ListNode l2){
        if(l1==null){
            return l2;
        }
        if(l2==null){
            return l1;
        }
        ListNode dum=new ListNode(1);
        ListNode cur=dum;
        while (l1!=null&&l2!=null){
            if(l1.val>l2.val){
                cur.next=l2;
                l2=l2.next;
                cur=cur.next;
            }else {
                cur.next=l1;
                l1=l1.next;
                cur=cur.next;
            }
        }
        if (l1!=null){
            cur.next=l1;
        }
        if (l2!=null){
            cur.next=l2;
        }
        return dum.next;
    }
}
