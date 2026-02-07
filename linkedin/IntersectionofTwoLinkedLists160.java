package linkedin;



import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class IntersectionofTwoLinkedLists160 {
    static void main() {

    }
    //不会,只会用set的，看回以前的方法也行，下面这个很难想
    //https://leetcode.cn/problems/intersection-of-two-linked-lists/solutions/2958778/tu-jie-yi-zhang-tu-miao-dong-xiang-jiao-m6tg1/
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> map=new HashSet<>();
        ListNode cur=headA;

        while (cur!=null){
            map.add(cur);
            cur=cur.next;
        }
        ListNode cur2=headB;

        while (cur2!=null){
            if(map.contains(cur2)){
                return cur2;
            }
            cur2=cur2.next;
        }
        return null;

    }

       class ListNode {
      int val;
      ListNode next;
      ListNode(int x) {
          val = x;
          next = null;
      }
  }

}
