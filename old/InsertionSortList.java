//做时的问题：dum.next的改变会影响到之前的node（因为之前的node的换位用到了dum.next）

//第二次做一次accept了，但是其实是想复杂了，其实不用把已经sort的部分当成是新的链表并记录其起点和终点
//http://blog.csdn.net/linhuanmars/article/details/21144553

public class InsertionSortList {

	public static void main(String[] args) {
		ListNode n1=new ListNode(1);
		ListNode n2=new ListNode(1);
		ListNode n3=new ListNode(7);
		ListNode n4=new ListNode(30);
		ListNode n5=new ListNode(9);
//		ListNode n6=new ListNode(9);
		n1.next=n2;
//		n2.next=n3;
//		n3.next=n4;
//		n4.next=n5;
//		n5.next=n6;
	
		ListNode curr=n1;
		
	
//		试验交换两个头2个node
//		ListNode temp=curr.next;
//		curr.next=curr.next.next;
//		temp.next=curr;
//		
//		while(temp!=null){
//			System.out.println(temp.val);
//			temp=temp.next;
//		}
		
		
//		试验交换35和40（中间隔了元素的交换），只要用temp记录被去掉的那个元素（即如
		//curr.next=curr.next.next，则curr.next被去掉了了）即可
//		ListNode temp1=n5;
//		
//		
//		n4.next=n5.next;
//		temp1.next=n3;
//		n2.next=temp1;
//		
//		
		while(curr!=null){
		System.out.println(curr.val);
		curr=curr.next;
	}
		System.out.println("-------------");
		ListNode xx=sort(n1);
		
		while(xx!=null){
			System.out.println(xx.val);
			xx=xx.next;
		}
		
		
		
		
	}
	
	public static ListNode sort(ListNode root){
		
		ListNode curr=root;
		ListNode head=root;
//		ListNode curr2=null;
		ListNode curr3=null;
		
		
		if(root==null||root.next==null){
			return root;
		}
		
		while(null!=curr.next){
			curr3=curr.next;
			
			ListNode dum=new ListNode(0);//注意这里每次要new一个dum，不能吧dum定义在前面
			dum.next=head;
			
			while(dum.next!=curr3){
//				ListNode curr2=dum.next;
				if(curr.next.val<dum.next.val){
					
					if(dum.next==head){
						ListNode temp=curr.next;
						curr.next=curr.next.next;
						temp.next=dum.next;
						head=temp;
						dum.next=curr.next;//为什么这里dum.next变了不会影响text.next？区别
						//就在于不是和头结点换的情况下，执行了下面dum=dum.next这句
						break;
					}
					else{
					
						
						ListNode temp=curr.next;
						curr.next=curr.next.next;
						temp.next=dum.next;//这里用了dum.next，到了下一层dum.next=head之后，
						//这里的head也会跟着变，为什么？
						dum.next=temp;
//						dum.next=null;
						break;
						
					}
				
				}
				dum=dum.next;
			}
			
//			if(curr.next!=null){
//				curr=curr.next;
//			}else{
//				break;
//			}

		}	
		
		return head;
		
	}
	
	public static ListNode sort2(ListNode head){
		ListNode dum=new ListNode(Integer.MIN_VALUE);
		if(head==null){
			return null;
		}
		dum.next=head;
		ListNode dd=dum;
		ListNode cur=dum;
		int count=0;
		ListNode last=dd;
		while(cur.next!=null){
			if(cur.next.val>=last.val){
				cur=cur.next;
				count++;
				last=last.next;
			}
			else{//此时cur.next指向大于其前面元素值的那个元素，其实应该不用count1和2来记录
				//sorted部分的长度的，只要当dum.next这个移动的节点不等于cur.next来判断就行了
				int count2=0;
				while(count2<count){//这个while循环要找到该cur.next再sorted部分的位置
					if(dd.next.val>cur.next.val){
						ListNode temp1=cur.next;
						cur.next=cur.next.next;
						ListNode temp2=dd.next;
						dd.next=temp1;
						temp1.next=temp2;
						
						dd=dum;
						count++;
						break;
					}else{
						dd=dd.next;
						count2++;
					}
					
				}
			}
		}
		return dum.next;
		
	}

	
}
