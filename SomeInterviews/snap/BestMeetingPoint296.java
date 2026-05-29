package SomeInterviews.snap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BestMeetingPoint296 {

    //3/4/2026 还是不好想，我想的应该是加权的把，比如有一行0，3，4位置上是1，则(0+3+4)/3=2或3，那中点肯定是2或3，那是每一行都可能产生一个或2个
    //候选中点？还是把所有行的j都加起来算中点？列同理，那就是可能有2个j和两个i，组合成四个候选中点？想错了，不应该是加起来算平均数，由于每一个grid最多
    //只有一个1，因此只要看谁是中间那个点，如0，3，4那么肯定是3，如0，5那么0，5都行，即len/2就对.还要记住要sort 列才行
    public int minTotalDistance(int[][] grid) {
        List<Integer> row=new ArrayList<>();
        List<Integer> col=new ArrayList<>();
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                if(grid[i][j]==1){
                    row.add(i);
                    col.add(j);
                }
            }
        }
        int m1=row.size()/2;
        int m2=col.size()/2;
        //这里容易漏，要sort col， 因为row本来就是从小到大遍历的
        Collections.sort(col);
        return helper(row,m1)+helper(col,m2);
    }
    int helper(List<Integer> al,int mid){
        int rs=0;
        for (int i:al){
            rs+=Math.abs(al.get(mid)-i);
        }
        return rs;
    }
}
