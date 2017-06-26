import java.util.ArrayList;
import java.util.List;

/**
 * Created by 502575560 on 6/25/17.
 */
//相似题:Surrounded Regions
public class PacificAtlanticWaterFlow {
    public static void main(String[] args){
        PacificAtlanticWaterFlow pf=new PacificAtlanticWaterFlow();
        int[][] m={{1,2},{4,3}};//{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}
        pf.pacificAtlantic(m);
    }
    //先理解题目,妈的理解错了2次,矩阵某处的值的意义即使用来和周围的值比较的,只要周围的值小于等于它那么它就能溜过去,而不是说它要到矩阵边沿的距离要小于等于这个值,
    //而且!这个水是可以拐弯的!我以为只能从上下左右直着走.还有比如7,4,5,当7流到4了,那么4就不能流到5了!
    //暴力法的话就是搜索每个点,能否到达太平洋沿岸的其中一个点,和能否到达大西洋沿岸的其中一个点,会超时,看了别人的思路,那么就倒过来,从太平洋沿岸往里走,
    //路过哪就说明哪可以流出来,再从大西洋往里走,得出两个矩阵,这两个矩阵都true的点加入结果集
//http://blog.csdn.net/mebiuw/article/details/52766269 dfs
    //http://blog.csdn.net/Andy_Shan/article/details/52781456 bfs
    //http://blog.csdn.net/mcf171/article/details/52770078 这个说的启发式?不就是dfs吗
    public List<int[]> pacificAtlantic(int[][] m) {
        List<int[]> rs=new ArrayList<>();
        if(m.length==0){
            return rs;
        }
        boolean[][] temp=new boolean[m.length][m[0].length];
        boolean[][] temp2=new boolean[m.length][m[0].length];
        for(int i=0;i<m[0].length;i++){
            flow(m,temp,0,i);//先扫第一行,太平洋
            flow(m,temp2,m.length-1,i);//最后一行,大西洋
        }
        for(int i=0;i<m.length;i++){
            flow(m,temp,i,0);//扫第一列,太平洋
            flow(m,temp2,i,m[0].length-1);//最后一列,大西洋
        }
        for(int i=0;i<temp.length;i++){
            for(int j=0;j<temp[0].length;j++){
                if(temp[i][j]&&temp2[i][j])
                rs.add(new int[]{i,j});
            }
        }

        return rs;
    }
    void flow(int[][] m,boolean[][] temp,int a,int b){
        temp[a][b]=true;
        //上右下左一次搜,
        if(a-1>=0&&m[a-1][b]>=m[a][b]&&temp[a-1][b]==false){
            flow(m,temp,a-1,b);
        }
        if(b+1<m[0].length&&m[a][b+1]>=m[a][b]&&temp[a][b+1]==false){
            flow(m,temp,a,b+1);
        }
        if(a+1<m.length&&m[a+1][b]>=m[a][b]&&temp[a+1][b]==false){
            flow(m,temp,a+1,b);
        }
        if(b-1>=0&&m[a][b-1]>=m[a][b]&&temp[a][b-1]==false){
            flow(m,temp,a,b-1);
        }
    }

}
