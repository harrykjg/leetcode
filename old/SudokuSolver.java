//http://www.cnblogs.com/jdflyfly/p/3810741.html  �����һ�д�ķ�����һ�����뷨����������forѭ��
//�������̣���code ganker���Լ������Լ�����д��
//http://blog.csdn.net/linhuanmars/article/details/20748761
public class SudokuSolver {
	
	
	public static void main(String[] args) {
		char[][] board = { { '5', '3', '.', '.', '7', '.', '.', '.', '.' }, 
                { '6', '.', '.', '1', '9', '5', '.', '.', '.' }, { '.', '9', '8', '.', '.', '.', '.', '6', '.' }, 
                { '8', '.', '.', '.', '6', '.', '.', '.', '3' }, { '4', '.', '.', '8', '.', '3', '.', '.', '1' }, 
                { '7', '.', '.', '.', '2', '.', '.', '.', '6' }, { '.', '6', '.', '.', '.', '.', '2', '8', '.' }, 
                { '.', '.', '.', '4', '1', '9', '.', '.', '5' }, { '.', '.', '.', '.', '8', '.', '.', '7', '9' } };
		SudokuSolver ss=new SudokuSolver();
		ss.solveSudoku3(board);
		System.out.println(board);
		
	}
	
	
	
	public boolean solveSudoku(char[][] board) {
		
		if(board.length!=9||board[0].length!=9){
			return false;
		}
		for(int i=0;i<board.length;i++){
			for(int j=0;j<board[0].length;j++){
				if(board[i][j]=='.'){
					for(int k=1;k<=9;k++){
						board[i][j]=(char)('0'+k);
						//�˻��ݷ��ؼ�������������˵�ǰ��֮������֤������ǰ��֮���ܷ����
						//���һ�Ҫ��ȥ����һ����λ��������һ�����Ƿ�����������˲���˵����ǰ�ճ���
						//��������һ����ʱ�����Լ��ֻ�ȥ��֤���¸��գ������������ѵ�ǰ���˵�
						//������Ϊ'.'Ȼ������һ��k������������
						if(valid(board,i,j)&&solveSudoku(board)){
							return true;
						}
						board[i][j]='.';
					}
					return false;//�������9�����ֶ��������Ļ�����˵���޽⣬����false������н�
					//�Ļ�����������Ǹ�if�ﷵ��true��
				}
			}
		}
		return true;
	}
	//���Ǽ��õ����ڵ��У��У�3*3С�������Ƿ����
	public boolean valid(char[][] b,int x,int y){
		char value=b[x][y];
		for(int i=0;i<9;i++){
			if(b[i][y]==value&&i!=x){//���y��һ��
				return false;
			}
		}
		for(int i=0;i<9;i++){
			if(b[x][i]==value&&i!=y){//���x��һ��
				return false;
			}
		}
		
		for(int i=x/3*3;i<x/3*3+3;i++){
			for(int j=y/3*3;j<y/3*3+3;j++){//ע�������valid Sudoku��Ĳ�ͬ�ˣ��Ǹ�����i���ж�λ��
				//�ģ�����ֱ������x��y���Ϳ�ֱ���ж����ڵ�3*3С����
				if(b[i][j]==value&&x!=i&&y!=j){
					return false;
				}
			}
		}
		return true;
		
	}
	//�ڶ���д������˼·����ó����ģ������������Ǹ�����true or false�ģ��ٽ�ϻ��ݷ���������̫
	//���Ӧ������ô���أ����ǵ��õ�һ�����ַ������ء����´��벻�е�
	public boolean solveSudoku2(char[][] board) {
		if(board.length!=9||board[0].length!=9){
			return false;
		}
		return slove(0,0,board);
		
	}//�����ﱾ�����������Ľ���ǰ����ÿһ�㶼��board�ĵ�һ��Ԫ�ؿ�ʼ�ѣ�����������ĳɴӵ�ǰvalid��
	//��һ���㿪ʼ����dfs��Ŀ���в�ͨ������3,3��valid�ˣ���ô�²��Ǵ�row��3��col��3��ʼ���Ǳ���
	//���3,4valid�ˣ���ô���²���Ǵ�3,4��ʼ��һֱ����3,8������Ҳvalid�ˣ���ô�²�col��ʼ��8��
	//3,8����Ѿ����ڣ���ô8+1=9��������ѭ����ע��col����8������һ�ο�ʼ�Դ�4,8��ʼ���Ͳ��Ǵ�4,0��ʼ
	//�ˣ����Ի��
	private boolean slove(int row,int col,char[][] board){
		if(row>board.length||col>board[0].length){
			return false;
		}
		for(int i=row;i<board.length;i++){
			for(int j=col;j<board[0].length;j++){
				if(board[i][j]=='.'){
					for(int k=1;k<=9;k++){
						board[i][j]=(char)(k+'0');
						if(valid(board,i,j)&&slove(i,j,board)){
							return true;
						}
						board[i][j]='.';
					}
					return false;
				}
			}
		}
		return true;
	}
//������,д�Ĳ�̫˳����Ҫ��slove2���return false ��true�ǣ���󻹵�return true���������������
	//һ������ô��ֱ�������forѭ��������ȥ�ˣ�����return true
	public void solveSudoku3(char[][] board) {
		if(board.length!=9||board[0].length!=9){
			return ;
		}
		slove2(board);
		
	}
	private boolean slove2(char[][] b){
		
		for(int i=0;i<b.length;i++){
			for(int j=0;j<b[0].length;j++){
				if(b[i][j]=='.'){
					for(int k=1;i<=9;i++){
						b[i][j]=(char)('0'+k);
						if(valid2(b,i,j)&&slove2(b)){
							return true;
						}
						b[i][j]='.';
					}
					
					return false;
				}
				
			}
		}
		return true;
	}
	private boolean valid2(char[][] b,int row,int col){
		for(int i=0;i<b.length;i++){
			if(b[row][col]==b[i][col]&&i!=row){
				return false;
			}
			if(b[row][col]==b[row][i]&&i!=col){
				return false;
			}
		}
		for(int i=row/3*3;i<row/3*3+3;i++){
			for(int j=col/3*3;j<col/3*3+3;j++){
				if(b[row][col]==b[i][j]&&i!=row&&j!=col){
					return false;
				}
			}
		}
		return true;
	}
}
