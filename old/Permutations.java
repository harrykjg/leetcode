import java.util.ArrayList;

//http://blog.csdn.net/linhuanmars/article/details/21569031
public class Permutations {
//第一次是很久之前写的，这是第二次，一次accept
	public ArrayList<ArrayList<Integer>> permute(int[] num) {
		ArrayList<ArrayList<Integer>> al=new ArrayList<ArrayList<Integer>>();
		if(num.length==0){
			return al;
		}
		boolean[] memo=new boolean[num.length];
		ArrayList<Integer> a=new ArrayList<Integer>();
		backtrack(a,al,num,memo);
		return al;
	}
	public void backtrack(ArrayList<Integer> a,ArrayList<ArrayList<Integer>> al,int[] num,boolean[] memo){
		if(a.size()==num.length){
			al.add(new ArrayList<Integer>(a));
		}
		for(int i=0;i<num.length;i++){
			if(memo[i]==false){
				a.add(num[i]);
				memo[i]=true;
				backtrack(a,al,num,memo);
				a.remove(a.size()-1);
				memo[i]=false;
			}
		}
		
	}
}
