package SomeInterviews.snap;

import java.util.*;

public class MinimumAreaRectangle939 {
    //3/7/2026 开始想的是map里面装pq，想的是同一行取前后两个，那么再找另一个行的连个比较，但是不好找另一行。看答案做法应该是枚举两个点
    //他们不能是同一行或者同一列，即是对角线上的，那么就可以查map是否存在另一个对角线上的两个点即可算正方形面积
    //https://leetcode.com/problems/minimum-area-rectangle/solutions/192025/java-n2-hashmap-by-wangzi6147-gtb3
    public int minAreaRect(int[][] points) {
        Map<Integer, Set<Integer>> map=new HashMap<>();
        for (int i=0;i<points.length;i++){
            map.putIfAbsent(points[i][0],new HashSet<>());
            map.get(points[i][0]).add(points[i][1]);

        }
        int rs=Integer.MAX_VALUE;
        for(int i=0;i<points.length;i++){
            for (int j=i+1;j<points.length;j++){
                    int[] p1=points[i];
                    int[] p2=points[j];
                    if(p1[0]==p2[0]||p1[1]==p2[1]){
                        continue;
                    }
                    if(map.get(p1[0]).contains(p2[1])&&map.get(p2[0]).contains(p1[1])){
                        rs=Math.min(rs,Math.abs(p1[0]-p2[0])*Math.abs(p1[1]-p2[1]));
                    }
            }
        }
        if(rs==Integer.MAX_VALUE){
            return 0;
        }
        return rs;
    }
}
