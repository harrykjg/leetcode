import java.util.ArrayList;
import java.util.Arrays;
//http://jixiangsanbao.wordpress.com/2014/03/23/3sum-closest/
//
//�����ȹ̶�һ������ʣ�µ���������ͷ��ʼ���м��,Ҫע��ȥ�ظ����
public class ThreeSum {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		ThreeSum ts=new ThreeSum();
		int[] num={-2,0,1,1,2};
		
		ArrayList<ArrayList<Integer>> al=new ArrayList<ArrayList<Integer>>();
		al=ts.threeSum(num);
		for(ArrayList<Integer> a:al){
			for(int i:a){
				System.out.print(i);
				
			}
			System.out.println();
		}
		
		
	}

	ArrayList<ArrayList<Integer>> al = new ArrayList<ArrayList<Integer>>();

	public ArrayList<ArrayList<Integer>> threeSum(int[] num) {

		if (num.length < 3) {
			return al;
		}
		Arrays.sort(num);
		for(int i=0;i<num.length-2;i++){
			if (i != 0 && num[i] == num[i - 1]) {  //���������leetcode���п���200ms����
                continue;  
            }
			find(num,0,num[i],i,i+1);
		}
		
		return al;

	}

	public void find(int[] num, int target, int cursum, int index,int begin) {

		int f = num.length - 1;
		

		while (f > begin) {

			if (f == index) {// ��������ܳ��֣���Ϊ���f��index����ߵĻ��������ܴ���һ������f��index֮���һ������ʹ��
				// num[index]+num[f]+num[b]��0
				System.out.println("xxx");
			}
			if (begin == index) {
				// Ҳ�������
			}

			if (cursum + num[f] + num[begin] == 0) {
				ArrayList<Integer> a = new ArrayList<Integer>();
				a.add(num[index]);
				a.add(num[begin]);
				a.add(num[f]);
				if (!al.contains(a)) {
					al.add(a);
				
				}
				f--;//f��С�ˣ���ôb��ȻҪ++���п������ҵ�cursum + num[f] + num[begin] == 0
				begin++;
				//������while����Ϊ���������ڵ���ͬ�����֣�û�еĻ�leetcode�� time exceed
//15��11�£�������ѭ��������Ӧ���������ظ��Ľ��������-2,0,2,2,û��������ѭ���Ļ��ͻ�ó�����-2,0,2�����Ľ��
				while(f>begin&&num[begin]==num[begin-1]){
					begin++;
				}
				while(f>begin&&num[f]==num[f+1]){
					f--;
				}
			}
			else if( num[f] + num[begin] >-cursum){
				f--;
			}
			else{
				begin++;
			}

		}

	}

}
