package 灵神.贪心;

public class MinimumTimetoCompleteAllDeliveries3773 {
    static void main() {

    }
    //1/4/2026不会，原来是数学题,找规律计算。感觉没什么通用性，算了
    //https://leetcode.cn/problems/minimum-time-to-complete-all-deliveries/solutions/3821373/liang-chong-fang-fa-er-fen-da-an-shu-xue-vyqv/
    public long minimumTime(int[] d, int[] r) {
        long rs=0;
        long need1=(long)Math.floor(d[0]/(double)(r[0]-1))*r[0];
        if(d[0]%r[0]==0){
            need1-=1;
        }else{
            need1+=d[0]%r[0];
        }
        long need2=(long)Math.floor(d[1]/(double)(r[1]-1))*r[1];
        if(d[1]%r[1]==0){
            need2-=1;
        }else{
            need2+=d[1]%r[1];
        }
        long charge1=(long)Math.floor(d[0]/(double)(r[0]-1));
        long charge2=(long)Math.floor(d[1]/(double)(r[1]-1));

    }
}
