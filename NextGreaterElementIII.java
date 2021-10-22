import java.util.Arrays;

public class NextGreaterElementIII {
//10/13/2021 就是next permutation的做法，没有大于的数就return -1。我这里直接把输入转换一下然后套next permutation的代码
    public int nextGreaterElement(int n) {
        String s=String.valueOf(n);
        int[] nums=new int[s.length()];
        for(int i=0;i<nums.length;i++){
            nums[i]=s.charAt(i)-'0';
        }
        String rs="";
        int t=-1;
        for(int i=nums.length-1;i>0;i--){
            if(nums[i]>nums[i-1]){
                t=i-1;
                break;
            }
        }
        if(t==-1){
            return -1;
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

        for(int i=0;i<nums.length;i++){
            rs+=nums[i];
        }
        long l=Long.parseLong(rs);
        if(l>Integer.MAX_VALUE){
            return -1;
        }
        return (int)l;
    }
}
