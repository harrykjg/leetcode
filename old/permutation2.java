import java.util.ArrayList;
import java.util.Arrays;
//http://blog.csdn.net/linhuanmars/article/details/21570835
public class permutation2 {

	public static void main(String[] args) {

		permutation2 p = new permutation2();
		int[] a = {1,1};

		ArrayList<ArrayList<Integer>> al = new ArrayList<ArrayList<Integer>>();
		al = p.permuteUnique3(a);
		for (ArrayList<Integer> k : al) {
			for (int i : k) {
				System.out.print(i);
			}
			System.out.println();
		}

	}

	ArrayList<ArrayList<Integer>> al = new ArrayList<ArrayList<Integer>>();

	public ArrayList<ArrayList<Integer>> permuteUnique(int[] num) {
		//����ò���ǶԵĵ��ǻᳬʱ,Ӧ�������������

		for (int i = 0; i < num.length; i++) {
			ArrayList<Integer> a = new ArrayList<Integer>();

			a.add(num[i]);
			ArrayList<Integer> memo = new ArrayList<Integer>();
			memo.add(i);
			create(a, num.length, 1, num, memo,i);
		}

		return al;

	}

	public void create(ArrayList<Integer> a, int total, int already, int[] num,
			ArrayList<Integer> memo,int last) {
		if (already == total) {
			if (!al.contains(a)) {
				al.add(a);
			}
			return;
		}
//		for (int i = 0; i < num.length; i++) {
		int i=last+1;
		do{

			ArrayList<Integer> aa = new ArrayList<Integer>(a);
			

			ArrayList<Integer> memo2 = new ArrayList<Integer>(memo);//memo��������¼�������һ��
			//λ�õ���ȡ����񣨶��������������ȡ�����
			

			if (!memo2.contains(i)&&i<num.length) {
				aa.add(num[i]);
				memo2.add(i);

				if (already + 1 == total && (!al.contains(aa))) {
					al.add(aa);
					return;
				}
				create(aa, total, already + 1, num,memo2,i);
			}
			i=(i+1)%num.length;
		}while(i!=last);

		return;
	}
	public ArrayList<ArrayList<Integer>> permuteUnique2(int[] num) {  
		//code ganker�Ĵ���
	    ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();  
	    if(num==null && num.length==0)  
	        return res;  
	    Arrays.sort(num);  
	    helper(num, new boolean[num.length], new ArrayList<Integer>(), res);  
	    return res;  
	}  
	private void helper(int[] num, boolean[] used, ArrayList<Integer> item, ArrayList<ArrayList<Integer>> res)  
	{  
	    if(item.size() == num.length)  
	    {  
	        res.add(new ArrayList<Integer>(item));  
	        return;  
	    }  
	    for(int i=0;i<num.length;i++)  
	    {  
	    	
	        if(i>0 && used[i-1]==false && num[i]==num[i-1]) continue;//ע��used[i-1]==false�������
	        //����num��1122����ȡ�˵�һ��1��for�������������ڶ���1ʱ����Ϊ��һ��1�Ѿ����ˣ�����
	        //�������ڶ���1�ӽ�����������1122���������������ȡ�ڶ���1��Ϊ��ͷʱ����ʱ��һ��1
	        //û���ù������Ծͽ���continue����ȥ�ˣ���tm���棬subset2����������Ƶ�
	        if(!used[i])  
	        {  
	            used[i] = true;  
	            item.add(num[i]);  
	            helper(num, used, item, res);  
	            item.remove(item.size()-1);  
	            used[i] = false;  
	        }  
	    }  
	}  
//�ڶ���д��������
	public ArrayList<ArrayList<Integer>> permuteUnique3(int[] num) {  
		ArrayList<ArrayList<Integer>> al=new ArrayList<ArrayList<Integer>>();
		if(num.length==0){
			return al;
		}
		Arrays.sort(num);
		boolean[] memo=new boolean[num.length];
		ArrayList<Integer> a=new ArrayList<Integer>();
		go(num,memo,a,al);
		return al;
	}
	private void go(int[] num,boolean[] memo,ArrayList<Integer> a,ArrayList<ArrayList<Integer>> al){
		if(a.size()==num.length){
			al.add(new ArrayList<Integer> (a));
			return;
		}
		for(int i=0;i<num.length;i++){
			if(i-1>=0&&num[i-1]==num[i]&&memo[i-1]==false){
				continue;
			}
			if(memo[i]==false){
				a.add(num[i]);
				memo[i]=true;
				go(num,memo,a,al);
				memo[i]=false;
				a.remove(a.size()-1);
			}
		}
		
	}
	//������,����һ��accept
	public ArrayList<ArrayList<Integer>> permuteUnique4(int[] num) {
		ArrayList<ArrayList<Integer>> al=new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> a=new ArrayList<Integer>();
		boolean[] memo=new boolean[num.length];
		Arrays.sort(num);
		go2(num,memo,a,al);
		return al;
	}
	private void go2(int[] num,boolean[] memo,ArrayList<Integer> a,ArrayList<ArrayList<Integer>> al){
		if(a.size()==num.length){
			al.add(new ArrayList<Integer>(a));
			return;
		}
		for(int i=0;i<num.length;i++){
			if(i>0&&num[i]==num[i-1]&&memo[i-1]==false){
				continue;
			}
			if(memo[i]==false){
				a.add(num[i]);
				memo[i]=true;
				go2(num,memo,a,al);
				memo[i]=false;
				a.remove(a.size()-1);
			}
			
		}
		
	}

}
