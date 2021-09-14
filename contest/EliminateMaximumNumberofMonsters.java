package contest;

import java.util.*;

public class EliminateMaximumNumberofMonsters {
    public static void main(String[] args){
        EliminateMaximumNumberofMonsters em=new EliminateMaximumNumberofMonsters();
        int[] dist={3,2,4};
        int[] speed={5,3,2};
        System.out.println(em.eliminateMaximum(dist,speed));
    }

    //不能用贪心法，比如当前最小的dist【i】但是i的速度很慢，所以很久也不会hit，而另一个dist大但是速度更大
    //所以要算所有monster最终到达0的时间，然后排序，这里因为他0时刻也能消灭monster所以不好理解
    public int eliminateMaximum(int[] dist, int[] speed) {
        double[] time=new double[dist.length];
        for (int i=0;i<dist.length;i++) {
            time[i]=dist[i]*1.0/speed[i];//比如dist【i】=2，speed=3，那么得出来的就是用0分钟到达，这样不好搞，他们用double的话就好搞了,比如算
            // 出0。6min到达，那么0分钟小于0。6分钟就是可以，现在到达1分钟了，发现下一个monster是0。7min到达，就是不行了
        }
        Arrays.sort(time);
        int rs=0;
        for (int i=0;i<time.length;i++){
            if (rs<time[i]){
                rs++;
            }else {
                return rs;
            }
        }
        return rs;

    }
}


