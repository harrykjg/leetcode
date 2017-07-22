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
        char[][] b=new char[4][4];
        b[0][0]='X';
        b[0][1]='X';
        b[0][2]='X';
        b[0][3]='X';
        b[1][0]='X';
        b[1][1]='O';
        b[1][2]='X';
        b[1][3]='X';
        b[2][0]='X';
        b[2][1]='X';
        b[2][2]='O';
        b[2][3]='X';
        b[3][0]='X';
        b[3][1]='X';
        b[3][2]='O';
        b[3][3]='X';
        sr.surroundedRegions(b);
    }
    //这次想都没想出来怎么做,看回code ganker的才知道
    //http://blog.csdn.net/linhuanmars/article/details/22904855
    //https://segmentfault.com/a/1190000007010346 unionfind的解法,还有有几个同类题的解法,这题套unionfind这个确实有点生硬
    int[] indentrow=new int[]{-1,0,1,0};
    int[] indentcol=new int[]{0,1,0,-1};
    public void surroundedRegions(char[][] board) {//union find思路就是遍历board,发现O在四边上的话,就把这个点的根设为dummy(就是用union方法)
        // 如果不是四边上的O,并且这个点上下左右的点也是O的话,就把她们union. 全部board遍历完之后,所有的X的跟节点还是它自己(就是初始化时候的值),
        //所有的没有和边上的O连上的O被连在了一起,他们的跟节点就是某个O,所有的和边上O连上一起的O,他们的跟节点最终是dummy
        //然后在扫描一遍整个board,如果和dummy连在一起的话就说明要改成O,否则全都改成X
        if(board.length == 0 || board[0].length == 0){
            return;
        }
        UnionFind uf=new UnionFind(board.length*board[0].length+1);//这个+1有点巧妙,否则数组所有点都默认是ids[i]=i,然而我要一个特殊的点
        int dummy=board.length*board[0].length;     //标记边上的O,所以就取了+1,那么有多出一个dummy点作为特殊的点了
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                char c=board[i][j];
                if(c=='O'){//如果不是'O'直接不管了
                    if(i==0||i==board.length-1||j==0||j==board[0].length-1){//先找到四边上的O
                        uf.ids[i*board[0].length+j]=dummy;
                    }else {//如果是是里面的O,且
                        if (i > 0 && board[i-1][j] == 'O') uf.union(i*board[0].length+j, (i-1)*board[0].length+j);
                        if (i > 0 && board[i+1][j] == 'O') uf.union(i*board[0].length+j, (i+1)*board[0].length+j);
                        if (j > 0 && board[i][j-1] == 'O') uf.union(i*board[0].length+j, i*board[0].length+j-1);
                        if (j > 0 && board[i][j+1] == 'O') uf.union(i*board[0].length+j, i*board[0].length+j+1);
                    }//其实想回numberofisland2,那里没有写单独的union方法,而是直接把union方法的逻辑写在code里面了,只是写了find方法
                }
            }
        }
        for(int i=0;i<board.length;i++) {
            for (int j = 0; j < board[0].length; j++) {
                char c=board[i][j];
                if(uf.isConnected(i*board[0].length+j,dummy)){
                    board[i][j]='O';
                }else {
                    board[i][j]='X';
                }
            }
        }
    }

}
class UnionFind{
    int ids[];
    public UnionFind(int n){
        ids=new int[n];
        for(int i=0;i<n;i++){
            ids[i]=i;
        }
    }
    public void union(int p,int q){
        int idp=find(p);
        int idq=find(q);
        if(idp!=idq){
            ids[idp]=idq;
        }
    }
    public int find(int p){
        while (ids[p]!=p){
            p=ids[p];
        }
        return p;
    }
    boolean isConnected(int p,int q){
        return find(p)==find(q);
    }
}
