//http://blog.csdn.net/linhuanmars/article/details/24066199
//居然这样都被他想得出来


public class SetMatrixZeroes {
	//思想就是把矩阵的第一行和第一列作为存放的一个空间，存放的是他对应列和行上是否有0的信息
	public void setZeroes(int[][] matrix) {  
	    if(matrix==null || matrix.length==0 || matrix[0].length==0)  
	        return;  
	    boolean rowFlag = false;  
	    boolean colFlag = false;  
	    for(int i=0;i<matrix.length;i++)//检测第一行和第一列是否有0  
	    {  
	        if(matrix[i][0]==0)  
	        {  
	            colFlag = true;  
	            break;  
	        }  
	    }  
	    for(int i=0;i<matrix[0].length;i++)  
	    {  
	        if(matrix[0][i]==0)  
	        {  
	            rowFlag = true;  
	            break;  
	        }  
	    }        
	    for(int i=1;i<matrix.length;i++)  //然后从除去第一行第一列开始遍历
	    {  
	        for(int j=1;j<matrix[0].length;j++)  
	        {  
	            if(matrix[i][j]==0)  
	            {  //即如果某一点点是0的话，就把矩阵的的第一行和第一列的对应点设为0，这样不会影响
	            	//这第一行和第一列的别的数据，应为如果有m-1*n-1的矩阵中如果有0，那么其对应
	            	//的第一行和第一列的那个点最后也得设为0
	                matrix[i][0] = 0;  
	                matrix[0][j] = 0;  
	            }  
	        }  
	    }  
	    for(int i=1;i<matrix.length;i++)  //然后按着第一行和第一列记录的信息，把行和列设为零
	    {                                //这个也写的巧妙，不用单独看一行或者一列是否为0然后再把
	        for(int j=1;j<matrix[0].length;j++)  //该行和列设为0，而是直接把行列都扫完，遇到对应
	        {                                 //行或列为0的就设当前格为0
	            if(matrix[i][0]==0 || matrix[0][j]==0)  
	                matrix[i][j] = 0;  
	        }  
	    }  
	    if(colFlag)  
	    {  
	        for(int i=0;i<matrix.length;i++)  
	        {  
	            matrix[i][0] = 0;  
	        }  
	    }  
	    if(rowFlag)  
	    {  
	        for(int i=0;i<matrix[0].length;i++)  
	        {  
	            matrix[0][i] = 0;  
	        }  
	    }  
	}  
//第三轮大概思路记得，没写

	//九月29，再想一次一位只要一行记录就行了，其实还要一列记录，否则当列有不止一个0时，无法记录这些
	//0的所在行，所以要列来记录行的位置
}
