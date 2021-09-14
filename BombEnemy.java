import java.util.Arrays;

public class BombEnemy {
    //7/20/2021自己想的就是统计某个点的四个方向上的enemy的数量，找最大的就行了。写的时候没想清楚怎么统计。看了别人是要个数据结构统计四个方向
    //写的时候还挺恶心的。算右下方向时，到底这个点是enemy的话算到底该不该算
    //https://leetcode.com/problems/bomb-enemy/discuss/83391/Java-straightforward-solution-DP-O(mn)-time-and-space
    public int maxKilledEnemies(char[][] grid) {
        int rs=0;
        Point[][] points=new Point[grid.length][grid[0].length];
        for (int i=0;i<grid.length;i++){//第一遍算上面和左边的兵
            for (int j=0;j<grid[0].length;j++){
                Point p=new Point();
                if (grid[i][j]=='W'){
                    p.up=0;
                    p.left=0;
                    points[i][j]=p;
                    continue;
                }
                if (i>0){
                    p.up=points[i-1][j].up+(grid[i][j]=='E'?1:0);//【i】【j】的兵被这个算了
                }else {
                    p.up=grid[i][j]=='E'?1:0;
                }
                if (j>0){
                    p.left=points[i][j-1].left+(grid[i][j]=='E'?1:0);
                }else {
                    p.left=grid[i][j]=='E'?1:0;//左边还得算，不能因为up方向被算了就不算他
                }
                points[i][j]=p;
            }
        }

        for (int i=grid.length-1;i>=0;i--){
            for (int j=grid[0].length-1;j>=0;j--){//
                if (grid[i][j]=='W'){
                    points[i][j].up=0;
                    points[i][j].left=0;
                    points[i][j].right=0;
                    points[i][j].down=0;
                    continue;
                }
                if (i+1<grid.length){
                    points[i][j].down=points[i+1][j].down+(grid[i][j]=='E'?1:0);//这里有点难想，如果不是最后一行的话，当前是enemy的话，要算在down里吗，
                    // 因为up里已经算了当前为enemy了，再算down的话不是重复了吗？其实不是，当算某店上下左右enemy时，当前点肯定是空，就不会存在重复。而不算的话那down永远是0
                }else {
                    //由于上面的原因，开始一位最后一行的话，down肯定为0。其实还是要算的
                    points[i][j].down=grid[i][j]=='E'?1:0;
                }
                if (j+1<grid[0].length){
                    points[i][j].right=points[i][j+1].right+(grid[i][j]=='E'?1:0);
                }else {
                    points[i][j].right=grid[i][j]=='E'?1:0;
                }
                if (grid[i][j]=='0'){
                    rs=Math.max(rs,points[i][j].up+points[i][j].down+points[i][j].left+points[i][j].right);
                }
            }
        }
        return rs;
    }
    class Point{
        int up;
        int down;
        int left;
        int right;
    }

    //8/12/2021又不会了
    public int maxKilledEnemies2(char[][] grid) {
        Point[][] points=new Point[grid.length][grid[0].length];
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                points[i][j]=new Point();
                if(grid[i][j]=='0'){
                    if(i>0){
                        points[i][j].up=points[i-1][j].up;
                    }
                    if(j>0){
                        points[i][j].left=points[i][j-1].left;
                    }
                }else if(grid[i][j]=='E'){

                    if(i>0){
                        points[i][j].up=points[i-1][j].up;
                    }
                    if(j>0){
                        points[i][j].left=points[i][j-1].left;
                    }
                    points[i][j].up+=1;
                    points[i][j].left+=1;
                }
            }
        }
        int rs=0;
        for (int i=grid.length-1;i>=0;i--){
            for (int j=grid[0].length-1;j>=0;j--){
                if(grid[i][j]=='0'){
                    if(i+1<grid.length){
                        points[i][j].down=points[i+1][j].down;
                    }
                    if(j+1<grid[0].length){
                        points[i][j].right=points[i][j+1].right;
                    }
                    rs=Math.max(points[i][j].down+points[i][j].right+points[i][j].up+points[i][j].left,rs);
                }else if(grid[i][j]=='E'){

                    if(i+1<grid.length){
                        points[i][j].down=points[i+1][j].down;
                    }
                    if(j+1<grid[0].length){
                        points[i][j].right=points[i][j+1].right;
                    }
                    points[i][j].down+=1;
                    points[i][j].right+=1;
                }
            }
        }
        return rs;
    }
}
