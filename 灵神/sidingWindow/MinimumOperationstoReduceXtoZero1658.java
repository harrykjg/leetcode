package 灵神.sidingWindow;

public class MinimumOperationstoReduceXtoZero1658 {
    public static void main(String[] args) {
        int[] nums={5,1,4,2,3};


        System.out.println(minOperations2(nums,6));

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

//1/12/2026不会了，只想到bfs，正确想法是用sliding window找中间一段使其sum=总sum-target并且最长的一段。不好想像这个缩放按什么规律.
    //是通过e前进可以到达sum，还是通过b右移达到结果,这两者都要检测，边界条件也容易漏
    public static int minOperations2(int[] nums, int x) {
        long sum=0;
        for (int i=0;i<nums.length;i++){
            sum+=nums[i];
        }
        long target=sum-(long)x;
        if(target<0){
            return -1;
        }
        int midLen=-1;
        int b=0;
        int e=0;
        int cur=0;
        while (e<nums.length){
            if(cur<target&&e<nums.length){
                cur+=nums[e];
                e++;
                if(cur==target){//开始想只检测一次，貌似不行
                    int len=e-b;
                    midLen=Math.max(len,midLen);
                }
            }
            while (b<=e&&b<nums.length&&cur>=target){//少了b<nums。lenght也错
                cur-=nums[b];
                b++;
                if(cur==target){
                    int len=e-b;
                    midLen=Math.max(len,midLen);
                }
            }

        }
        if(midLen==-1){
            return -1;
        }
        return nums.length-midLen;
    }
}
