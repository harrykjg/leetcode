/**
 * Created by yufengzhu on 8/5/18.
 */
public class WiggleSort {
    public void wiggleSort(int[] nums) {
        if(nums.length<2){
            return;
        }
        for(int i=1;i<nums.length;i++){
            if(i%2==0){
                if(nums[i-1]<nums[i]){
                    int temp=nums[i];
                    nums[i]=nums[i-1];
                    nums[i-1]=temp;
                }
            }else{
                if(nums[i-1]>nums[i]){
                    int temp=nums[i];
                    nums[i]=nums[i-1];
                    nums[i-1]=temp;
                }
            }

        }
    }
}
