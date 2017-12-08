import java.util.ArrayList;

public class BinaryTreeMaximumPathSum {
//http://jixiangsanbao.wordpress.com/2014/07/19/binary-tree-maximum-path-sum/
//http://blog.csdn.net/linhuanmars/article/details/22969069
	public static void main(String[] args) {
		BinaryTreeMaximumPathSum bt=new BinaryTreeMaximumPathSum();
		TreeNode root=new TreeNode(-3);
		root.left=new TreeNode(2);
		root.right=new TreeNode(3);
//		root.left.right=new TreeNode(3);
//		root.left.left=new TreeNode(1);
//		root.left.left.left=new TreeNode(-1);
//		root.left.left.right=new TreeNode(2);
//		root.right.right=new TreeNode(4);
//		root.right.left=new TreeNode(-2);
//		root.right.right.right=new TreeNode(1);
		System.out.println(bt.maxPathSum(root));
	}
	
	//思路有点乱可能，修修改改才行的
	int max=Integer.MIN_VALUE;
	public int maxPathSum(TreeNode root) {
		if(root==null){
			return 0;
		}
		find(root);
		return max;
	}
	public int find(TreeNode root){
		if(root==null){
			return 0;
		}
		int left=Math.max(0, find(root.left));//之前这里没有把find（root.left）和0比，小于0的就
		int right=Math.max(0, find(root.right));//直接免了
		if(left==0&&right==0){//如果左右都是空或者都小于0，那么最大值可能就是root自己了
			max= Math.max(root.val,max);//如果case是一个-3，没有这部的话就错了
			
		}else{//否则比较左，右，左中右，左中，中，max这6个值中的最大的，注意，左右不行
			//其实这里不用写这么多的，左，右其实在上一层的if(left==0&&right==0)这里就单独判断的了，
			//而中，在当前这层的if(left==0&&right==0)也是判断的了，其实就判断一个左中右是否大于
			//max就行了，因为如果左或者右小于0的话，都变成0了
		max=Math.max(left, Math.max(right, Math.max(left+right+root.val, Math.max(root.val, 
				Math.max(left+root.val, Math.max(max, right+root.val))))));
		}
		//比较max是比较max，但是返回的话只能是左中或者右中或者中，这里要画图看一下才明白，想
		return Math.max( right+root.val, Math.max(left+root.val, root.val));//一下，这里有点绕，
		// 如         1
		//    2    3
		//  1   2     -4
		//我们能得到的最大sum是2,2,1,3=8，而不是1,2,2,1,3，因为后者不能成为一条path(走了1-2-2之后
		//就到不了最上面那层的1了，所以说第二层的2能返回的最大值就是左中或者右中或者中，而最大值的
		//更新是可以把左中右加一起的，因为能连成path
	}
//第二次还是不会，就看第一次的吧
	public int maxPathSum2(TreeNode root) {  //code ganker的
	    if(root==null)  
	        return 0;  
	    ArrayList<Integer> res = new ArrayList<Integer>();  
	    res.add(Integer.MIN_VALUE);  
	    helper(root,res);  
	    return res.get(0);  
	}  
	private int helper(TreeNode root, ArrayList<Integer> res)  
	{  
	    if(root == null)  
	        return 0;  
	    int left = helper(root.left, res);  
	    int right = helper(root.right, res);  
	    int cur = root.val + (left>0?left:0)+(right>0?right:0);  
	    if(cur>res.get(0))  
	        res.set(0,cur);  
	    return root.val+Math.max(left, Math.max(right,0));  
	}  

	//第3轮,思路是记得的，就是写到后来发现必须写一个helper方法，否则没法返回2个值.基本一次过
	int rs=Integer.MIN_VALUE;
	public int maxPathSum3(TreeNode root) {
		find2(root);
		return rs;
	}
	
	
	public int find2(TreeNode root) {
		if(root==null){
			return 0;
		}
		int l=Math.max(find2(root.left), 0);
		int r=Math.max(find2(root.right), 0);
		int temp=Math.max(root.val, Math.max(root.val+l, root.val+r));
		rs=Math.max(root.val+l+r,rs );
		return temp;
	}
	
}
