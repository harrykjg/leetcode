public class NQueensII {
	
	//Ū��nqueens��һ�����Ҳ�Ͷ���
	public static void main(String[] args) {
		NQueensII nq=new NQueensII();
		nq.totalNQueens(2);
	}

	int count = 0;

	public int totalNQueens(int n) {
		if (n <= 0) {
			return 0;
		}
		int[] row=new int[n];
		go(n,row,0);
		return count;

	}
	public void go(int n,int[] row,int rownum){
		if(rownum==n){
			count++;
			return;
		}
		for(int i=0;i<n;i++){
			row[rownum]=i;
			if(valid(row,rownum)){
				go(n,row,rownum+1);
			}else{
				row[rownum]=0;
			}
		}
	}
	public boolean valid(int []row,int rownum){
		
		for(int i=0;i<=rownum;i++){//������ʵд��i<rownum�Ͳ���дi��=rownum���������
			if(i!=rownum&&row[rownum]==row[i]){
				return false;
			}
			
			if(i!=rownum&&rownum-i==Math.abs(row[rownum]-row[i])){//����Сϸ��ע��һ�£�
				//rownum�ǿ϶����ڵ���i�ģ����Ǻ���������Ͳ�һ���ˣ����Ժ�����abs���������Ļ�
				//����
				return false;
			}

		}
		return true;
	}
	//�����֣�һ�ι�����Ϊ������nqueen1
	int count2=0;
     public int totalNQueens2(int n) {
        int[] row=new int[n];
        go(row,0);
        return count2;
    }
     private void go(int[] row,int b){
    	 if(b>row.length-1){
    		 count2++;
    		 return;
    	 }
    	 for(int i=0;i<row.length;i++){
    		 row[b]=i;
    		 if(check(row,b)){
    			 go(row,b+1);
    		 }
    	 }
     }
     private boolean check(int[] row,int b){
    	 for(int i=0;i<b;i++){
    		 if(row[i]==row[b]){
    			 return false;
    		 }
    		 if(b-i==Math.abs(row[b]-row[i])){
    			 return false;
    		 }
    	 }
    	 return true;
     }

}
