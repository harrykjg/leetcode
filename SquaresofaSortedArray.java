public class SquaresofaSortedArray {
    //8/17/2021 想复杂了，想找到中间的正负分割点，在各自向左和右扩展，结果直接找两头向中间夹逼，答案从后往前填就行了
    public int[] sortedSquares(int[] nums) {
        int r=nums.length-1;
        int l=0;
        int[] rs=new int[nums.length];

        int i=nums.length-1;
        while (l<=r){
            if (nums[r]*nums[r]>nums[l]*nums[l]){
                rs[i--]=nums[r]*nums[r];
                r--;
            }else{
                rs[i--]=nums[l]*nums[l];
                l++;
            }
        }
        return rs;
    }
}
