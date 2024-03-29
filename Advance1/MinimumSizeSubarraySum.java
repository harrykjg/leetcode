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
    //5/18/2020,就是缩放不难，改了几次过了
    public static int minSubArrayLen3(int s, int[] nums) {
        if(nums.length==0){
            return 0;
        }

        int l=0;
        int cursum=0;
        int rs=nums.length;
        for(int i=0;i<nums.length;i++){
            cursum+=nums[i];
            while (cursum>s&&l<i){
                if(cursum-nums[l]>=s){
                    cursum-=nums[l];
                    l++;
                }else {
                    break;
                }
            }
            if(cursum>=s){
                rs=Math.min(rs,i-l+1);
            }
        }
        if(cursum<s){
            return 0;
        }
        return rs;
    }

    //6/13/2021，自己想的用prefix sum再2pointer从2边夹逼，貌似行不通，因为夹逼的时候不知道是左边挪还是右边挪。而以前的是从头开始向右扩张然后缩放
    public static int minSubArrayLen4(int target, int[] nums) {
        int cursum=0;
        int b=0;
        int rs=Integer.MAX_VALUE;

        for (int i=0;i<nums.length;i++){
            cursum+=nums[i];
            while (b<i&&cursum-nums[b]>=target){//开始少写=
                cursum-=nums[b];
                b++;
            }
            if (cursum>=target){
                rs=Math.min(rs,i-b+1);//开始少写+1
            }
        }
        return rs==Integer.MAX_VALUE?0:rs;

    }

}
