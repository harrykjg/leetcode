

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
}
