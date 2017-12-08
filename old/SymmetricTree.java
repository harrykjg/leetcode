import java.util.ArrayList;
import java.util.LinkedList;
//�����������ˣ������ά��һ��queue��ÿ�ε�����һ���ڵ�t1�����һ���ڵ�t2��Ȼ��Աȣ�Ȼ���ٰ�
//t1�����ң�t2���������μӽ�queue���ÿ�ε�����һ�������һ���ڵ�Աȡ���ʵ�ǲ��еģ������3��
//��4���ڵ㣬�����˵�һ�������һ������queue�ﻹʣ2������ʱ�ٰ�t1��t2�Ľڵ�������Ҽӽ�queue�
//queue���˳��ͱ�ɵ��������������ϵ��Ĳ�Ľڵ��ˣ��Ͳ����м�ԳƵ��ˡ�

//http://blog.csdn.net/linhuanmars/article/details/23072829  ���Ҳ����
//http://jixiangsanbao.wordpress.com/2014/02/22/symmetric-tree/����������������2��queue��������ʵ�뷨�ǲ�ͬ��

public class SymmetricTree {
	public static void main(String[] args) {
		SymmetricTree st=new SymmetricTree();
		TreeNode root=new TreeNode(1);
		root.left=new TreeNode(2);
		root.right=new TreeNode(2);
		root.left.left=new TreeNode(4);
		root.left.right=new TreeNode(5);
		root.right.left=new TreeNode(5);
		root.right.right=new TreeNode(4);
//		root.left.left.left=new TreeNode(6);
//		root.left.right.left=new TreeNode(8);
//		root.left.right.right=new TreeNode(9);
//		root.right.left.left=new TreeNode(9);
//		root.right.left.right=new TreeNode(8);
//		root.right.right.right=new TreeNode(6);
		System.out.println(st.isSymmetric4(root));
	}
	
	
	public boolean isSymmetric(TreeNode root) {
		if(root==null){
			return true;
		}
		
	
		LinkedList<TreeNode> q1=new LinkedList<TreeNode>();
		LinkedList<TreeNode> q2=new LinkedList<TreeNode>();
		q1.add(root);
		while(!q1.isEmpty()){
			//���������������������q1�����ΰ�nodeȫ���ӵ�q2֮�У�������֮����ȥ���q2�е�ֵ�Ƿ�Գ�
				TreeNode temp=q1.pop();
				if(temp!=null){
					q2.add(temp.left);
					q2.add(temp.right);
					
				}
		//��q1�Ѿ����ˣ�˵����ǰ��һ��ĺ��Ӷ��ӵ�q2���ˣ��Ϳ��Կ�ʼ���q2�еĺ����Ƿ�Գ���
			if(q1.isEmpty()){//��q2����û�м���ڵ㵽q�еģ���Ϊq2�ó����Ľڵ���ǰ��ԳƵĽڵ�
				//���Ҫ����queue�еĻ��ͱ�Ȼ����queue�в��Գ��ˣ�ֻ����q1�����μӲŶԳ�
				int i=0;
				int j=q2.size()-1;
				while(i<=j){
					TreeNode t1=q2.get(i);
					TreeNode t2=q2.get(j);
					
					if((t1!=null&&t2==null)||t1==null&&t2!=null){
						return false;
					}
					if(t1==null&&t2==null){
						i++;
						j--;
						continue;
					}
					if(t1.val!=t2.val){
						return false;
					}
					
//					if(t1.left!=null&&t2.right==null){//��һϵ����ʵ���ã���Ϊ��q1�ӽڵ�ʱ
					                                  //��ÿ��λ�ö����˵ģ������ǿգ�����
					                                  //����ȡ�����϶��ǶԳƵ�ȡ,��Ȼ�����ж�
//						return false;
//					}
//					if(t1.left==null&&t2.right!=null){
//						return false;
//					}
//					if(t1.right!=null&&t2.left==null){
//						return false;
//					}
//					if(t1.right==null&&t2.left!=null){
//						return false;
//					}
					i++;
					j--;
				}
				q1=new LinkedList<TreeNode>(q2);
				q2.clear();
			}
			
		}
		return true;

	}
	//�Ҳ�����Ϊ���inorder�ĳ�����˳���Ƿ���Ŀ��Եģ�����ǲ��еģ���1,2,3,3,#,2,#
	public boolean isSymmetric2(TreeNode root){
		if(root==null){
			return true;
		}
		ArrayList<Integer> al=new ArrayList<Integer>();
		
		LinkedList<TreeNode> q=new LinkedList<TreeNode>();
		q.add(root);
		while(!q.isEmpty()){
			TreeNode temp=q.peek();
			
			if(temp.left!=null){
				q.push(temp.left);
				temp.left=null;
				continue;
			}else{
				al.add(temp.val);
				q.pop();
			}
			if(temp.right!=null){
				q.add(temp.right);
				temp.right=null;	
			}
			
		}
		int b=0;
		int e=al.size()-1;
		while(b<e){
			if(al.get(b)!=al.get(e)){
				return false;
			}
			b++;
			e--;
		}
		return true;
				
	}
	//�ҵ����������ʵ����code ganker���뷨.Ȼ��������һ���е�仯��,��Ľ���Ǵ�ģ��뷨��
	//q1����ߵģ��ڵ��Ǵ����Ҽӽ�ȥ�ģ�q2���ұߵģ��ڵ�Ҳ�Ǵ����Ҽӽ�ȥ�ģ��ؼ��Ǵ�q���ýڵ�
	//��ʱ��q1��poll��q2��polllast��1,2,2,#,3,#,3������ӾͲ�����
	//��������Ǵ�ģ�
	public boolean isSymmetric3(TreeNode root){
		if(root==null){
			return true;
		}
		if(root.left==null&&root.right==null){
			return true;
		}
		if((root.left==null&&root.right!=null)||(root.right==null&&root.left!=null)){
			return false;
		}
		ArrayList<Integer> al=new ArrayList<Integer>();
		
		LinkedList<TreeNode> q1=new LinkedList<TreeNode>();
		LinkedList<TreeNode> q2=new LinkedList<TreeNode>();
		q1.add(root.left);
		q2.add(root.right);
		
		while(!q1.isEmpty()&&!q2.isEmpty()){
			TreeNode n1=q1.poll();
			TreeNode n2=q2.pollLast();
			if((n1==null&&n2!=null)||(n2==null&&n1!=null)){
				return false;
			}
			
			if(n1.val!=n2.val){
				return false; 
			}
			if(n1.left!=null){
				q1.add(n1.left);
			}
			if(n2.left!=null){
				q2.add(n2.left);
			}
			if(n1.right!=null){
				q1.add(n1.right);
			}
			if(n2.right!=null){
				q2.add(n2.right);
			}
		}
	
		return true;
				
	}
	//1/10,�����ϼ����code ganker�ģ����ǲ����Ҳ�
	public boolean isSymmetric4(TreeNode root){
		if(root==null){
			return true;
		}
		LinkedList<TreeNode> q1=new LinkedList<TreeNode>();
		LinkedList<TreeNode> q2=new LinkedList<TreeNode>();
		q1.add(root.left);
		
		q2.add(root.right);
		
		int count1=1;
		int count2=0;
		while(!q1.isEmpty()&&!q2.isEmpty()){
			while(count1>0){
				TreeNode temp1=q1.poll();
				TreeNode temp2=q2.removeLast();
				if(temp1==null&&temp2!=null||(temp1!=null&&temp2==null)){
					return false;
				}
				if(temp1!=null&&temp1.val!=temp2.val){
					return false;
				}
				if(temp1!=null){
					q1.add(temp1.left);
					q1.add(temp1.right);
					count2+=2;
				}
				if(temp2!=null){
					q2.add(temp2.left);
					q2.add(temp2.right);
				}
				count1--;
			}
			count1=count2;
			count2=0;

			
		}
		return true;
	}

}
