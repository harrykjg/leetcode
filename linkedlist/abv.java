package linkedlist;

public class abv {
    public static void main(String[] args){
        PartitionList pl =new PartitionList();
        ListNode head=new ListNode(1);
        head.next=new ListNode(4);
        head.next.next=new ListNode(3);
        head.next.next.next=new ListNode(0);
        head.next.next.next.next=new ListNode(2);
        head.next.next.next.next.next=new ListNode(5);
        head.next.next.next.next.next.next=new ListNode(2);
        ListNode rs=pl.partition(head, 3);
        while(rs!=null){
            System.out.println(rs.val);
            rs=rs.next;
        }
    }
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

    //6/1/2021,不是很好想。我觉得我想的是对的，不用先找到第一个大的或者小的点，只要dum作为anchor，遇到小的就换到anchor前面，然后anchor前进一位，遇到大的不动，
    //anchor也不动，继续看下一个，如
    public ListNode partition4(ListNode head, int x) {
        if(head==null){
            return null;
        }
        ListNode dum=new ListNode(1);
        dum.next=head;
        ListNode pre=dum;
        ListNode cur=pre;

        while (cur!=null&&cur.next!=null){
            if(cur.next.val<x){
                ListNode temp=cur.next;
                cur.next=cur.next.next;
                temp.next=pre.next;
                pre.next=temp;
                pre=pre.next;
            }
            cur=cur.next;

        }
        return dum.next;
    }
}
