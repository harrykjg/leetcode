package contest;

import java.util.Arrays;
import java.util.Comparator;

public class BestTeamWithNoConflicts {
    public static void main(String[] args) {
        BestTeamWithNoConflicts bt=new BestTeamWithNoConflicts();
        int[] a={1,1,2,2,3,3,3,3,3,3};
        int[] s={5,5,2,6,3,3,3,3,3,3};
        bt.bestTeamScore(s,a);
    }
    //没想到要sort,看别人的答案,sort之后的dp也不好写，dp[i]的意义是必定要取第i位元素的时候的0-i的最大值
    //https://leetcode.com/problems/best-team-with-no-conflicts/discuss/899631/Java-first-double-sort-then-DP
    //https://leetcode.com/problems/best-team-with-no-conflicts/discuss/899475/Fairly-easy-DP 他说了和longest increasing subsquence有点像
    public int bestTeamScore(int[] scores, int[] ages) {
        int[] dp=new int[ages.length];
        int[][] pair=new int[ages.length][2];
        for(int i=0;i<ages.length;i++){
            pair[i][0]=ages[i];
            pair[i][1]=scores[i];
        }
        Arrays.sort(pair, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]==o2[0]){
                    return o1[1]-o2[1];
                }
                return o1[0]-o2[0];
            }
        });

        dp[0]=pair[0][1];//dp[i]的意义是必定要取第i位元素的时候的0-i的最大值,不然的话，dp【i】的值如果不一定是要取第i位的话，比如i+1时要取dp【i】
        //的值会出错，还是不好想，举个例子
        int rs=dp[0];
        //[1,1,2,2,3,3,3,3,3,3] ages
        //[5,5,2,6,3,3,3,3,3,3] scores 答案20
        for(int i=1;i<pair.length;i++){
            int temp=pair[i][1];//相当于i进来了自己组一个团
            for(int j=0;j<i;j++){
                if(pair[i][1]>=pair[j][1]){//如果新的i的值大于之前的某个值，则我可以取以下2种情况中的大的那个，1：取i并且取j（取j即取0-j中必须取j的最大值即dp[j]）
                                                //2：不取这个j，这里是关键，为啥不取j呢，因为取j的话有可能导致取不了0到j中间的某个值，导致结果变小
                                                //比如现在i=3，则可取5+5+6=16，如果取了j=2，则dp【j】的值是取不了了前两个5的，所以小了
                    temp=Math.max(pair[i][1]+dp[j],temp);
                }

            }
            dp[i]=temp;
            rs=Math.max(rs,dp[i]);
        }
        return rs;
    }
}
