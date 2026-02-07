package 灵神.单调栈;

import java.util.ArrayList;
import java.util.Stack;

public class NextGreaterNodeInLinkedList1019 {

    //就是比起array的题要多了下标
    public int[] nextLargerNodes(ListNode head) {
        ArrayList<Integer> al=new ArrayList<>();
        ListNode cur=head;
        Stack<int[]> st=new Stack<>();
        int i=0;
        while (cur!=null){
            al.add(0);//这个不能漏，就是先把al扩展
            if(st.isEmpty()){
                st.push(new int[]{cur.val,i++});
                cur=cur.next;
                continue;
            }
            while (!st.isEmpty()&&st.peek()[0]<cur.val){
                al.set(st.pop()[1],cur.val);
            }
            st.push(new int[]{cur.val,i++});
            cur=cur.next;
        }
        int[] rs=new int[al.size()];
        for(i=0;i<rs.length;i++){
            rs[i]=al.get(i);
        }
        return rs;
    }


    //1/21/2026 懒得写，应该就是先loop放进arraylist就行了，或者直接只记录length都行
    // https://leetcode.cn/problems/next-greater-node-in-linked-list/solutions/2217563/tu-jie-dan-diao-zhan-liang-chong-fang-fa-v9ab/


      public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
}
