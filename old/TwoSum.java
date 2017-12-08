//http://blog.csdn.net/linhuanmars/article/details/19711387
//http://jixiangsanbao.wordpress.com/2014/02/27/two-sum/ 他这个我没怎么看懂

import java.util.Arrays;
public class TwoSum {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int[] n={5,75,25};
		TwoSum ts=new TwoSum();
		int[] i=ts.twoSum(n, 100);
		for(int k:i){
			System.out.println(k);
		}
		
		
	}
//第二次写的，想起之前问何雨辰的，brute force会超时，所以还是得sort，然后用夹逼，问题是sort之后index
	//就不对了，所以得先复制这个数组，然后sort了这个复制的数组，找到答案，再去原来的数组找对应
	//数字的index。由于题目说了答案是唯一的，所以一旦找到了就是对的index
	public int[] twoSum(int[] numbers, int target) {
		
		int[] num=new int[numbers.length];
		for(int i=0;i<numbers.length;i++){
			num[i]=numbers[i];
		}
		int behind=num.length-1;
		int front=0;
		Arrays.sort(num);
		
		int[] ii=new int[2];
		int[] rs=new int[2];
		while(front < behind){
			
			if(num[front]+num[behind]==target){
			 ii[0]=num[front];
			 ii[1]=num[behind];
				break;
			}
			
			else if(num[front]+num[behind]>target){
				behind--;
			}
			else{
				front++;
			}
			
		}
		boolean flag=false;//要这个boolean的原因是如0,2,3,0找0这个例子，如果每个i都判断这
		//那么答案出来就是4,4，因为最后一个数是0，等于ii里存的数，所以要用这个boolean来记录，如果
		//找到了第一个出现的，那么以后就不用找了
		for(int i=0;i<numbers.length;i++){
			if(numbers[i]==ii[0]&&flag==false){
				rs[0]=i+1;
				flag=true;
				continue;
			}
			if(numbers[i]==ii[1]){
				rs[1]=i+1;
			}
		}
		if(rs[0]>rs[1]){//由于题目说了答案应该是升序，所以要这个判断一下，不是升序的话就调转过来
			int temp=rs[0];
			rs[0]=rs[1];
			rs[1]=temp;
		}
		return rs;

	}

}
