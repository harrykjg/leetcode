import java.util.ArrayList;
import java.util.Arrays;
//http://jixiangsanbao.wordpress.com/2014/05/28/flatten-binary-tree-to-linked-list/
//http://blog.csdn.net/beiyetengqing/article/details/8533596 这个用非递归的，屌
public class FlattenedBinaryTree {

	
	public static void main(String[] args) {

		
		TreeNode n=new TreeNode(1);
		n.left=new TreeNode(2);
		n.right=new TreeNode(5);
		n.left.left=new TreeNode(3);
		n.left.right=new TreeNode(4);
		n.right.right=new TreeNode(6);
		n.right.left=new TreeNode(10);
		n.left.right.right=new TreeNode(8);
		n.left.right.left=new TreeNode(9);
		FlattenedBinaryTree f=new FlattenedBinaryTree();
		f.flatten4(n);
		
		while(n!=null){
			System.out.println((n.val));
			n=n.right;
			
		}
		
		
		
	}

	ArrayList<Integer> al= new ArrayList<Integer>();
	
	public void flatten(TreeNode root) {

		
		if(root==null){
			return;
		}
		
		flat2(root);
		Integer[] a=new Integer[al.size()];
		a=(Integer[])al.toArray(new Integer[0]);//注意这里arraylist转成普通数组，一定要是
		//Integer数组才行，int不行
		Arrays.sort(a);
		
		root.val=a[0];
		root.left=null;
		for(int i=1;i<al.size();i++){
			root.right=new TreeNode(a[i]);
			root=root.right;
		}
		
	}
	public void flat2(TreeNode root){
		if(root==null){
			return;
		}
		
		al.add(root.val);
		flat2(root.left);
		flat2(root.right);
		
	}
	//第二次还是不怎么写得出来，背着就好了。他这个思想就是把每个节点都flatten
	public void flatten2(TreeNode root) {
		if(root==null){
			return ;
		}
		flatten2(root.left);//走的路线很奇怪，比如         1
		flatten2(root.right);  //               2       5
		if(root.left==null){//               3     4  10  6
			return;  //                           9 8   
		}//                         这个例子，先把左边的2,3,4,8都走完了又倒回2那去
		else{
			TreeNode temp=root.right;
			root.right=root.left;
			root.left=null;
			while(root.right!=null){
				root=root.right;
			}
			root.right=temp;
			
		}
		
		
	}
	//第三轮还是不行。
	public void flatten3(TreeNode root) {
		if(root==null){
			return;
		}
		flatten3(root.left);
		flatten3(root.right);
		if(root.left==null){
			return;
		}
		else{
			TreeNode temp=root.right;
			root.right=root.left;
			root.left=null;
			while(root.right!=null){
				root=root.right;
			}
			root.right=temp;
		}
	}
	//第四次,居然调试了一次就自己写对了，思路就是先看左节点，其实左节点是否是空都无所谓，先faltten
	//root.left，那么到了最后，left肯定是null，那么就返回，然后就把右节点换成左节点，开始想的时候
	//觉得是空就不用换了，想着，如果左空右不空，那么把右设成左，即把右设成空了，那不就错了吗？
	//其实不是，因为后面还有个root.right=temp！因为root设成了空，所以那个while循环不进入，那么
	//再执行root=root.right就刚刚好没影响！那就没问题了，root节点的左边flatten之后，再flatten
	//现在root的right，再返回上一层。刚刚好。说白了就是把不断把左节点差到右节点和root节点之间
	public void flatten4(TreeNode root) {
		if(root==null){
			return;
		}
//		if(root.left==null){//开始是写了这个的，结果错了，1#23这个例子会错，删了居然就accept了
//			return;
//		}
		flatten4(root.left);
		TreeNode temp=root.right;
		root.right=root.left;
		root.left=null;
		while(root.right!=null){
			root=root.right;
		}
		root.right=temp;
		flatten4(root.right);
	}
//第四次的时候想到了，这样写也行，就是改了一点,应该思路更顺一点，就是左节点不是空才是换，是空的话
	//就去flatten右边
	public void flatten5(TreeNode root) {
		if(root==null){
			return;
		}
		if(root.left!=null){
			flatten5(root.left);
			TreeNode temp=root.right;
			root.right=root.left;
			root.left=null;
			while(root.right!=null){
				root=root.right;
			}
			root.right=temp;
		}
		
		
		flatten5(root.right);
	}
}
