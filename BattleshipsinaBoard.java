/**
 * Created by 502575560 on 6/25/17.
 */
public class BattleshipsinaBoard {
    //题目不怎么看得懂,意思是一个棋盘,战船可以横着放或者竖着放,横竖方向和连着的X就是一条船(单个x也是一条),横竖穿之间至少隔着一个点
    //自己想就是遍历数组,遇到一个X就dfs把这条穿所有的x够变成点,居然还写错了,把rs++的位置写在dfs里了,当时没有想清楚就提交了
    //貌似有要求要不鞥改变原数组,那就用一个visited的二维数组记录已访问过的点,进阶要求见他们的解法二

    //https://www.cnblogs.com/grandyang/p/5979207.html 聪明的解法
    //http://blog.csdn.net/camellhf/article/details/52871104
    //http://blog.csdn.net/xiangwanpeng/article/details/53033819

    int rs=0;
    public int countBattleships(char[][] board) {
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(board[i][j]=='.'){
                    continue;
                }else{
                    dfs(board,i,j);
                    rs++;
                }
            }
        }
        return rs;

    }
    void dfs(char[][] b,int x,int y){
        b[x][y]='.';
        if(x-1>=0&&b[x-1][y]=='X'){
            dfs(b,x-1,y);
        }
        if(y+1<b[0].length&&b[x][y+1]=='X'){
            dfs(b,x,y+1);
        }
        if(x+1<b.length&&b[x+1][y]=='X'){
            dfs(b,x+1,y);
        }
        if(y-1>=0&&b[x][y-1]=='X'){
            dfs(b,x,y-1);
        }

    }
    //9/29/2018,用的是聪明的解法，见第一个链接
    public int countBattleships2(char[][] board) {
        int rs=0;
        for(int i=0;i<board.length;i++){
            for(int j=0;j<board[0].length;j++){
                if(board[i][j]=='X'){
                    if(i>0&&board[i-1][j]=='X'){
                        continue;
                    }
                    if(j>0&&board[i][j-1]=='X'){
                        continue;
                    }
                    rs++;
                }
            }
        }
        return rs;
    }
}
