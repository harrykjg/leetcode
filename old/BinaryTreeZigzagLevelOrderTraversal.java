import java.util.LinkedList;
import java.util.List;
//http://blog.csdn.net/linhuanmars/article/details/24509105

public class BinaryTreeZigzagLevelOrderTraversal {
	public static void main(String[] args) {
		BinaryTreeZigzagLevelOrderTraversal bt=new BinaryTreeZigzagLevelOrderTraversal();
		TreeNode root=new TreeNode(1);
		root.left=new TreeNode(2);
		root.right=new TreeNode(3);
		bt.zigzagLevelOrder(root);
		
	}
	
	
	public LinkedList<LinkedList<Integer>> zigzagLevelOrder(TreeNode root) {
		//这个是按我自己想法写的，code ganker用的是栈
		LinkedList<LinkedList<Integer>> rs=new LinkedList<LinkedList<Integer>>();
		if(root==null){
			return rs;
		}
		
		LinkedList<TreeNode> ls=new LinkedList<TreeNode>();
		boolean flag=true;
		int count1=1;
		int count2=0;
		ls.add(root);
		LinkedList<Integer> l=new LinkedList<Integer>();
		//思路就是每次从队列ls中取出元素时，该元素的孩子还是按左右的顺序加进队列里
		//每次要打印该层的元素个数，可用count1来记录当前层有多少个元素，即要打多少个
		//元素，用count2来记录下一层加了多少个元素。
		//如果要从左到右打印，即flag=true时，每次从queue中取出的元素就append进l里就行了
		//如果是从右到左的话即flag=false时，每次从queue中取出的元素就塞到l的最前面（用addFirst方法）
		while(!ls.isEmpty()){
			
			while(count1>0){
				TreeNode temp=ls.poll();
				count1--;
				if(flag){
					l.add(temp.val);
				}
				else{
					l.addFirst(temp.val);
				}
				if(temp.left!=null){
					ls.add(temp.left);
					count2++;
				}
				if(temp.right!=null){
					ls.add(temp.right);
					count2++;
				}
				if(count1==0){
					rs.add(new LinkedList<Integer>(l));
					flag=flag?false:true;
					l.removeAll(l);
				}
			}
			count1=count2;
			count2=0;
			
		}
		return rs;

	}
	//第二次还想歪了，要2个q来存node，其实就第一次的方法就挺好的

}
