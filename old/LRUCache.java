import java.util.HashMap;

//http://blog.csdn.net/linhuanmars/article/details/21310633
//http://www.cnblogs.com/x1957/p/3485053.html
//http://www.tuicool.com/articles/R7R3Mb   java��

//http://www.cnblogs.com/dolphin0520/p/3741519.html
//http://blog.csdn.net/ict2014/article/details/17448281
//http://www.cnblogs.com/xinsheng/p/3514968.html


//get�Ļ���Ҫ�����node�ŵ���һλ��
//�����ܹ��ǲο����˵ģ�����ɾ�����������Լ��뷨д�ģ������˼��¾�Ȼ����
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
	            if(head==n){//���Ҫȡ�ı�������head���Ͳ��ö���
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
	        }else{//�����˸������n��ֵ
	        	n.val=value;;
	        	puthead(n);
	        }
	    }
	    private void puthead(Node n){
	    	if(head==null){//���head�ǿգ����½�һ��head����ʱhead��last��ָ��ͬһ����head��β
	    		//���Լ�����
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
	    	if(n.next!=null&&n.pre!=null){//�г�����ע�����������n����new�����ģ���ô����
	    		n.next.pre=n.pre;  //pre��next����null���������Ͳ����г����ˣ����������ǵ�����
		    	n.pre.next=n.next;
	    	}
	    	
	    	//����ǰ
	    	n.next=head;
	    	head.pre=n;
	    	n.pre=last;
	    	last.next=n;
	    	
	    	head=n;
	    }
	    private void remove(){//remove���ò�������Ϊ�϶���remove last
	    	if(last==head){
	    		return;
	    	}
	    	hm.remove(last.key);//�����˰�hm���remove��
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