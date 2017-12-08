//http://blog.csdn.net/linhuanmars/article/details/23904937
//code ganker这样是用的nlogn的空间，很巧妙
//http://jixiangsanbao.wordpress.com/2014/06/19/convert-sorted-list-to-binary-search-tree/
//http://www.2cto.com/kf/201311/260287.html 这个有启发
import java.util.ArrayList;

public class ConvertSortedlistToBinarySearchTree {

	public static void main(String[] args) {

		ListNode l=new ListNode(0);
		l.next=new ListNode(1);
		l.next.next=new ListNode(2);
		l.next.next.next=new ListNode(3);
		l.next.next.next.next=new ListNode(4);
		l.next.next.next.next.next=new ListNode(5);
//		l.next.next.next.next.next.next=new ListNode(30);
//		l.next.next.next.next.next.next.next=new ListNode(8);
//		
		ConvertSortedlistToBinarySearchTree c=new ConvertSortedlistToBinarySearchTree();
		
		
		TreeNode n=c.sortedListToBST6(l);
		System.out.println(n.val);
		
	}

	//这个第一次写是用了arraylist记录下整个链表的
	public TreeNode sortedListToBST(ListNode head) {
		
		ListNode cur=head;
		ArrayList<Integer> al=new ArrayList<Integer>();
		
		if(head==null){
			return null;
			
		}
		
		while(cur!=null){
			al.add(cur.val);
			cur=cur.next;
		}
		TreeNode root=create(al,0,al.size()-1);
		
		return root;
	}
	
	public TreeNode create(ArrayList<Integer> a,int start,int end){
		
        
		TreeNode n=new TreeNode(a.get((start+end)/2));
		
		
		if(start==end){
			return n;
		}
		
		if((start+end)/2-start>0){//若n点左边还有东西，则创造，否则为空
		n.left=create(a,start,(start+end)/2-1);
		}
		else{
			n.left=null;
			}
		if(end-(start+end)/2>0){//若n点右边还有东西，则创造，否则为空
		n.right=create(a,(start+end)/2+1,end);
		}
		else{
			n.right=null;
			}
		return n;
		

	}
	//第二次
	public TreeNode sortedListToBST2(ListNode head) {
	    if(head==null){
	        return null;
	    }
	    ListNode cur=head;
	    int len=1;
	    while(cur.next!=null){
	    	cur=cur.next;
	    	len++;
	    }
	    TreeNode t=help(head,0,len-1);//他返回的是0和len-1中间的那个节点
	    return t;
	  
	}
	//这里还是挺绕的，这个root要维持是当前层左右b和e中间的那个节点，即head要保持指向中间那个点。
	//用3，5，7，10，15，,20,30这个例子
	//在建立中间节点之前要建立左孩子，最后一个左孩子3的左孩子是null，然后把root用head的值建立，
	//然后这个head节点就要往后一步了，这里挺绕的，这个head往后一步后和建立右孩子看似没啥关系，
	//其实有关系，建立右孩子时，要先建立其左孩子，是null，然后用这个head建立了根节点7，然后head又
	//往后了一步，（其实已经指向了上一层节点的根节点了10），然后建立其右孩子，是null，返回7作为5的
	//右孩子，然后返回root，即5作为上一层的左孩子，此时head指向的是10，然后root即10，head又往后
	//一步，指向15，开始建立10的右孩子20，然后这个右孩子又要先建立左孩子15，这个左孩子的又要先建立
	//他的左孩子，是null，然后建立根节点15，然后head再往后一位指向20，然后建立15的右孩子是null，
	//然后返回15，作为20的左孩子，然后head指向30，开始建立20的右孩子，再找20的右孩子的左孩子，
	//是null，返回作为左孩子，此时head.next就为空了，就不往后移了
	//以上是即时个节点的例子，如果是3,5,7,10,15,20这个例子，那么建立出来的数就是
	//       7
	//   3      15
	//     5  10  20 这个样子的了
	public TreeNode help(ListNode head,int b,int e){
		if(b>e){
			return null;
		}
		int mid=(b+e)/2;
		TreeNode l=help(head,b,mid-1);
		TreeNode root=new TreeNode(head.val);
		
		root.left=l;
//		if(head.next!=null){//这里我这样写是想把head前进一位，但是是不行的，跳出当前栈之后回到
//		head=head.next;//上一层栈的时候，head还是原来的head，没有前进一位，这样写只改变了当前层
		              //head的指向，而上一层的head指向谁还是指向谁
//	}
	 if(head.next != null) {//吉祥这样写就行
            head.val = head.next.val;
            head.next = head.next.next;//比如358，head是3，这就是先变成558然后把第二个5去掉，
        }                               //变成了58
		TreeNode r=help(head,mid+1,e);
		root.right=r;

		return root;
				
	}
	//吉祥的
	 public TreeNode sortedListToBST3(ListNode head){
	        if(head == null){
	            return null;
	        }
	        int len = 1;
	        ListNode curr = head;
	        while(curr.next != null){
	            curr = curr.next;
	            len++;
	        }
	        TreeNode res = helper(head, 0, len-1);
	        return res;
	    }

	    //construct tree from the listnode in (start)th index to the listnode in (end)th index
	    private TreeNode helper(ListNode head, int start, int end){
	        if(start > end){
	            return null;
	        }
	        int mid = (start+end)/2;
	        TreeNode left = helper(head, start, mid-1);
	        TreeNode root = new TreeNode(head.val);
	        root.left = left;
	        //java is passed by value, the following two lines equal to head = head.next. It makes sure that head points to the next root
	        if(head.next != null) {
	            head.val = head.next.val;
	            head.next = head.next.next;
	        }
	        root.right = helper(head, mid+1, end);
	        return root;
	    }
//第三轮,没想出来,试着背一遍
	    
	    public TreeNode sortedListToBST4(ListNode head){
	    	if(head==null){
	    		return null;
	    	}
	    	ListNode cur=head;
	    	int len=1;
	    	while(cur.next!=null){
	    		cur=cur.next;
	    		len++;
	    	}
	    	return go(head,0,len-1);
	    }
	    private TreeNode go(ListNode head,int b,int e){
	    	if(b>e){
	    		return null;
	    	}
	    	int m=(b+e)/2;
	    	TreeNode l=go(head,b,m-1);
	    	TreeNode root=new TreeNode (head.val);
	    	root.left=l;
	    	if(head.next!=null){
	    		head.val=head.next.val;
	    		head.next=head.next.next;
	    	}
	    	TreeNode r=go(head,m,e);//这里m要+1，尽管之前的left已经m-1了
	    	root.right=r;
	    	return root;
	    }
	    //网上的方法，应该复杂度是nlogn，也accept了，但是构造出来的树是不同形状的，比较符合常理
	    public  TreeNode sortedListToBST5(ListNode head) {    
	    	return rec(head, null);     
	    	}   
	       public  TreeNode rec(ListNode start, ListNode end){    
	    	   if(start == end){      
	    		   return null;         
	    		   }      
	    	          ListNode mid = start;          
	    	          ListNode probe = start;    
	    	          while(probe!=end && probe.next!=end){    
                              mid = mid.next;              
                              probe = probe.next.next;         
                              }       
	    	          TreeNode root = new TreeNode(mid.val);        
	    	          root.left = rec(start, mid);        
	    	          root.right = rec(mid.next, end);      
	    	          return root;     
	     }
	  //自己再写一遍这个nlogn的方法，比较符合常理。
	  public  TreeNode sortedListToBST6(ListNode head) {
		  if(head==null){
			  return null;
		  }
		  return con(head,null); 
	  }
	  private TreeNode con(ListNode begin,ListNode end){
		  if(begin==end){
			  return null;
		  }
		  ListNode wal=begin;
		  ListNode run=begin;
		  //注意我这里的while和sortedListToBST5的不一样，导致构造的树也不一样，我这样写居然也对了
		  //我这个是写法感觉比较顺，比如012，一般是这样写的run.next!=null&&run.next.next!=null
		  //那么wal就能顺利到达1，正好是中点，但是这题是run.next!=end&&run.next.next!=end，wal
		  //其实就走不了，只能指向0，但这样居然也刚好对了。而sortedListToBST5写的是
		  //probe!=end && probe.next!=end，对于012来说的话wal能走到1，这样写起来有变化但是树的
		  //构造是比较顺的思路，我这样是写法比较顺但是树的构造比较不一样.这里后来我想想会不会有
		  //run为空导致run.next的时候报错，结果好像不会出现这样的情况，因为run为空即begin为空
		  //begin为空的时候end其实也肯定是空了（肯定到了list的最后了）所以begin==end就return了
		  while(run.next!=end&&run.next.next!=end){
			  wal=wal.next;
			  run=run.next.next;
		  }
		  TreeNode root=new TreeNode(wal.val);
		  TreeNode l=con(begin,wal);
		  TreeNode r=con(wal.next,end);
		  root.left=l;
		  root.right=r;
		  return root;
	  }
	       
}
