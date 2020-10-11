package contest;

public class MaximumProfitOperatingaCentennialWheel {
    //自己想的，基本一次过
    public int minOperationsMaxProfit(int[] customers, int boardingCost, int runningCost) {
        int cur=0;//当前盈利
        int rotate=0;//上轮带下来的
        int max=0;//最大盈利
        int rs=-1;//结果轮数
        int i=0;//当前轮数
        for( i=0;i<customers.length;i++){
            if(customers[i]+rotate>4){
                cur+=boardingCost*4;
                cur-=runningCost;
                rotate=customers[i]+rotate-4;
            }else if(customers[i]+rotate>0){
                cur+=boardingCost*(customers[i]+rotate);
                cur-=runningCost;
                rotate=0;
            }
            if(cur>max){//如果是最大盈利的话才更新轮数，如果一直没盈利那么default结果轮数就是负的
                max=cur;
                rs=i+1;
            }
        }
        while (rotate>0){
            if(rotate>=4){
                cur+=boardingCost*4-runningCost;
                rotate-=4;
            }else{
                cur+=boardingCost*rotate-runningCost;
                rotate=0;
            }
            if(cur>max){
                max=cur;
                rs=i+1;
            }
            i++;
        }
        return rs;
    }
}
