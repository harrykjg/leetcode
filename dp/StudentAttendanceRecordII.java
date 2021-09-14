package dp;

public class StudentAttendanceRecordII {
    //9/10/2021 dp太难了，dfs参考别人的
    //https://leetcode.com/problems/student-attendance-record-ii/discuss/881790/Java-DFS-to-DP-on-index-late-and-absent-state
    //https://leetcode.com/problems/student-attendance-record-ii/discuss/379148/Java-DFS
    int mod=(int)Math.pow(10,9)+7;
    public int checkRecord(int n) {
        int[][][] memo=new int[n+1][2][3];//代表已经过了i天时，已经用了几次absent和几次late的状态下，剩下n-i天有多少种组合可以走。有点绕
        int rs=0;
        rs=dfs(0,0,0,n,memo)%mod;
        return rs%mod;
    }
    int dfs(int cur,int absent,int late,int n,int[][][] memo){
        if (cur>=n){//开始写成0，1是代表最终成为一个path
            return 1;
        }
        if (memo[cur][absent][late]!=0){
            return memo[cur][absent][late];
        }
        int rs=0;
        if (absent==0){//没有缺席的话，可以用一次缺席
            rs+=dfs(cur+1,1,0,n,memo)%mod;//这里很容易错。用了非late则说明连续的late被打断了，late就应该为0
            rs%=mod;//缺了这个也会错，因为rs加这个玩意虽然已经mod了还是可能会越界
        }
        if (late<2){//小于2的late的话，可以用一次late
            rs+=dfs(cur+1,absent,late+1,n,memo)%mod;
            rs%=mod;
        }
        rs+=dfs(cur+1,absent,0,n,memo)%mod;//用一次present。
        rs%=mod;
        memo[cur][absent][late]=rs;
        return rs;
    }
}