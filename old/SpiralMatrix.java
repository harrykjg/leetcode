import java.util.ArrayList;
//http://blog.csdn.net/linhuanmars/article/details/21667181
//他的思想是和rotate image的遍历方法一样的，先最外层，然后第二层从第二个位置开始

//爽啊，自己想的过了，看着还比code ganker的简单，但是我这个要一个二维数组的memo
public class SpiralMatrix {
	public static void main(String[] args) {
		SpiralMatrix sm=new SpiralMatrix();
		int[][] m={{1,2,3,4,5},{8,9,10,11,12},{15,16,17,18,19}};
		
		sm.spiralOrder2(m);
	}
	
    ArrayList<Integer> al=new ArrayList<Integer>();
	public ArrayList<Integer> spiralOrder(int[][] matrix) {
		
		if(matrix.length==0){
			return al;
		}
		boolean[][] memo=new boolean[matrix.length][matrix[0].length];
		go(matrix,memo,0,0);
		return al;

	}
	public void go(int[][] m,boolean[][]memo,int l,int r){
		if(memo[l][r]==false){
			al.add(m[l][r]);
			memo[l][r]=true;
		}
		//思路就是如果能往右走就往右，否则往下，在否则往左，在否则往上
		//但是这样的话会出问题，要注意往右的情况是要在：1，其上面那个点已经被访问过了。2，
		//或者这是第一行
		if((r+1<m[0].length&&memo[l][r+1]==false&&(l==0||(l-1>=0&&memo[l-1][r]==true)))){
			go(m,memo,l,r+1);
		}
		else if(l+1<m.length&&memo[l+1][r]==false){
			go(m,memo,l+1,r);
		}
		else if(r-1>=0&&memo[l][r-1]==false){
			go(m,memo,l,r-1);
		}
		else if(l-1>=0&&memo[l-1][r]==false){
			go(m,memo,l-1,r);
		}
	}
//code ganker的代码
	public ArrayList<Integer> spiralOrder2(int[][] matrix) {  
	    ArrayList<Integer> res = new ArrayList<Integer>();  
	    if(matrix == null || matrix.length==0 || matrix[0].length==0)  
	        return res;  
	    int min = Math.min(matrix.length, matrix[0].length);  
	    int levelNum = min/2;//画图可知，只要遍历小的那个数的2分之1次就行  
	    for(int level=0;level<levelNum;level++)  
	    {  //每层里，把上面的行，右边的列，下面的行，左边的列用for循环搞出来
	    	//注意这里是减了1的，就是其实他遍历行的时候，是从左到倒数第二个右，然后再从上到倒数第二
	    	//个下，再从右到倒数第二个左，再从下到倒数第二个上
	        for(int i=level;i<matrix[0].length-level-1;i++)  
	        {  
	            res.add(matrix[level][i]);  
	        }  
	        for(int i=level;i<matrix.length-level-1;i++)  
	        {  
	            res.add(matrix[i][matrix[0].length-1-level]);  
	        }  
	        for(int i=matrix[0].length-1-level;i>level;i--)  
	        {  
	            res.add(matrix[matrix.length-1-level][i]);  
	        }  
	        for(int i=matrix.length-1-level;i>level;i--)  
	        {  
	            res.add(matrix[i][level]);  
	        }  
	    }  
	    if(min%2==1)  
	    {  //如果是单数的话，画图可知最后还要把剩下的那一行或者列弄出来
	        if(matrix.length < matrix[0].length)  
	        {  //如果是行数小于列数的话，最后剩的是行
	            for(int i=levelNum; i<matrix[0].length-levelNum;i++)  
	            {  
	                res.add(matrix[levelNum][i]);  
	            }  
	        }  
	        else  
	        {  //如果是列数小于行的话，最后剩的是列
	            for(int i=levelNum; i<matrix.length-levelNum;i++)  
	            {  
	                res.add(matrix[i][levelNum]);  
	            }  
	        }  
	    }  
	    return res;  
	}  
	//第三轮，还是没完全记清楚code ganker的是怎么遍历一个环不用数组记录的。主要是for循环把行和列
	//遍历的细节没想好

	//9/29 思想是OK的就是细节index很容易错
	public ArrayList<Integer> spiralOrder3(int[][] matrix) {  
		ArrayList<Integer> a=new ArrayList<Integer>();
		if(matrix.length==0){
			return a;
		}
		
		int lev=Math.min(matrix.length, matrix[0].length)/2;
		for(int i=0;i<lev;i++){
			for(int j=0+i;j<matrix[0].length-i;j++){
				a.add(matrix[i][j]);
				}
			for(int j=1+i;j<matrix.length-i-1;j++){
				a.add(matrix[j+1][matrix[0].length-i]);
				}
			for(int j=matrix[0].length-i-1-1;j>=i;j--){
				a.add(matrix[matrix.length-i-1][j]);
				}
			for(int j=matrix.length-i-1-1;j>i;j--){
				a.add(matrix[j][i]);
				}
			
			}
		
	
	if(Math.min(matrix.length, matrix[0].length)%2!=0){
		if(matrix.length>matrix[0].length){
			for(int i=lev;i<=matrix.length-lev-1;i++){
				a.add(matrix[i][lev]);
			}
		}else{
			for(int i=lev;i<=matrix[0].length-lev-1;i++){
				a.add(matrix[lev][i]);
			}
		}
		
	}
	return a;
	}
}
