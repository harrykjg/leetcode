public class ConstructBinaryTreefromPreorderandInorderTraversal {
	public static void main(String[] args) {
		
		ConstructBinaryTreefromPreorderandInorderTraversal cb=new ConstructBinaryTreefromPreorderandInorderTraversal();
		int[] pre={1};
		int[] in={1};
		TreeNode t=cb.buildTree(pre, in);
		System.out.println(t.val);
		
	}
	
	
	public TreeNode buildTree(int[] preorder, int[] inorder) {

		if(preorder.length==0){
			return null;
		}
		TreeNode root=build(preorder,0,preorder.length-1,inorder,0,inorder.length-1);
		return root;
		
	}
	
	public TreeNode build(int[] pre,int b1,int e1, int[] in, int b2,int e2){
		if(b1-e1>0||b2-e2>0||b1>pre.length-1){
			return null;
		}
		TreeNode n=new TreeNode(pre[b1]);
		int i=0;//找到pre中确定的根在in中的位置
		for( int j=0;j<=e2;j++){
			if(in[j]==pre[b1]){
				i=j;
				break;
			}
		}
		n.left=build(pre,b1+1,b1+(i-b2),in,b2,i-1);
		n.right=build(pre,b1+(i-b2)+1,b1+(i-b2)+1+(e2-i)-1,in,i+1,e2);
		return n;
	}

}
