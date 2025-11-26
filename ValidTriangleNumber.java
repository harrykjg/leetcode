import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ValidTriangleNumber {
    //8/9/2021 暴力dfs法超时
    //形成三角形的条件：两边之和大于第三边，两边之差小于第三边
    //https://leetcode.com/problems/valid-triangle-number/discuss/128135/A-similar-O(n2)-solution-to-3-Sum 看他的解释
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int rs=0;
        for (int i=nums.length-1;i>=2;i--){
            int l=0;
            int r=i-1;
            int a=nums[i];
            while (l<r){
                int b=nums[l];
                int c=nums[r];
                if (b+c>a){//我以为是要a+b>c&&b+c>a&&a+c>b。结果只要b+c>a就行了
                    rs+=r-l;//注意不是break，而是r左移。
                    r--;
                }else {
                    l++;
                }
            }
        }

        return rs;
    }
    //8/15/2021 忘了是右边先确定一个点，左边的话貌似不好做(没想明白）.其实还是忘了left和right还需要把right左移
    public int triangleNumber2(int[] nums) {
        Arrays.sort(nums);
        int rs=0;
        for (int i=nums.length-1;i>=2;i--){
            int l=0;
            int r=i-1;
            int a=nums[i];
            while (l<r){
                int b=nums[l];
                int c=nums[r];
                if (b+c>a){//我以为是要a+b>c&&b+c>a&&a+c>b。结果只要b+c>a就行了
                    rs+=r-l;//注意不是break，而是r左移。
                    r--;
                }else {
                    l++;
                }
            }
        }

        return rs;
    }

}
