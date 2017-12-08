//http://blog.csdn.net/linhuanmars/article/details/21314059
//http://blog.csdn.net/salutlu/article/details/21077835
//http://blog.csdn.net/magisu/article/details/14515209

public class MaximumSubarray {
	
	
	
	
	
	//自己想了好久才想出来的，不伦不类的，不是brute force。可能算dp吧，但是没有dp的感觉！！
	//还是用了O（n）的空间，他们都是用两个变量就行了
	//感觉就是要仔细找题目的独有规律才写出来的（就是值把当前值大于0的算开口，把小于0的前面都切掉）
	public int maxSubArray(int[] A) {
		if(A.length==0){
			return 0;
		}
		int[] dp=new int[A.length];
		int max=A[0];
		dp[0]=A[0];
		for(int i=0;i<A.length;i++){
			if(A[i]>0&&dp[i-1]<0){//如果当前A[i]大于0并且前面的dp小于0，说明要把前面的去掉
				//从这个大于0的开始继续找最大子序
				dp[i]=A[i];
				if(dp[i]>max){
					max=dp[i];
				}
			}else{//否则的话（即当前A[i]小于0或者dp[i-1]大于0），就算当前的A[i]值小于0，还是把当前
				//A[i]和dp加一起（有可能当前A[i]是个很大的负数，搞得整个都是负数了），
				//看这个值大还是A[i]大
				dp[i]=Math.max(dp[i-1]+A[i],A[i]);
				if(dp[i]>max){
					max=dp[i];
				}
			}
		}
		return max;
	}
	//拿-2,9，-1，-1，-1，-1,5，-3，-1这个例子和-2,1，-3,4，-1,2,1，-5,4考虑一下
	public int maxSubArray2(int[] A) {//他们这个这么简单就行了，真他妈神奇
		if(A.length==0){
			return 0;
		}
		int local=A[0];
		int glob=A[0];
		for(int i=1;i<A.length;i++){
			local=Math.max(local+A[i], A[i]);//关键在这步，这里local只能从local+A[i]和A[i]中
			//选大的，local+A[i]意味着他必须把他下一个值（就算是负数）加进来，否则就选A[i]
			glob=Math.max(local, glob);
		}
		return glob;
	}
	//第三次
	public int maxSubArray3(int[] A) {
		int loc=A[0];
		int glo=loc;
		for(int i=1;i<A.length;i++){
			loc=Math.max(loc+A[i], A[i]);//就是这里是0还是A[i]记不清楚
			glo=Math.max(glo, loc);
		}
		return glo;
	}

}
