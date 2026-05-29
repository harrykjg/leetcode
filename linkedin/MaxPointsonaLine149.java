package linkedin;

import java.awt.*;
import java.util.*;

public class MaxPointsonaLine149 {
    static void main() {

    }
    //2/9/2026单单确定斜率还不够吧，如果是平行线咋办，得斜率加上一个点才能确定是否一条直线上,那得map里面再加一个map，里面的key是点，value是set装
    // 的是另一堆点？maybe可以，但是看回以前是每一个outer forloop里创建一个新的map，里面value就是count就行了，因为固定一个点之后不会遇到重复的点
    public int maxPoints(int[][] points) {
        if(points.length==0){
            return 0;
        }
        int rs=1;

        for (int i=0;i<points.length;i++){
            Map<Double, Integer> map=new HashMap<>();
            for (int j=0;j<points.length;j++){
                if(i==j){
                    continue;
                }
                double k=0;
                if(points[j][0]-points[i][0]!=0){
                    k=(double) (points[j][1]-points[i][1])/(double) (points[j][0]-points[i][0]);//不转成double的话不行
                    if(!map.containsKey(k)){
                        map.put(k,2);
                    }else{
                        map.put(k,map.get(k)+1);
                    }
                }else{
                    k=(double)Integer.MAX_VALUE;
                    if(!map.containsKey(k)){
                        map.put(k,2);
                    }else {
                        map.put(k,map.get(k)+1);
                    }
                }
                rs=Math.max(rs,map.get(k));

            }
        }
        return rs;
    }


}
