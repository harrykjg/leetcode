

//http://www.2cto.com/kf/201401/274473.html
//http://blog.csdn.net/aivin24/article/details/24345263

public class RotateImage {
	//Ҳ�ǿ��˱��˵ķ������뵽��
	public static void main(String[] args) {
		RotateImage ri=new RotateImage();
		int[][] m=new int[2][2];
		m[0][0]=1;
		m[0][1]=2;
		m[1][0]=3;
		m[1][1]=4;
		ri.rotate(m);
				
	}

	public void rotate(int[][] matrix) {
		int len=matrix.length;
		int temp1=0;
		int temp2=0;
		int temp3=0;
		//���ǵķ��������һ�㣬ֻҪһ������������Ҫ�������뷨�������ģ�
		//����1 2 3
//		      4 5 6
//		      7 8 9
//		��ΪҪ��1λ�õ�Ԫ���Ƶ�3����������temp1����3����1�ƹ�ȥ֮����ΪҪ��3�Ƶ�9�ǣ�����
		//��temp2��¼9��Ȼ��3�Ƶ�9��Ȼ������temp1��¼7���ٰ�9�Ƶ�7��Ȼ���ٰ�7�Ƶ�1��
		//�����ǵ��������ģ��ȱ���1��Ȼ���7�Ƶ�1��Ȼ���9�Ƶ�7ԭ����λ�ã���ʱ������������ν�ˣ�
		//Ȼ���ٰ�3�Ƶ�9ԭ����λ�ã��ٰ�temp��1�Ƶ�3ԭ����λ�ã��㶨
		//
		for(int i=0;i<len/2;i++){
			for(int j=i;j<matrix.length-1-i;j++){
				temp1=matrix[j][len-1-i];
				matrix[j][len-1-i]=matrix[i][j];
				temp2=matrix[len-1-i][len-1-j];
				matrix[len-1-i][len-1-j]=temp2;
				temp3=matrix[len-1-j][i];
				matrix[len-1-j][i]=temp2;
				matrix[i][j]=temp1;
			}
		}

	}
//�ڶ���д
	public void rotate2(int[][] matrix) {
		
	
		for(int i=0;i<matrix.length/2;i++){
			for(int j=i;j<matrix.length-1-i;j++){//ע�⣬����һ����4��Ԫ�أ�ֵ����3���͹���
				swap(matrix,i,j);   
			}
		}
	}
	public void swap(int[][] m,int i,int j){//i��j�����к���
		//������Ҫ���ͱ����������ڵ�������ϵ��:(i,j)���(j,n-i)
		int n=m.length-1;
		
		int t1=m[j][n-i];
		m[j][n-i]=m[i][j];
		
		int t2=m[n-i][n-j];
		m[n-i][n-j]=t1;
		
		int t3=m[n-j][i];
		m[n-j][i]=t2;
		
		m[i][j]=t3;
	}
}
