import java.util.ArrayList;
import java.util.Arrays;
//http://blog.csdn.net/linhuanmars/article/details/20828631
public class CombinationSum {
//˼·����ÿ��������num������ȡһ��������target��ȥ��������Ƿ�Ϊ0��Ϊ0��������������Ϊ0������
//target-num[i]����target����һ��������num�������numҪsort������Ҫ��һ��begin������ֻ��
//��begin֮��ʼȡ����Ϊ����Ļ��ֻ��ظ�ȡ�ˣ�����Ҳ�����Ͻ������С���������
	
	public static void main(String[] args) {

		int[] a = { 2,2,3,6,7 };
		CombinationSum c = new CombinationSum();
		ArrayList<ArrayList<Integer>> all = new ArrayList<ArrayList<Integer>>();

		all = c.combinationSum2(a, 7);
		for (ArrayList<Integer> k : all) {
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
			while(i>0&&num[i-1]==num[i]){//��ͬ����ȥ
				continue;
			}
			if(num[i]<=cursum){
				a.add(num[i]);
				
				if(cursum-num[i]>0){
					find(num,i,cursum-num[i],new ArrayList<Integer>(a));
					a.remove(a.size()-1);
				}else{//��num[i]==cursum��
					al.add(a);
					return;
				}
			}else{
				break;
			}	
		}
	}
	//�ڶ���д
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
	//30/9����һ�����⣬���ﲻ��Ҫ�ж�if(i>0 && candidates[i]==candidates[i-1])��ԭ������Ŀ˵��
	//candidate��set���Բ������ظ�������Ļ���Ҫ���if�жϣ�������
    
}
