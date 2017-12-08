import java.util.ArrayList;
import java.util.LinkedList;
//http://blog.csdn.net/linhuanmars/article/details/19660209 别人写的很简便要学这种

public class MinimumDepthofBinaryTree {//自己写的吧
	
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
    //第二次写，意识到了一点，单纯的level order遍历的话就不用count1和coung2记录每一层的节点数，
    //就用个q一直进进出出就行了，这里算是bfs吧，要记录层数，每一层才把depth加1，或者
    //binary tree level order traversal那题那种的，就要count1和count2记录层数
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
