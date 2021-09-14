package contest;

import java.util.*;

public class MaximumNumberofWeeksforWhichYouCanWork {
    public static void main(String[] args){
        MaximumNumberofWeeksforWhichYouCanWork mn=new MaximumNumberofWeeksforWhichYouCanWork();
        mn.numberOfWeeks(new int[]{5,2,1});
    }
    //按taskchedlue那样写会超时。这里就是找出最大的那个数，那个数能形成n个空位，在统计剩余的数的和，看能填几个空
    public long numberOfWeeks(int[] milestones) {
        int max = 0;
        int index = 0;
        for (int i = 0; i < milestones.length; i++) {
            if (milestones[i] > max) {
                index = i;
                max = milestones[i];
            }
        }
        long total = 0;
        long remain = 0;
        for (int i = 0; i < milestones.length; i++) {
            total += milestones[i];
            if (i != index) {
                remain += milestones[i];
            }
        }
        if (remain >= max) {
            return total;
        }
        return remain * 2 + 1 > total ? total : remain * 2 + 1;
    }
}
