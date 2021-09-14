package Advance4;

/**
 * Created by yufengzhu on 4/15/18.
 */
public class FindtheDuplicateNumber {
    //4/15/2018,还是忘了，后来看到思路是要用count的就大概想起来了,但是还是没想清楚到底怎么写
    public int findDuplicate(int[] nums) {
        int b=1;
        int e=nums.length-1;
        while (b<e-1){
            int m=b+(e-b)/2;
            int count=0;
            for(int i=0;i<nums.length;i++){
                if(m>=nums[i]){
                    count++;
                }
            }
            if(count>m){
                e=m;//就如一个数组重复的那个数是7，而我这里m是10，那么count也会>m，那么怎么把m再缩到7呢？其实就是这样e=m，b=m就会来回调整了。
            }else{
                b=m;
            }
        }
        //再看到底是b还是e
        int count=0;
        for(int n:nums){//比如我这里是7重复了，其实我是不知道while出来b和e的值是b=7，e=8还是b=6，e=7，或许我不需要知道，再loop一遍看哪个重复就完了
            if(n==b){
                count++;
            }
        }
        if(count>1){
            return b;
        }
        return e;

    }
//05/21/2020,还是不会，看回以前的解释
    //6/17/2021还是不会，看以前的
    public int findDuplicate2(int[] nums) {
        int b=0;
        int e=nums.length-1;
        while (b<e){
            int m=b+(e-b)/2;
            int count=count2(nums,m);
            if (count>m){
                e=m;
            }else {
                b=m+1;
            }
        }
        return b;
    }
    int count2(int[] nums,int m){
        int rs=0;
        for (int i=0;i<nums.length;i++){
            if (nums[i]<=m){
                rs++;
            }
        }
        return rs;
    }
}
