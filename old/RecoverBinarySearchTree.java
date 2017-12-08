import java.util.ArrayList;
import java.util.Collections;
//http://blog.csdn.net/linhuanmars/article/details/24566995 ��˵����ȫ�ֱ�����ԭ��
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
//		n.right.right.right=new TreeNode(40);//��������Ӻ���û�������������ǵ��㷨��
//		��������Ϊ���������3���ڵ��ֵ������
		RecoverBinarySearchTree rb=new RecoverBinarySearchTree();
		rb.recoverTree2(n);
		
	}
    TreeNode pre=null; //pre����inorder������ǰһ���ڵ㣬�����жϵ�ǰ�ڵ�����Ĵ�С��ϵ
	TreeNode first=null;
	TreeNode second=null;
	public void recoverTree(TreeNode root) {
		
		
		inorder(root);
		if(first!=null&&second!=null){//�������������½���first��second��ֵ��һ�£��ͻ�
			//Ӱ�쵽ԭ����
			int temp=first.val;
			first.val=second.val;
			second.val=temp;
		}

		
		
		System.out.println(1);
		

	}
	public void inorder(TreeNode cur){//���ǲ��ܼ���һ��pre��Ϊ�������,������һ����������
		//������ʱ����Ȼ��pre�����ˣ�����������inorder��cur.right��pre��֮�󣬻ص���һ��
		//��ʱ��pre����null�ˣ���preһ����Ҫһ����Ա�������С�
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
			
				second=cur;//����������ڵ������Ļ�����������ڶ����ҵ�һ��pre.val>cur.val
				//���������second��ֱ�Ӹ����ˣ���������ڵ��Ƿֿ��ģ����ڶ��ν������if��
				//second��������ȷ�ĸ���
				
			}else{
				second=cur;
				return;
			}

		}
		
		pre=cur;
		inorder(cur.right);
		
	}
//���ϵķ���������O��n���ռ䣬����������ֵ��node�ֱ����2��arraylist����value������������
//���丽��ÿ��node��val��
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
//	          Collections.sort(values);//ע��������������arraylist����
//	          int index = 0;
//	          for(TreeNode n : nodes){
//	              n.val = values.get(index++);
//	          }
//	          
//	      }
	//code ganker�ģ��ȼ���Ŀ������ÿ�Щ
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
	        if(res.size()==0)  //���res����������������Ӧ�ý����ĵ��
	        {  
	            res.add(pre.get(0));  //��������˵�һ���������res��1,2��λ����Ϊ����������
	            res.add(root);  //������ֻ����һ������Ļ�������˵�����ڵ�����ֱ�ӽ�������
	        }  //��������˵ڶ����������res�ĵڶ���λ����Ϊ�Ǹ�ֵ���У������ǹ۲췢�ֵڶ���
	        else  //����Ļ�Ӧ�ý������Ƕ�Ӧ��root�����԰�root�ӵ�res�ĵڶ���λ��
	        {  
	            res.set(1,root);  
	        }  
	    }  
	    pre.set(0,root);  
	    helper(root.right,pre,res);  
	}  

	//�����֣����ǲ��ᡣ�������������ͦ����ģ�����     10            ��code ganker��
                                       //     30      20
     //                                     3     8  15   5 ������� �����õ�pre��¼����
//�ĵ�ǰ�ڵ�����������ǰ����Ȼ��ÿ�αȽ����ǰ���͵�ǰ�ڵ��ֵ��debug��һ��Ҫ

     //9/29,���Լ�д������
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
