
import java.util.LinkedList;
//http://blog.csdn.net/doc_sgl/article/details/11832965
//http://blog.csdn.net/linhuanmars/article/details/24444491
//http://www.cnblogs.com/easonliu/p/3657489.html
//http://www.cnblogs.com/jdflyfly/p/3815885.html
public class MaximalRectangle {
	public static void main(String[] args) {
		MaximalRectangle mr=new MaximalRectangle();
		char[][] m={{'1','0'},{'0','1'}};
		
		System.out.println(mr.maximalRectangle(m));
	}
	
	
	

	public int maximalRectangle(char[][] matrix) {
		if(matrix.length==0){
			return 0;
		}
		int max=0;
		int[] a=new int[matrix[0].length];
		for(int i=0;i<matrix.length;i++){ 
			for(int j=0;j<matrix[0].length;j++){//��ÿһ�ж���largestֱ��ͼ������һ�ԣ�������
				if(matrix[i][j]=='1'){        //������matrix��ת��1�У������Ǵ�ģ���Ϊ1������
					a[j]+=1;                 //�������ġ���ÿһ����Ϊһ��ֱ��ͼ�Ļ�������ȷ��1
				}else{                     //�Ǵ�ֱ�����ġ���������1ʱ���ͰѸ��е�ֵ�ӵ�������
					a[j]=0;                //�е�ֵ���������0����Ϊ�������ˣ�
				}
			}
			max=Math.max(max, find(a));
		}
		return max;

	}
	private int find(int[] a){//�������lagrestrectangleinhistogram��ԭ����
		LinkedList<Integer> st=new LinkedList<Integer>();
		int max=0;
		for(int i=0;i<a.length;i++){
			if(st.isEmpty()||a[st.peek()]<=a[i]){
				st.push(i);
			}else{
				while(!st.isEmpty()&&a[i]<a[st.peek()]){
					int t=a[st.pop()];
					int area=t*(st.isEmpty()?i:(i-st.peek()-1));
					
					max=Math.max(max, area);
				}
				st.push(i);
			}
		}
		while(!st.isEmpty()){
			int t=a[st.pop()];
			int area=t*(st.isEmpty()?a.length:(a.length-st.peek()-1));
			max=Math.max(max, area);
		}
		return max;	
	}

}
