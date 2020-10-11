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

    //九章第二轮,10/1/2017,不熟,开始思路可能还不对,具体写法和第一次还不太一样
    public ListNode partition2(ListNode head, int x) {//1,4,3,2,5,2,cur先是1,然后cur2是3,然后就是要把2切出来放到1的后面,变成1,2,3,4,5,2
        ListNode dum=new ListNode(1);       //然后cur2还是3,cur是2,进入下个循环
        dum.next=head;
        ListNode cur=dum;
        while (cur.next!=null&&cur.next.val<x){
            cur=cur.next;
        }
        if(cur.next==null){
            return dum.next;
        }
        ListNode cur2=cur.next;
        while (cur2.next!=null){
            if(cur2.next.val>=x){
                cur2=cur2.next;
                continue;
            }else{
                ListNode temp=cur2.next;
                cur2.next=cur2.next.next;
                temp.next=cur.next;
                cur.next=temp;
                cur=temp;
            }
        }
        return dum.next;
    }
    //05/24/2020,我觉得是先找到比x小的点作为anchor，遇到比x小的就丢到anchor那，然后anchor前进一步，遇到比x大的就不动，继续走。不太好写，看回old的第二个思路
    //改了几次对了
    public ListNode partition3(ListNode head, int x) {
        if(head==null){
            return head;
        }
        ListNode dum=new ListNode(0);
        dum.next=head;
        ListNode cur=dum;
        ListNode anchor=dum;
        boolean flag=false;
        while (cur!=null&&cur.next!=null){
            if(cur.next.val<x&&flag){
                ListNode temp=cur.next.next;
                cur.next.next=anchor.next;
                anchor.next=cur.next;
                cur.next=temp;
                anchor=anchor.next;
            }else{
                if(cur.next.val>=x&&!flag){
                    flag=true;
                    anchor=cur;
                }
                cur=cur.next;
            }
        }
        return dum.next;
    }
}
