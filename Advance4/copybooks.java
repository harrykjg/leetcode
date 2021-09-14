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

    //6/17/2021, 不容易理解，用二分on值，实际上就是找一个大小为x的桶，看多要装完所有书要多少个桶，书只能从头往后装桶，不能跳着装，得出这个桶的数目不能超过k个人，
    //超过了说明桶太小人不够，小于了则说明桶太大，题目求的是最小的桶的size
    public int copyBooks2(int[] pages, int k) {
        if(pages==null||pages.length==0){
            return 0;
        }
        int b=1;
        int e=0;
        for(int i=0;i<pages.length;i++){
            e+=pages[i];
        }
        while (b+1<e){
            int m=b+(e-b)/2;
            int count=helper2(pages,m,k);
            if (count>k){
                b=m;
            }else {
                e=m;
            }
        }
        if (helper2(pages,b,k)==k){
            return b;
        }
        return e;
    }
    int helper2(int[] pages,int m,int k){
        int rs=0;
        int cur=0;
        int i=0;
        while (i<pages.length){
            if (cur+pages[i]<=m){
                cur+=pages[i];
                i++;
                continue;
            }
            cur=0;
            rs++;
            if (rs>k){
                return rs;
            }

        }
        return rs+1;
    }
}
