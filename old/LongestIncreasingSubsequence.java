//���䶯̬�滮��,���������
//http://www.cnblogs.com/dolphin0520/archive/2011/07/09/2102044.html
//http://blog.csdn.net/wangkechuang/article/details/7949151
public class LongestIncreasingSubsequence {
	public static void main(String[] args) {
		LongestIncreasingSubsequence li=new LongestIncreasingSubsequence();
		int[] a={1,7,3,5,9,4,8};
		System.out.println(li.longest(a));
		
	}
	
	public int longest(int[] a){//�����nƽ���ĸ��Ӷȣ�����nlogn�ķ���
		
		if(a.length<=0){
			return 0;
		}
		int [] dp=new int[a.length];
		dp[0]=1;
		
		int temp=0;
		for(int i=0;i<a.length;i++){
			temp=0;//���temp���ô��Ǽ�¼��0��i-1���������򳤶�
			for(int j=0;j<i;j++){
				if(a[i]>a[j]&&dp[j]>temp){//��a[i]��ֵ����a[j]ʱ��˵����0��a[i]����������
					//�϶��Ǵ�0��a[j]���������򳤶ȼ�1������a[i]���ܴ��ںܶ�a0��a[i-1]����������
					//ai�����������������a0��a[i-1]�����������Ǹ������ټ�1��temp����������
					//������ģ�ע�����temp��ÿ�ζ������ѭ������������Ϊÿ��a[i]����ͬ
					temp=dp[j];
				}
			}
			dp[i]=temp+1;  //������dp����˵���һ���������ֵ�����Ǵ����0��a[i]�������������
			//���Ժ��滹Ҫ�ҳ�dp�е����ֵ����
			
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
