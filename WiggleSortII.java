import java.util.Arrays;

/**
 * Created by 502575560 on 4/8/17.
 */
public class WiggleSortII {
public static void main(String[] args){
    int[] a={1,2,3,5,5,5,6,7,8};
    wiggleSort(a);
}
    //不会啊,只能参考别人的,O(n)的方法还没仔细想,第一种方法都写的很烂
    //http://www.cnblogs.com/grandyang/p/5139057.html   为啥不能取前半段的第一个,然后后半段的第一个,再前半段的第二个,再后半段的第二个?
    //http://www.cnblogs.com/grandyang/p/5177285.html 他第一问[1,1,1,4,5,6] 这样的例子也不行,但是leetcode没这样的test case
    //https://leetcode.com/problems/wiggle-sort-ii/discuss/77677/O(n)+O(1)-after-median-Virtual-Indexing
    //https://segmentfault.com/a/1190000003783283  他第一问不对的,第二问[1,1,1,4,5,6] 这样的例子也不行,但是leetcode没这样的test case

    public static void wiggleSort(int[] nums) {
        Arrays.sort(nums);
//这题还要O(n)的space
        if(nums.length<=2){
            return;
        }
        int[] a=new int[nums.length];
        for(int i=0;i<nums.length;i++){//a是排序后的数组,辅助用
            a[i]=nums[i];
        }
        //这里不好写,对于数组是奇数或者偶数的,中间值是去nums.length/2 -1还是nums.length/2这里不好想,要分奇偶,否则得不出正确答案
        //如果数组长度是奇数,那么中间值就取nums.length/2,即前半段比后半段多一个,如果是长度是偶数则取nums.length/2 -1
        int m=nums.length%2==0?nums.length/2 -1:nums.length/2;
        int e=nums.length-1;
        for(int i=0;i<nums.length;i++){
            if(i%2==0){
                nums[i]=a[m];
                m--;
                continue;
            }else{
                nums[i]=a[e];
                e--;
            }
        }
        return;

    }
}
