import java.util.ArrayList;
import java.util.Arrays;
//http://jixiangsanbao.wordpress.com/2014/03/23/3sum-closest/
//
//就是先固定一个数，剩下的两个从两头开始向中间夹,要注意去重复检测
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
			if (i != 0 && num[i] == num[i - 1]) {  //加了这个后leetcode运行宽了200ms左右
                continue;  
            }
			find(num,0,num[i],i,i+1);
		}
		
		return al;

	}

	public void find(int[] num, int target, int cursum, int index,int begin) {

		int f = num.length - 1;
		

		while (f > begin) {

			if (f == index) {// 这个不可能出现，因为如果f在index的左边的话，不可能存在一个处于f和index之间的一个数，使得
				// num[index]+num[f]+num[b]＝0
				System.out.println("xxx");
			}
			if (begin == index) {
				// 也不会出现
			}

			if (cursum + num[f] + num[begin] == 0) {
				ArrayList<Integer> a = new ArrayList<Integer>();
				a.add(num[index]);
				a.add(num[begin]);
				a.add(num[f]);
				if (!al.contains(a)) {
					al.add(a);
				
				}
				f--;//f减小了，那么b必然要++才有可能再找到cursum + num[f] + num[begin] == 0
				begin++;
				//这两个while就是为了跳过相邻的相同的数字，没有的话leetcode会 time exceed
//15年11月：这两个循环的作用应该是跳过重复的结果，比如-2,0,2,2,没有这两个循环的话就会得出两个-2,0,2这样的结果
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
