import java.util.HashMap;

//http://blog.csdn.net/linhuanmars/article/details/21310633
//http://www.cnblogs.com/x1957/p/3485053.html
//http://www.tuicool.com/articles/R7R3Mb   java的

//http://www.cnblogs.com/dolphin0520/p/3741519.html
//http://blog.csdn.net/ict2014/article/details/17448281
//http://www.cnblogs.com/xinsheng/p/3514968.html


//get的话还要把这个node放到第一位上
//整个架构是参考别人的，插入删除基本按我自己想法写的，调试了几下居然对了
public class LRUCache {
	 public static void main(String[] args) {
		 LRUCache lr=new LRUCache(1);
		 lr.set(2, 1);
		 System.out.println(lr.get(2));
		 lr.set(3, 2);
		 System.out.println(lr.get(2));
		 System.out.println();
		 
	}
	    
	    
	    private int cap;
	    private int cur;
	    private HashMap< Integer,Node> hm;
	    private Node head;
	    private Node last;
	    
	    public LRUCache(int capacity) {
	        cap=capacity;
	        hm=new HashMap<Integer,Node >();
	    }
	    
	    public int get(int key) {
	        Node n=hm.get(key);
	        if(n==null){
	            return -1;
	        }else{
	            if(head==n){//如果要取的本来就是head，就不用动了
	                return head.val;
	            }else{
	            	puthead(n);
	            }
	        }
	        return n.val;
	    }
	    
	    public void set(int key, int value) {
	        Node n=hm.get(key);
	        if(n==null){
	        	Node nn=new Node(key,value);
	        	hm.put(key, nn);
	        	puthead(nn);
	        	cur++;
	        	if(cur>cap){
	        		remove();
	        		cur--;
	        	}
	        	return;
	        }else{//别忘了更新这个n的值
	        	n.val=value;;
	        	puthead(n);
	        }
	    }
	    private void puthead(Node n){
	    	if(head==null){//如果head是空，则新建一个head，此时head和last都指向同一个，head首尾
	    		//和自己相连
	    		n.next=n;
	    		n.pre=n;
	    		head=n;
	    		last=head;
	    		return;
	    	}
	    	if(head==n){
	    		return;
	    	}
	    	if(last==n){
	    		head=n;
	    		last=n.pre;
	    		return;
	    	}
	    	if(n.next!=null&&n.pre!=null){//切出来。注意这里，如果这个n是新new出来的，那么他的
	    		n.next.pre=n.pre;  //pre和next都是null，即根本就不用切出来了，它本来就是单个的
		    	n.pre.next=n.next;
	    	}
	    	
	    	//放最前
	    	n.next=head;
	    	head.pre=n;
	    	n.pre=last;
	    	last.next=n;
	    	
	    	head=n;
	    }
	    private void remove(){//remove不用参数，因为肯定是remove last
	    	if(last==head){
	    		return;
	    	}
	    	hm.remove(last.key);//别忘了把hm里的remove了
	    	last.pre.next=last.next;
	    	last.next.pre=last.pre;
	    	last=head.pre;
	    	
	    }

}
class Node{
    Node pre;
    Node next;
    int val;
    int key;
    public Node(int key,int val){
        this.key=key;
        this.val=val;
    }
}