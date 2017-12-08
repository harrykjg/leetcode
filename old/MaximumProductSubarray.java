//http://blog.csdn.net/whuwangyi/article/details/39577455
//http://blog.csdn.net/linhuanmars/article/details/39537283
//
public class MaximumProductSubarray {
         public int maxProduct(int[] A) {
		if(A.length<=0) return 0;
		if(A.length==1) return A[0];
		int curMax = A[0];
		int curMin = A[0];
		int ans = A[0];
		for(int i=1;i<A.length;i++){
			int temp = curMin *A[i];
			curMin = Math.min(A[i], Math.min(temp, curMax*A[i]));
			curMax = Math.max(A[i], Math.max(temp, curMax*A[i]));
			ans = Math.max(ans, curMax);
		}
		return ans;
	}
}
