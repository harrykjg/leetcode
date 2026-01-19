package 灵神.链表二叉树回溯.链表;


public class PartitionList86 {
    void main() {
        ListNode n=new ListNode(2);
        n.next=new ListNode(1);
//        n.next.next=new ListNode(3);
//        n.next.next.next=new ListNode(2);
//        n.next.next.next.next=new ListNode(5);
//        n.next.next.next.next.next=new ListNode(2);
        partition(n,2);
    }
    //12/23/2025，看回以前old的方法，就是先找出第一个大于等于x的点的前一个点作为pre，再开始完后看，遇到比x小的就换到pre后面，pre前进
    public ListNode partition(ListNode head, int x) {
        ListNode dum=new ListNode();
        dum.next=head;
        if (head==null||head.next==null){
            return head;
        }
        ListNode pre=dum;
        while (pre!=null&&pre.next!=null){
            if(pre.next.val>=x){
                break;
            }
            pre=pre.next;
        }
        ListNode cur=pre;
        while (cur!=null&&cur.next!=null){
            if(cur.next.val>=x){
                cur=cur.next;
            }else {
                ListNode temp=cur.next;
                cur.next=cur.next.next;
                temp.next=pre.next;
                pre.next=temp;
                pre=pre.next;
            }

        }
        return dum.next;
    }
    class ListNode {

        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next; }

    }
}
