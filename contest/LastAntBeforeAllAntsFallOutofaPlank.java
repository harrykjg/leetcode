package contest;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;

public class LastAntBeforeAllAntsFallOutofaPlank {
    public static void main(String[] args){
        LastAntBeforeAllAntsFallOutofaPlank la=new LastAntBeforeAllAntsFallOutofaPlank();
        int[] left=new int[]{1,4,5,10,9};
        int[] right=new int[]{2,7,6,3};
        System.out.println(la.getLastMoment(11,left,right));
    }
    //这题模拟的话做不出来，左右的蚂蚁可以相互交织的，基本上是脑经急转弯
    //https://leetcode.com/problems/last-moment-before-all-ants-fall-out-of-a-plank/discuss/720094/C%2B%2B-ants-continues-walking-after-meeting-each-other
    public int getLastMoment(int n, int[] left, int[] right) {

    }
}
