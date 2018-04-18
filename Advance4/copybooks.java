package Advance4;

/**
 * Created by yufengzhu on 4/16/18.
 */
public class copybooks {
    //4/16/2018,思路和woodcut一样，但是b，e，还有valid条件不一样，还不是很容易想清楚
    public int copyBooks(int[] pages, int k) {
        if(pages==null||pages.length==0){
            return 0;
        }
        long b=1;
        long e=0;
        for(int i=0;i<pages.length;i++){
            e+=pages[i];
        }
        while (b<e-1){
            long m=b+(e-b)/2;
            if(helper(pages,m,k)){
                e=m;
            }else{
                b=m;
            }
        }
        if(helper(pages,b,k)){
            return (int)b;
        }
        return (int)e;

    }
    boolean helper(int[] pages,long m,int k){
        int count=0;
        int i=0;
        int cur=0;
        while (i<pages.length){
            if(pages[i]>m){
                return false;
            }
            while (i<pages.length&&cur+pages[i]<=m){
                cur+=pages[i];
                i++;
            }
            count++;
            cur=0;
            if(count>k){
                return false;
            }
        }
        return true;
    }
}
