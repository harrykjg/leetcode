//动态规划，只需从前往后扫，更新一个变量即可
public class JumpGmae {
	public boolean canJump(int[] A) {
		if (A.length == 0) {
			return false;
		}
		if(A.length==1){
			return true;
		}
		return go(A,0);

	}
	public boolean go(int[]A, int b){
		for(int i=b;i<A.length;i++){
			int step=A[i];
			if(step==0){
				 break;
			}
			if(i+step>=A.length-1){
				return true;
			}
			for(int j=step;j>0;j--){
				if(go(A,i+j)){
					return true;
				}
			}
			
		}
		return false;
	}

}
