import java.util.ArrayList;
import java.util.List;

/**
 * Created by yufengzhu on 8/6/18.
 */
public class MajorityElementII {
    //以前的代码, 还是很神奇
    //https://blog.csdn.net/xudli/article/details/46784149
    //https://blog.csdn.net/novostary/article/details/47680171 有更高级的follow up
    public static List<Integer> majorityElement(int[] n) {
        List<Integer> rs = new ArrayList<Integer>();
        if (n.length < 1) {

            return rs;
        }
        if (n.length < 2) {
            rs.add(n[0]);
            return rs;
        }

        int m1 = n[0];
        int m2 = n[0];
        int count1 = 0;
        int count2 = 0;
        for (int i = 0; i < n.length; i++) {
            if (n[i] == m1) {
                count1++;
                continue;
            }
            if (n[i] == m2) {
                count2++;
                continue;
            }
            if (count1 == 0) {
                m1 = n[i];
                count1 = 1;
                continue;
            }
            if (count2 == 0) {
                m2 = n[i];
                count2 = 1;
                continue;
            }
            count1--;
            count2--;
        }
        count1=0;
        count2=0;
        for (int i = 0; i < n.length; i++) {
            if (n[i] == m1) {
                count1++;
            } else if (n[i] == m2) {
                count2++;
            }
        }
        if(count1>(n.length/3)){
            rs.add(m1);
        }
        if(count2>(n.length/3)){
            rs.add(m2);
        }

        return rs;

    }
}
