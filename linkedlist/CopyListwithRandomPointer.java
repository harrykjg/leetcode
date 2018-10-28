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
    //12/11/2017九章第二轮一次过
    public RandomListNode copyRandomList2(RandomListNode head) {
        if(head==null){
            return null;
        }
        HashMap<RandomListNode,RandomListNode> map=new HashMap<>();
        RandomListNode cpHead=new RandomListNode(head.label);
        map.put(head,cpHead);
        RandomListNode cur=head;
        while (cur!=null){
            RandomListNode cp=null;
            if(map.containsKey(cur)){
                cp=map.get(cur);
            }else{
                cp=new RandomListNode(cur.label);
                map.put(cur,cp);
            }
            if(cur.random!=null){
                if(map.containsKey(cur.random)){
                    cp.random=map.get(cur.random);
                }else{
                    cp.random=new RandomListNode(cur.random.label);
                    map.put(cur.random,cp.random);
                }
            }
            if(cur.next!=null){
                if(map.containsKey(cur.next)){
                    cp.next=map.get(cur.next);
                }else{
                    cp.next=new RandomListNode(cur.next.label);
                    map.put(cur.next,cp.next);
                }
            }
            cur=cur.next;
        }
        return cpHead;
    }

    //10/23/2018,还凑活，思路和之前有点不同，就是先搞出一个copy，包括他的random，作为一个pre，再去看原list的第二，3，4，5个node，复制之，连上pre
    public RandomListNode copyRandomList3(RandomListNode head) {
        if(head==null){
            return null;
        }
        HashMap<RandomListNode,RandomListNode> map=new HashMap<>();
        RandomListNode cur=head;
        RandomListNode pre=new RandomListNode(head.label);
        map.put(head,pre);
        RandomListNode preRan=null;
        if(head.random!=null&&map.containsKey(head.random)){
            preRan=map.get(head.random);
        }else if(head.random!=null){
            preRan=new RandomListNode(head.random.label);
            map.put(head.random,preRan);
        }
        pre.random=preRan;
        cur=cur.next;
        while (cur!=null){
            RandomListNode cop=null;
            RandomListNode ran=null;
            if(map.containsKey(cur)){
                cop=map.get(cur);
            }else{
                cop=new RandomListNode(cur.label);
                map.put(cur,cop);
            }
            if(cur.random!=null&&map.containsKey(cur.random)){
                ran=map.get(cur.random);
            }else if(cur.random!=null){
                ran=new RandomListNode(cur.random.label);
                map.put(cur.random,ran);
            }
            cop.random=ran;

            cur=cur.next;
            pre.next=cop;
            pre=pre.next;

        }
        return map.get(head);

    }




}




class RandomListNode {
         int label;
         RandomListNode next, random;
         RandomListNode(int x) { this.label = x; }
};