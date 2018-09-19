import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//http://jixiangsanbao.wordpress.com/2014/04/23/subsets/
//http://blog.csdn.net/linhuanmars/article/details/24286377
public class Subsets {

	public static void main(String[] args) {

		int[] s = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
		ArrayList<ArrayList<Integer>> xx = new ArrayList<ArrayList<Integer>>();
		Subsets sb = new Subsets();
		long l1 = System.nanoTime();
		xx = sb.subsets2(s);
		long l2 = System.nanoTime();
		System.out.println(l2 - l1);
//		for(ArrayList<Integer> temp:xx){
//			for(int i=0;i<temp.size();i++){
//				System.out.print(temp.get(i));
//			}
//			System.out.println();
//		}


	}

	//���ϳ��ķ�������code ganker�ĺ����
	public ArrayList<ArrayList<Integer>> subsets(int[] S) {
//��ʵ��code ganker��һ���ģ�ֻ������indexֱ�Ӵ�0��ʼ�ˣ����ǵ�һ���ӵ�Ԫ�ؾ���0����code ganker
		//���Ǵ�s.length-1��ʼ���ٵݹ鵽0��ʼ��Ԫ��
		ArrayList<ArrayList<Integer>> cache = new ArrayList<ArrayList<Integer>>();
		if (S == null || S.length == 0) {
			return cache;
		}
		cache.add(new ArrayList<Integer>());//�ȼ��˸��ռ����������Ļ����ϲ���������Ԫ��
		Arrays.sort(S);
		return helper(S, 0, cache);
	}

	private ArrayList<ArrayList<Integer>> helper(int[] s, int index,
												 ArrayList<ArrayList<Integer>> cache) {
		if (index == s.length)
			return cache;

		ArrayList<ArrayList<Integer>> newCache = new ArrayList<ArrayList<Integer>>(
				cache);
		for (ArrayList<Integer> set : cache) {
			ArrayList<Integer> temp = new ArrayList<Integer>(set);
			temp.add(s[index]);//index������ȷ���ٱ�������ĸ�Ԫ�ص�
			newCache.add(temp);
		}

		return helper(s, index + 1, newCache);
	}

	//�Լ��ķ���l
	ArrayList<ArrayList<Integer>> al = new ArrayList<ArrayList<Integer>>();

	public ArrayList<ArrayList<Integer>> subsets2(int[] s) {
		int b = 1;
		int end = s.length;
		Arrays.sort(s);//ע�����������ֱ�ӾͰ�s������
		al.add(new ArrayList<Integer>());

		while (b <= end) {
			pick(b, s);
			b++;
		}
		return al;

	}

	void pick(int n, int[] s) {
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for (int i = 0; i < s.length - n + 1; i++) {
			pick2(n, i, 0, s, temp);
		}
	}

	void pick2(int n, int begin, int already, int[] s, ArrayList<Integer> t) {
		ArrayList<Integer> a2 = new ArrayList<Integer>(t);

		a2.add(s[begin]);
		begin++;
		already++;
		if (n > already) {
			for (int i = begin; i <= s.length - (n - already) && i < s.length; i++) {
				pick2(n, i, already, s, a2);
			}
		} else if (!al.contains(a2)) {
			al.add(a2);
		}
	}

	//�ڶ���д,�е㿨ס�ˣ���Ҫ��begin�Ĳ�������Ϊ������������Ҫ����ģ�˵�����и�begin����
	//ÿ�μ����ֽ�ȥҪ�����begin��֮��ӣ����ܱ�֤�����򣬶����begin��subset2�������for
	//ѭ��û�й�ϵ���Ǻ�go�������forѭ���й�ϵ
	//���ǻ��Ǻͼ����code ganker�Ĳ�һ��,code ganker�Ŀ�88ms����
	public ArrayList<ArrayList<Integer>> subsets3(int[] s) {

		ArrayList<ArrayList<Integer>> al = new ArrayList<ArrayList<Integer>>();
		al.add(new ArrayList<Integer>());
		if (s.length == 0) {

			return al;
		}
		Arrays.sort(s);
		ArrayList<Integer> a = new ArrayList<Integer>();
		for (int i = 1; i <= s.length; i++) {
			go(s, a, i, 0, 0, al);
		}
		return al;
	}

	private void go(int[] s, ArrayList<Integer> a, int n, int cur, int begin, ArrayList<ArrayList<Integer>> al) {
		if (cur == n) {
			al.add(new ArrayList<Integer>(a));
			return;
		}
		for (int i = begin; i < s.length; i++) {
			a.add(s[i]);
			go(s, a, n, cur + 1, i + 1, al);//������֮ǰд����begin+1���Ǵ�ģ�Ӧ����i+1��Ϊ��һ��Ҫ����
			a.remove(a.size() - 1); //������
		}

	}

	//code ganker��
	public ArrayList<ArrayList<Integer>> subsets4(int[] num) {
		if (num == null)
			return null;
		Arrays.sort(num);
		return helper(num, num.length - 1);
	}

	private ArrayList<ArrayList<Integer>> helper(int[] num, int index) {
		if (index == -1) {
			ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
			ArrayList<Integer> elem = new ArrayList<Integer>();
			res.add(elem);
			return res;
		}
		ArrayList<ArrayList<Integer>> res = helper(num, index - 1);
		int size = res.size();
		for (int i = 0; i < size; i++) {
			ArrayList<Integer> elem = new ArrayList<Integer>(res.get(i));
			elem.add(num[index]);
			res.add(elem);
		}
		return res;
	}

	//������,����һ�ι�������Ҫmemo����Ϊ����ֵ��Ψһ�ģ���һ����ʼ������ȥ��λ������һ��ȷ��������
	public ArrayList<ArrayList<Integer>> subsets5(int[] num) {
		ArrayList<ArrayList<Integer>> al = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> a = new ArrayList<Integer>();

		al.add(new ArrayList<Integer>(a));

		Arrays.sort(num);
		for (int i = 1; i <= num.length; i++) {
			go2(i, 0, a, al, num);
		}
		return al;
	}

	private void go2(int pick, int b, ArrayList<Integer> a, ArrayList<ArrayList<Integer>> al, int[] num) {
		if (pick == a.size()) {
			al.add(new ArrayList<Integer>(a));
			return;
		}
		for (int i = b; i < num.length; i++) {
			a.add(num[i]);
			go2(pick, i + 1, a, al, num);
			a.remove(a.size() - 1);
		}
	}

	//9/5/2018
	public List<List<Integer>> subsets6(int[] num) {
		List<List<Integer>> rs = new ArrayList<List<Integer>>();
		List<Integer> a = new ArrayList<Integer>();
		if(num.length==0){
			return rs;
		}
		for(int i=1;i<num.length;i++){
			dfs(i,0,a,num,rs);
		}
		rs.add(new ArrayList<>());
		return rs;
	}
	void dfs(int count,int b,List<Integer> al,int[] nums,List<List<Integer>>rs ){
		if(al.size()==count){
			rs.add(new ArrayList<>(al));
			return;
		}
		for(int i=b;i<nums.length;i++){
			al.add(nums[i]);
			dfs(count,i+1,al,nums,rs);
			al.remove(al.size()-1);
		}
	}
}
