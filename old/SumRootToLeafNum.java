import java.util.ArrayList;
//http://blog.csdn.net/linhuanmars/article/details/22913699 
//http://jixiangsanbao.wordpress.com/2014/05/20/sum-root-to-leaf-numbers/

public class SumRootToLeafNum {
//自己的
	public static void main(String[] args) {
		TreeNode tree = new TreeNode(1);
		tree.left= new TreeNode(2);
		tree.right= new TreeNode(3);
//		tree.left.right= new TreeNode(5);
//		tree.right.left= new TreeNode(6);
//		tree.right.right= new TreeNode(9);
		SumRootToLeafNum xx=new SumRootToLeafNum();
		int x=xx.sumNumbers2(tree);
		System.out.println(x);

	}
	
	ArrayList<Integer> al=new ArrayList<Integer>();

	public int sumNumbers(TreeNode root) {
		
		int sum=0;
		if(root==null){
			return sum;
		}
		find(root);
		for(int i=0;i<al.size();i++){
			sum+=al.get(i);
		}
		return sum;

	}
	
	public void find(TreeNode root){
		if(root.left!=null){
			String s=new String(""+root.val+root.left.val);
			int i=Integer.parseInt(s);
			root.left.val=i;
			find(root.left);
		}
		if(root.right!=null){
			String s=new String(""+root.val+root.right.val);
			int i=Integer.parseInt(s);
			root.right.val=i;
			find(root.right);
		}
		
		
		if(root.left==null&&root.right==null){
			al.add(root.val);
		}
		
	}
	//第二次自己写，还是有点磕磕绊绊，把rs作为参数传给dfs居然不行，非得写成成员变量？这是个大问题
	int rs=0;
	public int sumNumbers2(TreeNode root) {
		if(root==null){
			return 0;
		}
		
		dfs(root,0);
		return rs;
	}
	public void dfs(TreeNode root,int cur){
		if(root.left==null&&root.right==null){
			rs+=10*cur+root.val;
			
		}
		if(root.left!=null){
			dfs(root.left,root.val+10*cur);
		}
		if(root.right!=null){
		dfs(root.right,root.val+10*cur);
		}
	}
	//1/10
	 int rss=0;
		public int sumNumbers3(TreeNode root) {
			if(root==null){
				return 0;
			}
			
			
			dfs2(root,0);
			return rss;
		}
		public void dfs2(TreeNode root,int cur){
		    if(root==null){
		        return ;
		    }
			if(root.left==null&&root.right==null){
				rss+=10*cur+root.val;
				
			}
			
				dfs2(root.left,root.val+10*cur);
			

			dfs2(root.right,root.val+10*cur);
			
		}

}
