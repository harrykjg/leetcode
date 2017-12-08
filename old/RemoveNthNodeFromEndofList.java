import java.util.ArrayList;
//这样做空间就需要O（n)了，人家是使用2个指针（一个先走一个后走）实现的只需O（1）
//http://blog.csdn.net/linhuanmars/article/details/19778441
public class RemoveNthNodeFromEndofList {

	public ListNode removeNthFromEnd(ListNode head, int n) {

		ArrayList<ListNode> al = new ArrayList<ListNode>();
		ListNode pre = null;
		ListNode post = null;
		int len = 0;
		ListNode cur = head;
		while (cur != null) {
			len++;
			al.add(cur);
			cur = cur.next;
		}

		if (len != 1) {
			if (n != 1&&n!=len) {//长度不为1，且返回的不是第一个
				pre = al.get(len - n - 1);
				post = al.get(len - n + 1);
				pre.next = post;
				return head;
			}
			else if(n!=1){//长度不为1，且返回的是第一个
				post = al.get(len - n + 1);
				return post;
			}
			else{
				pre = al.get(len - n - 1);
				pre.next=null;
				return head;
			}
		} else {//n说是肯定有效的，所以如果len是1的话，n肯定也是1，所以返回空
			return null;
		}
	}
	public ListNode removeNthFromEnd2(ListNode head, int n) {//用两个指针

		ListNode run=head;
		ListNode slow=head;
		int count=0;
		
		while(count!=n){
			run=run.next;
			count++;
			if(run==null){//即要删掉第一个
				return head.next;
			}
		}
		while(run.next!=null){
			slow=slow.next;
			run=run.next;
		}
		slow.next=slow.next.next;
		return head;

	}
	
	//1/10 还要调试了一次，就是删除的点是第一个节点的判断，此时front已经是null了，返回head.next就行
	 public ListNode removeNthFromEnd3(ListNode head, int n) {
	        if(head==null){
	            return head;
	        }
	        ListNode front=head;
	        
	        for(int i=0;i<n;i++){
	            if(front!=null){
	                front=front.next;
	            }else{
	                return head;
	            }
	        }
	        ListNode cur=head;
	        if(front==null){
	            return head.next;
	        }
	        while(front.next!=null){
	            front=front.next;
	            cur=cur.next;
	        }
	        cur.next=cur.next.next;
	        return head;
	        
	        
	    }

}
