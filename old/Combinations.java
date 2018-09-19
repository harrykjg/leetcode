import java.util.ArrayList;
import java.util.List;
//dfs的思想

public class Combinations {
	
	public static void main(String[] args) {
		Combinations cc=new Combinations();
		ArrayList<ArrayList<Integer>> xx=new ArrayList<ArrayList<Integer>>();
		xx=cc.combine2(4, 2);
		for(int i=0;i<xx.size();i++){
			for(int j=0;j<xx.get(i).size();j++){
				System.out.print(xx.get(i).get(j));
			}
			System.out.println();
		}
		
	}
	
	ArrayList<ArrayList<Integer>> al=new ArrayList<ArrayList<Integer>>();
	
	public ArrayList<ArrayList<Integer>> combine(int n,int k){
		if(n<k){
			return al;
		}
		int[] nn=new int[n];		
		for(int i=0;i<nn.length;i++){
			nn[i]=i+1;
		}
		ArrayList<Integer> aa=new ArrayList<Integer>();
		for(int i=0;i<=n-k;i++){
			dfs(nn,k,0,i,aa);
		}
		return al;
		
	}
	
	public void dfs(int[] a,int n,int o,int begin,ArrayList<Integer> na){
		//n是要取几个，o是去了几个
		
		ArrayList<Integer> a2=new ArrayList<Integer>();
		if(na.size()!=0){
			a2.addAll(na);
		}
		a2.add(a[begin]);
		o++;
		begin++;
		if(o<n){
			for(int i=begin;i<=a.length-(n-o);i++){
				dfs(a,n,o,i,a2);
			}
		}else{
			al.add(a2);
		}
	}
	//第二次写，错了，搞成排列的了，只要每次for循环都在有一个begin开始就可以避免重复，
	//而且memo数组不用
	public ArrayList<ArrayList<Integer>> combine2(int n,int k){
		ArrayList<ArrayList<Integer>> al=new ArrayList<ArrayList<Integer>>();
		if(k==0&&k>n){
			return al;
		}
		int[] num=new int[n];
		for(int i=0;i<num.length;i++){
			num[i]=i+1;
		}
		
		ArrayList<Integer> a=new ArrayList<Integer>();
		go(n,k,num,0,al,a,0);
		return al;
	}
	private void go(int n,int k,int[] num,int begin,ArrayList<ArrayList<Integer>> al,ArrayList<Integer> a,int cur){
		if(cur==k){
			al.add(new ArrayList<Integer>(a));
			return;
		}
		
		for(int i=begin;i<num.length;i++){
			
				a.add(num[i]);
				
				go(n,k,num,i+1,al,a,cur+1);
				a.remove(a.size()-1);
				
			
		}
	}
	//9/9/2018,妈的写的还不好
	public List<List<Integer>> combine3(int n, int k){
		List<List<Integer>> rs=new ArrayList<>();
		if(k==0&&k>n){
			return rs;
		}
		ArrayList<Integer> al=new ArrayList<>();

		dfs3(1,rs,al,k,n);
		return rs;
	}
	void dfs3(int b,List<List<Integer>> rs,ArrayList<Integer> al,int k,int n){
		if(al.size()==k){
			rs.add(new ArrayList<>(al));
			return;
		}
		for(int i=b;i<=n;i++){
			al.add(i);
			dfs3(i+1,rs,al,k,n);
			al.remove(al.size()-1);
		}
	}
//9/13/2018,还好一次过
	public List<List<Integer>> combine4(int n, int k){
		List<List<Integer>> rs=new ArrayList<>();
		if(k==0&&k>n){
			return rs;
		}
		ArrayList<Integer> al=new ArrayList<>();
		dfs4(1,0,n,k,al,rs);

		return rs;
	}
	void dfs4(int b,int cur,int n,int k,List<Integer> al,List<List<Integer>> rs){
		if(cur==k){
			rs.add(new ArrayList<>(al));
			return;
		}
		for(int i=b;i<=n;i++){
			al.add(i);
			dfs4(i+1,cur+1,n,k,al,rs);
			al.remove(al.size()-1);
		}
	}

}
