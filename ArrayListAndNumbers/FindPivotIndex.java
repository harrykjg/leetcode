package ArrayListAndNumbers;

public class FindPivotIndex {
//9/16/2021自己想的就是prefixsum ，然后从左到右连加维护一个sum。遇到每个点就看看cursum是否等于右边那一段的和就行了，其实不需要prefixsum，直接先算一个total就行了
    public int pivotIndex(int[] nums) {
        int sum=0;
        for (int i=0;i<nums.length;i++){
            sum+=nums[i];
        }
        int cur=0;
        for (int i=0;i<nums.length;i++){
            if (cur==sum-cur-nums[i]){
                return i;
            }
            cur+=nums[i];
        }
        return -1;

    }
}
