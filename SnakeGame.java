import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class SnakeGame {
    int[][] m;
    int score =0;
    Deque<int[]> snake;
    int foodIndex;
    int[][] food;

    //7/11/2021.72个case时错了，懒得debug。思路就是用dequeue存snake的位置，移动一位就要加一个位置在dequeue头部，或者删除尾巴，而图上也把蛇占据的位置设为0，空为-1
    //食物为1。
    public SnakeGame(int width, int height, int[][] food) {
        m=new int[height][width];
        this.food=food;
        for (int i=0;i<m.length;i++){
            Arrays.fill(m[i],-1);
        }
        m[0][0]=0;
        snake =new LinkedList<>();
        snake.offerLast(new int[]{0,0});
    }

    /** Moves the snake.
     @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
     @return The game's score after the move. Return -1 if game over.
     Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        switch (direction){
            case "U":
                if (snake.peekFirst()[0]==0){//头部撞墙
                    return -1;
                }
                if (m[snake.peekFirst()[0]-1][snake.peekFirst()[1]]==0){//撞到自己
                    return -1;
                }
                if (foodIndex<food.length&&snake.peekFirst()[0]-1==food[foodIndex][0]&&snake.peekFirst()[1]==food[foodIndex][1]){//头部吃到
                    score++;
                    m[snake.peekFirst()[0]-1][snake.peekFirst()[1]]=0;
                    snake.offerFirst(food[foodIndex++]);//把snake的头部更新到food这
                }else {
                    snake.offerFirst(new int[]{snake.peekFirst()[0]-1,snake.peekFirst()[1]});
                    m[snake.peekFirst()[0]][snake.peekFirst()[1]]=0;
                    m[snake.peekLast()[0]][snake.peekLast()[1]]=-1;//把尾巴从图上抹去
                    snake.pollLast();
                }
                break;
            case "L":
                if (snake.peekFirst()[1]==0){//头部撞墙
                    return -1;
                }
                if (m[snake.peekFirst()[0]][snake.peekFirst()[1]-1]==0){//撞到自己
                    return -1;
                }
                if (foodIndex<food.length&&snake.peekFirst()[0]==food[foodIndex][0]&&snake.peekFirst()[1]-1==food[foodIndex][1]){//头部吃到
                    score++;
                    m[snake.peekFirst()[0]][snake.peekFirst()[1]-1]=0;
                    snake.offerFirst(food[foodIndex++]);//把snake的头部更新到food这
                }else {
                    snake.offerFirst(new int[]{snake.peekFirst()[0],snake.peekFirst()[1]-1});
                    m[snake.peekFirst()[0]][snake.peekFirst()[1]]=0;
                    m[snake.peekLast()[0]][snake.peekLast()[1]]=-1;//把尾巴从图上抹去
                    snake.pollLast();
                }
                break;
            case "R":
                if (snake.peekFirst()[1]+1>=m[0].length){//头部撞墙
                    return -1;
                }
                if (m[snake.peekFirst()[0]][snake.peekFirst()[1]+1]==0){//撞到自己
                    return -1;
                }
                if (foodIndex<food.length&&snake.peekFirst()[0]==food[foodIndex][0]&&snake.peekFirst()[1]+1==food[foodIndex][1]){//头部吃到
                    score++;
                    m[snake.peekFirst()[0]][snake.peekFirst()[1]+1]=0;
                    snake.offerFirst(food[foodIndex++]);//把snake的头部更新到food这
                }else {
                    snake.offerFirst(new int[]{snake.peekFirst()[0],snake.peekFirst()[1]+1});
                    m[snake.peekFirst()[0]][snake.peekFirst()[1]]=0;
                    m[snake.peekLast()[0]][snake.peekLast()[1]]=-1;//把尾巴从图上抹去
                    snake.pollLast();
                }
                break;
            case "D":
                if (snake.peekFirst()[0]+1>=m.length){//头部撞墙
                    return -1;
                }
                if (m[snake.peekFirst()[0]+1][snake.peekFirst()[1]]==0){//撞到自己
                    return -1;
                }
                if (foodIndex<food.length&&snake.peekFirst()[0]+1==food[foodIndex][0]&&snake.peekFirst()[1]==food[foodIndex][1]){//头部吃到
                    score++;
                    m[snake.peekFirst()[0]+1][snake.peekFirst()[1]]=0;
                    snake.offerFirst(food[foodIndex++]);//把snake的头部更新到food这
                }else {
                    snake.offerFirst(new int[]{snake.peekFirst()[0]+1,snake.peekFirst()[1]});
                    m[snake.peekFirst()[0]][snake.peekFirst()[1]]=0;
                    m[snake.peekLast()[0]][snake.peekLast()[1]]=-1;//把尾巴从图上抹去
                    snake.pollLast();
                }
                break;

        }
        return score;
    }
}
