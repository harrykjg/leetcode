//http://blog.csdn.net/linhuanmars/article/details/23904937
//code ganker�������õ�nlogn�Ŀռ䣬������
//http://jixiangsanbao.wordpress.com/2014/06/19/convert-sorted-list-to-binary-search-tree/
//http://www.2cto.com/kf/201311/260287.html ���������
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

	//�����һ��д������arraylist��¼�����������
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
		
		if((start+end)/2-start>0){//��n����߻��ж��������죬����Ϊ��
		n.left=create(a,start,(start+end)/2-1);
		}
		else{
			n.left=null;
			}
		if(end-(start+end)/2>0){//��n���ұ߻��ж��������죬����Ϊ��
		n.right=create(a,(start+end)/2+1,end);
		}
		else{
			n.right=null;
			}
		return n;
		

	}
	//�ڶ���
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
	    TreeNode t=help(head,0,len-1);//�����ص���0��len-1�м���Ǹ��ڵ�
	    return t;
	  
	}
	//���ﻹ��ͦ�Ƶģ����rootҪά���ǵ�ǰ������b��e�м���Ǹ��ڵ㣬��headҪ����ָ���м��Ǹ��㡣
	//��3��5��7��10��15��,20,30�������
	//�ڽ����м�ڵ�֮ǰҪ�������ӣ����һ������3��������null��Ȼ���root��head��ֵ������
	//Ȼ�����head�ڵ��Ҫ����һ���ˣ�����ͦ�Ƶģ����head����һ����ͽ����Һ��ӿ���ûɶ��ϵ��
	//��ʵ�й�ϵ�������Һ���ʱ��Ҫ�Ƚ��������ӣ���null��Ȼ�������head�����˸��ڵ�7��Ȼ��head��
	//������һ��������ʵ�Ѿ�ָ������һ��ڵ�ĸ��ڵ���10����Ȼ�������Һ��ӣ���null������7��Ϊ5��
	//�Һ��ӣ�Ȼ�󷵻�root����5��Ϊ��һ������ӣ���ʱheadָ�����10��Ȼ��root��10��head������
	//һ����ָ��15����ʼ����10���Һ���20��Ȼ������Һ�����Ҫ�Ƚ�������15��������ӵ���Ҫ�Ƚ���
	//�������ӣ���null��Ȼ�������ڵ�15��Ȼ��head������һλָ��20��Ȼ����15���Һ�����null��
	//Ȼ�󷵻�15����Ϊ20�����ӣ�Ȼ��headָ��30����ʼ����20���Һ��ӣ�����20���Һ��ӵ����ӣ�
	//��null��������Ϊ���ӣ���ʱhead.next��Ϊ���ˣ��Ͳ���������
	//�����Ǽ�ʱ���ڵ�����ӣ������3,5,7,10,15,20������ӣ���ô����������������
	//       7
	//   3      15
	//     5  10  20 ������ӵ���
	public TreeNode help(ListNode head,int b,int e){
		if(b>e){
			return null;
		}
		int mid=(b+e)/2;
		TreeNode l=help(head,b,mid-1);
		TreeNode root=new TreeNode(head.val);
		
		root.left=l;
//		if(head.next!=null){//����������д�����headǰ��һλ�������ǲ��еģ�������ǰջ֮��ص�
//		head=head.next;//��һ��ջ��ʱ��head����ԭ����head��û��ǰ��һλ������дֻ�ı��˵�ǰ��
		              //head��ָ�򣬶���һ���headָ��˭����ָ��˭
//	}
	 if(head.next != null) {//��������д����
            head.val = head.next.val;
            head.next = head.next.next;//����358��head��3��������ȱ��558Ȼ��ѵڶ���5ȥ����
        }                               //�����58
		TreeNode r=help(head,mid+1,e);
		root.right=r;

		return root;
				
	}
	//�����
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
//������,û�����,���ű�һ��
	    
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
	    	TreeNode r=go(head,m,e);//����mҪ+1������֮ǰ��left�Ѿ�m-1��
	    	root.right=r;
	    	return root;
	    }
	    //���ϵķ�����Ӧ�ø��Ӷ���nlogn��Ҳaccept�ˣ����ǹ�����������ǲ�ͬ��״�ģ��ȽϷ��ϳ���
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
	  //�Լ���дһ�����nlogn�ķ������ȽϷ��ϳ���
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
		  //ע���������while��sortedListToBST5�Ĳ�һ�������¹������Ҳ��һ����������д��ȻҲ����
		  //�������д���о��Ƚ�˳������012��һ��������д��run.next!=null&&run.next.next!=null
		  //��ôwal����˳������1���������е㣬����������run.next!=end&&run.next.next!=end��wal
		  //��ʵ���߲��ˣ�ֻ��ָ��0����������ȻҲ�պö��ˡ���sortedListToBST5д����
		  //probe!=end && probe.next!=end������012��˵�Ļ�wal���ߵ�1������д�����б仯��������
		  //�����ǱȽ�˳��˼·����������д���Ƚ�˳�������Ĺ���Ƚϲ�һ��.�������������᲻����
		  //runΪ�յ���run.next��ʱ�򱨴�������񲻻�����������������ΪrunΪ�ռ�beginΪ��
		  //beginΪ�յ�ʱ��end��ʵҲ�϶��ǿ��ˣ��϶�����list������ˣ�����begin==end��return��
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
