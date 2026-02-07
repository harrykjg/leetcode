package linkedin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class BestMeetingPoint296 {
    //不会，看的官方答案。由于就是基于曼哈顿距离，所以可以分别算x轴距离和y轴距离再加一起就是答案。但是如何判断距离最近的点呢？如果是一行有奇数个1 如
    //100011那么肯定是中间那个1是最短总距离，即就是size/2.，如果是偶数如1010011那肯定是中间的，可知选下标为2345的点都行，那就是col坐标排序一下，
    //然后取col坐标的size/2就行了。即无论奇数还是偶数都是取第size/2个。不太好想，反正就是对的
    /*
    如果是 0 0 1
          1 0 1
          第一行只有一个，col是2，第二行有0和2，那么col数组就是0，2，2，size是3，3/2就是1，即取中间那个2，那么算col的总距离就是0，2，2这个数组
          找每一个元素到2的距离。同理算row也是
     */
    public int minTotalDistance(int[][] grid) {
        List<Integer> rows=new ArrayList<>();
        List<Integer> cols=new ArrayList<>();
        for (int i=0;i<grid.length;i++){
            for (int j=0;j<grid[0].length;j++){
                if (grid[i][j]==1){
                    rows.add(i);
                    cols.add(j);
                }

            }
        }
        int mid1=rows.size()/2;
        Collections.sort(cols);
        int mid2=cols.size()/2;
        return helper(rows,rows.get(mid1))+helper(cols,cols.get(mid2));
    }
    int helper(List<Integer> al,int mid){
        int rs=0;
        for (int i:al){
            rs+= Math.abs(mid-i);
        }
        return rs;
    }
}
