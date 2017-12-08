//http://www.cnblogs.com/jdflyfly/p/3810741.html  他和我会写的方法是一样的想法，都是两个for循环
//遍历棋盘，而code ganker是自己调用自己那种写法
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
						//此回溯法关键点在这里，在填了当前点之后，先验证当填了前点之后能否成立
						//并且还要再去填下一个空位，看该下一个空是否成立，成立了才能说明当前空成立
						//而在填下一个空时，他自己又会去验证下下个空，如果不成立则把当前填了的
						//空再设为'.'然后试下一个k。真他妈神奇
						if(valid(board,i,j)&&solveSudoku(board)){
							return true;
						}
						board[i][j]='.';
					}
					return false;//如果试了9个数字都不成立的话，就说明无解，返回false。如果有解
					//的话会进入里面那个if里返回true了
				}
			}
		}
		return true;
	}
	//就是检测该点所在的行，列，3*3小方格里是否成立
	public boolean valid(char[][] b,int x,int y){
		char value=b[x][y];
		for(int i=0;i<9;i++){
			if(b[i][y]==value&&i!=x){//检测y这一列
				return false;
			}
		}
		for(int i=0;i<9;i++){
			if(b[x][i]==value&&i!=y){//检测x这一行
				return false;
			}
		}
		
		for(int i=x/3*3;i<x/3*3+3;i++){
			for(int j=y/3*3;j<y/3*3+3;j++){//注意这里和valid Sudoku里的不同了，那个是用i来判断位置
				//的，这里直接有了x和y，就可直接判断所在的3*3小格了
				if(b[i][j]==value&&x!=i&&y!=j){
					return false;
				}
			}
		}
		return true;
		
	}
	//第二次写，基本思路是想得出来的，但是由于他是个返回true or false的，再结合回溯法，还是像不太
	//清楚应该在怎么返回，还是得用第一次那种方法返回。以下代码不行的
	public boolean solveSudoku2(char[][] board) {
		if(board.length!=9||board[0].length!=9){
			return false;
		}
		return slove(0,0,board);
		
	}//我这里本来是想做个改进，前面是每一层都从board的第一个元素开始搜，而我想把他改成从当前valid的
	//下一个点开始进入dfs。目测行不通，比如3,3，valid了，那么下层是从row是3，col是3开始，那比如
	//这层3,4valid了，那么再下层就是从3,4开始，一直到了3,8，假如也valid了，那么下层col开始是8，
	//3,8这点已经存在，那么8+1=9，则跳出循环，注意col还是8，则下一次开始试从4,8开始，就不是从4,0开始
	//了，所以会错
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
//第三轮,写的不太顺，主要是slove2里的return false 和true那，最后还得return true可能是填完了最后
	//一个，那么就直接最外层for循环都进不去了，所以return true
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
