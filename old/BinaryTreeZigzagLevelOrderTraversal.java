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
		//����ǰ����Լ��뷨д�ģ�code ganker�õ���ջ
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
		//˼·����ÿ�δӶ���ls��ȡ��Ԫ��ʱ����Ԫ�صĺ��ӻ��ǰ����ҵ�˳��ӽ�������
		//ÿ��Ҫ��ӡ�ò��Ԫ�ظ���������count1����¼��ǰ���ж��ٸ�Ԫ�أ���Ҫ����ٸ�
		//Ԫ�أ���count2����¼��һ����˶��ٸ�Ԫ�ء�
		//���Ҫ�����Ҵ�ӡ����flag=trueʱ��ÿ�δ�queue��ȡ����Ԫ�ؾ�append��l�������
		//����Ǵ��ҵ���Ļ���flag=falseʱ��ÿ�δ�queue��ȡ����Ԫ�ؾ�����l����ǰ�棨��addFirst������
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
	//�ڶ��λ������ˣ�Ҫ2��q����node����ʵ�͵�һ�εķ�����ͦ�õ�

}
