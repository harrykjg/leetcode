package dp;

import java.util.List;

public class Triangle {
    //6/3/2021,还不太好写，以为是比较简单的dp其实也是要用2维数组
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle.size()==0){
            return 0;
        }
        int rs=Integer.MAX_VALUE;
        int[][] memo=new int[triangle.size()][triangle.size()];
        memo[0][0]=triangle.get(0).get(0);
        for(int i=1;i<triangle.size();i++){
            memo[i][0]=triangle.get(i).get(0)+memo[i-1][0];
            for(int j=1;j<i;j++){
                int cur=triangle.get(i).get(j);
                memo[i][j]=Math.min(cur+memo[i-1][j-1],cur+memo[i-1][j]);
            }
            memo[i][i]=triangle.get(i).get(i)+memo[i-1][i-1];
        }
        for(int i=0;i<triangle.size();i++){
            rs=Math.min(rs,memo[triangle.size()-1][i]);
        }
        return rs;
    }
}
