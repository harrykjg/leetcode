package ArrayListAndNumbers;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

/**
 * Created by 502575560 on 7/10/17.
 */
public class TwoSum {
    //夹逼的话还要记录去原来的数组找回index,由于题目说的是只有1个解所以以前写的是可以的.用hashmap的解法挺巧妙的没想到,这次写写
    //http://blog.csdn.net/linhuanmars/article/details/19711387
    //http://blog.csdn.net/jiadebin890724/article/details/23305449
    public int[] twoSum(int[] nums, int target) {
        if(nums.length<=1){
            return new int[2];
        }
        int[] rs=new int[2];
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<nums.length;i++){//关键是这个map是以值为key,index为value

            //现在知道当前nums[i],然后看此时map是否存在key为target-nums[i]的key,存在的话就说明找到两个数和是target了
            if(map.containsKey(target-nums[i])){
                int i1=i;
                int i2=map.get(target-nums[i]);
                if(i1>i2){//其实这里i2肯定是小于i1的,因为如果已经存在target-nums[i]的话那么它对应的index就肯定小于i1,这里懒得改了
                    rs[0]=i2;
                    rs[1]=i1;
                }else {
                    rs[0]=i1;
                    rs[1]=i2;
                }
            }
            map.put(nums[i],i);
        }
        return rs;

    }

//8/29/2018 原来是不能排序的
    public int[] twoSum2(int[] nums, int target) {
        if(nums.length<2){
            return null;
        }
        int[] rs=new int[2];
        HashMap<Integer,Integer> map=new HashMap<>();
        for(int i=0;i<nums.length;i++){
            int temp=target-nums[i];
            if(map.containsKey(temp)){
                if(map.get(temp)>i){
                    rs[0]=i;
                    rs[1]=map.get(temp);
                }else{
                    rs[1]=i;
                    rs[0]=map.get(temp);
                }
                return rs;
            }
            map.put(nums[i],i);
        }

        return null;

    }
}
