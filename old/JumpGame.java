//http://blog.csdn.net/linhuanmars/article/details/21354751
public class JumpGame {
	//自己写的会TLE，要用动态规划
	public static void main(String[] args) {
		JumpGame jg=new JumpGame();
		int[] a={1,0,2};
		System.out.println(jg.canJump(a));
	}
	//我这个会超时
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
	//第二次还写不出来
	 public boolean canJump2(int[] A) {
		 if (A.length == 0) {
				return false;
			}
		 int can=A[0];
		 //就是维护一个最大值，这个for循环之后，看这个最大值是否大于A.lengt-1就行了
		 for(int i=0;i<A.length&&i<=can;i++){//注意这里容易漏i<=can这个条件
			 can=Math.max(i+A[i], can);
		 }
		 if(can<A.length-1){
			 return false;
		 }
		 return true;
		 
	 }
	 //第三次还得调试2次才行
	 public boolean canJump3(int[] A) {
		 if (A.length == 0) {
				return false;
			}
		 if (A.length == 1) {//开始少了这个判断，就算只有一个元素，该元素是0也算
				return true;
			}
		 int can=A[0];
		 
		 for(int i=0;i<A.length&&i<=can;i++){
			 can=Math.max(i+A[i], can);
			 if(can>=A.length-1){//开始少了等号
				 return true;
			 }
		 }
		
		 return false;
		 
	 }

}
