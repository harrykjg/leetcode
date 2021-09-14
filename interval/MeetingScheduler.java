package interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MeetingScheduler {
//9/13/2021  直接写的，就是2pointer比较。可以更简单，见答案一，他不管谁的begin在谁的中间，反正就搞出来2个slot最右边的begin和最左边的end，如果有交集
    //则end-begin是正数，否则是负数。
public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
    Arrays.sort(slots1,(a,b)->a[1]-b[1]);
    Arrays.sort(slots2,(a,b)->a[1]-b[1]);
    List<Integer> rs=new ArrayList<>();
    int i=0;
    int j=0;
    while (i<slots1.length&&j<slots2.length){
        if (slots1[i][0]>=slots2[j][0]&&slots1[i][0]<=slots2[j][1]){//slot1的begin在2的中间
            int begin=slots1[i][0];
            int end=Math.min(slots1[i][1],slots2[j][1]);
            if (end-begin>=duration){
                rs.add(begin);
                rs.add(begin+duration);
                return rs;
            }else {
                if (slots1[i][1]<slots2[j][1]){
                    i++;
                }else {
                    j++;
                }
            }

        } else if (slots2[j][0]>=slots1[i][0]&&slots2[j][0]<=slots1[i][1]){//slot2的begin在1的中间
            int begin=slots2[j][0];
            int end=Math.min(slots1[i][1],slots2[j][1]);
            if (end-begin>=duration){
                rs.add(begin);
                rs.add(begin+duration);
                return rs;
            }else {
                if (slots1[i][1]<slots2[j][1]){
                    i++;
                }else {
                    j++;
                }
            }

        }else {
            if (slots1[i][1]<slots2[j][1]){
                i++;
            }else {
                j++;
            }
        }
    }
    return rs;
}

}
