import java.util.Arrays;

public class NextPermutation {
    //8/22/2021 凑活，还可以写的更好
    //https://leetcode.com/problems/next-permutation/solution/
    public void nextPermutation(int[] nums) {
        int p=-1;
        for (int i=nums.length-1;i>=0;i--){
            if (i-1>=0&&nums[i]>nums[i-1]){
                p=i-1;
                break;
            }
        }
        if (p==-1){
            reverse(0,nums.length-1,nums);
            return;
        }
        for (int i=p+1;i<nums.length;i++){
            if (i+1<nums.length&&nums[i+1]<=nums[p]){//比如这个例子[6, 8 ,7,7,6]，6应该是和第一个7还是第二个7换呢。应该是第二个7，这样p后面的都是降序的
                int temp=nums[p];            //后面直接反转就是排序的。其实这里不改从p往后看，应该从后面往前找第一个大于nums【p】的
                nums[p]=nums[i];
                nums[i]=temp;
                break;
            }else if (i+1==nums.length){
                int temp=nums[p];
                nums[p]=nums[i];
                nums[i]=temp;
                break;
            }
        }
        reverse(p+1,nums.length-1,nums);

    }
    void reverse(int begin,int end,int[] nums){
        int b=begin;
        int e=end;
        while (b<e){
            int temp=nums[b];
            nums[b]=nums[e];
            nums[e]=temp;
            b++;
            e--;
        }
    }
//10/3/2021
    public void nextPermutation2(int[] nums) {
        int t=-1;
        for(int i=nums.length-1;i>0;i--){
            if(nums[i]>nums[i-1]){
                t=i-1;
                break;
            }
        }
        if(t==-1){
            int b=0;
            int e=nums.length-1;
            while(b<e){
                int temp=nums[e];
                nums[e]=nums[b];
                nums[b]=temp;
                b++;
                e--;
            }
            return;
        }
        int index=0;
        for(int i=nums.length-1;i>=0;i--){
            if(nums[i]>nums[t]){
                index=i;
                break;
            }
        }
        int temp=nums[t];
        nums[t]=nums[index];
        nums[index]=temp;
        Arrays.sort(nums,t+1,nums.length);//排序也对，reverse也对

        return;

    }
}
