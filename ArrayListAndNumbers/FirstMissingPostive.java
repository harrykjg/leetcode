package ArrayListAndNumbers;

public class FirstMissingPostive {
    public static void main(String[]ars){
        FirstMissingPostive fm=new FirstMissingPostive();
        System.out.println(fm.firstMissingPositive2(new int[]{3,-1,23,7,21,12,8,9,18,21,-1,16,1,13,-3,22,23,13,7}));//{3,-1,23,7,21,12,8,9,18,21,-1,16,1,13,-3,22,23,13,7}
    }
    //04/21/2020,不会
    //http://www.cnblogs.com/panda_lin/p/first_missing_positive.html
//http://blog.csdn.net/linhuanmars/article/details/20884585
    public int firstMissingPositive(int[] nums) {
        return 1;
    }

    //8/26/2021 还是不会，见上面两个链接,写起来index还是很恶心
    public int firstMissingPositive2(int[] nums) {
        for (int i=0;i<nums.length;i++){
            if (nums[i]==i+1||nums[i]<=0||nums[i]-1>=nums.length||nums[i]==nums[nums[i]-1]){//nums[i]==nums[nums[i]-1]这个条件容易漏，即换过去的位置
                continue;              //和现在这个位置的数是一样的，那就会死循环。这个条件挪到下面换完之后再判断也行，是一样的话就不i--了
            }
            int temp=nums[nums[i]-1];
            nums[nums[i]-1]=nums[i];
            nums[i]=temp;
            i--;
        }
        for (int i=0;i<nums.length;i++){
            if (nums[i]!=i+1){
                return i+1;
            }
        }
        if(nums.length==0){
            return 1;
        }
        return nums.length+1;
    }

}
