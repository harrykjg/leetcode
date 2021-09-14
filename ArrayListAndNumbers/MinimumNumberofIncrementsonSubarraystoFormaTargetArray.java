package ArrayListAndNumbers;

public class MinimumNumberofIncrementsonSubarraystoFormaTargetArray {
    //9/5/2021 不会  就是看前一个元素的大小时候大于等于后面的一个，大于的话就说明前面上升的次数可以cover后面的，就不用再算了，否则就说明后面的元素要加上
    //前面这个元素和后面元素的差值，因为是连续的，所以每次都要把pre更新成当前元素再去看下一个
    //https://leetcode.com/problems/minimum-number-of-increments-on-subarrays-to-form-a-target-array/discuss/754623/Detailed-Explanation
    //https://leetcode.com/problems/minimum-number-of-increments-on-subarrays-to-form-a-target-array/discuss/754682/Wall-of-bricks
    public int minNumberOperations(int[] target) {
        int pre=target[0];
        int rs=pre;
        for (int i=1;i<target.length;i++){
            if (target[i]>pre){
                rs+=target[i]-pre;
            }
            pre=target[i];
        }
        return rs;
    }
}
