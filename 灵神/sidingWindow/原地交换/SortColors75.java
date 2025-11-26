package 灵神.sidingWindow.原地交换;

public class SortColors75 {


    public static void main(String[] args) {
        int[] nums={2,0,1};
        sortColors(nums);
        for(int i:nums){
            System.out.println(i);
        }

    }

    public static void sortColors(int[] nums) {
        int zero=0;
        int b=0;
        int e=nums.length-1;
        while (b<=e){
            if(nums[b]==2){
                nums[b]=nums[e];
                nums[e]=2;
                e--;

                continue;
            }
            if(nums[b]==1){
                b++;
                continue;
            }
            nums[b]=nums[zero];
            nums[zero]=0;
            //b++这里没想好，想的是如果换过来的数还需要检查，是1还是2，其实不用了，换过来的肯定是1，因为是前面不可能存在2，已经把所有2处理了
            b++;

            zero++;
        }

    }
}
