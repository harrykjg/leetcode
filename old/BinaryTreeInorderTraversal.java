import java.util.ArrayList;
import java.util.LinkedList;
//http://blog.csdn.net/hairongtian/article/details/7930937  ����д�Ƚϼ��
//http://blog.csdn.net/linhuanmars/article/details/20187257 ���漰�� morris traversal
public class BinaryTreeInorderTraversal {
	public static void main(String[] args) {
		
	}
	
	
	 public ArrayList<Integer> inorderTraversal(TreeNode root) {
	        ArrayList<Integer> al=new ArrayList<Integer>();
	        if(root==null){
	        	return al;
	        }
	        
	        
	        LinkedList<TreeNode> ls=new LinkedList<TreeNode>();
	        ls.add(root);
	        while(!ls.isEmpty()){
	        	TreeNode temp=ls.peek();
	        	if(temp.left!=null){
	        		ls.push(temp.left);
	        		temp.left=null;//��Ϊ�յ�ԭ���ǣ����ص�������nodeʱ��������û�������ˣ�
	        		continue;    //�������
	        	}
	        	else{
	        		al.add(temp.val);//���ǵ�һ���ڵ���û�����ӵ�ʱ�򣬾͸������
	        		ls.pop();
	        	}
	        	if(temp.right!=null){
	        		ls.push(temp.right);
	        		temp.right=null;//�����д���У���ΪֻҪ�ڵ��leftΪ�վͿ��������right�Ƿ��
	                               //û��ϵ
	        	}
	        }
	        return al;
	        
	    }
	 //�ڶ���д��������3�βŶԣ��Աȵ�һ�Σ���һ�ξ��ǵ�ʱ����push��temp.left֮��Ͱ�left��Ϊnull��
	 //�������ǰѵ�ǰ�ڵ�ӽ�al֮�����ø�temp2��ls�����һ���ڵ����������Ϊ�յģ��Ƚϱ�Ť
	 public ArrayList<Integer> inorderTraversal2(TreeNode root) {
	        ArrayList<Integer> al=new ArrayList<Integer>();
	        if(root==null){
	        	return al;
	        }
	        
	        
	        LinkedList<TreeNode> ls=new LinkedList<TreeNode>();
	        ls.add(root);
	        while(!ls.isEmpty()){
	        	TreeNode temp=ls.peek();
	        	if(temp.left==null){
	        		al.add(temp.val);
	        		ls.pop();
	        		TreeNode temp2=ls.peek();
	        		if(temp2!=null){
	        			temp2.left=null;
	        		}
	        		
	        		if(temp.right!=null){
	        			ls.push(temp.right);
	        		}
	        	}
	        	else{
	        		ls.push(temp.left);
	        	}
	        	
	        }
	        return al;
	        
	    }
	 //����Ĵο��˰�
	 public ArrayList<Integer> inorderTraversal4(TreeNode root) {
	  
			 ArrayList<Integer> a=new ArrayList<Integer>();
			 if(root==null){
	        return a;   
	       }
			 LinkedList<TreeNode> q=new LinkedList<TreeNode>();
			 q.add(root);
			 while(!q.isEmpty()){
				 TreeNode temp=q.peek();
				 if(temp.left!=null){
					 q.push(temp.left);
					 temp.left=null;
					 continue;
				 }
				 else{
					 a.add(temp.val);
					 q.pop();
					 if(temp.right!=null){
						 q.push(temp.right);
					 }
				 }
			 }
			 return a;
		 }
	 //�����,˼·�ǶԵģ�typo���������ط�
	 public ArrayList<Integer> inorderTraversal5(TreeNode root) {
		 ArrayList<Integer> a=new ArrayList<Integer>();
		 if(root==null){
			 return a;
		 }
		 LinkedList<TreeNode> st=new LinkedList<TreeNode>();
		 st.add(root);
		 while(!st.isEmpty()){
			 TreeNode temp=st.peek();
			 
			 if(temp.left==null){
				 a.add(temp.val);
				 st.pop();
				 if(temp.right!=null){
					 st.push(temp.right);
					 temp.right=null;
				 }
				 continue;
			 }
			 if(temp.left!=null){
				 st.push(temp.left);
				temp.left=null;
			 }
		 }
		 return a;
	 }
	 //recursive��,��㲻��д��
	 public ArrayList<Integer> inorderTraversal6(TreeNode root) {
		 ArrayList<Integer> a=new ArrayList<Integer>();
		 if(root==null){
			 return a;
		 }
		 recur(a,root);
		 return a;
	 }
	 private void recur(ArrayList<Integer> a,TreeNode root){
		 if(root==null){
			 return;
		 }
		
			 recur(a,root.left);
			 a.add(root.val);
		 
		 recur(a,root.right);
	 }

}
