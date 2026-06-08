package SomeInterviews.databricks;

import java.util.Arrays;

public class LongestMountaininArray845 {
    static void main() {
        int[] a={0,2,0,2,1,2,3,4,4,1};
        System.out.println(longestMountain3(a));
    }
    //3/21/2026 开始想的是单调栈，后来想的是dp，还不是最优。最优实际上就是1pass，边走边记录上升长度和下降长度。不太好想
    //https://leetcode.com/problems/longest-mountain-in-array/solutions/135593/cjavapython-1-pass-and-o1-space-by-lee21-xb4b/
    public static int longestMountain(int[] arr) {
        if(arr.length<3){
            return 0;
        }
        int[] dp1=new int[arr.length];
        int[] dp2=new int[arr.length];
        dp1[0]=1;
        dp2[dp2.length-1]=1;
        for (int i=1;i<arr.length;i++){
            if(arr[i]>arr[i-1]){
                dp1[i]=dp1[i-1]+1;
            }else{
                dp1[i]=1;
            }
        }
        for (int i=arr.length-2;i>=0;i--){
            if(arr[i]>arr[i+1]){
                dp2[i]=dp2[i+1]+1;
            }else{
                dp2[i]=1;
            }
        }
        int rs=0;
        for (int i=0;i<dp1.length;i++){
            if(dp1[i]+dp2[i]-1>rs&&dp1[i]>1&&dp2[i]>1){//开始漏了dp1[i]>1&&dp2[i]>1，就会导致单边上升的也是合法答案
                rs=dp1[i]+dp2[i]-1;
            }
        }
        if(rs<3){
            return 0;
        }
        return rs;
    }
    public static int longestMountain2(int[] arr) {
        int rs=0;
        int up=0;
        int down=0;
        for (int i=1;i<arr.length;i++){
            if((down>0&&arr[i]>=arr[i-1])||arr[i]==arr[i-1]){//这里容易想不清楚，是什么时候才rest，是遇见下坡之后（即down>0）再上坡才reset，
                                    // 纯下坡不reset,还有要加上||arr[i]==arr[i-1]，不然相等的时候就不能正确reset，如0,2,0,2,1,2,3,4,4,1
                down=0;
                up=0;
            }
            if(arr[i]>arr[i-1]){
                up++;
            }
            if(arr[i]<arr[i-1]){
                down++;
            }
            if(up+down+1>rs&&up>0&&down>0){
                rs=Math.max(up+down+1,rs);
            }
        }
        return rs;
    }
    //5/28/2026自己写的改了几次，就是左扫一次又扫一次，右扫的时候就用一个变量就行了
    public static int longestMountain3(int[] arr) {
        int[] left=new int[arr.length];
        Arrays.fill(left,1);
        for (int i=1;i<arr.length;i++){
            if(arr[i]>arr[i-1]){
                left[i]=left[i-1]+1;
            }
        }
        int rs=0;
        int right=1;
        for (int i=arr.length-2;i>=0;i--){
            if(arr[i]>arr[i+1]){
                right++;
            }else{//这个容易漏
                right=1;
            }
            if(left[i]>1&&right>1){//这个条件能防止只有单边上升或下降的情况，也能保证长度必须大于等于3
                rs=Math.max(rs,left[i]+right-1);
            }
        }
        return rs;
    }
}
