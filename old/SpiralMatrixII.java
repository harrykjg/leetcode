public class SpiralMatrixII {

//http://blog.csdn.net/linhuanmars/article/details/21906331
	public static void main(String[] args) {
		SpiralMatrixII sm=new SpiralMatrixII();
		sm.generateMatrix2(3);
	}

	
	public int[][] generateMatrix(int n) {
		int[][] m=new int[n][n];
		if(n<=0){
			return m;
		}
		
		go(m,1,0,0);
		return m;

	}
	public void go(int[][]m,int v,int l,int r){
		if(m[l][r]==0){
			m[l][r]=v;
		}
		
		if((r+1<m[0].length&&m[l][r+1]==0&&l-1>=0&&m[l-1][r]!=0)||l==0&&r+1<m[0].length){
			go(m,v+1,l,r+1);
		}
		else if(l+1<m.length&&m[l+1][r]==0){
			go(m,v+1,l+1,r);
		}
		else if(r-1>=0&&m[l][r-1]==0){
			go(m,v+1,l,r-1);
		}
		else if(l-1>=0&&m[l-1][r]==0){
			go(m,v+1,l-1,r);
		}
	}
//第二次写，还是细节问题，n=0和1的时候要想好才行
	public int[][] generateMatrix2(int n){
		int[][] m=new int[n][n];
		int b=1;
		for(int i=0;i<=n/2;i++){
			for(int j=i;j<n-i;j++){
				m[i][j]=b;
				b++;
				if(b>n*n){
					return m;
				}
			}
			for(int j=i+1;j<n-i;j++){
				m[j][n-i-1]=b;
				b++;
				if(b>n*n){
					return m;
				}
			}
			for(int j=n-1-1-i;j>=i;j--){
				m[n-1-i][j]=b;
				b++;
				if(b>n*n){
					return m;
				}
			}
			for(int j=n-1-1-i;j>i;j--){
				m[j][i]=b;
				b++;
				if(b>n*n){
					return m;
				}
			}
			
		}
		
		return m;
		
	}
}
