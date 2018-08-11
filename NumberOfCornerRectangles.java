/**
 * Created by yufengzhu on 8/3/18.
 */
public class NumberOfCornerRectangles {
    public static void main(String[] args){
        NumberOfCornerRectangles no=new NumberOfCornerRectangles();
        int[][] g={{0,1,0},{1,0,1},{1,0,1},{0,1,0}};
        System.out.print(no.countCornerRectangles(g));
    }
    //https://www.cnblogs.com/grandyang/p/8433813.html  解法3最好吧
    //https://www.jianshu.com/p/825b5100a9e1  竖线的思路看他的
    //https://leetcode.com/problems/number-of-corner-rectangles/solution/ 第二个答案看不懂
    //只能想到暴力法，先写暴力法,开始还想错了，以为只是正方形，其实长方形也可以
    public int countCornerRectangles(int[][] grid) {
        if(grid.length<2){
            return 0;
        }
        int rs=0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]==0){
                    continue;
                }
                for(int k=i+1;k<grid.length;k++){
                    if(grid[k][j]==0){
                        continue;
                    }
                    for(int l=j+1;l<grid[0].length;l++){//有点绕，想清楚
                        if(grid[k][l]==1&&grid[i][l]==1){
                            rs++;
                        }
                    }
                }
            }
        }
        return rs;
    }
    //固定两行（这两行不一定要相邻）的方法,就是找有几条竖线，然后竖线之间能组成的正方形就是排列组合的n选2种
    public int countCornerRectangles2(int[][] grid) {
        if(grid.length<2){
            return 0;
        }
        int rs=0;
        for(int i=0;i<grid.length;i++) {
            for (int j = i+1; j < grid.length; j++) {
                int count=0;

                for(int k=0;k<grid[0].length;k++){
                    if(grid[i][k]==0||grid[j][k]==0){
                        continue;
                    }
                    count++;
                }
                rs+=count*(count-1)/2;
            }
        }
        return rs;
    }

}
