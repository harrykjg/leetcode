package SomeInterviews.snowflake;

public class PersonCake {
    /*
    第一题：1D数组，每个位置可填{0,1,2}，0代表empty，1代表person，2代表cake，求person和cake间最小距离。
    解题思路：two pointer；从左向右遍历一次即可，每次遇到person或cake就尝试更新最小距离
     */
    public int findDist(int[] nums){
        int rs=Integer.MAX_VALUE;
        int p=-1;
        int c=-1;
        for (int i=0;i<nums.length;i++){
            if(nums[i]==1){
                p=i;
            }else if(nums[i]==2){
                c=i;
            }
            if(p!=-1&&c!=-1){
                rs=Math.min(rs,Math.abs(p-c));
            }
        }
        return rs==Integer.MAX_VALUE?-1:rs;
    }
}
