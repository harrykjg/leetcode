import java.util.ArrayList;
import java.util.LinkedList;
//http://blog.csdn.net/hairongtian/article/details/7930937  这样写比较简便
//http://blog.csdn.net/linhuanmars/article/details/20187257 还涉及到 morris traversal
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
	        		temp.left=null;//设为空的原因是，当回到上面层的node时，看到他没有左孩子了，
	        		continue;    //才能输出
	        	}
	        	else{
	        		al.add(temp.val);//就是当一个节点他没有左孩子的时候，就该输出了
	        		ls.pop();
	        	}
	        	if(temp.right!=null){
	        		ls.push(temp.right);
	        		temp.right=null;//这个不写都行，因为只要节点的left为空就可以输出，right是否空
	                               //没关系
	        	}
	        }
	        return al;
	        
	    }
	 //第二次写还调试了3次才对，对比第一次，第一次就是当时就在push了temp.left之后就把left设为null了
	 //而我这是把当前节点加进al之后，再用个temp2把ls里的上一个节点的左子树设为空的，比较别扭
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
	 //算第四次看了吧
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
	 //第五次,思路是对的，typo错了两个地方
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
	 //recursive的,差点不会写了
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
