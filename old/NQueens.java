import java.util.ArrayList;
//http://blog.csdn.net/linhuanmars/article/details/20667175
//����ͽл��ݷ���
//http://www.2cto.com/kf/201311/256634.html
public class NQueens {
	//code ganker�Ĵ��룬˼·��ʵ����ÿһ��ÿһ��λ��ȥ���ŷ�q������֤����������Ȼ�������ֹͣ
	//ֻ����ʵ�ֵ�ʱ��Ƚ�����ʡ�˿ռ䣨ֻ��һλ����ȥ��q��λ�ã�
	
	public static void main(String[] args) {
		NQueens nq=new NQueens();
		nq.solveNQueens2(2);
	}
	
	public ArrayList<String[]> solveNQueens(int n) {  
	    ArrayList<String[]> res = new ArrayList<String[]>();  
	    helper(n,0,new int[n], res);  
	    return res;  
	}  
	private void helper(int n, int row, int[] columnForRow, ArrayList<String[]> res)  
	{  
	    if(row == n)  //����n=4�������ⲽ������0,1,2,3�ж���������������ʼ����𰸵ĸ�ʽ
	    {  
	        String[] item = new String[n];  
	        for(int i=0;i<n;i++)  
	        {  
	            StringBuilder strRow = new StringBuilder();  
	            for(int j=0;j<n;j++)  
	            {  
	                if(columnForRow[i]==j)  //������ڱ�������board��ʱ�򣬷��ֵ�i�е�j��
	                	//��j�������ͱ���qλ�õ�columnForRow�������������q��λ�õ�������ͬʱ
	                	//������q����
	                    strRow.append('Q');  
	                else  
	                    strRow.append('.');  
	            }  
	            item[i] = strRow.toString();  
	        }  
	        res.add(item);  
	        return;  
	    }  
	    for(int i=0;i<n;i++)  
	    {  
	        columnForRow[row] = i;  //���columnForRow�������������q���ڵ�λ��
	        //columnForRow[row]=i��q�ڵ�row�е�i��
	        if(check(row,columnForRow))//���ok��ʼ����һ�У�����ͼ����Ե�ǰ�е��¸�λ��  
	        {  
	            helper(n,row+1,columnForRow,res);  
	        } 
	        else{
	        	columnForRow[row] = 0;//ע�����else��ʵ����д����Ϊ���λ���ܻ����¸�forѭ��
	        	//ʱ���£����Բ��õ������ᱣ�治����������ֵ
	        }
	    }  
	}  
	private boolean check(int row, int[] columnForRow)  
	{  
	    for(int i=0;i<row;i++)  //���˼��оͲ⼸��
	    {   //columnForRow[row]==columnForRow[i]����⵱ǰ�У���row�У�q���ڵ������Ƿ��
	    	//��0��row����q���ڵ�������ͬ
	    	//Math.abs(columnForRow[row]-columnForRow[i])==row-i�����q�����е�������ȥ0��row��
	    	//��q�����е�ֵ���Ƿ��������������Ĳ�ֵ������˵�����еĲ�ֵ�Ƿ�����еĲ�ֵ������
	    	//�Ļ�����һ���Խ�����
	        if(columnForRow[row]==columnForRow[i] || Math.abs(columnForRow[row]-columnForRow[i])==row-i)  
	            return false;  //ע������û�м��ͬһ������û�ж��queen����Ϊ�������ʱ�����
	        //ÿһ��ֻ����һ��queen��������һ�е�
	    }  
	    return true;  
	}
	//�������д�������˺ü��Σ�ϸ������ܶ�
	public ArrayList<String[]> solveNQueens2(int n) {  
		int[] row=new int[n];
		ArrayList<String[]> a=new ArrayList<String[]>();
		go(row,0,a,n);
		return a;
	}
	private void go(int[] row,int b,ArrayList<String[]> a,int n){
		if(b>row.length-1){
			
			String s[]=new String[n];
			for(int j=0;j<n;j++){
				StringBuilder sb=new StringBuilder();
				for(int i=0;i<n;i++){
					if(row[j]==i){
						sb.append("Q");
					}else{
						sb.append(".");
					}
				}
				s[j]=sb.toString();
			}
			a.add(s);
			return;
		}
		for(int i=0;i<row.length;i++){
			row[b]=i;
			if(check2(row,b)){
				go(row,b+1,a,n);
			}
			
		}
		
	}
	private boolean check2(int[] row,int b){
		
		for(int i=0;i<b;i++){
			if(row[i]==row[b]&&i!=b){
				return false;
			}
            if(b-i==Math.abs(row[b]-row[i])){
				return false;
			}
		}
		return true;
	}

}
