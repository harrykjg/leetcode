package linkedlist;

/**
 * Created by 502575560 on 7/9/17.
 */
public class RotateList {
    public static void main(String[]args){
        RotateList sl=new RotateList();
        ListNode l=new ListNode(1);
        l.next=new ListNode(2);
//        l.next.next=new ListNode(3);
//        l.next.next.next=new ListNode(4);
//        l.next.next.next.next=new ListNode(5);
//        l.next.next.next.next.next=new ListNode(6);
        sl.rotateRight(l,1);
    }
    public ListNode rotateRight(ListNode head, int k) {
        if(head==null||head.next==null||k==0){
            return head;
        }
        System.out.println("21e1");
        int count=0;
        ListNode cur=head;
        while (cur!=null){//算出list长度
            cur=cur.next;
            count++;
        }
        if(k%count==0){
            return head;
        }
        k%=count;//k取模
        ListNode dum=new ListNode(1);
        dum.next=head;
        cur=dum;
        int n=count-k;
        int i=0;

        while (i<n){//从dum出发找到第n-k个点,把list分成两段,再把第二段放到第一顿的前面就行了
            cur=cur.next;
            i++;
        }
        ListNode head2=cur.next;
        System.out.println(head2.val);
        cur.next=null;
        cur=head2;
        while (cur.next!=null){
            cur=cur.next;
        }
        cur.next=head;
        return head2;

    }
}
