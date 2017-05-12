import java.util.Random;

/**
 * Created by 502575560 on 5/10/17.
 */
public class LinkedListRandomNode {


}

//这个暴力法就不说了,遍历数组得出长度然后取random,在走到那个node上取出,蓄水池法参考别人
//http://blog.csdn.net/yeqiuzs/article/details/52169483
//http://blog.csdn.net/yeqiuzs/article/details/52169369
//http://www.cnblogs.com/grandyang/p/5759926.html
class Solution {

    /** @param head The linked list's head.
    Note that the head is guaranteed to be not null, so it contains at least one node. */
    ListNode head;
    public Solution(ListNode head) {
        this.head=head;
    }

    /** Returns a random node's value. */
    public int getRandom() {
        int rs=-1;
        int count=0;
        ListNode node=head;
        Random random=new Random();
        while (node!=null){
            count++;
            int rn=random.nextInt(count);//返回[0,count),左闭右开
            if(rn==0){
                rs=node.val;
            }
            node=node.next;
        }
        return rs;
    }
}