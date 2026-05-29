package SomeInterviews.bloomberg;

import java.util.*;

public class TwoCityScheduling1209 {
    static void main() {
        int[][] a={{10,20},{30,200},{400,50},{30,20}};
        System.out.println(TwoCityScheduling1209.twoCitySchedCost(a));
    }
    //4/29/2026,我以为是分别sort两地的cost然后从头看谁最小就选谁，选了这个人自然他就不可能飞另一个地方了，同时这边的移到下一个candidate
    //但其实是错的，应该比较机会成本，反例[1,100],[2,3],[100,4],[5,100]。按我的逻辑就是0，1都选了去A，则3，4必须去B，因此不是最优解。

//https://leetcode.com/problems/two-city-scheduling/solutions/667786/java-c-python3-with-detailed-explanation-5d8q/
    public static int twoCitySchedCost(int[][] costs) {
        int[] diff=new int[costs.length];
        int rs=0;
        for (int i=0;i<costs.length;i++){
            diff[i]=costs[i][0]-costs[i][1];//a cost-b cost,如果是正数则说明a cost大，去b的话要改成a就要补钱,负数的话就是可以拿回钱
            rs+=costs[i][1];
        }
        Arrays.sort(diff);//负数肯定排前面了，因此这些人应该改去A
        for (int i=0;i<costs.length/2;i++){
            rs+=diff[i];
        }
        return rs;

    }
}
