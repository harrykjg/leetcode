package 灵神.链表二叉树回溯.链表;

public class ReverseLinkedListII92 {
    static void main() {

    }
    //left和right是1为底的下标
    public ListNode reverseBetween(ListNode head, int left, int right) {
        if(head==null||head.next==null||left==right){
            return head;
        }
        ListNode pre=new ListNode();
        pre.next=head;
        int index=1;
        while (index<left){
            pre=pre.next;
            index++;
        }
        ListNode l=pre.next;
        int diff=right-left;
        for(int i=0;i<diff;i++){
            ListNode temp=l.next;
            l.next=l.next.next;
            temp.next=pre.next;
            pre.next=temp;
        }

        if(left==1){
            return pre.next;
        }
        return head;

    }

}
