//这题新建两个list，一个存小于x的，一个存大于x的，再连起来即可，很简单，但是得用O（n）空间
//应该用inplace的交换方法
//http://blog.csdn.net/linhuanmars/article/details/24446871 他这个很奇怪，看不太懂
//http://alexjixiang.com/2014/05/12/partition-list/
public class PartitionList {
	
	public static void main(String[] args) {
		PartitionList pl =new PartitionList();
		ListNode head=new ListNode(4);
		head.next=new ListNode(3);
		head.next.next=new ListNode(2);
		head.next.next.next=new ListNode(5);
		head.next.next.next.next=new ListNode(2);
//		head.next.next.next.next.next=new ListNode(2);
		ListNode rs=pl.partition(head, 3);
		while(rs!=null){
			System.out.println(rs.val);
			rs=rs.next;
		}
	}
	

	public ListNode partition(ListNode head, int x) {
		//这个想法好像与他们的不同，但是是由code ganker启发的
		//即找出所有小于x的节点中的最后一个，然后从最开始往后扫，把所有大于等于x的都扔到这个节点之后
		if (head == null) {
			return null;
		}
		ListNode dum=new ListNode(0);
		dum.next=head;
		ListNode cur=head;
		ListNode cur2=head;
		
		while(cur!=null){//先找出小于x的最后一个节点
			if(cur.val<x){
				cur2=cur;
			}
			cur=cur.next;
		}
		ListNode cur3=cur2;//注意！！！cur3只是个引用，他指向的是cur2的对象，如果cur2后面变了，那
		//cur3还是指的是原来的cur2的对象！而cur2变了其实是cur2指向了其他的对象，cur2原来的对象还在那
		cur=dum;
		    
		while(cur.next!=null&&cur.next!=cur3){//当前节点如果遇到了最后一个小于x的节点的话，就结束
			//然后从前往后扫，如果当前节点大于等于x，则把x
			//插入到小于x的最后一个节点的后面，然后把这个小于x的最后一个节点指向其下一个节点，这样
			//就能保证下一个大于x的节点插进来时是对的位置，保证了原list的相对位置不变
			if(cur.next.val<x){
				cur=cur.next;//这个节点不用换的话，cur才往前一步，而如果要换的话，这个节点
				//就会被扔到后面去，而cur.next就正好会变成下一个要检查的节点，真tm巧妙
				
			}else{                    //例 1 4 3 2 5
				ListNode temp1=cur.next; //把4从1和3中切出来
				cur.next=cur.next.next;//把1和3连起来
				ListNode temp2=cur2.next;//把5保存起来
				cur2.next=temp1;//把4接到2后面
				cur2.next.next=temp2;//把5接到4后面，则原数组变成1 3 2 4 5
				cur2=cur2.next;//把cur2变成4，即下一个被扔过来的数就插在4后面
			}
			
		}
		return dum.next;

	}
//第二次写，思路是把先找到第一个大于等于x的点，然后继续往后扫，遇到比x大的就不管继续，遇到比x小的
	//就把它塞到这个第一个大于x的节点的前面，例：143251，先找到4，（得到它的前驱，1)，然后遇到2，
	//变成124351，然后遇到1，变成121435
	public ListNode partition2(ListNode head, int x) {
		if (head == null) {
			return null;
		}
		ListNode curbig=null;
		ListNode dum=new ListNode(0);
		dum.next=head;
		ListNode cur=dum;
		while(cur.next!=null){
			if(cur.next.val>=x){
				curbig=cur;
				break;
			}
			cur=cur.next;
		}
		if(curbig==null){//这里容易漏，就是根本没有大于x的情况
			return head;
		}
		cur=curbig.next;
		
		while(cur.next!=null){
			if(cur.next.val>=x){
				cur=cur.next;
			}else{
				ListNode temp1=cur.next;
				cur.next=cur.next.next;
				temp1.next=curbig.next;
				curbig.next=temp1;
				curbig=curbig.next;
			}
		}
		return dum.next;
	}
	
}
