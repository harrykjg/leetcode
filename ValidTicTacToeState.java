public class ValidTicTacToeState {

    //9/22/2021 不会，以为要dfs，其实只要检测不valid的条件，其余的都是合法的。x先走
    //invalid条件1，o大于x，2，x大于o数量+1。3，x赢了之后，o数量不能大于或等于x。4，o赢了的话，x数量肯定要等于o数量。因此还要判断谁赢，而不是简单的看count,
    //判断谁赢还是用design tic tae toe的方法看行列对角线是否等于3
    //https://leetcode.com/problems/valid-tic-tac-toe-state/discuss/117597/Java-Solution-with-Explain
    public boolean validTicTacToe(String[] board) {
        int x=0;
        int o=0;
        boolean xwin=false;
        boolean owin=false;
        int[] row=new int[3];
        int[] col=new int[3];
        int dia=0;
        int adia=0;
        for (int i=0;i<board.length;i++){
            char[] ch=board[i].toCharArray();
            for (int j=0;j<ch.length;j++){
                if (ch[j]=='X'){
                    x++;
                    row[i]++;
                    col[j]++;
                    if (i==j){
                        dia++;
                    }
                    if(i+j==2){
                        adia++;
                    }
                }else if (ch[j]=='O'){
                    o++;
                    row[i]--;
                    col[j]--;
                    if (i==j){
                        dia--;
                    }
                    if(i+j==2){
                        adia--;
                    }
                }
                if (row[0]==3||row[1]==3||row[2]==3||col[0]==3||col[1]==3||col[2]==3||dia==3||adia==3){
                    xwin=true;
                }
                if (row[0]==-3||row[1]==-3||row[2]==-3||col[0]==-3||col[1]==-3||col[2]==-3||dia==-3||adia==-3){
                    owin=true;
                }
            }
        }


        if (o>x||x>o+1||(xwin&&o>=x)||(owin&&x!=o)){
            return false;
        }

        return true;
    }
}
