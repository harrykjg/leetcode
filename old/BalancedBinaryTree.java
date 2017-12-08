//http://blog.csdn.net/linhuanmars/article/details/23731355
//http://jixiangsanbao.wordpress.com/2014/02/24/balanced-binary-tree/
public class BalancedBinaryTree {
//Ҫ��ÿ���ڵ㶼�Ƿ�ƽ��

	public static void main(String[] args) {
		
		TreeNode n1=new TreeNode(20);
		n1.left=new TreeNode(10);
		n1.right=new TreeNode(30);
		n1.left.left=new TreeNode(5);
		n1.left.left.left=new TreeNode(4);
//		n1.left.left.left.left=new TreeNode(3);
		n1.left.right=new TreeNode(15);
		n1.left.right.right=new TreeNode(18);
//		n1.left.right.right.right=new TreeNode(19);
		n1.right.right=new TreeNode(40);
		n1.right.right.right=new TreeNode(50);
//		n1.right.right.right.right=new TreeNode(60);
		
		System.out.println(isBalanced(n1));
		
	}
	
	public static boolean isBalanced(TreeNode root) {
	     if(root==null){
	    	 return true;
	     }
	     int left=0;
	     int right=0;
	     boolean flag=true;
	     if(root.left==null&&root.right==null){
	    	 return true;
	     }
	     if(root.left!=null){
	    	 left=1+getDegree(root.left);
	    	 flag=isBalanced(root.left);
	    	 if(flag==false){
	    		 return false;
	    	 }
	     }
	     if(root.right!=null){
	    	 right=1+getDegree(root.right);
	    	 flag=isBalanced(root.right);
	    	 if(flag==false){
	    		 return false;
	    	 }
	     }
	     
	     if(Math.abs(left-right)>1){
	    	 flag= false;
	     }else{
	    	 flag= true;
	     }
	     return flag;

	    }
	public static int getDegree(TreeNode root){
		
		int left=0;
		int right=0;
		if(root.left!=null){
			left++;
			left+=getDegree(root.left);
		}
		
		if(root.right!=null){
			right++;
			right+=getDegree(root.right);
		}
		
		if(left>right){
			return left;
		}else{
			return right;
		}
		
		
	}
	//�ڶ�������Ǵ�ģ��뿴��ǰ�ڵ��Ƿ��ǿգ���������ұ�ֻ��һ�����ӣ������ҿ����ֻ��һ������
	//����true�������ټ����ߺ��ұ��Ƿ�ͬʱbalance���������ǲ��е�,���ǵ�ȡ�ø߶�
	public  boolean isBalanced2(TreeNode root){
//		if(root==null){
//			return true;
//		}
//		if(root.left==null&&root.right==null){
//			return true;
//		}
//		if(root.left!=null&&root.right==null&&root.left.left==null&&root.left.right==null){
//			return true;
//		}
//		if(root.left==null&&root.right!=null&&root.right.left==null&&root.right.right==null){
//			return true;
//		}
//		if(isBalanced2(root.left)&&isBalanced2(root.right)){
//			return true;
//		}else{
//			return false;
//		}
		
		if(root==null){
			return true;
		}
		if(!isBalanced2(root.left)||!isBalanced2(root.right)){//Ҫÿ����������飬�������if
			return false;          //���ģ���{1,2,2,3,#,#,3,4,#,#,4}�������
		}
		return Math.abs(degree(root.left)-degree(root.right))>1;
			
	}
	public int degree(TreeNode root){
		if(root==null){
			return 0;
		}
		int l=degree(root.left);
		int r=degree(root.right);
		
		return (l>=r?l:r)+1;
	}
	//������дһ��accept
	public  boolean isBalanced3(TreeNode root) {
	     if(root==null){
	    	 return true;
	     }
	     int left=0;
	     int right=0;
	     
	     left=getDegree2(root.left);
	     right=getDegree2(root.right);
	     if(Math.abs(left-right)>1){
	    	 return false;
	     }
	     return isBalanced2(root.left)&&isBalanced2(root.right);
	     
	     
	    }
	public  int getDegree2(TreeNode root){
		
		if(root==null){
			return 0;
		}
		int left=0;
		int right=0;
		
			left=getDegree2(root.left);
		
		
	
			right=getDegree2(root.right);
		
		
		return 1+Math.max(left, right);
		
	}
	//ԭ���Ѿ����Ĵ��ˣ�Ҳ��һ��accept�����ǲ�����ô̤ʵ
	public  boolean isBalanced4(TreeNode root) {
		if(root==null){
			return true;
		}
		int l=degree3(root.left);
		int r=degree3(root.right);
		if(Math.abs(l-r)>1){
			return false;
		}
		if(!isBalanced3(root.left)||!isBalanced(root.right)){
			return false;
		}
		return true;
	}
	private int degree3(TreeNode root){
		if(root==null){
			return 0;
		}
		int l=degree3(root.left);
		int r=degree3(root.right);
		
		return Math.max(l, r)+1;
	}
	//����Σ�һ��ac
	public  boolean isBalanced5(TreeNode root) {
		if(root==null){
			return true;
		}
		if(Math.abs(getdegree4(root.left)-getdegree4(root.right))>1){
			return false;
		}
		return isBalanced5(root.left)&&isBalanced5(root.right);
	}
	private int getdegree4(TreeNode root){
		int count=0;
		if(root==null){
			return 0;
		}
		int l=getdegree4(root.left);
		int r=getdegree4(root.right);
		return count+1+Math.max(l, r);
	}
}

