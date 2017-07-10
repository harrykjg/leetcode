package linkedlist;

import java.util.HashMap;

/**
 * Created by 502575560 on 7/9/17.
 */
public class CopyListwithRandomPointer {
    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }
        HashMap<RandomListNode, RandomListNode> map = new HashMap<>();//map的意义是存的某个node和它对应的copy
        RandomListNode cur = head;
        while (cur != null) {//思路就是遍历原list,遇到一个node,就把它本身和next和ran这三个玩意拿出来看看,如果map里存了这个node,就拿出来,否则就新建一个,
            RandomListNode next = cur.next;  //然后对于这个copycur,更新其ran和next,也是看看map中是否存在,不存在就新建,否则就拿来直接assign给copycur
            RandomListNode ran = cur.random;
            RandomListNode copycur;
            if (!map.containsKey(cur)) {
                copycur = new RandomListNode(cur.label);
                map.put(cur, copycur);

            } else {
                copycur = map.get(cur);
            }
            if (!map.containsKey(ran) && ran != null) {
                RandomListNode copyran = new RandomListNode(ran.label);
                copycur.random = copyran;
                map.put(ran, copyran);
            } else if (ran != null) {
                copycur.random = map.get(ran);
            }
            if (!map.containsKey(next) && next != null) {
                RandomListNode copynext = new RandomListNode(next.label);
                copycur.next = copynext;
                map.put(next, copynext);
            } else if (next != null) {
                copycur.next = map.get(next);
            }
            cur = cur.next;
        }
        return map.get(head);//返回head对应的copy就行了,因为map存的是引用,所以实际的对象改变了这个引用还是指向它

    }
}
class RandomListNode {
         int label;
         RandomListNode next, random;
         RandomListNode(int x) { this.label = x; }
};