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
    //2/15/2026 还是想的是并查集，但是灵神说不行。例如炸弹 0 可以引爆炸弹 2，炸弹 1 可以引爆炸弹 2，对应有向边 0→2，1→2，
    // 那么正确答案是 2。如果用并查集做的话，会把 0,1,2 三个点合并起来，计算出错误的答案 3。
    //和695题很像，都是用全局变量不太行的，要用dfs返回值的写法,比如图：0 -> 1, 0 -> 2, 1 -> 3, 1 -> 4（总共能引爆 5 个）比如dfs从0 到1，此时count=2
    //再dfs 1，再到3和4，此时count是4，但是返回0之后，再到2，而这一层的count只是2，遇到2，count++的话也只是3，就不对吧
    //https://leetcode.cn/problems/detonate-the-maximum-bombs/solutions/1152450/jian-tu-bao-li-mei-ju-suo-you-qi-dian-by-h4mj/

    public int maximumDetonation2(int[][] bombs) {
        int rs=0;
        for (int i=0;i<bombs.length;i++){
            boolean[] memo=new boolean[bombs.length];
            int count=dfs2(i,bombs,memo);
            rs=Math.max(rs,count);
        }
        return rs;
    }
    int dfs2(int index,int[][] bombs,boolean[] memo){
        memo[index]=true;
        int[] cur=bombs[index];
        int rs=1;

        for (int i=0;i<bombs.length;i++){
            if(i==index){
                continue;
            }

            long dist=(long)Math.pow(bombs[i][0]-cur[0],2)+(long)Math.pow(bombs[i][1]-cur[1],2);
            long range=(long)Math.pow(cur[2],2);
            if(range>=dist&&!memo[i]){
                rs+=dfs2(i,bombs,memo);
            }
        }
        return rs;
    }
}
