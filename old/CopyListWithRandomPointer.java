import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
//http://blog.csdn.net/linhuanmars/article/details/22463599 他的第一种方法讲的好
//http://jixiangsanbao.wordpress.com/2014/07/02/copy-list-with-random-pointer/ 
public class CopyListWithRandomPointer {
	public static void main(String[] args) {

	}

	public RandomListNode copyRandomList(RandomListNode head) {
		
		
		if(head==null){
			return null;
		}
		HashMap<RandomListNode,RandomListNode> map=new HashMap<RandomListNode,RandomListNode>();
		HashSet<RandomListNode> set=new HashSet<RandomListNode>();
		LinkedList<RandomListNode> ls=new LinkedList<RandomListNode>();
		ls.add(head);
		
		while(!ls.isEmpty()){
			RandomListNode ori=ls.poll();
			RandomListNode n=map.get(ori);
			if(n==null){
				n=new RandomListNode(ori.label);
				map.put(ori, n);
			}
			if(!set.contains(ori)){
				if(ori.next!=null){
					if(!map.containsKey(ori.next)){
						RandomListNode nn=new RandomListNode(ori.next.label);
						n.next=nn;
						map.put(ori.next, nn);
					}else{
						n.next=map.get(ori.next);
					}
				}
				if(ori.random!=null){
					if(!map.containsKey(ori.random)){
						RandomListNode ran=new RandomListNode(ori.random.label);
						n.random=ran;
						map.put(ori.random, ran);
					}else{
						n.random=map.get(ori.random);
					}
					
				}
				
			}
			set.add(ori);
			if(ori.next!=null){
				ls.add(ori.next);
			}
		}
		return map.get(head);

	}
	//第三轮,知道用hashmap记录，但是没写出来，因为我想要一次扫描就行的，但是貌似至少要2次.
	//两次的话思路就简单了，就第一遍遍历原数组所有节点，并把所有节点复制放进map里。
	//然后建立复制品的第一个节点，从原数组的第二个节点开始，把第二个节点的复制品连上第一个复制品上
	//同时连上复制品的下个节点的random节点，就行了
    public RandomListNode copyRandomList2(RandomListNode head) {
        if(head==null){
        	return head;
        }
        HashMap<RandomListNode,RandomListNode> hm=new HashMap<RandomListNode,RandomListNode>();
        RandomListNode cur1=head;
        RandomListNode copy=new RandomListNode(head.label);
        hm.put(head, copy);
        RandomListNode cur2=null;
        cur1=cur1.next;
        while(cur1!=null){
        	hm.put(cur1, new RandomListNode(cur1.label));
        	cur1=cur1.next;
        }
        cur1=head.next;
        
        cur2=hm.get(head);
        cur2.random=hm.get(head.random);
        while(cur1!=null){
        	cur2.next=hm.get(cur1);
        	cur2.next.random=hm.get(cur1.random);
        	cur1=cur1.next;
        	cur2=cur2.next;
        }
        
        return hm.get(head);
    }

}
class RandomListNode {
	     int label;
         RandomListNode next, random;
	     RandomListNode(int x) { this.label = x; }
	  };