import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
//http://blog.csdn.net/hairongtian/article/details/7930937  ����д�Ƚϼ��
//https://jixiangsanbao.wordpress.com/2014/04/09/binary-tree-preorder-traversal/
public class BinaryTreePreorderTraversal {
	public ArrayList<Integer> preorderTraversal(TreeNode root) {//��ʵҲ�ǲο����ϵ�
//������continue���stack���Ƕ��У��ȼ���һ�������pop����peek����Щ��ϣ���һ�¾Ϳ���
		//�պõõ���Ҫ��˳��
		
		ArrayList<Integer> al=new ArrayList<Integer>();
		
		if(root==null){
			return al;
		}
		//�������ò��ܵ����������ж����������û����ķ�����
		//�������ÿ��Ե��ø���������ж��ж���ķ�����Ĭ���ǵ�������ģ�������Ƕ�̬�����壩
		//��������Ҳ���Ե��ø�������඼�ж���ķ����ĸ���汾����super
		LinkedList<TreeNode> ls=new LinkedList<TreeNode>();
		ls.add(root);
		
		while(!ls.isEmpty()){//������stack�����ǵ��ŵģ������ȼ��right
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
	
public ArrayList<Integer> preorderTraversal2(TreeNode root) {//���ϵ�
	//ȡ�����պ��ǵ��ŵģ����������for����ȡ����
		
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
//�ڶ��λ��ǲ��ᣬҪ��

//9��29,д���������ص�һ�εģ�������
   

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
