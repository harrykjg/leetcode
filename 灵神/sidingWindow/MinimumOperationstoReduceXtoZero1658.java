package 灵神.sidingWindow;

public class MinimumOperationstoReduceXtoZero1658 {
    public static void main(String[] args) {
        int[] nums={3};
        System.out.println("WDQWD");


        System.out.println(minOperations(nums,3));

    }
    int rs=Integer.MAX_VALUE;
    public static int minOperations(int[] nums, int x) {
        int sum=0;
        //特殊情况，如果写在求maximum length里不好写吧
        if(nums.length==1&&nums[0]==x){
            return 1;
        }
        for(int i:nums){
            sum+=i;
        }
        int target=sum-x;
        int b=0;
        int e=0;
        int cur=0;
        int max=-1;
        while(e<nums.length){

            while(e<nums.length){
                cur+=nums[e];
                e++;
                if(cur>=target){
                    break;
                }
            }
            int len=e-b;
            if(cur==target){
                max=Math.max(max,len);
            }
            while(b<=e&&b<nums.length&&cur>=target){
                if(cur==target){
                    len=e-b;
                    max=Math.max(max,len);
                }
                cur-=nums[b];
                b++;
            }
        }
        if(max==-1){
            return -1;
        }
        return nums.length-max;


    }
}
