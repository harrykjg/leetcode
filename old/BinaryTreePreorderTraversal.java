import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
//http://blog.csdn.net/hairongtian/article/details/7930937  这样写比较简便
//https://jixiangsanbao.wordpress.com/2014/04/09/binary-tree-preorder-traversal/
public class BinaryTreePreorderTraversal {
	public ArrayList<Integer> preorderTraversal(TreeNode root) {//其实也是参考网上的
//就是用continue与否，stack还是队列，先检查右还是先左，pop还是peek，这些组合，试一下就可能
		//刚好得到想要的顺序
		
		ArrayList<Integer> al=new ArrayList<Integer>();
		
		if(root==null){
			return al;
		}
		//父类引用不能调用子类中有定义而父类中没定义的方法。
		//父类引用可以调用父类和子类中都有定义的方法，默认是调用子类的（这个就是多态的意义）
		//父类引用也可以调用父类和子类都有定义的方法的父类版本，用super
		LinkedList<TreeNode> ls=new LinkedList<TreeNode>();
		ls.add(root);
		
		while(!ls.isEmpty()){//由于是stack所以是倒着的，所以先检查right
			TreeNode temp=ls.pop();
			al.add(temp.val);
			if(temp.right!=null){
				ls.push(temp.right);
				
			}
			if(temp.left!=null){
				ls.push(temp.left);			
			}
		}
		return al;
	}
	
public ArrayList<Integer> preorderTraversal2(TreeNode root) {//网上的
	//取出来刚好是倒着的，所以最后用for倒着取出来
		
		ArrayList<Integer> list = new ArrayList<Integer>();
		ArrayList<Integer> list2 = new ArrayList<Integer>();
        if (root == null)
        	return list;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        stack.push(root);
        while (stack.size() != 0){
        	TreeNode top = stack.peek();
        	if (top.left == null && top.right == null){
        		list.add(top.val);
        		stack.pop();
        	}
        	if (top.right != null){
        		stack.push(top.right);
        		top.right = null;
        		continue;
        	}
        	if (top.left != null){
        		stack.push(top.left);
        		top.left = null;
        		continue;
        	}
        }
        for (int i=0; i<list.size(); ++i){
        	list2.add(list.get(list.size()-1-i));
        }
        return list2;
    }
//第二次还是不会，要记

//9月29,写不出，看回第一次的，真神奇
   

    //1/10
    public ArrayList<Integer> preorderTraversal3(TreeNode root) {
    	ArrayList<Integer> al=new ArrayList<Integer>();
    	if(root==null){
    		return al;
    	}
    	LinkedList<TreeNode> ls=new LinkedList<TreeNode>();
		ls.add(root);
		while(!ls.isEmpty()){
			TreeNode temp=ls.pop();
			al.add(temp.val);
			if(temp.right!=null){
				ls.push(temp.right);
			}
			if(temp.left!=null){
				ls.push(temp.left);
			}
		}
		return al;
    }
}
