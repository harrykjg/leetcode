import java.util.ArrayList;
import java.util.Arrays;

//http://jixiangsanbao.wordpress.com/2014/06/22/subsets-ii/
public class subset2 {
	
	public static void main(String[] args) {

	
	int[] s={1,2,3};
	ArrayList<ArrayList<Integer>> xx=new ArrayList<ArrayList<Integer>>();
	subset2 sb=new subset2();
	
	xx=sb.subsetsWithDup2(s);
	for(ArrayList<Integer> temp:xx){
		for(int i=0;i<temp.size();i++){
			System.out.print(temp.get(i));
		}
		System.out.println();
	}
	}
			

//我这个方法应该不是别人想要的方法，应该要用一个可以跳过选择重复元素的方法，而我是都穷举出来再用
	//最后的result的arraylist去判断有没重复
	
	//我觉得应该是在假如下一个数时，判断该数与其前面（这个前面的数要属于当前集合的最后的一个元素后）
	//那个数是否相同，相同则不加进去
	//比如12233取3个，那么取了122，取了123，当取了12，再取第二个3时，由于在第二个3前面有一个3，而且
	//这个3是在2的后面，所以就不取第二个3了。再看122，当取了12，虽然最后一位是2和第二位一样，但是
	//这个第二位的2是在2的后一层取得，所以可以取（这个解释可能不应该是这样想的，见permutation2的解释）
	
	public ArrayList<ArrayList<Integer>> subsets(int[] s) {
		ArrayList<ArrayList<Integer>> al=new ArrayList<ArrayList<Integer>>();//不把这个al
		//放在外面的话就把里面的方法都加上它作为一个参数传递就好了
		int b=1;
		int end=s.length;
		Arrays.sort(s);//注意这个方法，直接就把s排序了
		al.add(new ArrayList<Integer>());
		
		while(b<=end){
			pick(b,s,al);
			b++;
		}
		return al;
	
	}
	void pick(int n,int[] s,ArrayList<ArrayList<Integer>> al){
		ArrayList<Integer> temp=new ArrayList<Integer>();
		for(int i=0;i<s.length-n+1;i++){
			pick2(n,i,0,s,temp,al);
		}
	}
	void pick2(int n,int begin, int already,int[] s,ArrayList<Integer> t,ArrayList<ArrayList<Integer>> al){
		ArrayList<Integer> a2=new ArrayList<Integer>(t);
		
		a2.add(s[begin]);
		begin++;
		already++;
		if(n>already){
			for(int i=begin;i<=s.length-(n-already)&&i<s.length;i++){
				pick2(n,i,already,s,a2,al);
			}
		}
		else if(!al.contains(a2)){//这个subset2就和subset1差了个这个判断
			al.add(a2);
		}
	}
	//我这个方法是可以的啊，用了permutation2解释的那个方法避免重复，但是和code ganker他们不同
	//运行时间几乎一样
	public ArrayList<ArrayList<Integer>> subsetsWithDup(int[] s) {
		ArrayList<ArrayList<Integer>> al=new ArrayList<ArrayList<Integer>> ();
		ArrayList<Integer> a=new ArrayList<Integer>();
		al.add(a);
		if(s.length==0){
			return al;
		}
		Arrays.sort(s);
		boolean[] memo=new boolean[s.length];
		for(int i=1;i<=s.length;i++){
			go(s,i,0,0,a,al,memo);
		}
		return al;
		
	}
	private void go(int[] s,int n,int cur,int b,ArrayList<Integer> a,ArrayList<ArrayList<Integer>> al,
			boolean[] memo){
		if(cur==n){
			al.add(new ArrayList<Integer>(a));
			return;
		}
		for(int i=b;i<s.length;i++){
			if(i-1>=0&&s[i-1]==s[i]&&memo[i-1]==false){
				continue;
			}
			a.add(s[i]);
			memo[i]=true;
			go(s,n,cur+1,i+1,a,al,memo);
			a.remove(a.size()-1);
			memo[i]=false;
		}
	}
	//第三轮,一次tle~,原来是fill方法的for循环的i应该是初始化为b
	public ArrayList<ArrayList<Integer>> subsetsWithDup2(int[] s) {
		ArrayList<ArrayList<Integer>> al=new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> a=new ArrayList<Integer>();
		Arrays.sort(s);
		al.add(a);
		boolean[] memo=new boolean[s.length];
		if(s.length==0){
			return al;
		}
		for(int i=1;i<=s.length;i++){
			fill(0,i,s,a,al,memo);
		}
		
		return al;
	}
	private void fill(int b,int cur,int[] s,ArrayList<Integer> a,
			ArrayList<ArrayList<Integer>> al,boolean[] memo){
		if(a.size()==cur){
			al.add(new ArrayList<Integer>(a));
			return;
		}
		for(int i=b;i<s.length;i++){
			if(i>0&&s[i-1]==s[i]&&memo[i-1]==false){
				continue;
			}
			if(memo[i]==false){
				a.add(s[i]);
				memo[i]=true;
				fill(i+1,cur,s,a,al,memo);//注意这里是i+1，写成b+1就错了
				a.remove(a.size()-1);
				memo[i]=false;
			}
			
		}
	}
	
	
}
