package binarysearch;

/**
 * Created by 502575560 on 7/26/17.
 */
public class copybooks {
    public static void main(String[] args){
        int[] n={3,2,4};
        copybooks cp=new copybooks();
        System.out.println(cp.copyBooks(n,2));
    }
    //这题题目看中文好理解一点,英语最后都不知道说的啥
    //思路应该和woodcut一样把,woodcut找少刀数,
    public int copyBooks(int[] pages, int k) {
        long b=0;
        long e=0;
        for(int p:pages){
            b=Math.max(p,b);
            e+=p;
        }
        long mid=0;
        while (b<e){
            mid=(b+e)/2;
            if(valid(mid,pages,k)){//valid说明这个mid时间段够长,可以缩减试试
                e=mid;
            }else {
                b=mid+1;
            }
        }
        return (int)b;
    }
    public boolean valid(long len,int[] pages,int k){
        int count=1;
        int cur=0;
        int i=0;
        while (i<pages.length){
            if(cur+pages[i]<=len){
                cur+=pages[i];
                i++;
            }else{
                count++;
                cur=0;
                if(count>k){//分的段数过多,说明len太小,说明mid要增加
                    return false;
                }
            }
        }
        return true;
    }
}
