package linkedlist;

import java.util.List;

/**
 * Created by 502575560 on 7/9/17.
 */
public class SortList {
    public static void main(String[]args){
        SortList sl=new SortList();
        ListNode l=new ListNode(4);
        l.next=new ListNode(2);
        l.next.next=new ListNode(1);
        l.next.next.next=new ListNode(3);
        sl.sortList2(l);
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
//6/1/2021,改了一次对了
    public ListNode sortList2(ListNode head) {
        if(head==null||head.next==null){
            return head;
        }

        ListNode dum=new ListNode(1);
        dum.next=head;
        ListNode walk=dum;
        ListNode run=dum;
        while (run!=null&&run.next!=null){
            walk=walk.next;
            run=run.next.next;
        }
        run=walk.next;
        walk.next=null;
        ListNode one=sortList2(dum.next);
        ListNode two=sortList2(run);
        return merge2(one,two);

    }
    ListNode merge2(ListNode n1,ListNode n2){
        ListNode dum=new ListNode(1);
        ListNode cur=dum;
        while (n1!=null&&n2!=null){
            if(n1.val<n2.val){
                cur.next=n1;
                n1=n1.next;
                cur=cur.next;
            }else{
                cur.next=n2;
                n2=n2.next;
                cur=cur.next;
            }
        }
        if (n1!=null){
            cur.next=n1;
        }
        if (n2!=null){
            cur.next=n2;
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
