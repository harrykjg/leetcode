package binarysearch;

public class CapacityToShipPackagesWithinDDays {
    //10/4/2021 居然一次过，但是不太好写
    public int shipWithinDays(int[] weights, int days) {
        int sum=0;
        for(int w:weights){
            sum+=w;
        }
        int min=sum/days;//其实min应该是weights里最大的那个，但是这样算出来的min也对，就是可能会比weights里最大的那个小
        while(min<sum){
            int m=min+(sum-min)/2;
            if(valid(m,weights,days)){
                sum=m;
            }else{
                min=m+1;
            }
        }
        return min;
    }
    boolean valid(int m,int[] weights, int days){//这个有点恶心。其实就是好算按m 容量的船，不断塞weight，塞不下就加一天，算出需要的天数，看是否小于等于days
        int count=0;
        int i=0;
        int cur=0;
        while(i<weights.length){
            while(i<weights.length&&cur+weights[i]<=m){
                cur+=weights[i];
                i++;
            }
            count++;
            cur=0;
            if(count>days){
                return false;
            }
        }
        return true;
    }
}
