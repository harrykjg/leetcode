import java.util.List;

/**
 * Created by 502575560 on 3/1/17.
 */

//自己写的,不太顺,还行吧
public class OddEvenLinkedList {
    public static void main(String[] args){
        ListNode n1=new ListNode(1);
        n1.next=new ListNode(2);
        n1.next.next=new ListNode(3);
        n1.next.next.next=new ListNode(4);
        n1.next.next.next.next=new ListNode(5);
//        n1.next.next.next.next.next=new ListNode(6);
        OddEvenLinkedList oe=new OddEvenLinkedList();
        oe.oddEvenList2(n1);
    }

    public ListNode oddEvenList(ListNode head) {
        if(head==null||head.next==null||head.next.next==null){
            return head;
        }

        ListNode odd=head;
        ListNode evenPre=head.next;
        ListNode nextEvenPre=head;
        while(evenPre!=null){
            if(evenPre.next!=null){
                nextEvenPre=evenPre.next.next;
                insert(odd,evenPre);
                odd= odd.next;
                evenPre=nextEvenPre;
            }else{
                break;
            }

        }
        return head;
    }
    private void insert(ListNode n1,ListNode n2){
        ListNode temp=n2.next;
        n2.next=n2.next.next;
        temp.next=n1.next;
        n1.next=temp;
    }

    //10／17／2018，想了很久，写得不好，但是下面的代码要比以前的好，要再练,要画图照着例子看
    public ListNode oddEvenList2(ListNode head) {
        if(head==null){
            return null;
        }
        if(head.next==null||head.next.next==null){
            return head;
        }
        ListNode odd=head;
        ListNode even=head.next;

        while (even!=null&&even.next!=null){
            ListNode temp=even.next;//就是把even切出来，放到odd后面，然后odd后移一位，关键是要找到下一个even前面的那个节点，画图发现其实就是even后移一位就行了
            even.next=even.next.next;
            temp.next=odd.next;
            odd.next=temp;
            odd=odd.next;

            even=even.next;
        }
        return head;
    }
}
