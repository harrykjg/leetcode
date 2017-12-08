//http://blog.csdn.net/linhuanmars/article/details/24216235#comments
//他这个两个二分法写在1个方法里，而且不是迭代的，牛逼
public class Searcha2DMatrix {
	//是自己写出来的，思路就是用两次二分搜索，第一次搜第一列的所有数，确定target可能存在的行数
	//然后再进入该行，再用二分搜索
	
	public static void main(String[] args) {
		Searcha2DMatrix sm=new Searcha2DMatrix();
		int[][] m=new int[1][1];
		m[0][0]=1;
		sm.searchMatrix(m, 2);
	}
	

	public boolean searchMatrix(int[][] matrix, int target) {
		
		if(matrix.length==0){
			return false;
		}
		int len=matrix.length;
		return bin1(matrix,0,len-1,target);

	}
	
	public boolean bin1(int[][] m,int b,int e,int target){
		if(e<0){
			return false;
		}
		if(b==m.length){
			return bin2(m,b-1,0,m[0].length-1,target);
		}
		int mid=(b+e)/2;
		if(m[mid][0]==target){
			return true;
		}
		if(m[mid][0]>target){
			if(mid-1>=0&&m[mid-1][0]<target){
				return bin2(m,mid-1,0,m[0].length-1,target);
			}
			return bin1(m,b,mid-1,target);
		}
		else{
			if(mid+1<m.length&&m[mid+1][0]>target){
				return bin2(m,mid,0,m[0].length-1,target);
			}
			return bin1(m,mid+1,e,target);
		}
	}
	public boolean bin2(int[][] m,int index,int b,int e,int target){
		if(b>e){
			return false;
		}
		int mid=(b+e)/2;
		if(m[index][mid]==target){
			return true;
		}
		if(m[index][mid]>target){
			return bin2(m,index,b,mid-1,target);
		}
		else{
			return bin2(m,index,mid+1,e,target);
		}
		
	}

}
