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
			

//���������Ӧ�ò��Ǳ�����Ҫ�ķ�����Ӧ��Ҫ��һ����������ѡ���ظ�Ԫ�صķ����������Ƕ���ٳ�������
	//����result��arraylistȥ�ж���û�ظ�
	
	//�Ҿ���Ӧ�����ڼ�����һ����ʱ���жϸ�������ǰ�棨���ǰ�����Ҫ���ڵ�ǰ���ϵ�����һ��Ԫ�غ�
	//�Ǹ����Ƿ���ͬ����ͬ�򲻼ӽ�ȥ
	//����12233ȡ3������ôȡ��122��ȡ��123����ȡ��12����ȡ�ڶ���3ʱ�������ڵڶ���3ǰ����һ��3������
	//���3����2�ĺ��棬���ԾͲ�ȡ�ڶ���3�ˡ��ٿ�122����ȡ��12����Ȼ���һλ��2�͵ڶ�λһ��������
	//����ڶ�λ��2����2�ĺ�һ��ȡ�ã����Կ���ȡ��������Ϳ��ܲ�Ӧ����������ģ���permutation2�Ľ��ͣ�
	
	public ArrayList<ArrayList<Integer>> subsets(int[] s) {
		ArrayList<ArrayList<Integer>> al=new ArrayList<ArrayList<Integer>>();//�������al
		//��������Ļ��Ͱ�����ķ�������������Ϊһ���������ݾͺ���
		int b=1;
		int end=s.length;
		Arrays.sort(s);//ע�����������ֱ�ӾͰ�s������
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
		else if(!al.contains(a2)){//���subset2�ͺ�subset1���˸�����ж�
			al.add(a2);
		}
	}
	//����������ǿ��Եİ�������permutation2���͵��Ǹ����������ظ������Ǻ�code ganker���ǲ�ͬ
	//����ʱ�伸��һ��
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
	//������,һ��tle~,ԭ����fill������forѭ����iӦ���ǳ�ʼ��Ϊb
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
				fill(i+1,cur,s,a,al,memo);//ע��������i+1��д��b+1�ʹ���
				a.remove(a.size()-1);
				memo[i]=false;
			}
			
		}
	}
	
	
}
