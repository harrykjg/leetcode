package DataStruct;

public class RemoveNthNodeFromEndofList {
    //10/11/2021 自己写的是走一遍得出count再走一遍，别人的有one pass的，比较巧妙
    //https://leetcode.com/problems/remove-nth-node-from-end-of-list/discuss/8822/Java-solution-1ms-u5bb9u6613u7406u89e3
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dum=new ListNode(0);
        dum.next=head;
        ListNode cur=head;
        int count=0;
        while(cur!=null){
            cur=cur.next;
            count++;
        }
        int step=count-n;
        int i=0;
        cur=dum;
        while(i<step){
            cur=cur.next;
            i++;

        }
        cur.next=cur.next.next;
        return dum.next;
    }
    //别人的代码，如长度是5，n是2，则他设一个cur=dum。只要cur。next不为空则一直走，n--，可知走了2步n就是0了，那么继续走n就是小于0了，可知最后长度是5则n小于
    //0的情况有3次，刚好把pre往前移3次就是在正确的位置，然后用pre把pre后面的删掉就行了
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode curr = dummy;
        ListNode pre = dummy;
        while (curr.next != null) {
            if (n <= 0) {
                pre = pre.next;
            } else {
                n--;
            }
            curr = curr.next;
        }
        pre.next = pre.next.next;
        return dummy.next;
    }
}
