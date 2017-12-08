import java.util.ArrayList;
//http://blog.csdn.net/linhuanmars/article/details/21667181
//����˼���Ǻ�rotate image�ı�������һ���ģ�������㣬Ȼ��ڶ���ӵڶ���λ�ÿ�ʼ

//ˬ�����Լ���Ĺ��ˣ����Ż���code ganker�ļ򵥣����������Ҫһ����ά�����memo
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
		//˼·��������������߾����ң��������£��ڷ��������ڷ�������
		//���������Ļ�������⣬Ҫע�����ҵ������Ҫ�ڣ�1���������Ǹ����Ѿ������ʹ��ˡ�2��
		//�������ǵ�һ��
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
//code ganker�Ĵ���
	public ArrayList<Integer> spiralOrder2(int[][] matrix) {  
	    ArrayList<Integer> res = new ArrayList<Integer>();  
	    if(matrix == null || matrix.length==0 || matrix[0].length==0)  
	        return res;  
	    int min = Math.min(matrix.length, matrix[0].length);  
	    int levelNum = min/2;//��ͼ��֪��ֻҪ����С���Ǹ�����2��֮1�ξ���  
	    for(int level=0;level<levelNum;level++)  
	    {  //ÿ�����������У��ұߵ��У�������У���ߵ�����forѭ�������
	    	//ע�������Ǽ���1�ģ�������ʵ�������е�ʱ���Ǵ��󵽵����ڶ����ң�Ȼ���ٴ��ϵ������ڶ�
	    	//���£��ٴ��ҵ������ڶ������ٴ��µ������ڶ�����
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
	    {  //����ǵ����Ļ�����ͼ��֪���Ҫ��ʣ�µ���һ�л�����Ū����
	        if(matrix.length < matrix[0].length)  
	        {  //���������С�������Ļ������ʣ������
	            for(int i=levelNum; i<matrix[0].length-levelNum;i++)  
	            {  
	                res.add(matrix[levelNum][i]);  
	            }  
	        }  
	        else  
	        {  //���������С���еĻ������ʣ������
	            for(int i=levelNum; i<matrix.length-levelNum;i++)  
	            {  
	                res.add(matrix[i][levelNum]);  
	            }  
	        }  
	    }  
	    return res;  
	}  
	//�����֣�����û��ȫ�����code ganker������ô����һ�������������¼�ġ���Ҫ��forѭ�����к���
	//������ϸ��û���

	//9/29 ˼����OK�ľ���ϸ��index�����״�
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
