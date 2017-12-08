//cracking the code上有
//http://blog.csdn.net/linhuanmars/article/details/23810735

public class ValidateBinarySearchTree {
	
	public static void main(String[] args) {
		ValidateBinarySearchTree vb=new ValidateBinarySearchTree();
		TreeNode root=new TreeNode(0);
//		root.left=new TreeNode(5);
		root.right=new TreeNode(1);
//		root.right.left=new TreeNode(6);
//		root.right.right=new TreeNode(20);
		
		System.out.println(vb.isValidBST3(root));
		
	}
	
	
//我只能说，太他妈巧妙了,开始向着以为没有去更新min或者max所以以为不行，其实min和max就在你传入参数
	//时就改变了。开始还以为要root和root的父节点去确定root左右孩子的范围，其实是不行的，因为如果
	//树的孩子多几层，可能还要用的root的父节点的父节点。。。所以不行
	public boolean isValidBST(TreeNode root) {  //code ganker的代码
	    return helper(root, Integer.MIN_VALUE, Integer.MAX_VALUE);   
	}  
	boolean helper(TreeNode root, int min, int max)     
	{    
	    if(root == null)    
	       return true;    
	    if(root.val <= min || root.val >= max)  
	         return false;    
	     return helper(root.left, min, root.val) && helper(root.right, root.val, max);  
	}   
	
	public boolean isValidBST2(TreeNode root){//如果是我就这样写的
		if(root==null){
			return true;
		}
		if(!check(root.left,Integer.MIN_VALUE,root.val)){
			return false;
		}
		if(!check(root.right,root.val,Integer.MAX_VALUE)){
			return false;
		}
		return true;
	}
	public boolean check(TreeNode root,int min, int max){
		if(root==null){
			return true;
		}
		if(root.val<=min||root.val>=max){
			return false;
		}
		if(!check(root.left,min,root.val)||!check(root.right,root.val,max)){
			return false;
		}
		return true;
	}
	//第二次写,还比较顺
	public boolean isValidBST3(TreeNode root) { 
		if(root==null){
			return true;
		}
		return check2(root.left,root.val,Integer.MIN_VALUE)&&
				check2(root.right,Integer.MAX_VALUE,root.val);
		
	}
	private boolean check2(TreeNode root,int max,int min){
		if(root==null){
			return true;
		}
		if(root.val>=max||root.val<=min){
			return false;
		}
		return check2(root.left,root.val,min)&&check2(root.right,max,root.val);
	}

}
