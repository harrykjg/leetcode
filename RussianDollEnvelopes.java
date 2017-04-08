import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by 502575560 on 11/13/16.
 */
public class RussianDollEnvelopes {

    //开始自己想以为是动态规划,后来感觉是图算法穷举找路径
    //然后暴力法也能过..
    //http://blog.csdn.net/jmspan/article/details/51688907
    //http://www.cnblogs.com/grandyang/p/5568818.html

    public static void main (String[] args){
        RussianDollEnvelopes rd=new RussianDollEnvelopes();
//        int[][] env={{2,100},{3,200},{4,300},{5,500},{5,400},{5,250},{6,370},{6,360},{7,380}};
        int[][] env={{6,1},{6,10},{13,2},{11,14},{16,14}};
        System.out.println(rd.maxEnvelopes(env));
    }

    public int maxEnvelopes(int[][] env) {
        if(env.length==0){
            return 0;
        }
        ArrayList<envelop> al=new ArrayList<envelop>();
        for(int i=0;i<env.length;i++){
            al.add(new envelop(env[i][0],env[i][1]));
        }
        Collections.sort(al);
        int[] dp=new int[env.length];
        dp[0]=1;
        int max=1;
        for(int i=1;i<env.length;i++){
            int j=i-1;
            int tempmax=1;

            while(j>=0){
                if(al.get(i).a>al.get(j).a&&al.get(i).b>al.get(j).b){
                    tempmax=Math.max(tempmax,dp[j]);
                    dp[i]=Math.max(tempmax+1,dp[i]);
                }
                j--;
            }
            max=Math.max(max,dp[i]);
        }
        return max;

    }

}
class envelop implements Comparable{
    public int a;
    public int b;
    public envelop(int a,int b){
        this.a=a;
        this.b=b;
    }
    @Override
    public int compareTo(Object o) {
        return (this.a+this.b)-(((envelop)o).a+((envelop)o).b);
    }
}