package 灵神.图论.dfs;

public class DetonatetheMaximumBombs2101 {
    public static void main(String[] args) {
        int[][] nums={{2,1,3},{6,1,4}};
        System.out.println(maximumDetonation(nums));
    }

    //写的不太好，就是实际上是左边爆不到右边不代表右边爆不到左边，因此每个点都要重开dfs和重开memo，然后就是count要+=dfs下一个
    static int rs=1;
    public static int maximumDetonation(int[][] bombs) {
        boolean[] memo=new boolean[bombs.length];
        for(int i=0;i<bombs.length;i++){
            dfs(i,bombs,new boolean[bombs.length]);

        }
        return rs;
    }
    static int dfs(int b,int[][] bombs,boolean[] memo){
        memo[b]=true;
        int cur=1;
        for(int i=0;i<bombs.length;i++){
            if(i==b){
                continue;
            }
            long range= (long) Math.pow(bombs[b][2],2);//之前搞错了，就是b的爆炸半径，不是b加i的爆炸半径
            //圆心距离
            long dist= (long) Math.abs(Math.pow(bombs[b][0]-bombs[i][0],2)+(Math.pow(bombs[b][1]-bombs[i][1],2)));
            if(dist>range){
                continue;
            }
            //会相交
            if(!memo[i]){
                cur+=dfs(i,bombs,memo);

            }
        }
        rs=Math.max(rs,cur);
        return cur;
    }
}
