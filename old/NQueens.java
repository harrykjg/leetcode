import java.util.ArrayList;
//http://blog.csdn.net/linhuanmars/article/details/20667175
//这题就叫回溯法了
//http://www.2cto.com/kf/201311/256634.html
public class NQueens {
	//code ganker的代码，思路其实就是每一行每一个位置去试着放q，在验证符合条件否，然后继续或停止
	//只是他实现的时候比较巧妙省了空间（只用一位数组去存q的位置）
	
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
	    if(row == n)  //比如n=4，到了这步则代表第0,1,2,3行都符合了条件，开始构造答案的格式
	    {  
	        String[] item = new String[n];  
	        for(int i=0;i<n;i++)  
	        {  
	            StringBuilder strRow = new StringBuilder();  
	            for(int j=0;j<n;j++)  
	            {  
	                if(columnForRow[i]==j)  //即如果在遍历整个board的时候，发现地i行第j列
	                	//中j的列数和保存q位置的columnForRow数组里所保存的q的位置的列数相同时
	                	//即代表q在这
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
	        columnForRow[row] = i;  //这个columnForRow很巧妙，他储存了q所在的位置
	        //columnForRow[row]=i即q在第row行第i列
	        if(check(row,columnForRow))//如果ok则开始填下一行，否则就继续试当前行的下个位置  
	        {  
	            helper(n,row+1,columnForRow,res);  
	        } 
	        else{
	        	columnForRow[row] = 0;//注意这个else其实不用写，因为这个位置总会在下个for循坏
	        	//时更新，所以不用担心他会保存不符合条件的值
	        }
	    }  
	}  
	private boolean check(int row, int[] columnForRow)  
	{  
	    for(int i=0;i<row;i++)  //搞了几行就测几行
	    {   //columnForRow[row]==columnForRow[i]即检测当前行（第row行）q所在的列数是否和
	    	//第0到row行中q所在的列数相同
	    	//Math.abs(columnForRow[row]-columnForRow[i])==row-i检测了q所在行的列数减去0到row行
	    	//中q所在列的值，是否等于其行数相减的差值，简单来说即看行的差值是否等于列的差值，等于
	    	//的话即在一个对角线上
	        if(columnForRow[row]==columnForRow[i] || Math.abs(columnForRow[row]-columnForRow[i])==row-i)  
	            return false;  //注意这里没有检测同一行中有没有多个queen，因为他构造的时候就是
	        //每一行只加入一个queen就跳到下一行的
	    }  
	    return true;  
	}
	//算第三轮写，调试了好几次，细节问题很多
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
