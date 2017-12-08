import java.util.ArrayList;

public class Pathsum2 {

	public static void main(String[] args) {
		TreeNode node = new TreeNode(5);
		node.left = new TreeNode(4);
		node.right = new TreeNode(8);
		node.left.left = new TreeNode(11);
		node.left.left.left = new TreeNode(7);
		node.left.left.right = new TreeNode(2);
		node.right.left = new TreeNode(13);
		node.right.right = new TreeNode(4);
		node.right.right.right = new TreeNode(1);
		node.right.right.left = new TreeNode(5);

		Pathsum2 xx = new Pathsum2();
		ArrayList<ArrayList<Integer>> aa = new ArrayList<ArrayList<Integer>>();
		aa = xx.pathSum(node, 22);
		for (int i = 0; i < aa.size(); i++) {
			for (int j = 0; j < aa.get(i).size(); j++) {
				System.out.print(aa.get(i).get(j));
			}
			System.out.println();
		}

	}

	ArrayList<ArrayList<Integer>> al = new ArrayList<ArrayList<Integer>>();

	public ArrayList<ArrayList<Integer>> pathSum(TreeNode root, int sum) {
		ArrayList<TreeNode> vis = new ArrayList<TreeNode>();
		ArrayList<Integer> a1 = new ArrayList<Integer>();
		a1.add(root.val);

		dfs(root, a1, sum, vis);

		return al;

	}

	public void dfs(TreeNode n, ArrayList<Integer> a, int sum,
			ArrayList<TreeNode> v) {
      
		TreeNode cur=n;
		
		if (cur == null) {
			return;
		}
		if (cur.val == sum&&!v.contains(n)) {
			ArrayList<Integer> aaa = new ArrayList<Integer>();
			aaa.add(cur.val);
			al.add(aaa);

		}

		// while(n.left!=null&&!v.contains(n.left)){  //��������while�Ļ���tim exceed��
		//�������ǵݹ�ķ������Ͳ�Ӧ������while��
		// ArrayList<Integer> a2=new ArrayList<Integer>();
		// a2.addAll(a);
		// a2.add(n.left.val);
		// v.add(n.left);
		// n.left.val=n.val+n.left.val;
		//
		// if(n.left.left==null&&n.left.right==null&&sum==n.left.val){
		// al.add(a2);
		// break;
		// }
		// dfs(n.left,a2,sum,v);
		// }
		//
		// while(n.right!=null&&!v.contains(n.right)){
		// ArrayList<Integer> a3=new ArrayList<Integer>();
		// a3.addAll(a);
		// a3.add(n.right.val);
		// v.add(n.right);
		// n.right.val=n.val+n.right.val;
		//
		// if(n.right.left==null&&n.right.right==null&&sum==n.right.val){
		// al.add(a3);
		// break;
		// }
		// dfs(n.right,a3,sum,v);
		// }

		v.add(cur);
		ArrayList<Integer> a2 = new ArrayList<Integer>();
		a2.addAll(a);
		if (cur.left != null) {
			a2.add(cur.left.val);
			v.add(cur.left);
			 cur.left.val= cur.val + cur.left.val;//��������Ҫ�ѽڵ��ֵ���˵ģ�code ganker����
			 //�ǲ���ֵ���ǰ�sum��ȥ��ǰnode��ֵȥ�жϵ� 

			if (cur.left.left == null && cur.left.right == null
					&& sum == cur.left.val) {
				al.add(a2);

			}
		}
		dfs(cur.left, a2, sum, v);

		ArrayList<Integer> a3 = new ArrayList<Integer>();
		a3.addAll(a);
		if (cur.right != null) {
			a3.add(cur.right.val);
			v.add(cur.right);
			cur.right.val = cur.val + cur.right.val;

			if (cur.right.left == null && cur.right.right == null
					&& sum == cur.right.val) {
				al.add(a3);
			}
		}
		dfs(cur.right, a3, sum, v);
	}

	public ArrayList<ArrayList<Integer>> pathSum2(TreeNode root, int sum) {
		ArrayList<ArrayList<Integer>> al=new ArrayList<ArrayList<Integer>> ();
		if(root==null){
			return al;
		}
		ArrayList<Integer> a=new ArrayList<Integer>();
		dfs2(root,sum,0,a,al);
		return al;
	}
	//�ڶ���д
	private void dfs2(TreeNode root,int sum,int cur,ArrayList<Integer> a,ArrayList<ArrayList<Integer>> al){
		if(root.left==null&&root.right==null&&cur+root.val==sum){
			a.add(root.val);
			al.add(new ArrayList<Integer> (a));
			a.remove(a.size()-1);//��һ�俪ʼд�ˣ�������ɾ�ˣ�������ˣ���Ҫ��
			return;
		}
		if(root.left!=null){
			a.add(root.val);
			dfs2(root.left,sum,cur+root.val,a,al);
			a.remove(a.size()-1);
		}
		if(root.right!=null){
			a.add(root.val);
			dfs2(root.right,sum,cur+root.val,a,al);
			a.remove(a.size()-1);
		}
	}
}
