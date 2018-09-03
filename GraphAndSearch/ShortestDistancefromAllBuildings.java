package GraphAndSearch;

/**
 * Created by yufengzhu on 8/13/18.
 */
public class ShortestDistancefromAllBuildings {
    //https://leetcode.com/problems/shortest-distance-from-all-buildings/discuss/76891/Java-solution-with-explanation-and-time-complexity-analysis
    //看了一下讨论，貌似也没有特别好的方法，原始的想法就是每个0去bfs到各个1，记录下该点到每个1的距离加起来，取最小的就完了。答案是从每个1，bfs到所有的0，记录下每个0到各个1的距离，取最小的就完了
    public int shortestDistance(int[][] grid) {

    }
}
