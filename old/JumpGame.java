//http://blog.csdn.net/linhuanmars/article/details/21354751
public class JumpGame {
	//�Լ�д�Ļ�TLE��Ҫ�ö�̬�滮
	public static void main(String[] args) {
		JumpGame jg=new JumpGame();
		int[] a={1,0,2};
		System.out.println(jg.canJump(a));
	}
	//������ᳬʱ
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
	//�ڶ��λ�д������
	 public boolean canJump2(int[] A) {
		 if (A.length == 0) {
				return false;
			}
		 int can=A[0];
		 //����ά��һ�����ֵ�����forѭ��֮�󣬿�������ֵ�Ƿ����A.lengt-1������
		 for(int i=0;i<A.length&&i<=can;i++){//ע����������©i<=can�������
			 can=Math.max(i+A[i], can);
		 }
		 if(can<A.length-1){
			 return false;
		 }
		 return true;
		 
	 }
	 //�����λ��õ���2�β���
	 public boolean canJump3(int[] A) {
		 if (A.length == 0) {
				return false;
			}
		 if (A.length == 1) {//��ʼ��������жϣ�����ֻ��һ��Ԫ�أ���Ԫ����0Ҳ��
				return true;
			}
		 int can=A[0];
		 
		 for(int i=0;i<A.length&&i<=can;i++){
			 can=Math.max(i+A[i], can);
			 if(can>=A.length-1){//��ʼ���˵Ⱥ�
				 return true;
			 }
		 }
		
		 return false;
		 
	 }

}
