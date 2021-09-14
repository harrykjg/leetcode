import java.util.Arrays;

public class KClosestPointstoOrigin {
    //8/9/2021没想到quick select
    //https://leetcode.com/problems/k-closest-points-to-origin/discuss/220235/Java-Three-solutions-to-this-classical-K-th-problem.
    public int[][] kClosest(int[][] points, int k) {
        //基本的quickselect是返回的一个数。这力邀返回一堆点
        int a=helper(points,0,points.length-1,k);


        return Arrays.copyOfRange(points,0,k);
    }
    int helper(int[][] p,int b,int e,int k){
        int bb=b;
        int ee=e;
        int[] x=p[bb];
        while (bb<ee){
            while (ee>bb&&compare(x,p[ee])<=0){
                ee--;
            }
            if (bb<ee){
                p[bb]=p[ee];
                bb++;
            }
            while (bb<ee&&compare(x,p[bb])>=0){
                bb++;
            }
            if (bb<ee){
                p[ee]=p[bb];
                ee--;
            }
        }
        if (bb==k-1){
            return bb;
        }else if (bb>k){
            return helper(p,b,bb-1,k);
        }else {
            return  helper(p,bb+1,e,k);
        }

    }

    int compare(int[] a,int[] b ){
        return a[0]*a[0]+a[1]*a[1]-b[0]*b[0]-b[1]*b[1];
    }

    //8/27/2021
    public int[][] kClosest2(int[][] points, int k) {
        select2(0,points.length-1,points,k);
        return Arrays.copyOfRange(points,0,k);
    }
    void select2(int begin,int end,int[][] points,int k){
        int b=begin;
        int e=end;
        int[] x=points[b];
        while (b<e){
            while (b<e&&compare2(points[e],x)>0){
                e--;
            }
            if (b<e){
                points[b]=points[e];
                b++;
            }
            while (b<e&&compare2(points[b],x)<0){
                b++;
            }
            if (b<e){
                points[e]=points[b];
                e--;
            }
        }
        points[b]=x;
        if (b==k-1){
            return;
        }else if (b<k-1){
            select2(b+1,end,points,k);
        }
        select2(begin,b-1,points,k);
    }
    int compare2(int[] a,int[] b){
        return a[0]*a[0]+a[1]*a[1]-b[0]*b[0]-b[1]*b[1];
    }
}
