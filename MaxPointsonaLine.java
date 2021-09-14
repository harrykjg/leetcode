import java.util.*;

public class MaxPointsonaLine {
    public static void main(String[] args) {
        MaxPointsonaLine mp=new MaxPointsonaLine();

        int[][] p={{9,-25},{-4,1},{-1,5},{-7,7}};
        System.out.println(mp.maxPoints(p));
    }

    //8/11/2021 思路是记得的，就是map原来是放在for循环里面的。以前的题目貌似是用重复点的现在是没有的
    public int maxPoints(int[][] points) {
        if (points.length<=1){
            return points.length;
        }
        int rs=0;

        for (int i=0;i<points.length;i++){
            HashMap<Double, Integer> map=new HashMap<>();//这里不太好想，每个点和其余的点组成的线，用一个map记录。如果有4个点组成2条平行线，则斜率是一样。
            for (int j=0;j<points.length;j++){  //若用一个map的话则这个斜率上有4个点了，而实际每条直线上只有两个点
                if (i==j){
                    continue;
                }
                double xdiff=points[i][0]-points[j][0];//这里写成int的话后面算斜率就不是int了
                double ydiff=points[i][1]-points[j][1];
                if (ydiff==0){
                    if (!map.containsKey((double)Integer.MAX_VALUE)){
                        map.put((double)Integer.MAX_VALUE,2);
                        rs=Math.max(rs,2);
                    }else {
                        map.put((double)Integer.MAX_VALUE,map.get((double)Integer.MAX_VALUE)+1);
                        rs=Math.max(rs,map.get((double)Integer.MAX_VALUE));
                    }
                }else {
                    double k=xdiff/ydiff;
                    if (!map.containsKey(k)){
                        map.put(k,2);
                        rs=Math.max(rs,2);
                    }else {
                        map.put(k,map.get(k)+1);
                        rs=Math.max(rs,map.get(k));
                    }
                }
            }
        }
        return rs;
    }
}
