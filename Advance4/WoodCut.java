package Advance4;

/**
 * Created by yufengzhu on 4/16/18.
 */
//九章第二轮，4／16／2018，还是忘了用long，别的基本没问题
public class WoodCut {
    public int woodCut(int[] L, int k) {
        long b=1;
        long e=0;
        long total=0;
        for (int i=0;i<L.length;i++){
            total+=L[i];
        }
        e=total/k;
        while (b<e-1){
            long m=b+(e-b)/2;
            if(valid(L,(int)m,k)){
                b=m;
            }else{
                e=m;
            }
        }
        if(valid(L,(int)b,k)){
            if(valid(L,(int)e,k)){
                return (int)e;
            }
            return (int)b;
        }
        return 0;
    }
    boolean valid(int[] l,int m,int k){
        int count=0;
        for(int i=0;i<l.length;i++){
            int cur=l[i];
            while (cur>=m){
                count++;
                cur-=m;
            }
            if(count>=k){
                return true;
            }
        }
        return false;
    }

    //05/21/2020,还行吧，就是max应该是L的和除以k，不过我这样也能过
    public int woodCut2(int[] L, int k) {

        long min=1;
        long max=Integer.MIN_VALUE;
        for(int i=0;i<L.length;i++){
            max=Math.max(max,L[i]);
        }
        while (min<max-1){
            long m=min+(max-min)/2;
            boolean found=check(m,L,k);
            if(found){
                min=m;
            }else{
                max=m;
            }
        }
        if(check(max,L,k)){
            return (int)max;
        }
        if(check(min,L,k)){
            return (int)min;
        }
        return 0;
    }
    boolean check(long m,int[] L,int k){
        int count=0;
        for(int i=0;i<L.length;i++){
            if(L[i]>=m){
                count+=L[i]/m;
            }
            if(count>=k){
                return true;
            }
        }
        return false;
    }
}