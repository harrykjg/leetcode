//http://blog.csdn.net/linhuanmars/article/details/24389549
//http://jixiangsanbao.wordpress.com/2014/05/28/construct-binary-tree-from-preorder-and-inorder-traversal-3/

public class ConstructBinaryTreefromInorderandPostorderTraversal {
	public static void main(String[] args) {

	}
//解析见草稿本
	public TreeNode buildTree(int[] inorder, int[] postorder) {
		if(inorder.length==0){
			return null;
		}
		TreeNode root=build(inorder,0,inorder.length-1,postorder,0,postorder.length-1);
		return root;

	}
	
	public TreeNode build(int[] in,int b1,int e1,int[] po,int b2,int e2){
		if(b1-e1>0||b2-e2>0){//用postorder的数组的来确定节点，inorder是拿来确定post数组的范围而已
			//因为有了post的范围才能确定这个范围内post的最后一个节点是什么
			return null;
		}
		TreeNode n=new TreeNode(po[e2]);
		int index=0;
		for(int i=0;i<in.length;i++){
			if(in[i]==po[e2]){
				index=i;
				break;
			}
		}
		
		n.left=build(in,b1,index-1,po,b2,b2+(index-b1)-1);
		n.right=build(in,index+1,e1,po,e2-(e1-index),e2-1);
		return n;
		
	}
	
}
