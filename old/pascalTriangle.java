import java.util.ArrayList;
import java.util.List;

public class pascalTriangle {

	public static void main(String[] args) {

		pascalTriangle ps = new pascalTriangle();
		ArrayList<ArrayList<Integer>> xx = new ArrayList<ArrayList<Integer>>();
		xx = ps.generate(5);
		for (ArrayList<Integer> temp : xx) {
			for (int k : temp) {
				System.out.print(k);
			}
			System.out.println();
		}

	}

	ArrayList<ArrayList<Integer>> al = new ArrayList<ArrayList<Integer>>();

	public ArrayList<ArrayList<Integer>> generate(int numRows) {
		
		if(numRows==0){
			return al;
		}
		if(numRows==1){
			ArrayList<Integer> all = new ArrayList<Integer>();
			all.add(1);
			al.add(all);
			return al;
		}

		int i = numRows;
		int j = 0;
		ArrayList<Integer> all = new ArrayList<Integer>();
		all.add(1);
		ArrayList<Integer> all2 = new ArrayList<Integer>();
		all2.add(1);
		all2.add(1);

		al.add(all);
		al.add(all2);

		if (i >= 3) {
			j = 3;
			gen(i, j, all2);

		}
		return al;

	}

	public void gen(int i, int j, ArrayList<Integer> a) {

		if (i >= j) {
			ArrayList<Integer> all = new ArrayList<Integer>();

			all.add(1);
			for (int k = 1; k < j - 1; k++) {
				all.add(a.get(k - 1) + a.get(k));
			}
			all.add(1);
			al.add(all);
			j++;
			gen(i, j, all);
		}

	}
	//第二次
	public ArrayList<ArrayList<Integer>> generate2(int numRows){
		ArrayList<ArrayList<Integer>> al=new ArrayList<ArrayList<Integer>>();
		if(numRows==0)
		{
			return al;
		}
		ArrayList<Integer> a=new ArrayList<Integer>();
		a.add(1);
		al.add(a);
		if(numRows==1){
			return al;
		}
		gen2(al,2,numRows);
		return al;
	}
	private void gen2(ArrayList<ArrayList<Integer>> al,int cur,int n){
		ArrayList<Integer> pre=al.get(al.size()-1);
		ArrayList<Integer> a=new ArrayList<Integer>();
		a.add(1);
		for(int i=0;i<pre.size()-1;i++){
			a.add(pre.get(i)+pre.get(i+1));
		}
		a.add(1);
		al.add(a);
		if(cur==n){
			return;
		}else{
			gen2(al,cur+1,n);
		}
	}

	//10/3/2018,基本一次过
	public List<List<Integer>> generate3(int numRows){
		List<List<Integer>> rs=new ArrayList<>();
		ArrayList<Integer> first=new ArrayList<>();
		if(numRows==0){
			return rs;
		}
		first.add(1);
		rs.add(first);
		for(int i=1;i<numRows;i++){
			ArrayList<Integer> al=new ArrayList<>();
			List<Integer> pre=rs.get(i-1);
			for(int j=0;j<pre.size()-1;j++){
				al.add(pre.get(j)+pre.get(j+1));
			}
			al.add(0,1);
			al.add(1);
			rs.add(al);
		}
		return rs;
	}

}
