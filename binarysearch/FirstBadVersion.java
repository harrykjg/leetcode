package binarysearch;

/**
 * Created by 502575560 on 7/24/17.
 */
public class FirstBadVersion {
    public static void main(String[] args){
        FirstBadVersion fb=new FirstBadVersion();
        fb.firstBadVersion2(5);
    }
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
    //1/21/2018,九章第二轮懒得写了

    //10/1/2018，没写对，想错了，要找first bad version但是写成了找last bad version
    public int firstBadVersion2(int n) {
        int b=1;
        int e=n;
        int m=0;
        while (b+1<e){
            m=b+(e-b)/2;
            if(isBadVersion(m)){
                e=m;
            }else{
                b=m;
            }
        }
        if(isBadVersion(b)){
            return b;
        }
        return e;
    }
}
