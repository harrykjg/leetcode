import java.util.ArrayList;
import java.util.Collections;
//http://blog.csdn.net/linhuanmars/article/details/24566995 他说了用全局变量的原因
//http://jixiangsanbao.wordpress.com/2014/07/01/recover-binary-search-tree/
//http://blog.csdn.net/worldwindjp/article/details/21694179
//http://blog.163.com/ya_mzy/blog/static/19959325520137153340725/
public class RecoverBinarySearchTree {
	public static void main(String[] args) {

		TreeNode n=new TreeNode(10);
		n.left=new TreeNode(30);
		
		n.left.left=new TreeNode(3);
		n.left.right=new TreeNode(8);
	
		n.right=new TreeNode(20);
		n.right.left=new TreeNode(15);
		n.right.right=new TreeNode(5);
//		n.right.right.right=new TreeNode(40);//我这个例子好像没法成立啊用他们的算法都
//		可能是因为我这个算是3个节点的值都错了
		RecoverBinarySearchTree rb=new RecoverBinarySearchTree();
		rb.recoverTree2(n);
		
	}
    TreeNode pre=null; //pre代表inorder遍历的前一个节点，用来判断当前节点和他的大小关系
	TreeNode first=null;
	TreeNode second=null;
	public void recoverTree(TreeNode root) {
		
		
		inorder(root);
		if(first!=null&&second!=null){//就这样这两个新建的first和second的值换一下，就会
			//影响到原树了
			int temp=first.val;
			first.val=second.val;
			second.val=temp;
		}

		
		
		System.out.println(1);
		

	}
	public void inorder(TreeNode cur){//还是不能加上一个pre作为传入参数,那样第一次下来最左
		//子树的时候，虽然把pre附上了，但是运行完inorder（cur.right，pre）之后，回到上一层
		//此时的pre又是null了，即pre一定是要一个成员变量才行。
		if(cur==null){
			return;
		}
		inorder(cur.left);
		if(pre==null){
			pre=cur;
		}
		if(pre.val>cur.val){
			if(first==null){
				first=pre;
			
				second=cur;//如果是两个节点连续的话，（即不会第二次找到一个pre.val>cur.val
				//的情况），second就直接附上了，如果两个节点是分开的，则会第二次进入这个if，
				//second照样会正确的更新
				
			}else{
				second=cur;
				return;
			}

		}
		
		pre=cur;
		inorder(cur.right);
		
	}
//网上的方法，用了O（n）空间，就是所有数值和node分别放入2个arraylist，把value的数组排序，再
//把其附上每个node的val。
//	 ArrayList<TreeNode> nodes;
//	      ArrayList<Integer> values;
//	      public void inOrder(TreeNode root){
//	          if(root == null) return;
//	          
//	          inOrder(root.left);
//	          
//	         values.add(root.val);
//	          nodes.add(root);
//	          
//	          inOrder(root.right);
//	      }
//	      public void recoverTree(TreeNode root) {
//	          // Start typing your Java solution below
//	          // DO NOT write main() function
//	          nodes = new ArrayList<TreeNode>();
//	          values = new ArrayList<Integer>();
//	          inOrder(root);
//	          Collections.sort(values);//注意可以用这个来对arraylist排序
//	          int index = 0;
//	          for(TreeNode n : nodes){
//	              n.val = values.get(index++);
//	          }
//	          
//	      }
	//code ganker的，比吉祥的看起来好看些
	public void recoverTree2(TreeNode root) {  
	    if(root == null)  
	        return;  
	    ArrayList<TreeNode> pre = new ArrayList<TreeNode>();  
	    pre.add(null);  
	    ArrayList<TreeNode> res = new ArrayList<TreeNode>();  
	    helper(root,pre, res);  
	    if(res.size()>0)  
	    {  
	        int temp = res.get(0).val;  
	        res.get(0).val = res.get(1).val;  
	        res.get(1).val = temp;  
	    }  
	}  
	
	private void helper(TreeNode root, ArrayList<TreeNode> pre, ArrayList<TreeNode> res)  
	{  
	    if(root == null)  
	    {  
	        return;  
	    }  
	    helper(root.left, pre, res);  
	    if(pre.get(0)!=null && pre.get(0).val>root.val)  
	    {  
	        if(res.size()==0)  //这个res就是用来存那两个应该交换的点的
	        {  
	            res.add(pre.get(0));  //如果出现了第一个逆序，则把res的1,2个位置设为这两个逆序
	            res.add(root);  //如果最后只出现一次逆序的话，就是说是相邻点逆序，直接交换就行
	        }  //如果出现了第二次逆序，则把res的第二个位置设为那个值就行，经他们观察发现第二次
	        else  //逆序的话应该交换的是对应的root，所以把root加到res的第二个位置
	        {  
	            res.set(1,root);  
	        }  
	    }  
	    pre.set(0,root);  
	    helper(root.right,pre,res);  
	}  

	//第三轮，还是不会。他这个遍历还是挺神奇的，比如     10            看code ganker的
                                       //     30      20
     //                                     3     8  15   5 这个例子 ，他用的pre记录的是
//的当前节点的中序遍历的前驱，然后每次比较这个前驱和当前节点的值，debug走一遍要

     //9/29,靠自己写出来了
      TreeNode pre2;
      TreeNode first2;
      TreeNode second2;
      
      public void recoverTree4(TreeNode root) { 
    	
    	  help(root);
    	  if(first!=null&&second!=null){
    		  int temp=first2.val;
    	      first2.val=second2.val;
    	      second2.val=temp;
    	  }
    	  
    	  
    	
    	 
      }
      private void help(TreeNode root){
    	  if(root==null){
    		  return;
    	  }
    	  recoverTree4(root.left);
    	  if(pre2!=null){
    		  if(pre2.val>root.val){
    			  if(first2==null){
    				  first2=pre2;
    				  second2=root;
    			  }else{
    				  second2=root;
    			  }
    		  }
    	  }
    	  pre2=root;
    	  recoverTree4(root.right);
      }
  }
