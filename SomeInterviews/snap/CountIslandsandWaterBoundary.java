package SomeInterviews.snap;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CountIslandsandWaterBoundary {
    /*
    Count Islands and Water Boundary
Medium
DFS


Interview Stages
Screening
Frequency
Asked By
SNAPCHAT-icon
WAYMO-icon
Last Reported
4 days ago

AI Insights
(This question is a variation of the LeetCode question 200. Number of Islands. If you haven't completed that question yet, it is recommended to solve it first.)

You are given a 2D binary grid of size m × n, where each cell is either '0'(water) or '1'(land). An island is defined as a group of connected land cells (1s) where connection is only possible through vertical or horizontal neighbors.

For each island in the grid, compute:

The area: the total number of land cells in the island.
The number of distinct water cells horizontally or vertically adjacent to the island (the water boundary count). A water cell is counted at most once for each island, even if it touches multiple sides of the island. Do not count grid boundaries as water cells.
Return a list of pairs, where each pair contains the area and the water boundary count for an island. The order of the pairs in the output list does not matter.

Constraints:

1 ≤ m, n ≤ 100
Each grid[i][j] is either '0' or '1'.
Example 1:

Input: grid = [[0, 1, 0], [1, 1, 0], [0, 0, 0]]
Output: [[3, 5]]
Explanation: The single island consists of three land cells. The five distinct water cells adjacent to it are located at: (0, 0), (0, 2), (1, 2), (2, 0), and (2, 1). The grid is shown below:


Example 2:

Input: grid = [[1, 0, 1], [0, 0, 0], [1, 0, 1]]
Output: [[1, 2], [1, 2], [1, 2], [1, 2]]

Example 3:

Input: grid = [[1, 1, 0, 0, 0], [1, 0, 0, 1, 1], [0, 0, 0, 1, 0], [0, 1, 1, 0, 0]]
Output: [[3, 3], [3, 6], [2, 4]]
     */
    //写的不好，主要是岛屿和水不应该用同一个set去重，因为岛屿只能访问一次，但是水可以访问多次
    public List<List<Integer>> islandAreasAndBoundaries(int[][] grid) {
        // TODO: Implement islandAreasAndBoundaries logic
        List<List<Integer>> rs=new ArrayList<>();
        HashSet<Integer> set=new HashSet<>();
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                if(grid[i][j]==1&&!set.contains(i*grid[0].length+j)){
                    List<Integer> island=new ArrayList<>();
                    island.add(0);
                    Set<Integer> waterSet=new HashSet<>();
                    dfs(island,i,j,set,waterSet,grid);//waterSet的的size作为这个water的计数，island list就用来计数island的大小
                    List<Integer> ls=new ArrayList<>();
                    ls.add(island.get(0));
                    ls.add(waterSet.size());
                    rs.add(ls);
                }
            }
        }
        return rs;
    }

    void dfs(List<Integer> island,int row, int col, Set<Integer> set,Set<Integer> waterSet,int[][] grid){
        int[] dx={0,1,0,-1};
        int[] dy={1,0,-1,0};

        if(grid[row][col]==1){//注意这里就不要再检测set里是否已经访问了，因为caller那里已经弄过了
            island.set(0,island.get(0)+1);
            set.add(row*grid[0].length+col);
        }else  {
            waterSet.add(row*grid[0].length+col);
        }
        for (int i=0;i<4;i++){
            int r=row+dx[i];
            int c=col+dy[i];
            int cord=r*grid[0].length+c;
            if(r>=0&&r<grid.length&&c>=0&&c<grid[0].length){
                if(grid[r][c]==1&&!set.contains(cord)){
                    set.add(cord);
                    dfs(island,r,c,set,waterSet,grid);
                }else if(grid[r][c]==0){
                    waterSet.add(cord);
                }

            }
        }

    }
}
