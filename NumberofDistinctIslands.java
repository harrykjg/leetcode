import java.util.HashSet;
import java.util.Set;

public class NumberofDistinctIslands {
    public static void main(String[] args){
        NumberofDistinctIslands nb=new NumberofDistinctIslands();
        int[][] m={{1,1,0,0,0},{1,1,0,0,0},{0,0,0,1,1},{0,0,0,1,1}};
        System.out.println(nb.numDistinctIslands(m));
    }
    //7/24/2021,不会。
    //看答案第三个。
    //https://leetcode.com/problems/number-of-distinct-islands/discuss/108475/Java-very-Elegant-and-concise-DFS-Solution(Beats-100)
    //https://leetcode.com/problems/number-of-distinct-islands/discuss/150037/DFS-with-Explanations
    public int numDistinctIslands(int[][] grid) {
        Set<String> set=new HashSet<>();
        boolean[][] memo=new boolean[grid.length][grid[0].length];
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                if(grid[i][j]==1&&!memo[i][j]){
                    StringBuilder sb=new StringBuilder();
                    sb.append("o");//不需要o也对
                    dfs(i,j,grid,sb,memo);
                    set.add(sb.toString());
                }
            }
        }
        return set.size();
    }

    void dfs(int i,int j,int[][] grid,StringBuilder sb,boolean[][] memo){
        if (memo[i][j]){
            return;
        }
        memo[i][j]=true;
        if (i>0&&grid[i-1][j]==1&&!memo[i-1][j]){
            dfs(i-1,j,grid,sb.append("u"),memo);
        }
        if (j+1<grid[0].length&&grid[i][j+1]==1&&!memo[i][j+1]){
            dfs(i,j+1,grid,sb.append("r"),memo);
        }
        if (i+1<grid.length&&grid[i+1][j]==1&!memo[i+1][j]){
            dfs(i+1,j,grid,sb.append("d"),memo);
        }
        if (j>0&&grid[i][j-1]==1&&!memo[i][j-1]){
            dfs(i,j-1,grid,sb.append("l"),memo);
        }
        sb.append("x");//开始这个没想明白，看第二个链接的例子
        /*
                {1, 1, 0},  如果不加x，则这两个岛都是ord
                {1, 0, 0},
                {0, 0, 0},
                {1, 1, 0},
                {0, 1, 0}
         */
    }
}
