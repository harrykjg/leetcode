import java.util.ArrayList;
import java.util.Arrays;
//http://blog.csdn.net/linhuanmars/article/details/20829099
//http://jixiangsanbao.wordpress.com/2014/05/04/combination-sum-ii/

public class CombinationsSum2 {

	public static void main(String[] args) {

		int[] a = { 1,1,1,1,2,3 };
		CombinationsSum2 c = new CombinationsSum2();
		ArrayList<ArrayList<Integer>> all = new ArrayList<ArrayList<Integer>>();

		all = c.combinationSum24(a, 7);
		for (ArrayList<Integer> k : all) {
			for (int i : k) {
				System.out.print(i);
			}
			System.out.println();
		}

	}

	ArrayList<ArrayList<Integer>> al = new ArrayList<ArrayList<Integer>>();

	public ArrayList<ArrayList<Integer>> combinationSum2(int[] num, int target) {

		Arrays.sort(num);
		for (int i = 0; i < num.length; i++) {
			ArrayList<Integer> a = new ArrayList<Integer>();
			a.add(num[i]);
			if (num[i] == target) {
				if (!al.contains(a)) {
					al.add(a);
					continue;
				}

			}
			ttry(i + 1, target, num[i], num, a);
		}

		return al;
	}

	public boolean ttry(int begin, int target, int cursum, int[] num,
			ArrayList<Integer> a) {

		ArrayList<Integer> aa = new ArrayList<Integer>();
		aa.addAll(a);
		if (begin >= num.length) {
			return false;
		}
		for (int i = begin; i < num.length; i++) {
			ArrayList<Integer> aaa = new ArrayList<Integer>();
			aaa.addAll(aa);
			aaa.add(num[i]);
			int temp = cursum + num[i];

			if (temp == target) {
				if (!al.contains(aaa)) {
					al.add(aaa);
					return false;
				}
			}

			else if (temp > target) {
				return false;
			} else {

				for (int ii = i + 1; ii < num.length; ii++) {
					boolean flag=ttry(ii, target, temp, num, aaa);
					if (flag==false) {//这个实际上不能得到他上一行ttry
						//得出的结果，因为ttry里面的新加的num[i]是有个for循环的，这里得到
						//的ii不是ttry里所得到的i

						break;
					}
				}
			}
			if (temp >= target) {
				break;
			}
			
		}
		return true;
	}
	
	//第二次写，意识到了会有重复问题，比如111123，target是7，则可以选前2个1和2和3，或者第一个1和
	//最后一个1和2和3，吉祥是直接用set来左结果集的，然后再转化成arraylist的
	public ArrayList<ArrayList<Integer>> combinationSum22(int[] num, int target) {
		ArrayList<ArrayList<Integer>> al=new ArrayList<ArrayList<Integer>>();
		if(num.length==0){
			return al;
		}
		ArrayList<Integer> a=new ArrayList<Integer>();
		Arrays.sort(num);
		go(num,0,0,target,a,al);
		return al;
		
	}
	private void go(int[] num,int begin,int cur,int target,ArrayList<Integer> a,ArrayList<ArrayList<Integer>> al){
		if(cur>target){
			return;
		}
		if(cur==target){
			al.add(new ArrayList<Integer>(a));
			return;
		}
		for(int i=begin;i<num.length;i++){
			if(i>begin && num[i]==num[i-1]){
				continue;
			}
			a.add(num[i]);
			go(num,i,cur+num[i],target,a,al);
			a.remove(a.size()-1);
		}
	}
	//code ganker的
	public ArrayList<ArrayList<Integer>> combinationSum23(int[] num, int target) {  
	    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();  
	    if(num == null || num.length==0)  
	        return res;  
	    Arrays.sort(num);  
	    helper(num,0,target,new ArrayList<Integer>(),res);  
	    return res;  
	}  
	private void helper(int[] num, int start, int target, ArrayList<Integer> item,  
	ArrayList<ArrayList<Integer>> res)  
	{  
	    if(target == 0)  
	    {  
	        res.add(new ArrayList<Integer>(item));  
	        return;  
	    }  
	    if(target<0 || start>=num.length)  
	        return;  
	    for(int i=start;i<num.length;i++)  
	    {  
	        if(i>start && num[i]==num[i-1]) continue;  //他这个i>start设的巧妙啊，i一开始是等于start的，所以
	                                                 //肯定不会大于start，但是当i在继续走时就会大于start了。
	        item.add(num[i]);     //即如果要取的这个值是当前层第一个试的数字，就可以，否则的话，重复的话，就不取了
	        helper(num,i+1,target-num[i],item,res);  //例如111123，取前4个1时，每次都是当前层的第一个数，所以ok
	        item.remove(item.size()-1); //然后比如取了前2个1，第二个1退回来，变成1个1，再去时第3或者四个1时，就会略过
	    }                               //，想一下，因为当第一个1存在，再去试第3或4个1时，他已经试过第二个1了，而
	}                                 //第二个1也会试第3个1，第3个一也会试第4个1.。。。挺神奇的


	//第三轮,调试了2下就对了，index的细节写错，还是用permutation2的那个方法避开重复
	public ArrayList<ArrayList<Integer>> combinationSum24(int[] num, int target) {
		ArrayList<ArrayList<Integer>> al=new ArrayList<ArrayList<Integer>> ();
		
		if(num.length==0){
			return al;
		}
		Arrays.sort(num);
		boolean[] memo=new boolean[num.length];
		ArrayList<Integer> a=new ArrayList<Integer>(); 
		go2(0,0,a,al,memo,num,target);
		return al;
	}
	private void go2(int b,int cur,ArrayList<Integer> a,ArrayList<ArrayList<Integer>> al,
			boolean[]memo,int[] num,int target){
		if(cur==target){
			al.add(new ArrayList<Integer>(a));
			return;
		}
		
		for(int i=b;i<num.length;i++){
			//还是permutation2的那个方法避开重复
			if(i>0&&num[i]==num[i-1]&&memo[i-1]==false){
				continue;
			}
			if(num[i]+cur>target){
				return;
			}
			//这里不用判断if（memo[i]==false），因为下一个数肯定是i后面的，肯定没用过，memo的作用
			//就是用在那个避免重复的if
			memo[i]=true;
			a.add(num[i]);
			go2(i+1,num[i]+cur,a,al,memo,num,target);
			a.remove(a.size()-1);
			memo[i]=false;
			
		}
	}
}
