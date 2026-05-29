package 灵神.图论.bfs;

import java.util.*;

public class FreedomTrail514 {

    public static void main(String[] args) {
        FreedomTrail514 ft=new FreedomTrail514();
        System.out.println(ft.findRotateSteps2("godding","gd"));
    }
    //我看是想的就是建图每个字母左右为邻居，然后从0开始bfs，找到一个之后再递归bfs找下一个字母，这样不行。比如"iotfo"找"fioot"这个例子，由于i左边和右边都是o，
    //如果去的左边的o再去找t就远了，所以要比较左旋和右旋之中找小的。
    //https://www.youtube.com/watch?v=NOgnlTXidSs 他是cache的递归，好理解

    public static int findRotateSteps(String ring, String key) {
        char[] r=ring.toCharArray();
        char[] k=key.toCharArray();
        int[][] memo=new int[ring.length()][key.length()];
        for(int[] m:memo){
            Arrays.fill(m,-1);
        }
        return dfs(0,r,k,0,memo);


    }
    //这里dfs代表的意义也不好想，caller那边直接return dfs作为答案，看起来好像dfs的意义是找到从ring的0开始到match所有key的值，那么假如说
    //找到了now开始到match key的index的字符时用的步数，然后呢？就要再dfs找now左边或者右边开始到下一个index的答案，并且你找到第一个match的key
    //的index并不代表是最优解，因为可能是ring有重复的字符也match key的index，那些option也要试。
    //但是这个memo似乎是代表从某个source到找到另一个index这个单点之间的距离。
    static int dfs(int now,char[] ring,char[] key,int index,int[][] memo){
        if(index==key.length){
            return 0;
        }
        if(memo[now%ring.length][index]!=-1){
            return memo[now%ring.length][index];
        }
        int rs=Integer.MAX_VALUE;
        for(int i=now;i<ring.length+now;i++){//注意这里是饶了一圈ring.length+now
            char c=ring[i%ring.length];
            if(c==key[index]){
                int dist1=Math.abs(now-i);
                int dist2=ring.length-dist1;
                int curmin= Math.min(dist1+1+dfs(i,ring,key,index+1,memo), dist2+1+dfs(i,ring,key,index+1,memo));
                rs=Math.min(rs,curmin); //这里也不是很好想，是要选2个dfs的中最小的那个，再和rs相比取小的，因为curmin只是当前i的，但是可能还有别的i也是一样的字符，所以大家都要比较再选最小的
            }
        }
        memo[now%ring.length][index]=rs;
        return rs;
    }
    //2/14/2026,想的还是bfs原来是不行的，见上面的解释。太难了算了。
    public int findRotateSteps2(String ring, String key) {
        int[][] memo=new int[ring.length()][key.length()];
        for (int i=0;i<memo.length;i++){
            Arrays.fill(memo[i],-1);
        }
        return   dfs2(0,ring,0,key,0,memo);

    }

    int dfs2(int source,String ring,int target,String key,int cur,int[][] memo){


    }

}
