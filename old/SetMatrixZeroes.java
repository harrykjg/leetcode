//http://blog.csdn.net/linhuanmars/article/details/24066199
//��Ȼ������������ó���


public class SetMatrixZeroes {
	//˼����ǰѾ���ĵ�һ�к͵�һ����Ϊ��ŵ�һ���ռ䣬��ŵ�������Ӧ�к������Ƿ���0����Ϣ
	public void setZeroes(int[][] matrix) {  
	    if(matrix==null || matrix.length==0 || matrix[0].length==0)  
	        return;  
	    boolean rowFlag = false;  
	    boolean colFlag = false;  
	    for(int i=0;i<matrix.length;i++)//����һ�к͵�һ���Ƿ���0  
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
	    for(int i=1;i<matrix.length;i++)  //Ȼ��ӳ�ȥ��һ�е�һ�п�ʼ����
	    {  
	        for(int j=1;j<matrix[0].length;j++)  
	        {  
	            if(matrix[i][j]==0)  
	            {  //�����ĳһ�����0�Ļ����ͰѾ���ĵĵ�һ�к͵�һ�еĶ�Ӧ����Ϊ0����������Ӱ��
	            	//���һ�к͵�һ�еı�����ݣ�ӦΪ�����m-1*n-1�ľ����������0����ô���Ӧ
	            	//�ĵ�һ�к͵�һ�е��Ǹ������Ҳ����Ϊ0
	                matrix[i][0] = 0;  
	                matrix[0][j] = 0;  
	            }  
	        }  
	    }  
	    for(int i=1;i<matrix.length;i++)  //Ȼ���ŵ�һ�к͵�һ�м�¼����Ϣ�����к�����Ϊ��
	    {                                //���Ҳд��������õ�����һ�л���һ���Ƿ�Ϊ0Ȼ���ٰ�
	        for(int j=1;j<matrix[0].length;j++)  //���к�����Ϊ0������ֱ�Ӱ����ж�ɨ�꣬������Ӧ
	        {                                 //�л���Ϊ0�ľ��赱ǰ��Ϊ0
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
//�����ִ��˼·�ǵã�ûд

	//����29������һ��һλֻҪһ�м�¼�����ˣ���ʵ��Ҫһ�м�¼���������в�ֹһ��0ʱ���޷���¼��Щ
	//0�������У�����Ҫ������¼�е�λ��
}
