//cracking the code����
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
	
	
//��ֻ��˵��̫����������,��ʼ������Ϊû��ȥ����min����max������Ϊ���У���ʵmin��max�����㴫�����
	//ʱ�͸ı��ˡ���ʼ����ΪҪroot��root�ĸ��ڵ�ȥȷ��root���Һ��ӵķ�Χ����ʵ�ǲ��еģ���Ϊ���
	//���ĺ��Ӷ༸�㣬���ܻ�Ҫ�õ�root�ĸ��ڵ�ĸ��ڵ㡣�������Բ���
	public boolean isValidBST(TreeNode root) {  //code ganker�Ĵ���
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
	
	public boolean isValidBST2(TreeNode root){//������Ҿ�����д��
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
	//�ڶ���д,���Ƚ�˳
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
