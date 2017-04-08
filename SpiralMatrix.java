
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
}
