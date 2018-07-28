/**
 * Created by yufengzhu on 7/20/18.
 */
public class RobotRoomCleaner {
    //难
    public void cleanRoom(Robot robot) {

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
