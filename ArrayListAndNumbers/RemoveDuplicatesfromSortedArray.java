package ArrayListAndNumbers;

public class RemoveDuplicatesfromSortedArray {
    //04/22/2020,不会做
    //https://blog.csdn.net/linhuanmars/article/details/20023993
    public int removeDuplicates(int[] nums) {
        int rs=0;
        if(nums.length==0){
            return rs;
        }
        int index=0;
        for(int i=0;i<nums.length;i++){//这里如果把index初始化为1，i初始化为1也对
            if(i>0&&nums[i]==nums[i-1]){
                continue;
            }
            nums[index]=nums[i];
            index++;
        }
        return index;
    }
}
