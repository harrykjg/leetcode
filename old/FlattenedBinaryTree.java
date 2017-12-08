import java.util.ArrayList;
import java.util.Arrays;
//http://jixiangsanbao.wordpress.com/2014/05/28/flatten-binary-tree-to-linked-list/
//http://blog.csdn.net/beiyetengqing/article/details/8533596 ����÷ǵݹ�ģ���
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
		a=(Integer[])al.toArray(new Integer[0]);//ע������arraylistת����ͨ���飬һ��Ҫ��
		//Integer������У�int����
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
	//�ڶ��λ��ǲ���ôд�ó��������žͺ��ˡ������˼����ǰ�ÿ���ڵ㶼flatten
	public void flatten2(TreeNode root) {
		if(root==null){
			return ;
		}
		flatten2(root.left);//�ߵ�·�ߺ���֣�����         1
		flatten2(root.right);  //               2       5
		if(root.left==null){//               3     4  10  6
			return;  //                           9 8   
		}//                         ������ӣ��Ȱ���ߵ�2,3,4,8���������ֵ���2��ȥ
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
	//�����ֻ��ǲ��С�
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
	//���Ĵ�,��Ȼ������һ�ξ��Լ�д���ˣ�˼·�����ȿ���ڵ㣬��ʵ��ڵ��Ƿ��ǿն�����ν����faltten
	//root.left����ô�������left�϶���null����ô�ͷ��أ�Ȼ��Ͱ��ҽڵ㻻����ڵ㣬��ʼ���ʱ��
	//�����ǿվͲ��û��ˣ����ţ��������Ҳ��գ���ô��������󣬼�������ɿ��ˣ��ǲ��ʹ�����
	//��ʵ���ǣ���Ϊ���滹�и�root.right=temp����Ϊroot����˿գ������Ǹ�whileѭ�������룬��ô
	//��ִ��root=root.right�͸ոպ�ûӰ�죡�Ǿ�û�����ˣ�root�ڵ�����flatten֮����flatten
	//����root��right���ٷ�����һ�㡣�ոպá�˵���˾��ǰѲ��ϰ���ڵ��ҽڵ��root�ڵ�֮��
	public void flatten4(TreeNode root) {
		if(root==null){
			return;
		}
//		if(root.left==null){//��ʼ��д������ģ�������ˣ�1#23������ӻ��ɾ�˾�Ȼ��accept��
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
//���Ĵε�ʱ���뵽�ˣ�����дҲ�У����Ǹ���һ��,Ӧ��˼·��˳һ�㣬������ڵ㲻�ǿղ��ǻ����ǿյĻ�
	//��ȥflatten�ұ�
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
