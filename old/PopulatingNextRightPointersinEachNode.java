public class PopulatingNextRightPointersinEachNode {
	public void connect(TreeLinkNode root) {
		
		if(root==null){
			return;
		}
		con(root);

		return;
	}
	public void con(TreeLinkNode root){
		if(root.left!=null){
			root.left.next=root.right;
			if(root.next!=null){
				root.right.next=root.next.left;
			}else{
				root.right.next=null;
				
			}
			connect(root.left);
			connect(root.right);
					
		}
		return;
	}
	

}

class TreeLinkNode {
	int val;
	TreeLinkNode left, right, next;

	TreeLinkNode(int x) {
		val = x;
	}
}