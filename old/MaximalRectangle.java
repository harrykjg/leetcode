
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
			for(int j=0;j<matrix[0].length;j++){//是每一行都用largest直方图方法试一试，而不是
				if(matrix[i][j]=='1'){        //把整个matrix都转成1行，那样是错的，因为1可能是
					a[j]+=1;                 //不连续的。而每一行作为一个直方图的话，即可确保1
				}else{                     //是垂直连续的。当该行是1时，就把该列的值加等上面那
					a[j]=0;                //行的值，否则就是0（因为不连续了）
				}
			}
			max=Math.max(max, find(a));
		}
		return max;

	}
	private int find(int[] a){//这个就是lagrestrectangleinhistogram的原方法
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
