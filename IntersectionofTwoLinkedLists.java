public class IntersectionofTwoLinkedLists {
    //8/15/2021不会，看答案的。就是遍历得出两个list的长度，然后让长的那个先开始走，走到剩余部分和短的一样，然后两个一起走，就行了
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int len1=0;
        int len2=0;
        ListNode cur=headA;
        while (cur!=null){
            len1++;
            cur=cur.next;
        }
        cur=headB;
        while (cur!=null){
            len2++;
            cur=cur.next;
        }
        ListNode cur1=headA;
        ListNode cur2=headB;
        if (len1>len2){
            int go=len1-len2;
            int i=0;
            while (i<go){
                cur1=cur1.next;
                i++;
            }
        }else{
            int go=len2-len1;
            int i=0;
            while (i<go){
                cur2=cur2.next;
                i++;
            }
        }
        while (cur1!=cur2){
            cur1=cur1.next;
            cur2=cur2.next;
        }
        return cur1;
    }
}
