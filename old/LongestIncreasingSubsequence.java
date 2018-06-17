//经典动态规划题,最长上升子序
//http://www.cnblogs.com/dolphin0520/archive/2011/07/09/2102044.html
//http://blog.csdn.net/wangkechuang/article/details/7949151
public class LongestIncreasingSubsequence {
	public static void main(String[] args) {
		LongestIncreasingSubsequence li=new LongestIncreasingSubsequence();
		int[] a={1,7,3,5,9,4,8};
		System.out.println(li.longest(a));
		
	}
	
	public int longest(int[] a){//这个是n平方的复杂度，还有nlogn的方法
		
		if(a.length<=0){
			return 0;
		}
		int [] dp=new int[a.length];
		dp[0]=1;
		
		int temp=0;
		for(int i=0;i<a.length;i++){
			temp=0;//这个temp的用处是记录从0到i-1的上升子序长度
			for(int j=0;j<i;j++){
				if(a[i]>a[j]&&dp[j]>temp){//当a[i]的值大于a[j]时，说明从0到a[i]的上升子序
					//肯定是从0到a[j]的上升子序长度加1，而在a[i]可能大于很多a0到a[i-1]的数，所以
					//ai处的最大上升子序是a0到a[i-1]中有最长子序的那个长度再加1，temp就是用来找
					//这个数的，注意这个temp是每次都在外层循环里清零了因为每次a[i]都不同
					temp=dp[j];
				}
			}
			dp[i]=temp+1;  //最后这个dp不是说最后一个就是最大值，而是存的是0到a[i]的最大上升子序
			//所以后面还要找出dp中的最大值返回
			
		}
		int rs=0;
		for(int i=0;i<dp.length;i++){
			if(dp[i]>rs){
				rs=dp[i];
			}
		}
		return rs;
		
	}
	

}
