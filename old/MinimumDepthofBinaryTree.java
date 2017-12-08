import java.util.ArrayList;
import java.util.LinkedList;
//http://blog.csdn.net/linhuanmars/article/details/19660209 ����д�ĺܼ��Ҫѧ����

public class MinimumDepthofBinaryTree {//�Լ�д�İ�
	
	public static void main(String[] args) {
		MinimumDepthofBinaryTree md=new MinimumDepthofBinaryTree();
		TreeNode root=new TreeNode(1);
		root.left=new TreeNode(2);
		root.right=new TreeNode(3);
		root.left.left=new TreeNode(4);
		root.right.right=new TreeNode(5);
		System.out.println(md.minDepth2(root));
	}
	
	
	ArrayList<Integer> al=new ArrayList<Integer>();
    public int minDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        getDepth(root,0);
        return al.get(al.size()-1);
    }
    
    public void getDepth(TreeNode root,int count){
        if(root==null){
            return;
        }else{
            count++;
        }
        
        if(root.left!=null||root.right!=null){
            getDepth(root.left,count);
            getDepth(root.right,count);
        }else{
            if(al.size()==0){
                al.add(count);
            }else if(al.get(al.size()-1)>count){
                al.add(count);
            }
        }
    }
    //�ڶ���д����ʶ����һ�㣬������level order�����Ļ��Ͳ���count1��coung2��¼ÿһ��Ľڵ�����
    //���ø�qһֱ�������������ˣ���������bfs�ɣ�Ҫ��¼������ÿһ��Ű�depth��1������
    //binary tree level order traversal�������ֵģ���Ҫcount1��count2��¼����
    public int minDepth2(TreeNode root) {
    	if(root==null){
    		return 0;
    	}
    	int d=0;
    	LinkedList<TreeNode> q=new LinkedList<TreeNode>();
    	q.add(root);
    	int count1=1;
    	int count2=0;
    	while(!q.isEmpty()){
    		TreeNode temp=q.poll();
    		count1--;
    		
    		if(temp.left==null&&temp.right==null){
    			return d+1;
    		}
    		if(temp.left!=null){
    			q.add(temp.left);
    			count2++;
    		}
    		if(temp.right!=null){
    			count2++;
    			q.add(temp.right);
    		}
    		if(count1==0){
    			d++;
    			count1=count2;
    			count2=0;
    		}
    	}
    	return d;
    }

}
