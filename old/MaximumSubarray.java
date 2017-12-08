//http://blog.csdn.net/linhuanmars/article/details/21314059
//http://blog.csdn.net/salutlu/article/details/21077835
//http://blog.csdn.net/magisu/article/details/14515209

public class MaximumSubarray {
	
	
	
	
	
	//�Լ����˺þò�������ģ����ײ���ģ�����brute force��������dp�ɣ�����û��dp�ĸо�����
	//��������O��n���Ŀռ䣬���Ƕ�������������������
	//�о�����Ҫ��ϸ����Ŀ�Ķ��й��ɲ�д�����ģ�����ֵ�ѵ�ǰֵ����0���㿪�ڣ���С��0��ǰ�涼�е���
	public int maxSubArray(int[] A) {
		if(A.length==0){
			return 0;
		}
		int[] dp=new int[A.length];
		int max=A[0];
		dp[0]=A[0];
		for(int i=0;i<A.length;i++){
			if(A[i]>0&&dp[i-1]<0){//�����ǰA[i]����0����ǰ���dpС��0��˵��Ҫ��ǰ���ȥ��
				//���������0�Ŀ�ʼ�������������
				dp[i]=A[i];
				if(dp[i]>max){
					max=dp[i];
				}
			}else{//����Ļ�������ǰA[i]С��0����dp[i-1]����0�������㵱ǰ��A[i]ֵС��0�����ǰѵ�ǰ
				//A[i]��dp��һ���п��ܵ�ǰA[i]�Ǹ��ܴ�ĸ���������������Ǹ����ˣ���
				//�����ֵ����A[i]��
				dp[i]=Math.max(dp[i-1]+A[i],A[i]);
				if(dp[i]>max){
					max=dp[i];
				}
			}
		}
		return max;
	}
	//��-2,9��-1��-1��-1��-1,5��-3��-1������Ӻ�-2,1��-3,4��-1,2,1��-5,4����һ��
	public int maxSubArray2(int[] A) {//���������ô�򵥾����ˣ�����������
		if(A.length==0){
			return 0;
		}
		int local=A[0];
		int glob=A[0];
		for(int i=1;i<A.length;i++){
			local=Math.max(local+A[i], A[i]);//�ؼ����ⲽ������localֻ�ܴ�local+A[i]��A[i]��
			//ѡ��ģ�local+A[i]��ζ�������������һ��ֵ�������Ǹ������ӽ����������ѡA[i]
			glob=Math.max(local, glob);
		}
		return glob;
	}
	//������
	public int maxSubArray3(int[] A) {
		int loc=A[0];
		int glo=loc;
		for(int i=1;i<A.length;i++){
			loc=Math.max(loc+A[i], A[i]);//����������0����A[i]�ǲ����
			glo=Math.max(glo, loc);
		}
		return glo;
	}

}
