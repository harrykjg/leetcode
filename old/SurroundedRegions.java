import java.util.LinkedList;
//http://blog.csdn.net/linhuanmars/article/details/22904855
//http://jixiangsanbao.wordpress.com/2014/08/03/surrounded-regions/

public class SurroundedRegions {
	public static void main(String[] args) {
		SurroundedRegions sr=new SurroundedRegions();
		char[][] b=new char[4][4];
		b[0][0]='O';
		b[0][1]='O';
		b[0][2]='O';
		b[0][3]='O';
		b[1][0]='O';
		b[1][1]='O';
		b[1][2]='O';
		b[1][3]='O';
		b[2][0]='O';
		b[2][1]='O';
		b[2][2]='O';
		b[2][3]='O';
		b[3][0]='O';
		b[3][1]='O';
		b[3][2]='O';
		b[3][3]='O';
		sr.solve2(b);
		System.out.println();
	}
	//��������memo��û���ٶȺ���һ������������Ϊ�����bfs����dfsĳ���㣬��ô�������������ĵ�
	//���Ѿ����任��T�ˣ����Ե��´��������õ�ʱ�����Ѿ�����'O'�ˣ�����Ҳ������ȥ���
    public void solve(char[][] board) {
    	 if(board.length == 0 || board[0].length == 0){ 
    		 return; 
    		 }
		boolean[][] memo=new boolean[board.length][board[0].length];
         for(int i=0;i<board.length;i++){
        	 for(int j=0;j<board[0].length;j++){
        		 if(i==0&&memo[i][j]==false&&board[i][j]=='O'){
        			 search(i,j,board,memo);
        			 continue;
        		 }
        		 if(j==board[0].length-1&&memo[i][j]==false&&board[i][j]=='O'){
        			 search(i,j,board,memo);
        			 continue;
        		 }
        		 if(i==board.length-1&&memo[i][j]==false&&board[i][j]=='O'){
        			 search(i,j,board,memo);
        			 continue;
        		 }
        		 if(j==0&&memo[i][j]==false&&board[i][j]=='O'){
        			 search(i,j,board,memo);
        			 continue;
        		 }
        	 }
         }
         
         for(int i=0;i<board.length;i++){
        	 for(int j=0;j<board[0].length;j++){
        		 if(board[i][j]=='O'){
        			 board[i][j]='X';
        			 continue;
        		 }
        		 if(board[i][j]=='T'){
        			 board[i][j]='O';
        		 }
        	 }
         }
    }
	//���search��dfs����stack over flow��Ҫ����bfs����
	public void search(int i,int j,char[][] b,boolean[][] memo){
		b[i][j]='T';
		memo[i][j]=true;
		if(i-1>=0&&b[i-1][j]=='O'&&memo[i-1][j]==false){
			search(i-1,j,b,memo);
		}
		if(j+1<b[0].length&&b[i][j+1]=='O'&&memo[i][j+1]==false){
			search(i,j+1,b,memo);
		}
		if(i+1<b.length&&b[i+1][j]=='O'&&memo[i+1][j]==false){
			search(i+1,j,b,memo);
		}
		if(j-1>=0&&b[i][j-1]=='O'&&memo[i][j-1]==false){
			search(i,j-1,b,memo);
		}
	}
	
	 public void solve2(char[][] board) {
    	 if(board.length == 0 || board[0].length == 0){ 
    		 return; 
    		 }
		boolean[][] memo=new boolean[board.length][board[0].length];
         for(int i=0;i<board.length;i++){
        	 for(int j=0;j<board[0].length;j++){
        		 if(i==0&&board[i][j]=='O'&&memo[i][j]==false){
        			 search2(i,j,board,memo);
        			 continue;
        		 }
        		 if(j==board[0].length-1&&board[i][j]=='O'&&memo[i][j]==false){
        			 search2(i,j,board,memo);
        			 continue;
        		 }
        		 if(i==board.length-1&&board[i][j]=='O'&&memo[i][j]==false){
        			 search2(i,j,board,memo);
        			 continue;
        		 }
        		 if(j==0&&board[i][j]=='O'&&memo[i][j]==false){
        			 search2(i,j,board,memo);
        			 continue;
        		 }
        	 }
         }
         
         for(int i=0;i<board.length;i++){
        	 for(int j=0;j<board[0].length;j++){
        		 if(board[i][j]=='O'){
        			 board[i][j]='X';
        			 continue;
        		 }
        		 if(board[i][j]=='T'){
        			 board[i][j]='O';
        		 }
        	 }
         }
    }
	//�����õ�bfs��
	public void search2(int i,int j,char[][]b, boolean[][] memo){
		
		if(b[i][j]!='O')  
	        return;
		b[i][j]='T';
		memo[i][j]=true;//����memoҲһ��

		LinkedList<SRpair> q=new LinkedList<SRpair>();
		q.add(new SRpair(i,j));
		while(!q.isEmpty()){
			SRpair sp=q.poll();
			int ii=sp.i;
			int jj=sp.j;
			b[ii][jj]='T';
			if(ii-1>=0&&b[ii-1][jj]=='O'&&memo[ii-1][jj]==false){
				q.add(new SRpair(ii-1,jj));//�������Լ�д�Ļ���������д�ģ���д����û������
				b[ii-1][jj]='T';//�����еģ���ֻ�ѵ�ǰq���ó����ĵ���T�������ǰ���һ��
				memo[ii-1][jj]=true;//���ڵĵ㶼���T�ˣ�������accept���������ͻᳬʱ��
				//��֪��Ϊʲô
			}
			if(jj+1<b[0].length&&b[ii][jj+1]=='O'&&memo[ii][jj+1]==false){
				q.add(new SRpair(ii,jj+1));
				b[ii][jj+1]='T';
				memo[ii][jj+1]=true;
			}
			if(ii+1<b.length&&b[ii+1][jj]=='O'&&memo[ii+1][jj]==false){
				q.add(new SRpair(ii+1,jj));
				b[ii+1][jj]='T';
				memo[ii+1][jj]=true;
			}
			if(jj-1>=0&&b[ii][jj-1]=='O'&&memo[ii][jj-1]==false){
				q.add(new SRpair(ii,jj-1));
				b[ii][jj-1]='T';
				memo[ii][jj-1]=true;
		    }

		}
	}
}
class SRpair{
	int i;
	int j;
	SRpair(int i,int j){
		this.i=i;
		this.j=j;
	}
}
