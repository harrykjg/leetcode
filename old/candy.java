
//http://blog.csdn.net/lanxu_yy/article/details/17752273 ������������
//http://blog.csdn.net/kenden23/article/details/14003169 ������������
//http://jixiangsanbao.wordpress.com/2014/07/26/candy/  ����֪�������Ҹ�ɨһ��
public class candy {

	public static void main(String[] args) {
		
		int[] x={4,2,3,4,1};
		candy xx=new candy();
		System.out.println(xx.candy2(x));
		
	}

	public int candy(int[] ratings) {

	
		int[] can = new int[ratings.length];
		int sum=0;

		if (ratings.length == 1) {
			return 1;
		}
		if (ratings.length == 0) {
			return 0;
		}

		for (int i = 0; i < can.length; i++) {
			can[i] = 1;
		}

		for (int i = 0; i < can.length-1; i++) {//��ǰһ������һ�������Ӻ���
			if (ratings[i] < ratings[i + 1] && can[i] >= can[i+1]) {
				can[i+1]=can[i]+1;
			}	
		}
		//�Ӻ���ǰ������ǰ��
		
		for(int k=can.length-1;k>0;k--){
			if(ratings[k]<ratings[k-1]&&can[k-1]<=can[k]){
				can[k-1]=can[k]+1;
			}
		}
		
		for(int i=0;i<can.length;i++){
			sum+=can[i];
		}
		return sum;

	}
	//�ڶ���д���������ǵ�˼·�ͼ�������֮�����õ�.��1,2,3,4,5,5,10,3,2,8,9���������
	public int candy2(int[] ratings) {
		if(ratings.length==0){
			return 0;
		}
		int[] dp=new int[ratings.length];
		dp[0]=1;
		for(int i=0;i<dp.length-1;i++){
			if(ratings[i]<ratings[i+1]){
				dp[i+1]=dp[i]+1;
			}else{
				dp[i+1]=1;
			}
		}
		for(int i=dp.length-2;i>=0;i--){
			if(ratings[i]>ratings[i+1]&&dp[i]<=dp[i+1]){//���￪ʼʱ����dp[i]<=dp[i+1]���������
				                           //��4,2,3,4,1�Ծ�֪���ˣ���Ϊ���dp�ĵ�i���������ͱȵ�
				dp[i]=dp[i+1]+1;           //i+1����ֵ��Ļ����Ͳ��ù��ˣ������Ҫ��1
			}
		}
		int rs=0;
		for(int i=0;i<dp.length;i++){
			rs+=dp[i];
		}
		return rs;
	}
//�����֣����Ǽǵ�˼·.��Ҫ�ǵ�����ratingһ���Ļ����������Բ�ͬ��
	public int candy3(int[] ratings) {
		if(ratings.length==0){
			return 0;
		}
		int[] dp=new int[ratings.length];
		dp[0]=1;
		int count=0;
		
		for(int i=1;i<ratings.length;i++){
			if(ratings[i]>ratings[i-1]){
				dp[i]=dp[i-1]+1;
			}else{
				dp[i]=1;
			}
		}
		for(int i=ratings.length-2;i>=0;i--){//�ڶ���ѭ��Ҫ�ǣ�
			if(ratings[i]>ratings[i+1]&&dp[i]<=dp[i+1]){//dp[i]<=dp[i+1]����©
				dp[i]=dp[i+1]+1;
			}//Ҳ����else��
		}
		for(int i=0;i<dp.length;i++){
			count+=dp[i];
		}
		return count;
	}
}
