import java.util.*;

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
//7/11/2021.,想到是dp，就是dp的意义错了，i位置不应该是0~i-1最大的.而是i位置必须选，能得到最大的。以前写的啥看不懂
    public int maxEnvelopes2(int[][] env) {
        List<int[]> list=new ArrayList<>();
        for (int[] e:env){ ;
            list.add(e);
        }
        Collections.sort(list, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]+o2[1]-o2[0]-o2[1];//这个按长先排宽后排也行
            }
        });
        int[] dp=new int[env.length];
        Arrays.fill(dp,1);
        int rs=1;
        for (int i=1;i<dp.length;i++){
            for (int j=i-1;j>=0;j--){
                if (list.get(i)[0]>list.get(j)[0]&&list.get(i)[1]>list.get(j)[1]){
                    dp[i]=Math.max(dp[j]+1,dp[i]);
                    rs=Math.max(rs,dp[i]);
                }//注意没有else，else的话就是啥也不做
            }

        }
        return rs;

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