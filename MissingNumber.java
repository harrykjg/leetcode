public class MissingNumber {
    //7/25/2021
    public int missingNumber(int[] nums) {
        int count=nums.length+1;
        int max=nums.length+1-1;
        int min=0;
        int rs=count*(min+max)/2;//等差数列求和公式得到总数，再减去数组里的就是missing的
        for (int i:nums){
            rs-=i;
        }
        return rs;

    }
}
