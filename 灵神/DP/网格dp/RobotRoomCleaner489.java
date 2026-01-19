package 灵神.DP.网格dp;

import java.util.HashSet;
import java.util.Set;

public class RobotRoomCleaner489 {
    static void main() {

    }
    //1/7/2026 看的gpt解释。 这个人有模拟路线https://codesandbox.io/p/sandbox/quizzical-yonath-j0ixe
    // 印象中以为当四周都访问过之后怎么办？要回去来时的点怎么回？要记录path吗。其实不用，思想就是dfs，即到达一个点
    //之后调用dfs，这个dfs会朝四个方向dfs，等四个方向递归回来的时候就是天然的知道是从哪来的，这里不是四个方向之后要调用goback，而是
    //每走一个方向都要goback方法，并且还要调用turnright方法
    public void cleanRoom(Robot robot) {
        Set<Integer> set=new HashSet<>();
        robot.clean();
        set.add(0);
        dfs(0,0,0,set,robot);//dir 0 代表右吧
    }

    void dfs(int row,int col,int dir,Set<Integer> set,Robot robot){
        robot.clean();
        int[] dx={0,1,0,-1};
        int[] dy={1,0,-1,0};
        for(int i=0;i<4;i++){
            int d=(i+dir)%4;
            int r=dx[d]+row;
            int c=dy[(d)%4]+col;
            if(!set.contains(r*100+c)&&robot.move()){
                set.add(r*100+c);
                dfs(r,c,d,set,robot);
                goback(robot);
            }
            robot.turnRight();//这里容易漏
        }

    }
    //即转180度走一步，再转180度，即返回上一个点，并且方向和来时一样
    void goback(Robot robot){
        robot.turnLeft();
        robot.turnLeft();
        robot.move();
        robot.turnLeft();
        robot.turnLeft();
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
