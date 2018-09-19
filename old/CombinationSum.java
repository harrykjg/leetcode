import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//http://blog.csdn.net/linhuanmars/article/details/20828631
public class CombinationSum {
//思路就是每次试着在num数组里取一个数，用target减去这个数看是否为0，为0即符合条件，不为0则把这个
//target-num[i]看做target，再一个个的试num里的数。num要sort，并且要设一个begin，即数只能
//从begin之后开始取，因为否则的话又会重复取了，而且也不符合结果集从小到大的条件
	
	public static void main(String[] args) {

		int[] a = { 2,3,6,7 };
		CombinationSum c = new CombinationSum();
		List<List<Integer>> all = new ArrayList<>();

		all = c.combinationSum3(a, 7);
		for (List<Integer> k : all) {
			for (int i : k) {
				System.out.print(i);
			}
			System.out.println();
		}

	}

	ArrayList<ArrayList<Integer>> al = new ArrayList<ArrayList<Integer>>();

	public ArrayList<ArrayList<Integer>> combinationSum(int[] num, int target) {
		
		if(num.length<0){
			return al;
		}
		Arrays.sort(num);
		ArrayList<Integer> a=new ArrayList<Integer>();
		find(num,0,target,a);
		return al;
		
	}

	public void find(int[] num,int begin, int cursum,ArrayList<Integer> a) {
		if(begin>=num.length){
			return;
		}
		for(int i=begin;i<num.length;i++){
			while(i>0&&num[i-1]==num[i]){//相同的略去
				continue;
			}
			if(num[i]<=cursum){
				a.add(num[i]);
				
				if(cursum-num[i]>0){
					find(num,i,cursum-num[i],new ArrayList<Integer>(a));
					a.remove(a.size()-1);
				}else{//即num[i]==cursum了
					al.add(a);
					return;
				}
			}else{
				break;
			}	
		}
	}
	//第二次写
	public ArrayList<ArrayList<Integer>> combinationSum2(int[] num, int target) {
		ArrayList<ArrayList<Integer>> al=new ArrayList<ArrayList<Integer>>();
		if(num.length==0){
			return al;
		}
		Arrays.sort(num);
		ArrayList<Integer> a=new ArrayList<Integer>();
		
			go(0,0,num,target,a,al);
		return al;
		
	}
	private void go(int cur,int begin,int[] num,int target,ArrayList<Integer> a,ArrayList<ArrayList<Integer>> al){
		if(cur==target){
			al.add(new ArrayList<Integer>(a));
			return;
		}
		if(cur>target){
			return;
		}
		for(int i=begin;i<num.length;i++){
			a.add(num[i]);
			go(cur+num[i],i,num,target,a,al);
			a.remove(a.size()-1);
		}
	}
	//30/9发现一个问题，这里不需要判断if(i>0 && candidates[i]==candidates[i-1])的原因是题目说了
	//candidate是set所以不会有重复，否则的话就要这个if判断，否则会错


	//9/9/2018,居然还是没写对,忘了用begin做限制
	public   List<List<Integer>> combinationSum3(int[] can, int tar) {
		List<List<Integer>> rs=new ArrayList<>();
		List<Integer> al=new ArrayList<>();
		if(can.length==0){

			rs.add(al);
			return rs;
		}
		Arrays.sort(can);

		helper2(0,0,tar,can,al,rs);
		return rs;

	}
	 void  helper2(int cur,int b,int tar,int[] can,List<Integer> al,List<List<Integer>> rs){
		if(cur==tar){
			rs.add(new ArrayList<>(al));
			return;
		}
		for(int i=b;i<can.length;i++){
			if(cur+can[i]<=tar){
				al.add(can[i]);
				helper2(cur+can[i],i,tar,can,al,rs);
				al.remove(al.size()-1);
			}

		}
	}
//9／13／2018
	public  List<List<Integer>> combinationSum4(int[] can, int tar) {
		List<List<Integer>> rs=new ArrayList<>();
		List<Integer> al=new ArrayList<>();
		if(can.length==0){
			return rs;
		}
		Arrays.sort(can);
		dfs4(0,0,can,tar,al,rs);
		return rs;
	}
	void dfs4(int b,int cur,int[] can,int target,List<Integer> al,List<List<Integer>> rs){
		if(cur==target){
			rs.add(new ArrayList<>(al));
			return;
		}
		for(int i=b;i<can.length;i++){
			if(cur+can[i]>target){
				return;
			}
			al.add(can[i]);
			dfs4(i,cur+can[i],can,target,al,rs);
			al.remove(al.size()-1);
		}
	}
}
