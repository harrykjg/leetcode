package 灵神.sidingWindow.原地交换;

public class SortArrayByParity905 {
    public static void main(String[] args) {

    }
    //简单一次过
    public int[] sortArrayByParity(int[] nums) {

        int b=0;
        int e=nums.length-1;
        while (b<e){
            if(nums[b]%2!=0){
                int temp=nums[b];
                nums[b]=nums[e];
                nums[e]=temp;
                e--;
            }else{
                b++;
            }

        }
        return nums;
    }
}
