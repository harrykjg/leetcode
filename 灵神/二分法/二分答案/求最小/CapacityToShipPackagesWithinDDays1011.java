package 灵神.二分法.二分答案.求最小;

public class CapacityToShipPackagesWithinDDays1011 {

    public static void main(String[] args) {

    }
    public int shipWithinDays(int[] weights, int days) {
        int b=1;
        int e=Integer.MIN_VALUE;
        int sum=0;//题目说了weight小于500
        for (int i:weights){
            sum+=i;
        }
        b=sum/days;
        e=sum;//假如day只有1，则这个船要全装
        while (b+1<e){
            int m=e-(e-b)/2;
            boolean good=good(weights,m,days);
            if(good){
                e=m;
            }else {
                b=m;
            }
        }
        if(good(weights,b,days)){
            return b;
        }
        return e;

    }
    boolean good(int[] nums,int cap,int days){
        int cur=cap;
        int i=0;
        while (i<nums.length){
            cur=cap;
            if(cap<nums[i]){
                return false;
            }
            while (i<nums.length&&cur-nums[i]>=0){
                cur-=nums[i];
                i++;
            }
            days--;
        }
        return days>=0;
    }

}
