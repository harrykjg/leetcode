package SomeInterviews.roblox;

public class CandyCrush723 {
    static void main() {
        int[][] b={{110,5,112,113,114},{210,211,5,213,214},{310,311,3,313,314},
                {410,411,412,5,414},{5,1,512,3,3},{610,4,1,613,614},{710,1,2,713,714},
                {810,1,2,1,1},{1,1,2,2,2},{4,1,4,4,1014}};
        CandyCrush723.candyCrush(b);
    }

    //
     /*
       1
       1 1 1这样的上面的1是不会被消掉的
      */
    //问题1：如何找到联通块，先垂直找？然后怎么看左右去不去?因为只有大于等于三个才才能过去。
    //2：现在你找到了要crush的块，现在怎么消掉？存在List<List<int[]>> 里？遍历所有要消的方格[i,j]然后把[0-i）的方格往下挪？但是这样不efficient，因为假如是垂直的几个格子，
    //应该一块减，而不是一个一个减。那样应该要预处理一下这些要消掉的格子，扫每一列，把这一列的高度算一下？也挺麻烦的
    //看答案，问题1的话就直接遍历board的时候用while只看右边和下面，这样拐弯的也会由遍历board的下一行开始被遍历到，关键是把这些可以消掉的点的值设为其负数
    //相当于标记了是要被删除的。问题2：基于问题1，那么问题2就好做一点了，但是还是有钱巧妙，从下到上，从左到右遍历board，设一个row=最后一行，
    // 然后遇到的数只要是正数就放到当前column的row那里，然后row--，遇到负数就不挪这个数，然后完成之后，如果row不==0说明有数字crush了，因此再把[0-row]
    //的值设成0，挺巧妙的
    public static int[][] candyCrush(int[][] board) {

        boolean found=true;
        while (found){
           found=false;
           //找能crush的块
            for (int i=0;i<board.length;i++){
                for (int j=0;j<board[0].length;j++){
                    int val=Math.abs(board[i][j]);//这里也很恶心，如果不写abs的话，可能会上一列往右扩的时候把这里改成负数了，现在到这里你要往下扩，负数就不行了
                    if(val==0){
                        continue;
                    }
                    int col=j;
                    for (int k=j+1;k<board[0].length;k++){
                        if (Math.abs(board[i][k])==val){//注意这里要abs，想像一下如果之前一行，往下扫的时候把某个值标称负数，现在在这行往右扫
                            col++;                     //又扫到这个已经变成负数的值，应该比较abs，否则会漏掉
                        }else {
                            break;
                        }
                    }
                    if(col-j+1>=3){//这里容易写错
                        found=true;
                        for(int l=j;l<=col;l++){//这里也容易写错，col这里的意义是包括的
                            board[i][l]=-val;
                        }
                    }
                    int row=i;
                    for(int k=i+1;k<board.length;k++){
                        if(Math.abs(board[k][j])==val){
                            row++;
                        }else {
                            break;
                        }
                    }
                    if(row-i+1>=3){//这里容易写错
                        found=true;
                        for (int l=i;l<=row;l++){//这里也容易写错，row这里的意义是包括的
                            board[l][j]=-val;
                        }
                    }
                }
            }
            //消除crush
            for(int j=0;j<board[0].length;j++){
                int row=board.length-1;
                for(int i=board.length-1;i>=0;i--){
                    if(board[i][j]>0){
                        board[row--][j]=board[i][j];
                    }
                }
                for (int i=row;i>=0;i--){
                    board[i][j]=0;
                }
            }

        }

        return board;
    }

}
