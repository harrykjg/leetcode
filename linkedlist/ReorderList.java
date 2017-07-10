package linkedlist;

/**
 * Created by 502575560 on 7/9/17.
 */
public class ReorderList {
    public static void main(String[]args){
        ReorderList sl=new ReorderList();
        ListNode l=new ListNode(1);
        l.next=new ListNode(2);
        l.next.next=new ListNode(3);
        l.next.next.next=new ListNode(4);
        l.next.next.next.next=new ListNode(5);
        l.next.next.next.next.next=new ListNode(6);
        sl.reorderList(l);
    }
    public void reorderList(ListNode head) {
        if(head==null||head.next==null){
            return;
        }//据几个例子,找到中电,把list分成两段,再把第二段反转,再插入第一段就行了

        ListNode slow=head;
        ListNode fast=head;
        while (fast.next!=null&&fast.next.next!=null){
            slow=slow.next;
            fast=fast.next.next;
        }
        ListNode head2=slow.next;
        slow.next=null;
        //反转第二段,如1,2,3,4,5先变成21345,再变成32145再43215,就是不断的把1后面的那个元素扔到最前面
        ListNode dum=new ListNode(1);
        dum.next=head2;
        while (head2.next!=null){
            ListNode temp=head2.next.next;
            head2.next.next=dum.next;
            dum.next=head2.next;
            head2.next=temp;
        }

        //合并两段
        ListNode cur2=dum.next;
        ListNode cur=head;
        while (cur!=null&&cur2!=null){
            ListNode temp=cur2.next;
            cur2.next=cur.next;
            cur.next=cur2;
            cur2=temp;
            cur=cur.next.next;

        }
    }
}
