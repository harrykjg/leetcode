package SomeInterviews.karat;

import java.util.ArrayList;
import java.util.List;

public class FindTreasure {
    public static void main(String[] args){
        FindTreasure ft=new FindTreasure();
        int[][] board={
                {  1,  0,  0, 0, 0 },
                {  0, -1, -1, 0, 0 },
                {  0, 0,  0, 1, 0 },
//                {  -1,  0,  0, 0, 0 },
//                {  0,  1, -1, 0, 0 },
//                {  0,  0,  0, 0, 0 },
        };
//        System.out.println(ft.canReach(board,0,2));
        ft.shortestPath(board,0,2);
    }
    //https://leetcode.com/discuss/interview-question/1050247/compass-first-round-via-karat
    //应该就是从某个点出发看能不能reach所有0。那就先统计所有0然后dfs就好了
    int count=0;
    public boolean canReach(int[][] m,int row,int col){
        for (int i=0;i<m.length;i++){
            for (int j=0;j<m[0].length;j++){
                if (m[i][j]==0){
                    count++;
                }
            }
        }
        boolean[][] memo=new boolean[m.length][m[0].length];
        memo[row][col]=true;
        dfs(row,col,m,memo);

        return count==0;
    }
    void dfs(int row,int col,int[][] m,boolean[][] memo){
        if (m[row][col]==0){//注意题目可能有1代表钻石，可以走，但是count要是0的才减1
            count--;
        }
        int[] dx={-1,0,1,0};
        int[] dy={0,1,0,-1};
        for (int i=0;i<4;i++){
            int r=row+dx[i];
            int c=col+dy[i];
            if (r>=0&&r<m.length&&c>=0&&c<memo[0].length&&m[r][c]!=-1&&memo[r][c]==false){
                memo[r][c]=true;
                dfs(r,c,m,memo);
            }
        }
    }
    //第三问貌似只能暴力dfs其实就是回溯。开始没把diamond递归而是做成全局变量就错了。
    int rs=Integer.MAX_VALUE;
    List<int[]> path;
    public List<int[]> shortestPath(int[][] m,int row,int col){
        boolean[][] memo=new boolean[m.length][m[0].length];
        int diamond=0;
        for (int i=0;i<m.length;i++){
            for (int j=0;j<m[0].length;j++){
                if (m[i][j]==1){
                    diamond++;
                }
            }
        }
        memo[row][col]=true;
        dfs2(0,row,col,m,memo,diamond,new ArrayList<>());
        System.out.println(rs);
        for (int[] p:path){
            System.out.print(p[0]+" "+p[1]);
            System.out.println();
        }
        return path;
    }

    void dfs2(int cur, int row, int col, int[][] m, boolean[][] memo, int diamond, List<int[]> p){
       if (diamond==0){
           if (cur<rs){
               rs=cur;
               p.add(new int[]{row,col});
               path=new ArrayList<>(p);
               p.remove(p.size()-1);
           }
           return;
        }
        int[] dx={-1,0,1,0};
        int[] dy={0,1,0,-1};
        for (int i=0;i<4;i++){
            int r=row+dx[i];
            int c=col+dy[i];
            if (r>=0&&r<m.length&&c>=0&&c<memo[0].length&&m[r][c]!=-1&&memo[r][c]==false){
                memo[r][c]=true;
                p.add(new int[]{row,col});
                if (m[r][c]==1){
                    dfs2(cur+1,r,c,m,memo,diamond-1,p);
                }else {
                    dfs2(cur+1,r,c,m,memo,diamond,p);
                }
                p.remove(p.size()-1);
                memo[r][c]=false;//开始没写这一步还原，就不是回溯，就会导致某个分支dfs访问过了某些点，但是这个dfs得出的就过不是最短的，后来的dfs又想
            }           //走某个点可能会是更近的但是缺因为之前的dfs走过而没法走了。
        }

    }
}
