//http://blog.csdn.net/linhuanmars/article/details/20888505
//http://jixiangsanbao.wordpress.com/2014/05/06/trapping-rain-water/吉祥这个可以看懂，但还是神奇
//http://blog.csdn.net/okiwilldoit/article/details/23266495 这个比较好理解
public class TrappingRainWater {
	
	public static void main(String[] args) {
		TrappingRainWater tr=new TrappingRainWater();
		int[] a={2,0,2};
				
		System.out.println(tr.trap(a));
		}
	
	int count=0;//写的啥玩意我都看不懂，要急的话还是记第一个那个的方法，吉祥的和他复杂度一样
	public int trap(int[] A) {
		if(A.length==0){
			return 0;
		}
		int b=-1;
		int e=0;
		for(int i=0;i<A.length;i++){
			if(A[i]>=0&&b==-1&&((i+1<=A.length-1&&A[i+1]<A[i])||i==A.length-1)){
				b=i;
				int j=i+1;
				int max=0;
				for(;j<A.length;j++){
					if(A[j]>=1&&A[j]>max){
						max=A[j];
						e=j;
						if(A[j]>=A[b]+1){
							break;
						}
					}
				}
				if(e!=0){
					cal(A,b,e);
					b=-1;
					i=e-1;
					e=0;
					
				}
			}
		}
		return count;
		

	}
	public void cal(int[] A,int b,int e){
		if(e-b<=1){
			return;
		}
		int low=A[b];
		if(A[e]<low){
			low=A[e];
		}
		int sq=low*(e-b-1);
		int minus=0;
		for(int i=b+1;i<e;i++){
			if(A[i]<=low){
				minus+=A[i];
			}else if(A[i]>low){
				minus+=low;
			}
		}
		count+=sq-minus;
		return;
	}

}
