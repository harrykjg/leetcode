package SomeInterviews.tiktok;

public class GasStation134 {
    //6/14/2026，自己只能想到n方的复杂度，这是参考别人的好的解法
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int total=0;//记录所有的油-cost
        int cur=0;
        int start=0;
        for (int i=0;i<gas.length;i++){
            total+=gas[i]-cost[i];
            cur=gas[i]-cost[i];
            if(cur<0){
                start=i+1;//暂设成成下一个
                cur=0;
            }
        }
        if(total<0){//肯定走不了圈
            return -1;
        }
        return start;
    }
}
