//http://blog.csdn.net/linhuanmars/article/details/20748171
public class ValidSudoku {
	
	public boolean isValidSudoku(char[][] board) {
		if(board==null||board.length!=9||board[0].length!=9){
			return false;
		}
		for(int i=0;i<9;i++){//��֤ÿһ��3*3С��
			boolean[] memo=new boolean[9];
			for(int j=i/3*3;j<i/3*3+3;j++){//i��0��2ʱ�ǵ�һ�е�3��3*3С��
				for(int k=i%3*3;k<i%3*3+3;k++){//j�����У�k�����У���ϸ����Ҫ,������i��Ū��
					if(board[j][k]!='.'){
						if(memo[board[j][k]-'1']){
							return false;
						}
						memo[board[j][k]-'1']=true;
					}
				}
			}
		}
		
		for(int i=0;i<9;i++){//��֤ÿһ��
			boolean[] memo=new boolean[9];
			for(int j=0;j<9;j++){
				if(board[i][j]!='.'){
					if(memo[board[i][j]-'1']){
						return false;
					}
					memo[board[i][j]-'1']=true;
				}
			}
		}
		for(int i=0;i<9;i++){//��֤ÿһ��
			boolean[] memo=new boolean[9];
			for(int j=0;j<9;j++){
				if(board[j][i]!='.'){
					if(memo[board[j][i]-'1']){
						return false;
					}
					memo[board[j][i]-'1']=true;
				}
			}
		}
		return true;

	}

}
