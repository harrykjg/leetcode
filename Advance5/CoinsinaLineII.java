package Advance5;

/**
 * Created by yufengzhu on 4/28/18.
 */
public class CoinsinaLineII {
    //4/28/2018，自己想了感觉要i-1，i-2，i-3，i-4的状态，觉得麻烦没想了
    public boolean firstWillWin(int[] values) {
        if(values.length<=2){
            return true;
        }
        int[] dp1=new int[values.length];
        boolean[] dp2=new boolean[values.length];
        dp1[0]=values[0];
        dp1[1]=values[0]+values[1];
        dp2[0]=true;
        dp2[1]=true;
        int total=values[0]+values[1];
        for(int i=2;i<values.length;i++){
            total+=values[i];
            if(dp2[i-1]&&dp2[i-2]){
                dp2[i]=false;
            }
            if(dp2[i]){
                dp1[i]=Math.max(dp1[i-1],dp1[i-2])+values[i];
            }else{
                dp1[i]=Math.max(dp1[i-1],dp1[i-2]);
            }
        }
    }
}
