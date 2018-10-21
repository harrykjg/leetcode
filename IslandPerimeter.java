/**
 * Created by yufengzhu on 10/7/18.
 */
public class IslandPerimeter {
    //基本一次过，看图找规律，就是遍历grid看有几个1，然后对每个1看有几个邻居
    public int islandPerimeter(int[][] grid) {
        if(grid.length==0){
            return 0;
        }
        int count=0;
        int count2=0;
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[0].length;j++){
                if(grid[i][j]!=1){
                    continue;
                }
                count++;
                if(i>0&&grid[i-1][j]==1){
                    count2++;
                }
                if(j+1<grid[0].length&&grid[i][j+1]==1){
                    count2++;
                }
                if(i+1<grid.length&&grid[i+1][j]==1){
                    count2++;
                }
                if(j>0&&grid[i][j-1]==1){
                    count2++;
                }

            }
        }
        return count*4-count2;
    }
}
