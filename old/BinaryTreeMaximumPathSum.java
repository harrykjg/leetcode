import java.util.ArrayList;

public class BinaryTreeMaximumPathSum {
//http://jixiangsanbao.wordpress.com/2014/07/19/binary-tree-maximum-path-sum/
//http://blog.csdn.net/linhuanmars/article/details/22969069
	public static void main(String[] args) {
		BinaryTreeMaximumPathSum bt=new BinaryTreeMaximumPathSum();
		TreeNode root=new TreeNode(-3);
		root.left=new TreeNode(2);
		root.right=new TreeNode(3);
//		root.left.right=new TreeNode(3);
//		root.left.left=new TreeNode(1);
//		root.left.left.left=new TreeNode(-1);
//		root.left.left.right=new TreeNode(2);
//		root.right.right=new TreeNode(4);
//		root.right.left=new TreeNode(-2);
//		root.right.right.right=new TreeNode(1);
		System.out.println(bt.maxPathSum(root));
	}
	
	//˼·�е��ҿ��ܣ����޸ĸĲ��е�
	int max=Integer.MIN_VALUE;
	public int maxPathSum(TreeNode root) {
		if(root==null){
			return 0;
		}
		find(root);
		return max;
	}
	public int find(TreeNode root){
		if(root==null){
			return 0;
		}
		int left=Math.max(0, find(root.left));//֮ǰ����û�а�find��root.left����0�ȣ�С��0�ľ�
		int right=Math.max(0, find(root.right));//ֱ������
		if(left==0&&right==0){//������Ҷ��ǿջ��߶�С��0����ô���ֵ���ܾ���root�Լ���
			max= Math.max(root.val,max);//���case��һ��-3��û���ⲿ�Ļ��ʹ���
			
		}else{//����Ƚ����ң������ң����У��У�max��6��ֵ�е����ģ�ע�⣬���Ҳ���
			//��ʵ���ﲻ��д��ô��ģ�������ʵ����һ���if(left==0&&right==0)����͵����жϵ��ˣ�
			//���У��ڵ�ǰ����if(left==0&&right==0)Ҳ���жϵ��ˣ���ʵ���ж�һ���������Ƿ����
			//max�����ˣ���Ϊ����������С��0�Ļ��������0��
		max=Math.max(left, Math.max(right, Math.max(left+right+root.val, Math.max(root.val, 
				Math.max(left+root.val, Math.max(max, right+root.val))))));
		}
		//�Ƚ�max�ǱȽ�max�����Ƿ��صĻ�ֻ�������л������л����У�����Ҫ��ͼ��һ�²����ף���
		return Math.max( right+root.val, Math.max(left+root.val, root.val));//һ�£������е��ƣ�
		// ��         1
		//    2    3
		//  1   2     -4
		//�����ܵõ������sum��2,2,1,3=8��������1,2,2,1,3����Ϊ���߲��ܳ�Ϊһ��path(����1-2-2֮��
		//�͵������������ǲ��1�ˣ�����˵�ڶ����2�ܷ��ص����ֵ�������л������л����У������ֵ��
		//�����ǿ��԰������Ҽ�һ��ģ���Ϊ������path
	}
//�ڶ��λ��ǲ��ᣬ�Ϳ���һ�εİ�
	public int maxPathSum2(TreeNode root) {  //code ganker��
	    if(root==null)  
	        return 0;  
	    ArrayList<Integer> res = new ArrayList<Integer>();  
	    res.add(Integer.MIN_VALUE);  
	    helper(root,res);  
	    return res.get(0);  
	}  
	private int helper(TreeNode root, ArrayList<Integer> res)  
	{  
	    if(root == null)  
	        return 0;  
	    int left = helper(root.left, res);  
	    int right = helper(root.right, res);  
	    int cur = root.val + (left>0?left:0)+(right>0?right:0);  
	    if(cur>res.get(0))  
	        res.set(0,cur);  
	    return root.val+Math.max(left, Math.max(right,0));  
	}  

	//��3��,˼·�Ǽǵõģ�����д���������ֱ���дһ��helper����������û������2��ֵ.����һ�ι�
	int rs=Integer.MIN_VALUE;
	public int maxPathSum3(TreeNode root) {
		find2(root);
		return rs;
	}
	
	
	public int find2(TreeNode root) {
		if(root==null){
			return 0;
		}
		int l=Math.max(find2(root.left), 0);
		int r=Math.max(find2(root.right), 0);
		int temp=Math.max(root.val, Math.max(root.val+l, root.val+r));
		rs=Math.max(root.val+l+r,rs );
		return temp;
	}
	
}
