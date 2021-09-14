
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 502575560 on 6/29/16.
 */
public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] m) {
        List<Integer> list=new ArrayList<>();
        if(m.length==0){
            return list;
        }
        int row=m.length;
        int col=m[0].length;
        int n=0;
        if(row>=col){
            n=col/2;
        }else{
            n=row/2;

        }
        for(int i=0;i<=n;i++){
            for(int j=i;j<m[0].length-i;j++){
                list.add(m[i][j]);
            }
            for(int j=i+1;j<m.length-i;j++){
                list.add(m[j][m[0].length-1-i]);
            }
            for(int j=m[0].length-1-i;j>=i;j--){
                list.add(m[m.length-1-i][j]);
            }
            for(int j=m.length-2-i;j>i;j--){
                list.add(m[j][i]);
            }

        }
        if(row>=col){
            if(col%2!=0){
                for(int i=col/2;i<m.length-col/2;i++){
                    list.add(m[i][col/2]);
                }
            }
        }else{
            if(row%2!=0){
                for(int i=row/2;i<m[0].length-row/2;i++){
                    list.add(m[row/2][i]);
                }
            }
        }

        return list;

    }

    //7/4/2021
    //7/4/2021,我以为可以直接找最小的圈数旋转，结果好想不行。如
    //[1,2,3,4],
    //[ 5,6,7,8],
    //[9,10,11,12]]到了内层6，7，然后还会运行到第三个for循环从右向左把6再加上就错了
    public List<Integer> spiralOrder2(int[][] m) {
        int min=Math.min(m.length,m[0].length);
        int limit=min/2;
        int layer=0;
        int row=m.length;
        int col=m[0].length;
        List<Integer> rs=new ArrayList<>();
        while (layer<limit){
            for (int i=layer;i<col-layer;i++){
                rs.add(m[layer][i]);
            }
            for(int i=layer+1;i<row-layer;i++){
                rs.add(m[i][col-1-layer]);
            }
            for(int i=col-2-layer;i>=layer;i--){
                rs.add(m[row-1-layer][i]);
            }
            for(int i=row-2-layer;i>layer;i--){
                rs.add(m[i][layer]);
            }
            layer++;
        }
        if (min%2!=0){
            if (row==min){//还差最里面一行
                for (int i=limit;i<col-limit;i++){
                    rs.add(m[limit][i]);
                }
            }else{//还差里面那一列
                for (int i=limit;i<row-limit;i++){
                    rs.add(m[i][limit]);
                }
            }
        }
        return rs;
    }
}
