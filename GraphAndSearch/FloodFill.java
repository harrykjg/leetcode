package GraphAndSearch;

/**
 * Created by yufengzhu on 8/9/18.
 */
public class FloodFill {
    //就是dfs，题目写的什么这个点的四个周围点的再四个周围点有有歧义
    //他们都没有用memo的，我用了。。一般dfs都要的，这题不需要，因为可以用颜色判断
    //https://leetcode.com/problems/flood-fill/solution/
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if(image.length==0){
            return image;
        }
        boolean[][] memo=new boolean[image.length][image[0].length];
        dfs(image, sr, sc, newColor,memo);
        return image;
    }
    void dfs(int[][] image,int r,int c,int color,boolean[][] memo){
        if(memo[r][c]){
            return;
        }
        int ori=image[r][c];
        image[r][c]=color;
        memo[r][c]=true;
        if(r-1>=0&&image[r-1][c]==ori){
            dfs(image,r-1,c,color,memo);
        }
        if(c+1<image[0].length&&image[r][c+1]==ori){
            dfs(image,r,c+1,color,memo);
        }
        if(r+1<image.length&&image[r+1][c]==ori){
            dfs(image,r+1,c,color,memo);
        }
        if(c-1>=0&&image[r][c-1]==ori){
            dfs(image,r,c-1,color,memo);
        }
    }
}
