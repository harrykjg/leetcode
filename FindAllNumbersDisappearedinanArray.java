import java.util.ArrayList;
import java.util.List;

/**
 * Created by yufengzhu on 8/6/18.
 */
public class FindAllNumbersDisappearedinanArray {
    //o(n)的方法一点也不容易想
    //https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/discuss/92956/Java-accepted-simple-solution
    //https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/discuss/93007/Simple-Java-In-place-sort-solution
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ret = new ArrayList<Integer>();

        for(int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;//比如[4,3,2,7,8,2,3,1]，i=0时，4的index应该是3，那么就把7变成-7，再i=1，把2变成-2，数组变成4，3，-2，-7，8，2，3，1，i=2继续4，-3，-2，-7，8，2，3，1
            if(nums[index] > 0) {            //i=3时，4，-3，-2，-7，8，2，-3，1，i=4时，4，-3，-2，-7，8，2，-3，-1，i=5时，不变，i=6时，-4，-3，-2，-7，8，2，-3，-1，i=7时，不变，就这么神奇的找到答案了
                nums[index] = -nums[index];  //思想就是，我现在见到一个数，就把他的值-1就是他的index那个位置的值变成负数，那么没有见到的那个数他的值-1那个位置上的数字就不会变成负数了，第二遍循环就能找出他了
            }
        }

        for(int i = 0; i < nums.length; i++) {
            if(nums[i] > 0) {
                ret.add(i+1);
            }
        }
        return ret;
    }
}
