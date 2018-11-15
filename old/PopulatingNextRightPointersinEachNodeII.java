package old;
import java.util.LinkedList;

//http://blog.csdn.net/wzy_1988/article/details/17412025
//http://jixiangsanbao.wordpress.com/2014/05/31/populating-next-right-pointers-in-each-node-ii/
public class PopulatingNextRightPointersinEachNodeII {
	public void connect(TreeLinkNode root) {

		if (root == null) {
			return;
		}
		if(root.right!=null){  
			if(root.next==null){
				root.right.next=null;
			}
			else{
				TreeLinkNode temp2=find(root.next);
				root.right.next=temp2;
			}
			
		}
		if(root.left!=null){
			if(root.right!=null){
				root.left.next=root.right;
			}
			else if(root.next==null){
				root.left.next=null;
			}
			else{
				TreeLinkNode temp=find(root.next);
				root.left.next=temp;
			}
			
		}
		connect(root.right);//Ҫ�Ȱ��ұߵ�����������ߵģ�����Ļ��ݹ���ȥҪ����ߵ�ʱ��,��Ҫ��ͬ
		//��ε��ұߵģ����ұ���û�������Ի��д�
		connect(root.left);
		
		return;
		

	}

	private TreeLinkNode find(TreeLinkNode n){
		if(n==null){
			return null;
		}
		TreeLinkNode cur=n;
		while(cur!=null&&cur.left==null&&cur.right==null){
			cur=cur.next;
		}
		if(cur==null){
			return null;
		}
		if(cur.left!=null){
			return cur.left;
		}else{
			return cur.right;
		}
		
	}
	//�ڶ���д��һ�ι����ؼ���Ҫ�ٺ����ӣ��͵�һ�α��˵��뷨����һ�������Ƿ�����Ҫ��constant space
	//�ģ�����������queue��Ӧ�ò���
	public void connect2(TreeLinkNode root) {
		if(root==null){
			return;
		}
		root.next=null;
		LinkedList<TreeLinkNode> q=new LinkedList<TreeLinkNode>();
		q.add(root);
		while(!q.isEmpty()){
			TreeLinkNode temp=q.poll();
			if(temp.left!=null){
				temp.left.next=con(temp,temp.left);
				q.add(temp.left);
			}
			if(temp.right!=null){
				temp.right.next=con(temp,temp.right);
				q.add(temp.right);
			}
			
		}
		
	}
	private TreeLinkNode con(TreeLinkNode root,TreeLinkNode child){
		if(root.right!=null&&root.right!=child){//�����������������������ڵ����Һ��ӵĻ�
			return root.right;              //��ֱ��connect��
		}
		
		else{
			while(root.next!=null){
				root=root.next;
				if(root.left!=null){
					return root.left;
				}
				if(root.right!=null){
					return root.right;
				}
				
			}
			return null;
		}
		
	}
	//������,����д��,���ǿ��ص�һ�ε���·
	public void connect3(TreeLinkNode root) {
		if(root==null){
			return;
		}
		if(root.right!=null){
			if(root.next==null){
				root.right.next=null;
			}else{
				root.right.next=con2(root.next);
			}
		}
		
		if(root.left!=null){
			if(root.right!=null){
				root.left.next=root.right;
			}else{
				root.left.next=con2(root.next);
			}
		}
		connect3(root.right);
		connect3(root.left);
		
	}
	private TreeLinkNode con2(TreeLinkNode root){
		TreeLinkNode cur=root;
	
		
		while(true){
			if(cur==null){
				return null;
			}
			if(cur.left!=null){
				
				return cur.left;
			}
			if(cur.right!=null){
				
				return cur.right;
			}
			cur=cur.next;
		}
		
	}
	//11/11/2018,д�Ĳ�˳�����ҵݹ�ʱ���ǵ������ұߵĲ��У�����һ�������ע��
	public void connect4(TreeLinkNode root) {
		if(root==null){
			return;
		}
		if(root.left!=null){
			if(root.right!=null){
				root.left.next=root.right;
			}else{
				root.left.next=find2(root.next);
			}
		}
		if(root.right!=null){
			root.right.next=find2(root.next);
		}
		connect4(root.right);
		connect4(root.left);

	}
	TreeLinkNode find2(TreeLinkNode root){
		TreeLinkNode cur=root;
		while (cur!=null){
			if(cur.left!=null){
				return cur.left;
			}
			if(cur.right!=null){
				return cur.right;
			}
			cur=cur.next;
		}
		return null;
	}
	
}
