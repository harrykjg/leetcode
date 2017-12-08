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
					if (flag==false) {//���ʵ���ϲ��ܵõ�����һ��ttry
						//�ó��Ľ������Ϊttry������¼ӵ�num[i]���и�forѭ���ģ�����õ�
						//��ii����ttry�����õ���i

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
	
	//�ڶ���д����ʶ���˻����ظ����⣬����111123��target��7�������ѡǰ2��1��2��3�����ߵ�һ��1��
	//���һ��1��2��3��������ֱ����set���������ģ�Ȼ����ת����arraylist��
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
	//code ganker��
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
	        if(i>start && num[i]==num[i-1]) continue;  //�����i>start��������iһ��ʼ�ǵ���start�ģ�����
	                                                 //�϶��������start�����ǵ�i�ڼ�����ʱ�ͻ����start�ˡ�
	        item.add(num[i]);     //�����Ҫȡ�����ֵ�ǵ�ǰ���һ���Ե����֣��Ϳ��ԣ�����Ļ����ظ��Ļ����Ͳ�ȡ��
	        helper(num,i+1,target-num[i],item,res);  //����111123��ȡǰ4��1ʱ��ÿ�ζ��ǵ�ǰ��ĵ�һ����������ok
	        item.remove(item.size()-1); //Ȼ�����ȡ��ǰ2��1���ڶ���1�˻��������1��1����ȥʱ��3�����ĸ�1ʱ���ͻ��Թ�
	    }                               //����һ�£���Ϊ����һ��1���ڣ���ȥ�Ե�3��4��1ʱ�����Ѿ��Թ��ڶ���1�ˣ���
	}                                 //�ڶ���1Ҳ���Ե�3��1����3��һҲ���Ե�4��1.������ͦ�����


	//������,������2�¾Ͷ��ˣ�index��ϸ��д��������permutation2���Ǹ������ܿ��ظ�
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
			//����permutation2���Ǹ������ܿ��ظ�
			if(i>0&&num[i]==num[i-1]&&memo[i-1]==false){
				continue;
			}
			if(num[i]+cur>target){
				return;
			}
			//���ﲻ���ж�if��memo[i]==false������Ϊ��һ�����϶���i����ģ��϶�û�ù���memo������
			//���������Ǹ������ظ���if
			memo[i]=true;
			a.add(num[i]);
			go2(i+1,num[i]+cur,a,al,memo,num,target);
			a.remove(a.size()-1);
			memo[i]=false;
			
		}
	}
}
