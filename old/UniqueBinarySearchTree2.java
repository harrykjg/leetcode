import java.util.ArrayList;


//http://blog.csdn.net/linhuanmars/article/details/24761437
//http://jixiangsanbao.wordpress.com/2014/05/16/unique-binary-search-trees-ii/
public class UniqueBinarySearchTree2 {
	//code ganker的代码,太他妈的神奇了这都行
	public static void main(String[] args) {
		UniqueBinarySearchTree2 ub=new UniqueBinarySearchTree2();
		ub.generateTrees(2);
	}
	
	public ArrayList<TreeNode> generateTrees(int n) {  
      ArrayList<TreeNode> al=new ArrayList<TreeNode>();
      if(n==0){
			return al;
		}
	    return helper(1,n);  //这个1就是最开始的根节点的值，就是用这个1来new的
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
	
	//第三次是靠自己理解写的，写的比较慢，主要是root要是否要new这里犯了错，见下面
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
			//对比第一次的，我这里想的是如果l是空r不是空的话，那么两层循环的第一层条件j<l.size()
			//就不成立了，就进不了循环了，所以我分开判断l和r的size是否为0，而第一次的那种写法
			//是如果（n==0||b>n）的话，a也加上一个null，就避免这种情况了，简化了代码
			else if(l.size()==0){
				for(int j=0;j<r.size();j++){
					TreeNode ro=new TreeNode(root.val);//注意这里要new一个root，否则这里的root
					ro.left=null;//先给他付了左右节点，加进了a中，到了下层循环，又把root的左右
					ro.right=r.get(j);//赋予了新的节点，那么原来存在于a中的root就变了
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
//第四次,忘得差不多了，要看回之前的才知道
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
