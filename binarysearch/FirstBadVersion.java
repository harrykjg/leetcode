package binarysearch;

/**
 * Created by 502575560 on 7/24/17.
 */
public class FirstBadVersion {
    //用模版一次过,但其实写的不好,应该不用判断mid-1,就判断mid就行了,循环结束后再判断b还是e就行了
    public int firstBadVersion(int n) {
        if(n<=0){
            return 0;
        }
        int b=0;
        int e=n;
        while (b+1<e){
            int mid=b+(e-b)/2;
            if(isBadVersion(mid)){
                b=mid;
            }else{
                e=mid;
            }

        }
        if(isBadVersion(b)){
            return b;
        }
        return e;
    }
    boolean isBadVersion(int n){
        return true;
    }
}
