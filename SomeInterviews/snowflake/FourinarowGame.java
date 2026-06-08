package SomeInterviews.snowflake;

import java.util.List;

public class FourinarowGame {
    //就是基本操作,但是要加个正负的sign才简洁
    public String gameWinner(List<List<String>> board, List<Integer> move, String player) {
        // TODO: Implement gameWinner logic
            int[] dx={0,1,1,1};//这里前两个就是右边和上面，后两个是对角线，然后下面for循环的时候加上正负
            int[] dy={1,0,1,-1};
            int row=move.get(0);
            int col=move.get(1);
            int len=board.size();
            int width=board.get(0).size();
            int[] sign={1,-1};
            for (int j=0;j<4;j++){
                int rowDir=dx[j];
                int colDir=dy[j];
                for(int k=0;k<2;k++) {
                    int s = sign[k];
                    int count = 1;
                    for (int i = 1; i <= 3; i++) {//最多延伸3个
                        int r = rowDir * i * s + row;
                        int c = colDir * i * s + col;
                        if (r >= 0 && r < len && c >= 0 && c < width && board.get(r).get(c).equals(player)) {
                            count++;
                            if (count >= 4) {
                                return player;
                            }
                        } else {
                            break;
                        }
                    }
                }
            }
            return "";
    }
}
