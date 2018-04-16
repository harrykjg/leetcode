package Advance4;

/**
 * Created by yufengzhu on 4/15/18.
 */
public class FirstBadVersion {
    //九章第二轮，基本一次过
    public int firstBadVersion(int n) {
        if(n<=1){
            return n;
        }
        int b=1;
        int e=n;

        while(b<e-1){
            int m=b+(e-b)/2;
            if(!isBadVersion(m)&&isBadVersion(m+1)){
                return m+1;
            }
            if(isBadVersion(m)){
                e=m;
            }else{
                b=m;
            }
        }
        return isBadVersion(b)?b:e;
    }
    boolean isBadVersion(int n){
        return true;
    }
}
