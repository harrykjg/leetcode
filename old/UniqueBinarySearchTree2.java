import java.util.ArrayList;


//http://blog.csdn.net/linhuanmars/article/details/24761437
//http://jixiangsanbao.wordpress.com/2014/05/16/unique-binary-search-trees-ii/
public class UniqueBinarySearchTree2 {
	//code ganker�Ĵ���,̫������������ⶼ��
	public static void main(String[] args) {
		UniqueBinarySearchTree2 ub=new UniqueBinarySearchTree2();
		ub.generateTrees(2);
	}
	
	public ArrayList<TreeNode> generateTrees(int n) {  
      ArrayList<TreeNode> al=new ArrayList<TreeNode>();
      if(n==0){
			return al;
		}
	    return helper(1,n);  //���1�����ʼ�ĸ��ڵ��ֵ�����������1��new��
	}  
	private ArrayList<TreeNode> helper(int left, int right)  
	{  
	    ArrayList<TreeNode> res = new ArrayList<TreeNode>();  
	    if(left>right)  
	    {  
	         res.add(null);
	        return res;  
	    }  
	    for(int i=left;i<=right;i++)  
	    {                          
	        ArrayList<TreeNode> leftList = helper(left,i-1);  
	        ArrayList<TreeNode> rightList = helper(i+1,right);  
	        for(int j=0;j<leftList.size();j++)  
	        {  
	            for(int k=0;k<rightList.size();k++)  
	            {  
	                TreeNode root = new TreeNode(i);  
	                root.left = leftList.get(j);  
	                root.right = rightList.get(k);  
	                res.add(root);  
	            }  
	        }  
	    }  
	    return res;  
	
	//�������ǿ��Լ����д�ģ�д�ıȽ�������Ҫ��rootҪ�Ƿ�Ҫnew���ﷸ�˴�������
	public ArrayList<TreeNode> generateTrees2(int n) {
		ArrayList<TreeNode> a=new ArrayList<TreeNode>();
		if(n==0){
			
			return a;
		}
		return gen(1,n);
	}
	private ArrayList<TreeNode> gen(int b,int n){
		int i=b;
		ArrayList<TreeNode> a=new ArrayList<TreeNode>();
		if(n==0||b>n){
			return a;
		}
		while(i<=n){
			TreeNode root=new TreeNode(i);
			ArrayList<TreeNode> l=gen(b,i-1);
			ArrayList<TreeNode> r=gen(i+1,n);
			if(l.size()==0&&r.size()==0){
				root.left=null;
				root.right=null;
				a.add(root);
				
			}
			//�Աȵ�һ�εģ���������������l�ǿ�r���ǿյĻ�����ô����ѭ���ĵ�һ������j<l.size()
			//�Ͳ������ˣ��ͽ�����ѭ���ˣ������ҷֿ��ж�l��r��size�Ƿ�Ϊ0������һ�ε�����д��
			//�������n==0||b>n���Ļ���aҲ����һ��null���ͱ�����������ˣ����˴���
			else if(l.size()==0){
				for(int j=0;j<r.size();j++){
					TreeNode ro=new TreeNode(root.val);//ע������Ҫnewһ��root�����������root
					ro.left=null;//�ȸ����������ҽڵ㣬�ӽ���a�У������²�ѭ�����ְ�root������
					ro.right=r.get(j);//�������µĽڵ㣬��ôԭ��������a�е�root�ͱ���
					a.add(ro);
				}
			}else if(r.size()==0){
				for(int j=0;j<l.size();j++){
					TreeNode ro=new TreeNode(root.val);
					ro.right=null;
					ro.left=l.get(j);
					a.add(ro);
				}
			}else{
				for(int j=0;j<l.size();j++){
					for(int k=0;k<r.size();k++){
						TreeNode ro=new TreeNode(root.val);
						ro.left=l.get(j);
						ro.right=r.get(k);
						a.add(ro);
					}
				}
			}
			i++;
		}
		return a;
	}
//���Ĵ�,���ò���ˣ�Ҫ����֮ǰ�Ĳ�֪��
	public ArrayList<TreeNode> generateTrees3(int n) {
		ArrayList<TreeNode> al=new ArrayList<TreeNode>();
		
		if(n==0){
			return al;
		}
		return gen2(1,n);
		
		
	}
		private ArrayList<TreeNode> gen2(int b,int e){
		ArrayList<TreeNode> a=new ArrayList<TreeNode>();
		if(b>e){
			a.add(null);
			return a;
		}
		int i=b;
		while(i<=e){
		
			ArrayList<TreeNode> left=gen2(b,i-1);
			ArrayList<TreeNode> right=gen2(i+1,e);
			for(int j=0;j<left.size();j++){
				for(int k=0;k<right.size();k++){
					TreeNode ro=new TreeNode(i);
					ro.left=left.get(j);
					ro.right=right.get(k);
					a.add(ro);
				}
			}
			i++;
		}
		return a;
		
	}
	

}
