package 灵神.贪心;

import java.util.Collections;
import java.util.List;

public class MinimumProcessingTime2895 {
    static void main() {

    }
    public int minProcessingTime(List<Integer> processorTime, List<Integer> tasks) {
        int rs=Integer.MIN_VALUE;
        Collections.sort(processorTime);
        Collections.sort(tasks,Collections.reverseOrder());
        int p=0;
        for(int i=0;i<tasks.size();i+=4){
            rs=Math.max(rs,processorTime.get(p++)+tasks.get(i));
        }
        return rs;
    }
}
