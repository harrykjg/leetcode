/**
 * Created by 502575560 on 5/31/17.
 */
public class RotateFunction {
    //自己没有想到什么好方法
    //http://www.cnblogs.com/grandyang/p/5869791.html 就是找规律
    public int maxRotateFunction(int[] A) {
        if(A.length==0){
            return 0;
        }
        int rs=Integer.MIN_VALUE;
        int[] f=new int[A.length];
        int sum=0;
        for(int i=0;i<A.length;i++){
            f[0]+=i*A[i];
            sum+=A[i];
        }
        rs=Math.max(rs,f[0]);
        for(int i=1;i<A.length;i++){
            rs=Math.max(f[i-1]+sum-A.length*A[A.length-i],rs);
            f[i]=f[i-1]+sum-A.length*A[A.length-i];
        }
        return rs;
    }
}
