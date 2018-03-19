package Advance1;

/**
 * Created by 502575560 on 7/15/17.
 */
//2 pointer
public class MinimumSizeSubarraySum {
    //题目和maximum subarry一类,但解法却不相似,这个是2 pointer
    //草,写的很不好,就是while里应该如何操作其实没有想清楚
    public static void main(String[] args){
        int[] n=new int[]{2,3,1,2,4,3,};
        System.out.println(minSubArrayLen2(15,n));
    }
    public static int minSubArrayLen(int s, int[] nums) {
        int rs=Integer.MAX_VALUE;
        if(nums.length==0){
            return 0;
        }
        int cur=0;
        int b=0;
        int e=0;
        while (e<nums.length&&b<=e){
            cur+=nums[e];
            if(cur<s){
                e++;
                continue;
            }

            while (cur-nums[b]>=s){
                cur-=nums[b];
                b++;
            }
            rs=Math.min(e-b+1,rs);
            e++;
        }
        return cur>=s?rs:0;

    }
    //3/19/2018,九章第二轮,还是写的不好
    public static int minSubArrayLen2(int s, int[] nums) {
        if(nums.length==0){
            return -1;
        }
        if(nums[0]>=s){
            return 1;
        }
        int rs=Integer.MAX_VALUE;
        int i=0;
        int j=0;
        int cur=0;
        while (i<nums.length&&j<nums.length){

            while (j<nums.length){
                cur+=nums[j];
                if(cur>=s){ //之前少些了等号就错了
                    break;
                }
                j++;
            }
            while (cur-nums[i]>=s&&i<j){//不屑i<j也行
                cur-=nums[i];
                i++;
            }

            if(cur>=s&&j-i+1<rs){
                rs=j-i+1;
            }

            j++;
        }
        if(rs==Integer.MAX_VALUE){
            return -1;
        }
        return rs;
    }
}
