package 灵神.二分法.二分答案.求最大;

import java.util.ArrayList;
import java.util.Arrays;

public class FindtheMaximumNumberofMarkedIndices2576 {
    public static void main(String[] args) {
        int[] nums={9,2,5,4};
        System.out.println(maxNumOfMarkedIndices(nums));
    }

    //https://leetcode.cn/problems/find-the-maximum-number-of-marked-indices/solutions/2134078/er-fen-da-an-pythonjavacgo-by-endlessche-t9f5/
    //开始想的是排序然后看nums【i】的时候要找刚好大于2*nuns【i】的位置的数，但是这里不能有重复，所以处理下一个的时候不好排除已经使用的数。看灵神的思路，其实是假设如果有k对匹配的话
    //那么必然是存在2*nums[i]<=nums[n-k+i]，既最小的那个数和第倒数第k个数匹配。自己举例看看。因此可以二分on k来验证
    public static int maxNumOfMarkedIndices(int[] nums) {
        Arrays.sort(nums);
        int b=0;
        int e=nums.length/2;
        while (b+1<e){
            int m=e-(e-b)/2;
            boolean good=good(nums,m);
            if(good){
                b=m;
            }else{
                e=m;
            }
        }
        if(good(nums,e)){
            return e*2;
        }
        return b*2;
    }
   static boolean good(int[] nums,int k){
        for(int i=0;i<k;i++){
            if(2*nums[i]>nums[nums.length-k+i]){//不等式要想清楚
                return false;
            }
        }
        return true;
    }

}
