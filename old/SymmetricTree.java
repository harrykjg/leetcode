import java.util.ArrayList;
import java.util.LinkedList;
//妈的这题想错了，想的是维护一个queue，每次弹出第一个节点t1和最后一个节点t2，然后对比，然后再把
//t1的左右，t2的左右依次加进queue里，再每次弹出第一个和最后一个节点对比。其实是不行的，比如第3层
//有4个节点，弹出了第一个和最后一个，则queue里还剩2个，这时再把t1和t2的节点从左往右加进queue里，
//queue里的顺序就变成第三场的两个加上第四层的节点了，就不是中间对称的了。

//http://blog.csdn.net/linhuanmars/article/details/23072829  这个也不错
//http://jixiangsanbao.wordpress.com/2014/02/22/symmetric-tree/他们两个都是用了2个queue，但是其实想法是不同的

public class SymmetricTree {
	public static void main(String[] args) {
		SymmetricTree st=new SymmetricTree();
		TreeNode root=new TreeNode(1);
		root.left=new TreeNode(2);
		root.right=new TreeNode(2);
		root.left.left=new TreeNode(4);
		root.left.right=new TreeNode(5);
		root.right.left=new TreeNode(5);
		root.right.right=new TreeNode(4);
//		root.left.left.left=new TreeNode(6);
//		root.left.right.left=new TreeNode(8);
//		root.left.right.right=new TreeNode(9);
//		root.right.left.left=new TreeNode(9);
//		root.right.left.right=new TreeNode(8);
//		root.right.right.right=new TreeNode(6);
		System.out.println(st.isSymmetric4(root));
	}
	
	
	public boolean isSymmetric(TreeNode root) {
		if(root==null){
			return true;
		}
		
	
		LinkedList<TreeNode> q1=new LinkedList<TreeNode>();
		LinkedList<TreeNode> q2=new LinkedList<TreeNode>();
		q1.add(root);
		while(!q1.isEmpty()){
			//吉祥这个方法，就是先再q1中依次把node全都加到q2之中，加完了之后再去检测q2中的值是否对称
				TreeNode temp=q1.pop();
				if(temp!=null){
					q2.add(temp.left);
					q2.add(temp.right);
					
				}
		//当q1已经空了，说明当前这一层的孩子都加到q2中了，就可以开始检测q2中的孩子是否对称了
			if(q1.isEmpty()){//而q2中是没有加入节点到q中的，因为q2拿出来的节点是前后对称的节点
				//如果要加入queue中的话就必然导致queue中不对称了，只能在q1中依次加才对称
				int i=0;
				int j=q2.size()-1;
				while(i<=j){
					TreeNode t1=q2.get(i);
					TreeNode t2=q2.get(j);
					
					if((t1!=null&&t2==null)||t1==null&&t2!=null){
						return false;
					}
					if(t1==null&&t2==null){
						i++;
						j--;
						continue;
					}
					if(t1.val!=t2.val){
						return false;
					}
					
//					if(t1.left!=null&&t2.right==null){//这一系列其实不用，因为在q1加节点时
					                                  //是每个位置都加了的（就算是空）所以
					                                  //后面取出来肯定是对称的取,自然可以判断
//						return false;
//					}
//					if(t1.left==null&&t2.right!=null){
//						return false;
//					}
//					if(t1.right!=null&&t2.left==null){
//						return false;
//					}
//					if(t1.right==null&&t2.left!=null){
//						return false;
//					}
					i++;
					j--;
				}
				q1=new LinkedList<TreeNode>(q2);
				q2.clear();
			}
			
		}
		return true;

	}
	//我操我以为检测inorder的出来的顺序是否回文可以的，结果是不行的，如1,2,3,3,#,2,#
	public boolean isSymmetric2(TreeNode root){
		if(root==null){
			return true;
		}
		ArrayList<Integer> al=new ArrayList<Integer>();
		
		LinkedList<TreeNode> q=new LinkedList<TreeNode>();
		q.add(root);
		while(!q.isEmpty()){
			TreeNode temp=q.peek();
			
			if(temp.left!=null){
				q.push(temp.left);
				temp.left=null;
				continue;
			}else{
				al.add(temp.val);
				q.pop();
			}
			if(temp.right!=null){
				q.add(temp.right);
				temp.right=null;	
			}
			
		}
		int b=0;
		int e=al.size()-1;
		while(b<e){
			if(al.get(b)!=al.get(e)){
				return false;
			}
			b++;
			e--;
		}
		return true;
				
	}
	//我第三次想的其实就是code ganker的想法.然后又想了一种有点变化的,妈的结果是错的，想法是
	//q1存左边的，节点是从左到右加进去的，q2存右边的，节点也是从左到右加进去的，关键是从q中拿节点
	//的时候，q1是poll，q2是polllast，1,2,2,#,3,#,3这个例子就不行了
	//这个代码是错的！
	public boolean isSymmetric3(TreeNode root){
		if(root==null){
			return true;
		}
		if(root.left==null&&root.right==null){
			return true;
		}
		if((root.left==null&&root.right!=null)||(root.right==null&&root.left!=null)){
			return false;
		}
		ArrayList<Integer> al=new ArrayList<Integer>();
		
		LinkedList<TreeNode> q1=new LinkedList<TreeNode>();
		LinkedList<TreeNode> q2=new LinkedList<TreeNode>();
		q1.add(root.left);
		q2.add(root.right);
		
		while(!q1.isEmpty()&&!q2.isEmpty()){
			TreeNode n1=q1.poll();
			TreeNode n2=q2.pollLast();
			if((n1==null&&n2!=null)||(n2==null&&n1!=null)){
				return false;
			}
			
			if(n1.val!=n2.val){
				return false; 
			}
			if(n1.left!=null){
				q1.add(n1.left);
			}
			if(n2.left!=null){
				q2.add(n2.left);
			}
			if(n1.right!=null){
				q1.add(n1.right);
			}
			if(n2.right!=null){
				q2.add(n2.right);
			}
		}
	
		return true;
				
	}
	//1/10,我想结合吉祥和code ganker的，还是不行我操
	public boolean isSymmetric4(TreeNode root){
		if(root==null){
			return true;
		}
		LinkedList<TreeNode> q1=new LinkedList<TreeNode>();
		LinkedList<TreeNode> q2=new LinkedList<TreeNode>();
		q1.add(root.left);
		
		q2.add(root.right);
		
		int count1=1;
		int count2=0;
		while(!q1.isEmpty()&&!q2.isEmpty()){
			while(count1>0){
				TreeNode temp1=q1.poll();
				TreeNode temp2=q2.removeLast();
				if(temp1==null&&temp2!=null||(temp1!=null&&temp2==null)){
					return false;
				}
				if(temp1!=null&&temp1.val!=temp2.val){
					return false;
				}
				if(temp1!=null){
					q1.add(temp1.left);
					q1.add(temp1.right);
					count2+=2;
				}
				if(temp2!=null){
					q2.add(temp2.left);
					q2.add(temp2.right);
				}
				count1--;
			}
			count1=count2;
			count2=0;

			
		}
		return true;
	}

}
