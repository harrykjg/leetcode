package DataStruct;

public class AddTwoNumbersII {
    //8/28/2021 就是反转，然后加起来，再反转，改了一下过了
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head1=l1;
        ListNode head2=l2;
        l1=reverse(l1);
        l2=reverse(l2);
        int carry=0;
        ListNode cur1=l1;
        ListNode cur2=l2;
        ListNode dum=new ListNode(0);
        ListNode last=dum;
        while (cur1!=null&&cur2!=null){
            int a=cur1.val;
            int b=cur2.val;
            ListNode cur3=null;
            if (a+b+carry>=10){

                cur3=new ListNode(a+b+carry-10);
                carry=1;
            }else {

                cur3=new ListNode(a+b+carry);
                carry=0;
            }
            cur1=cur1.next;
            cur2=cur2.next;
            last.next=cur3;
            last=last.next;
        }
        while (cur1!=null){
            ListNode cur3=null;
            if (cur1.val+carry>=10){
                cur3=new ListNode(cur1.val+carry-10);
                carry=1;
            }else {
                cur3=new ListNode(cur1.val+carry);
                carry=0;
            }
            cur1=cur1.next;
            last.next=cur3;
            last=last.next;
        }
        while (cur2!=null){
            ListNode cur3=null;
            if (cur2.val+carry>=10){
                cur3=new ListNode(cur2.val+carry-10);
                carry=1;
            }else {
                cur3=new ListNode(cur2.val+carry);
                carry=0;
            }
            cur2=cur2.next;
            last.next=cur3;
            last=last.next;
        }
        if (carry!=0){
            last.next=new ListNode(1);
        }
        return reverse(dum.next);

    }
    ListNode reverse(ListNode l1){
        ListNode dum=new ListNode(0);
        ListNode cur=l1;
        dum.next=l1;
        while (cur!=null&&cur.next!=null){
            ListNode temp=cur.next;
            cur.next=cur.next.next;
            temp.next=dum.next;
            dum.next=temp;
        }
        return dum.next;
    }
}
