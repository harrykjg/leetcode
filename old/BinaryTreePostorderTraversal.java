import java.util.ArrayList;
import java.util.LinkedList;


public class BinaryTreePostorderTraversal {

	
	
	  public ArrayList<Integer> postorderTraversal(TreeNode root) {
			ArrayList<Integer> al = new ArrayList<Integer>();

			if (root == null) {
				return al;
			}
			LinkedList<TreeNode> ls = new LinkedList<TreeNode>();
			
			ls.add(root);

			while (!ls.isEmpty()) {
				TreeNode temp = ls.peek();

				if (temp.left != null) {
	                ls.push(temp.left);
	                temp.left=null;
	                continue;
				}
				else if (temp.right!=null){
					ls.push(temp.right);
						temp.right=null;
						continue;
				}
				else{
					al.add(temp.val);
					ls.pop();
				}
				
			}
			return al;
		}
	   public ArrayList<Integer> postorderTraversal2(TreeNode root) {
		   ArrayList<Integer> al=new ArrayList<Integer>();
		   if(root==null){
			  return al;
		   }
		   LinkedList<TreeNode> q=new LinkedList<TreeNode>();
		   q.add(root);
		   while(!q.isEmpty()){
			   TreeNode temp=q.peek();
			   if(temp.left==null&&temp.right==null){
				   al.add(temp.val);
				   q.pop();
			   }
			   if(temp.left!=null){
				   q.push(temp.left);//Ð´add»á´í
				   temp.left=null;
				   continue;
			   }
			   if(temp.right!=null){
				   q.push(temp.right);
				   temp.right=null;
				   continue;
			   }
		   }
		  return al;
	  }
	   //9ÔÂ29
	   public ArrayList<Integer> postorderTraversal3(TreeNode root) {
		   ArrayList<Integer> al=new ArrayList<Integer>();
		   if(root==null){
			  return al;
		   }
		   LinkedList<TreeNode> q=new LinkedList<TreeNode>();
		   q.add(root);
		   while(!q.isEmpty()){
			   TreeNode temp=q.peek();
			   
			   if(temp.left==null&&temp.right==null){
				   al.add(temp.val);
				   q.pop();
				   
				   
			   }
			   if(temp.left!=null){
				   q.push(temp.left);
				   temp.left=null;
				   continue;
			   }
			   if(temp.right!=null){
				   q.push(temp.right);
				   temp.right=null;
			   }
			   
		   }
		   return al;
	   }
}
