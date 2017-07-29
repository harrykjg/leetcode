package linkedlist;

/**
 * Created by 502575560 on 7/26/17.
 */
public class ReverseLinkedList {
    //这次用recursion写,以前还从来没想过,还是看的别人得思路才知道应该怎么写
    //还有不同得recursion思路
    //https://discuss.leetcode.com/topic/15019/iteratively-and-recursively-java-solution  这个容易理解一些
    //https://discuss.leetcode.com/topic/16506/my-java-recursive-solution  这个从后往前得比较难理解

    public ListNode reverseList(ListNode head) {
        if(head==null||head.next==null){//如12345,思路先把数组分成2段了,1和2345,现在开始递归,把1和2345对换,变成21和345,再把21和345
            return head;         //对换,变成321和45,...最后变成54321和null,发现第二个参数是null则返回第一个参数
        }
        ListNode next=head.next;
        head.next=null;
        return swap(head,next);

    }
    ListNode swap(ListNode first,ListNode sec){
        if(sec==null){
            return first;
        }
        ListNode temp=sec.next;
        sec.next=first;

        return swap(sec,temp);
    }
}
