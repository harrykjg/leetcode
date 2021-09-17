import java.util.HashSet;

/**
 * Created by 502575560 on 6/30/16.
 */
public class SudokuSolver {
    //2016年2月29还是很难写出来
    public void solveSudoku(char[][] b) {

        slove(b);

    }
    public boolean slove(char[][] b){
        for(int i=0;i<b.length;i++)
        {
            for(int j=0;j<b[0].length;j++){
                if(b[i][j]=='.'){
                    for(char k=1;k<=9;k++){
                        b[i][j]=(char)(k+'0');
                        if(check(b,i,j)){
                            if(slove(b)){
                               return true;
                            }
                        }
                    }
                    b[i][j]='.';
                    return false;
                }
            }
        }
        return true;
    }

    public boolean check(char[][] b,int row,int col){
        for(int i=0;i<9;i++){
            if(b[row][i]==b[row][col]&&col!=i){
                return false;
            }
            if(b[i][col]==b[row][col]&&row!=i){
                return false;
            }
        }
        for(int i=row/3*3;i<row/3*3+3;i++){
           for(int j=col/3*3;j<col/3*3+3;j++){
               if(b[i][j]==b[row][col]&&(i!=row||j!=col)){
                   return false;
               }
           }
        }

        return true;
    }

    //9/16/2021 没看清题目说肯定是有解的，因此判断是否valid的时候只需要判断那一行和列。还有没有想清楚到底怎么样判断整个sudo填完了，其实也不需要明确判断
    //i，j到最后，for循环直接run完就完了.还有想着要用row和col定位一下想着不用每次都从头开始找第一个'。'，结果最多只能用row，col就不行了，col肯定得从0开始
    public void solveSudoku2(char[][] b) {
        dfs(0,b);//貌似用了row还是比不用row是快一点的
    }
    boolean dfs(int row,char[][] b){
        for (int i=row;i<9;i++){
            for(int j=0;j<9;j++){
                if(b[i][j]=='.'){
                    for(char k=1;k<=9;k++){
                        b[i][j]=(char)(k+'0');
                        if(valid(i,j,b)){
                           if (dfs(i,b)){
                               return true;
                           }
                        }
                    }
                    b[i][j]='.';
                    return false;
                }
            }
        }
        return true;
    }
    boolean valid(int row,int col,char[][] b){
        for (int i=0;i<9;i++){
            if(i!=col&&b[row][col]==b[row][i]){
                return false;
            }
            if(i!=row&&b[row][col]==b[i][col]){
                return false;
            }
        }
        for (int i=row/3*3;i<row/3*3+3;i++){
            for (int j=col/3*3;j<col/3*3+3;j++){
                if ((i!=row||j!=col)&&b[i][j]==b[row][col]){//注意这里是i！=row或j！=col，而不是&&
                    return false;
                }
            }
        }
        return true;
    }


}
