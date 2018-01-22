package binarysearch;

import java.util.List;

/**
 * Created by 502575560 on 8/20/17.
 */
public class RecoverRotatedSortedArray {
    //leetcode好像没有
    //就是先用二分法找到非单调的那个点,(没找到的话就不用搞了),分成两段,然后2段分别reverse,再总的reverse就行了,一次过
    public void recoverRotatedSortedArray(List<Integer> nums) {
        // write your code here
        if(nums.size()<=1){
            return;
        }
        int index=-1;
        for(int i=1;i<nums.size();i++){
            if(nums.get(i-1)>nums.get(i)){
                index=i;
                break;
            }
        }
        if(index==-1){//没发现rotate了
            return;
        }
        int b=0;
        int e=index-1;
        while (b<e){
            int temp=nums.get(b);
            nums.set(b,nums.get(e));
            nums.set(e,temp);
            b++;
            e--;
        }
        b=index;
        e=nums.size()-1;
        while (b<e){
            int temp=nums.get(b);
            nums.set(b,nums.get(e));
            nums.set(e,temp);
            b++;
            e--;
        }
        b=0;
        e=nums.size()-1;
        while (b<e){
            int temp=nums.get(b);
            nums.set(b,nums.get(e));
            nums.set(e,temp);
            b++;
            e--;
        }
    }
    //1／21／2018，九章第二轮懒得做了
}
