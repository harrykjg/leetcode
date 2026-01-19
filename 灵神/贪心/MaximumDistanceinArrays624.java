package 灵神.贪心;

import java.util.List;

public class MaximumDistanceinArrays624 {
    static void main() {

    }
    //12/31/2025 不会，只能想到n方的。 正确的方法应该是枚举i作为右边的数组，同时维护i左边的数组的最小值和最大值，那么最大的差值就是
    //max（|leftMax-iMin|,|leftmin-iMax|）
    //https://leetcode.cn/problems/maximum-distance-in-arrays/solutions/3067679/mei-ju-you-wei-hu-zuo-pythonjavaccgojsru-wtgb/
    public int maxDistance(List<List<Integer>> arrays) {
        int rs=0;
        int lMax=arrays.get(0).get(arrays.get(0).size()-1);
        int lMin=arrays.get(0).get(0);
        for(int i=1;i<arrays.size();i++){
            int curMax=arrays.get(i).get(arrays.get(i).size()-1);
            int curMin=arrays.get(i).get(0);
            int gap=Math.max(Math.abs(curMin-lMax),Math.abs(curMax-lMin));
            rs=Math.max(rs,gap);
            lMax=Math.max(lMax,curMax);
            lMin=Math.min(lMin,curMin);
        }
        return rs;
    }
}
