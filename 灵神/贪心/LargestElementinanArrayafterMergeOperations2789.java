package 灵神.贪心;

public class LargestElementinanArrayafterMergeOperations2789 {
    static void main() {

    }
    //12/31/2025 以为是要dfs，结果看提示说只要从后面开始一直往前merge就行了。 巧妙的地方是如何绕开merge的时候原数组的元素会越界，就是用另外的一个long 变量代表
    //nums[i],而不用去更新nums[i]
    //https://leetcode.cn/problems/largest-element-in-an-array-after-merge-operations/solutions/2685894/cong-bao-li-mei-ju-dao-yi-ci-bian-li-pyt-jjta/
    public long maxArrayValue(int[] nums) {
        long rs=0;
        if(nums.length==1){
            return nums[0];
        }
        long merge=nums[nums.length-1];//这个变量就代表merge之后的nums[i]的值，巧妙
        for(int i=nums.length-2;i>=0;i--){
            if(nums[i]<=merge){
                merge+=nums[i];
            }else{
                merge=nums[i];
            }
            rs=Math.max(rs,merge);
        }
        return rs;
    }
}
