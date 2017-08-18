package Advance7;

import java.util.ArrayList;

/**
 * Created by 502575560 on 8/9/17.
 */
public class SubarraySum {
    public static void main(String[] args){
        subarraySum(new int[]{-3,1,2,-3,4});
    }
    //这题自己写只能写出n方的方法,Hashmap的方法太屌了很难想到
    //https://segmentfault.com/a/1190000004883239
    public static ArrayList<Integer> subarraySum(int[] nums) {
        // write your code here

        int sum=0;
        for(int i=0;i<nums.length;i++){
            ArrayList<Integer> al=new ArrayList<>();
            al.add(i);
            sum=0;
            sum+=nums[i];
            if(sum==0){
                al.add(i);
                return al;
            }
            for(int j=i+1;j<nums.length;j++){
                if(sum+nums[j]==0){
                    al.add(j);
                    return al;
                }
                sum+=nums[j];
            }
        }
        return new ArrayList<Integer>();
    }
}
