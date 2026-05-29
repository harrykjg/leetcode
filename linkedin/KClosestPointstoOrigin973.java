package linkedin;

import java.util.Collections;
import java.util.PriorityQueue;

public class KClosestPointstoOrigin973 {
    static void main() {
        KClosestPointstoOrigin973 kc=new KClosestPointstoOrigin973();
        int[][] points={{3,3},{5,-1},{-2,4}};
        int[][] ans=kc.kClosest(points,2);
        for (int[] a:ans){
            System.out.println(a[0]+" "+a[1]);
        }

    }
    //就是扔进pq，pq注意要比较sqrt的大小，而不是直接做差，因为sqrt返回是double，作差之后要转成int再返回，这样就就有误差了
    public int[][] kClosest(int[][] points, int k) {
        int[][] rs=new int[k][2];
        PriorityQueue<Pair> pq=new PriorityQueue<>((a,b)->{
            if(Math.sqrt(Math.pow(b.x,2)+Math.pow(b.y,2))>Math.sqrt(Math.pow(a.x,2)+Math.pow(a.y,2))){
                return 1;
            }

            return -1;
        });
        for (int i=0;i<points.length;i++){
            Pair p=new Pair(points[i][0],points[i][1]);
            pq.offer(p);
            if(pq.size()>k){
                pq.poll();
            }
        }
        int index=0;
        while (!pq.isEmpty()){
            Pair p=pq.poll();
            rs[index][0]=p.x;
            rs[index][1]=p.y;
            index++;
        }
        return rs;
    }
    class Pair{
        int x;
        int y;

        public Pair(int x,int y){
            this.x=x;
            this.y=y;
        }
    }
}
