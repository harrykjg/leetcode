import java.util.HashSet;

//http://www.cnblogs.com/panda_lin/p/first_missing_positive.html
//http://blog.csdn.net/linhuanmars/article/details/20884585
public class FirstMissingPostive {
	public static void main(String[] args) {
		FirstMissingPostive fm=new FirstMissingPostive();
		int[] a={1,1};
		System.out.println(fm.firstMissingPositive2(a));
		
	}
	 public int firstMissingPositive(int[] A) {
		 
		 if(A.length==0){
			 return 1;
		 }
		 if(A.length==1&&A[0]!=1){
			 return 1;
		 }
		 for(int i=0;i<A.length;i++){
			 if(A[i]==i+1){
				 continue;
			 }
			 //��3, 4, -1, 1�����whileͦ����ģ����while����������ֻ�е�i����Ϊ
			 //���������䷶Χ��������ķ�Χʱ�Ų������������Ҫ�ѵ�i������ֵһֱ�㵽����i+1Ϊֹ
			 while(A[i]>0&&A[i]-1<A.length){
				 int temp=A[A[i]-1];
				 A[A[i]-1]=A[i];//i=0ʱ�����3,4,3,1
				 A[i]=temp;//���-1,4,3,1
				 if(A[i]-1>=0&&A[i]-1<A.length&&A[A[i]-1]==A[i]){//����[1,1]�����
					 break;
				 }
			 }
		 }
		 for(int i=0;i<A.length;i++){
			 if(A[i]!=i+1){
				 return i+1;
			 }
		 }
		 return A.length+1;
	        
	    }
	 //�ڶ���û��д��дҲ�ǵÿ��𰸲���д
	 //������,��ʼ����ˣ������longest consecutive sequence�ˡ������뷨�Ǽǵõ�.���ǵ����˺ü���
	 public int firstMissingPositive2(int[] A) {
		 if(A.length==0){
			 return 1;
		 }
		 if(A.length==2&&A[1]==A[0]){//���if�������Ը�{1��,1}��{2,2}������ӵ�,��ʵ��дҲ�У�
			 return A[0]==1?2:1;//�����ܶԸ���
		 }
		 int i=0;
		 while(i<A.length){
			 if(A[i]-1==i||A[i]<0||A[i]-1>=A.length||A[i]-1<0){
				 i++;
				 continue;
			 }
			 
			 int temp=A[A[i]-1];
			 if(temp==A[i]){//������������Ը�2,2,2,2���ֵ���������Ǽ���������Ҫ��������ͬ�Ļ���
				 i++; //��ô����Ҳ�ǰ׻��������һ��2�͵ڶ���2������ô����֮��i=0���λ���ϻ���
				 continue;//�ü���������ô���Ǻ͵ڶ���2�����ͻ���ѭ��
			 }
			 A[A[i]-1]=A[i];
			 A[i]=temp;
			
		 }
		 for( i=0;i<A.length;i++){
			 if(A[i]!=i+1){
				 break;
			 }
		 }
		 return i+1;
	 }

}
