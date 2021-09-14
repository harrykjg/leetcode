package GraphAndSearch;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class RobotRoomCleaner {
    //不会
    //https://www.youtube.com/watch?v=AkLu58eJQPc
    //实际上还是要记录那些点是访问过的，但是不是说他访问过就不能再走过了，回溯的时候是可以路过的。实际上是回溯法dfs，从一点点开始看四周，能走就走，四个方向都搞完
    //之后就回溯到上一个点继续dfs。而且我们还是要记录坐标点的，只能按原点作为0，0，然后上面和左边就是负的，右和下是正的。记坐标点是为了看谁被访问过了。回溯的时候
    //就是要把方向调整回反方向，然后走一步，再把方向转回来.还有就是dfs时没走一步，方向也是要作为一个参数过来的。这个方向在dfs的时候，不管他指向哪，反正是顺时针
    //转90度转4圈。
    //和答案差不多的代码
    public void cleanRoom(Robot robot) {
        Set<String> set=new HashSet<>();//开始想的用set装int【】记录坐标，那样是不行的，得写个pair class再重写equals和hashcode方法才行，否则后面dfs的
        set.add(0+"x"+0);    //时候判断某个点是否访问过的时候回错。但是答案貌似用jdk的pair，那个不用重写方法直接用就行
        dfs(0,0,0, set,robot);
    }

    void dfs(int row,int col,int dir,Set<String> set,Robot robot){
        int[] dx={-1,0,1,0};
        int[] dy={0,1,0,-1};
        robot.clean();
        for (int i=0;i<4;i++){
            int newdDir=(dir+i)%4;
            int r=row+dx[newdDir];//这里注意，dx里面是放的是dir，而不是i。这样才能表示跟着上层dfs的方向转
            int c=col+dy[newdDir];
            String id=r+"x"+c;//这里由于第一个方向是跟着上个dfs的方向，所以就不一定是上右下左了，但是肯定是顺时针转
            if (!set.contains(id)&&robot.move()){
                set.add(id);
                dfs(r,c,newdDir,set,robot);
                robot.turnRight();
                robot.turnRight();
                robot.move();
                robot.turnRight();
                robot.turnRight();
            }
            //
            robot.turnRight();//别忘了转
        }

    }


    interface Robot {
      // Returns true if the cell in front is open and robot moves into the cell.
      // Returns false if the cell in front is blocked and robot stays in the current cell.
      public boolean move();

      // Robot will stay in the same cell after calling turnLeft/turnRight.
      // Each turn will be 90 degrees.
      public void turnLeft();
      public void turnRight();
      // Clean the current cell.
      public void clean();
 }
}
