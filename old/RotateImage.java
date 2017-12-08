

//http://www.2cto.com/kf/201401/274473.html
//http://blog.csdn.net/aivin24/article/details/24345263

public class RotateImage {
	//也是看了别人的方法才想到的
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
		//他们的方法更简洁一点，只要一个变量我这里要两个，想法是这样的：
		//例：1 2 3
//		      4 5 6
//		      7 8 9
//		因为要把1位置的元素移到3，所以先用temp1保存3，把1移过去之后，因为要把3移到9那，所以
		//用temp2记录9，然后3移到9，然后再用temp1记录7，再把9移到7，然后再把7移到1那
		//而他们的是这样的：先保存1，然后把7移到1，然后把9移到7原来的位置，此时覆盖它就无所谓了，
		//然后再把3移到9原来的位置，再把temp即1移到3原来的位置，搞定
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
//第二次写
	public void rotate2(int[][] matrix) {
		
	
		for(int i=0;i<matrix.length/2;i++){
			for(int j=i;j<matrix.length-1-i;j++){//注意，比如一行有4个元素，值交换3个就够了
				swap(matrix,i,j);   
			}
		}
	}
	public void swap(int[][] m,int i,int j){//i和j代表行和列
		//发现了要换和被换的两个节点的坐标关系是:(i,j)变成(j,n-i)
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
