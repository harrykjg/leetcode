import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by 502575560 on 6/12/17.
 */
public class FrogJump {
    public static void main(String[] args){
        int[] a={0,1,3,6,10,15,16,21};
        System.out.println(canCross(a));
    }
    //自己想了一下,用dfs,比如从dp[2]推(i=2),可能他可以推出dp[3]dp[4]都能到达,然后到了i=3,dp[3]也能推出dp[4],那么说明dp[4]能被2种走法推出,那么到了i=4,
    //就要分别算这推到dp[4]的两种方法,他们有各自的k,再去推出后面的dp值,因此需要有一个list去存dp[i]的所有可能的上一步的k值,改了几次,爆栈了,然后arraylist改
    //成装hashet就accept了
    //http://www.2cto.com/kf/201610/556443.html
    //http://blog.csdn.net/mebiuw/article/details/52577052
    //http://www.cnblogs.com/grandyang/p/5888439.html
    public static boolean canCross(int[] stones) {
        boolean[] dp=new boolean[stones.length];
        dp[0]=true;
        ArrayList<HashSet<Integer>> al=new ArrayList<>();
        for(int i=0;i<stones.length;i++){
            HashSet<Integer> a=new HashSet<>();
            al.add(a);
        }
        al.get(0).add(0);
        for(int i=0;i<stones.length-1;i++){
            HashSet<Integer> candidate=al.get(i);
            for(Integer j:candidate){
                int k=i+1;//从当前石头往后开始一个个看能不能跳到
                while (true){
                    if(k==stones.length||stones[k]>stones[i]+j+1){//如果这个stones[k]值大于stones[i]+j+1,即从当前stone跳k+1步还到不了第k个石头的话,后面肯定跳不到了
                        break;
                    }
                    if(k<dp.length&&stones[k]==stones[i]+j+1){
                        dp[k]=true;
                        al.get(k).add(j+1);
                    }
                    if(j!=0&&k<dp.length&&stones[k]==stones[i]+j){
                        dp[k]=true;
                        al.get(k).add(j);
                    }
                    if(j-1>0&&k<dp.length&&stones[k]==stones[i]+j-1){
                        dp[k]=true;
                        al.get(k).add(j-1);
                    }
                    if(dp[k]==true&&k==dp.length-1){
                        return true;
                    }
                    k++;
                }
            }
        }
        return dp[dp.length-1];
    }
}
