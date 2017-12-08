import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
//http://blog.csdn.net/linhuanmars/article/details/24354291 

public class RemoveDuplicateFromSortedList {
	
	public static void main(String[] args) {
		ListNode list=new ListNode(1);
		list.next=new ListNode(2);
		list.next.next=new ListNode(3);
//		list.next.next.next=new ListNode(3);
//		list.next.next.next.next=new ListNode(1);
//		list.next.next.next.next.next=new ListNode(2);
//		list.next.next.next.next.next.next=new ListNode(3);
//		list.next.next.next.next.next.next.next=new ListNode(4);
//		list.next.next.next.next.next.next.next.next=new ListNode(5);
//		list.next.next.next.next.next.next.next.next.next=new ListNode(5);

		ListNode xx=deleteDuplicates2(list);
		ListNode temp=xx;
		while(temp!=null){
			System.out.println(temp.val);
			temp=temp.next;			
		}	
	}
	static class ListNode {
		      int val;
		      ListNode next;
		      ListNode(int x) {
		          val = x;
		          next = null;
		      }
		  }
	 public static ListNode deleteDuplicates(ListNode head) {
	        ListNode node=head;

	        ListNode node2=node;
	       
	        while(node!=null){
	            node2=node;
	            while(null!=node2.next){
	               if(node.val==node2.next.val){
	                   if(node2.next.next==null){
	                       node2.next=null;	                      
	                   }
	                   else{
	                       node2.next=node2.next.next;
	                   }	       
	               }
	               else{
	            	   node2=node2.next;
	               }
	            }	           
	            node=node.next;	            
	        }
	        return head;	        
	    }
	 public static ListNode deleteDuplicates2(ListNode head){
		 
		 Set<Integer> set=new TreeSet<Integer>();
		 ListNode node=head;
		 ListNode node2=head;
		 ListNode n=null;
		 ListNode n2=null;
		 int i=0;
		 while(node!=null){
			 set.add(node.val);
			 node=node.next;
		 }
		 int[][] a=new int[set.size()][2];
		 for(Iterator iter=set.iterator();iter.hasNext();){
			 a[i++][0]=(int) iter.next();
		 }
		for(int j=0;j<a.length;j++){
			node2=head;
			while(node2!=null){
				if(node2.val==a[j][0]){
					a[j][1]++;
				}
				node2=node2.next;
			}
		}
		
		for(int j=0;j<a.length;j++){
			
				if(a[j][1]==1){
					
					if(n==null){
						n=new ListNode(a[j][0]);
						n2=n;
					}else{
						n2.next=new ListNode(a[j][0]);
						n2=n2.next;
					}
				}
			
		}
		return n;
		
	 }
//第二次写，code ganker用2个变量就行了我要用3个
	 public ListNode deleteDuplicates3(ListNode head){
		 if(head==null||head.next==null){
			 return head;
		 }
		 ListNode dum=new ListNode(0);
		 ListNode index=dum;
		 ListNode cur=head;
		 while(cur!=null){
			 if(cur.next!=null&&cur.val==cur.next.val){
				 cur=cur.next;
			 }else{
				 dum.next=cur;
				 dum=dum.next;
				 cur=cur.next;
			 }
			 
		 }
		 return index.next;
		 
	 }
}
