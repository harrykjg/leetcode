import java.util.LinkedList;
import java.util.Queue;

public class RLEIterator {
    public static void main(String[] args){
        RLEIterator rl=new RLEIterator(new int[]{3,8,0,9,2,5});
        System.out.println(rl.next(2));
        System.out.println(rl.next(1));
        System.out.println(rl.next(1));
        System.out.println(rl.next(2));

    }
    //9/7/2021 暴力不行,就写个queue装pair的，边走边peek queue里看元素的count
    //https://leetcode.com/problems/rle-iterator/discuss/168294/Java-Straightforward-Solution-O(n)-time-O(1)-space 这个直接不用q，直接操作原数组就完了
    Queue<Pair> q=new LinkedList<>();
    public RLEIterator(int[] encoding) {
        for (int i=0;i<encoding.length-1;i+=2){
            if (encoding[i]<=0){
                continue;
            }
            q.offer(new Pair(encoding[i],encoding[i+1]));
        }
    }

    public int next(int n) {
        if (q.isEmpty()){
            return -1;
        }
        int rs=0;
        while (!q.isEmpty()&&n>0){
            Pair p=q.peek();

            if (p.count>=n){
                rs=p.v;
                p.count=p.count-n;
                n=0;
                if (p.count==0){
                    q.poll();
                }
            }else {
                n-=p.count;
                q.poll();
            }
        }
        if (n>0){
            return -1;
        }
        return rs;
    }
    class Pair{
        int count;
        int v;
        public Pair(int count,int v){
            this.count=count;
            this.v=v;
        }
    }
}
