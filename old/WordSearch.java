public class WordSearch {
//�ﱤ�ˣ��Լ�д��һ��ͨ��
	public boolean exist(char[][] board, String word) {
		if(board.length==0||word.length()==0){
			return false;
		}
		//˼·����dfs�����ҵ�word�ĵ�һ���ַ���Ȼ��Ӹ��ַ���ʼ��չ
		boolean[][] memo=new boolean[board.length][board[0].length];
		for(int i=0;i<board.length;i++){
			for(int j=0;j<board[0].length;j++){
				if(board[i][j]==word.charAt(0)){
					memo[i][j]=true;
					if(find(board,memo,i,j,word,1)){
						return true;
					}
					memo[i][j]=false;
				}
			}
		}
		return false;
	}
	//�ϣ��ң��£�����չ�����в���
	public boolean find(char[][] m,boolean[][] memo,int i,int j, String word, int cur){
		if(cur==word.length()){
			return true;
		}
		if(i-1>=0&&memo[i-1][j]==false&&m[i-1][j]==word.charAt(cur)){
			memo[i-1][j]=true;
			if(find(m,memo,i-1,j,word,cur+1)){
				return true;
			}
			memo[i-1][j]=false;
		}
		if(j+1<m[0].length&&memo[i][j+1]==false&&m[i][j+1]==word.charAt(cur)){
			memo[i][j+1]=true;
			if(find(m,memo,i,j+1,word,cur+1)){
				return true;
			}
			memo[i][j+1]=false;
		}
		if(i+1<m.length&&memo[i+1][j]==false&&m[i+1][j]==word.charAt(cur)){
			memo[i+1][j]=true;
			if(find(m,memo,i+1,j,word,cur+1)){
				return true;
			}
			memo[i+1][j]=false;
		}
		if(j-1>=0&&memo[i][j-1]==false&&m[i][j-1]==word.charAt(cur)){
			memo[i][j-1]=true;
			if(find(m,memo,i,j-1,word,cur+1)){
				return true;
			}
			memo[i][j-1]=false;
		}
		return false;
	}
	//�ڶ���ûд
}
