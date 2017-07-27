package binarysearch;

import java.util.*;

/**
 * Created by 502575560 on 7/24/17.
 */
//这题不好写,要记
public class FindtheDuplicateNumber {
    //只会排序之后看nums[i]和nums[i-1]是否重复,应该用二分法,思路不好想,关键是要利用数组有n+1个数字,并且值在1-n的范围内,
    //属于二分on值而不是二分on index的题目

    public static void main(String[]args){
        int[] n={4,3,1,2,4};
        findDuplicate(n);
    }
    //http://www.cnblogs.com/grandyang/p/4843654.html
    //https://segmentfault.com/a/1190000003817671
    //http://blog.csdn.net/monkeyduck/article/details/50439840
    //用了模版,好像有点生硬了,因为最后还是要再遍历一次看到底是b还是e
    public static int findDuplicate(int[] nums) {
        // Write your code here
        int b=1;
        int e=nums.length-1;//数组说了有n+1个数字,说明数组的长度就是n+1,即n是数组长度-1
        while (b+1<e){
            //如果我现在算出从小于等于mid有多少个,这里不太好想出来,如果说小于等于mid的数字大于mid(注意这里我之前想的是大于另一半,这样是不对的,不太好理解)
            // ,则说明重复的必在小于的这一边
            //举几个例子,如5,4,4,3,2,1,如5,4,3,2,2,1,这两个都是偶数,再举个奇数长度,6,5,4,3,2,1,1,或6,5,5,4,3,2,1,其中mid是(6+1)/2,如果说看大于等
            // 于mid的数量则不行了,为什么一定要看小于等于mid的数量呢?我觉得应为mid的值肯定是较小(或者等于中间值的那个数),如果说mid本来就是小于等于真正的
            //中间值,而小于等于mid的数量还是大于另一边,则说明肯定这边重复了
            int mid=b+(e-b)/2;
            int count=0;
            for(int i=0;i<nums.length;i++){
                if(nums[i]<=mid){
                    count++;
                }
            }
            if(count>mid){
                e=mid;
            }else{
                b=mid;
            }
        }
        int count=0;
        for(int i:nums){
            if(i==b){
                count++;
            }
        }
        if(count>=1){
            return b;
        }
        return e;
    }
    //非模版
    public static int findDuplicate2(int[] nums) {
        int b=1;
        int e=nums.length-1;//数组说了有n+1个数字,说明数组的长度就是n+1,即n是数组长度-1
        while (b<e){
            int mid=b+(e-b)/2;
            int count=0;
            for(int i=0;i<nums.length;i++){
                if(nums[i]<=mid){
                    count++;
                }
            }
            if(count>mid){
                e=mid;
            }else {
                b=mid+1;
            }
        }
        return b;
    }

}
