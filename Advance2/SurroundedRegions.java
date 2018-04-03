package Advance2;

/**
 * Created by 502575560 on 7/18/17.
 */
public class SurroundedRegions {
    public static void main(String[] args){
        //x x x x
        //x o x x
        //x x o x
        //x x o x
        SurroundedRegions sr=new SurroundedRegions();
        char[][] b=new char[5][5];



        b[0][0]='O';
        b[0][1]='X';
        b[0][2]='X';
        b[0][3]='O';
        b[0][4]='X';

        b[1][0]='X';
        b[1][1]='O';
        b[1][2]='O';
        b[1][3]='X';
        b[1][4]='O';

        b[2][0]='X';
        b[2][1]='O';
        b[2][2]='X';
        b[2][3]='X';
        b[2][4]='X';

        b[3][0]='O';
        b[3][1]='X';
        b[3][2]='O';
        b[3][3]='O';
        b[3][4]='X';

        b[4][0]='X';
        b[4][1]='X';
        b[4][2]='O';
        b[4][3]='X';
        b[4][4]='O';

        sr.surroundedRegions(b);
    }
    //这次想都没想出来怎么做,看回code ganker的才知道
    //http://blog.csdn.net/linhuanmars/article/details/22904855
    //https://segmentfault.com/a/1190000007010346 unionfind的解法,还有有几个同类题的解法,这题套unionfind这个确实有点生硬
    int[] indentrow=new int[]{-1,0,1,0};
    int[] indentcol=new int[]{0,1,0,-1};
    //第一轮写的代码lintcode过得了，leetcode过不了的，[["O","O","O"],["O","O","O"],["O","O","O"]]这个case就挂了，还有别的case也过不了，所以我直接删了code了，
    //应该是因为我自己没抄对，第二个链接的代码是对的
    int row, col;
    public void surroundedRegions(char[][] board) {//union find思路就是遍历board,发现O在四边上的话,就把这个点的根设为dummy(就是用union方法)

        if (board == null || board.length == 0) return;
        row = board.length;
        col = board[0].length;
        UnionFind uf = new UnionFind(row*col+1);
        int dummy = row*col;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (board[i][j] == 'O') {
                    if (i == 0 || i == row-1 || j == 0 || j == col-1) uf.union(node(i, j), dummy);
                    else {
                        if (i > 0 && board[i-1][j] == 'O') uf.union(node(i, j), node(i-1, j));
                        if (i > 0 && board[i+1][j] == 'O') uf.union(node(i, j), node(i+1, j));
                        if (j > 0 && board[i][j-1] == 'O') uf.union(node(i, j), node(i, j-1));
                        if (j > 0 && board[i][j+1] == 'O') uf.union(node(i, j), node(i, j+1));
                    }
                }
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (uf.isConnected(node(i, j), dummy)) board[i][j] = 'O';
                else board[i][j] = 'X';
            }
        }
    }
    public int node(int i, int j) {
        return i*col+j;
    }

//4/1/2018九章第二轮，unionfind感觉还是比以前的方法麻烦一点，改了挺久，union方法有点变形
    public void surroundedRegions2(char[][] board) {
        if(board.length == 0 || board[0].length == 0){
            return;
        }
        int[] ids=new int[board.length*board[0].length+1];//开始以为ids不用加一位，dummy用-1就行了，其实不行，因为find的时候要用到ids【-1】就outbound exception了
        for(int i=0;i<ids.length;i++){
            ids[i]=i;
        }
        int dummy=board.length*board[0].length;
        for(int i=0;i<board[0].length;i++){
            if(board[0][i]=='O'){
                ids[i]=dummy;
            }
            if(board[board.length-1][i]=='O'){
                ids[board[0].length*(board.length-1)+i]=dummy;
            }
        }
        for(int i=0;i<board.length;i++){
            if(board[i][0]=='O'){
                ids[i*board[0].length]=dummy;
            }
            if(board[i][board[0].length-1]=='O'){
                ids[i*board[0].length+board[0].length-1]=dummy;
            }
        }
        for(int i=1;i<board.length-1;i++){
            for(int j=1;j<board[0].length-1;j++){
                if(board[i][j]=='O'){
                    if (board[i-1][j] == 'O') union(ids,i*board[0].length+j, (i-1)*board[0].length+j);
                    if ( board[i][j+1] == 'O') union(ids,i*board[0].length+j, i*board[0].length+j+1);
                    if (board[i+1][j] == 'O') union(ids,i*board[0].length+j, (i+1)*board[0].length+j);
                    if (board[i][j-1] == 'O') union(ids,i*board[0].length+j, i*board[0].length+j-1);
                }
            }
        }
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(find(ids,i*board[0].length+j)!=dummy){
                    board[i][j]='X';
                }
            }
        }
        return;

    }
    void union(int[] ids,int a,int b){
        int root1=find(ids,a);
        int root2=find(ids,b);
        if(root1!=root2){
            if(root1==ids.length-1){//这个union是看root1和root2谁是dummy，则另一个改成dummy才能accept，但是之前的不用这样写，应该是因为我这是先扫四条边设置了dummy，
                ids[root2]=ids.length-1; //这样就导致内部的'O'有可能把四条边上的o的根节点改了，所以我这才要看谁是dummy。而第二个链接的写法是直接遍历整个board，关键是
            }else if(root2==ids.length-1){//union a和b点的时候，是把a的根节点的根节点update成b了，而不是直接ids[a]=b,我之前就这样写就错了
                ids[root1]=ids.length-1;
            }else{
                ids[root2]=root1;
            }
        }
    }
    int find(int[] ids,int x){
        if(ids[x]==x){
            return x;
        }
        return ids[x]=find(ids,ids[x]);
    }

}
class UnionFind{
    int[] parents;
    public UnionFind(int n) {
        parents = new int[n];
        for (int i = 0; i < n; i++) parents[i] = i;
    }
    public void union(int n1, int n2) {
        int r1 = find(n1);
        int r2 = find(n2);
        if (r1 != r2) parents[r1] = r2;
    }
    public int find(int node) {
        if (parents[node] == node) return node;
        parents[node] = find(parents[node]);
        return parents[node];
    }
    public boolean isConnected(int n1, int n2) {
        return find(n1) == find(n2);
    }
}
