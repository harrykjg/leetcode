package SomeInterviews.snap;

public class TheMaze490 {
    static void main() {
        int[][] m={{0,0,1,0,0},{0,0,0,0,0},{0,0,0,1,0},{1,1,0,1,1},{0,0,0,0,0}};
        TheMaze490 tm=new TheMaze490();
        System.out.println(tm.hasPath(m,new int[]{0,4},new  int[]{4,4}));
    }
    //3/7/2026 我想就是要用0，1，2，3表示4个方向，写法就是dfs，开始的时候就遍历四个方向，不用一步一步走到邻居那，而是直接按这个方向直到撞墙，
    //这个方向试完继续下一个方向,结果超时，因为dfs的当四个方向遍历完之后我又把memo rest成false了
    //bfs应该更好
    //https://leetcode.com/problems/the-maze/solutions/97071/easy-understanding-java-bfs-solution-by-6ayvs/
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        boolean[][] memo=new boolean[maze.length][maze[0].length];
        if(dfs( start,destination,maze,memo)){
            return true;
        }
        return false;
    }

    boolean dfs(int[] start,int[] des,int[][] maze,boolean[][] memo){
        if(start[0]==des[0]&&start[1]==des[1]){
            return true;
        }
        memo[start[0]][start[1]]=true;
        int[] dx={0,1,0,-1};
        int[] dy={1,0,-1,0};
        for (int i=0;i<4;i++){
            int r=start[0];
            int c=start[1];
            //这里容易写错
            while (r+dx[i]>=0&&r+dx[i]<maze.length&&c+dy[i]>=0&&c+dy[i]<maze[0].length&&maze[r+dx[i]][c+dy[i]]!=1){
                r+=dx[i];
                c+=dy[i];
            }
            //这里容易漏
            if(r<0||r>=maze.length||c<0||c>=maze[0].length||memo[r][c]){
                continue;
            }
            int[] next={r,c};
            if(dfs(next,des,maze,memo)){
                return true;
            }
        }
        //这里开始还reset成false导致超时，为啥不需要reset呢？
        return false;
    }

}
