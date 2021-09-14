package contest;

public class MaximumCompatibilityScoreSum {
    int rs=0;
    public int maxCompatibilitySum(int[][] students, int[][] mentors) {
        boolean[] memo=new boolean[students.length];
        dfs(0,0,students,mentors,memo);
        return rs;
    }
    void dfs(int s,int cur,int[][] students,int[][] mentors,boolean[] memo){
        if (s==students.length){
            rs=Math.max(rs,cur);
            return;
        }
        for (int i=0;i<mentors.length;i++){
            if (memo[i]){
                continue;
            }
            memo[i]=true;
            int temp=0;
            for (int j=0;j<mentors[i].length;j++){
                temp+=students[s][j]==mentors[i][j]?1:0;
            }
            dfs(s+1,cur+temp,students,mentors,memo);
            memo[i]=false;
        }
    }
}
