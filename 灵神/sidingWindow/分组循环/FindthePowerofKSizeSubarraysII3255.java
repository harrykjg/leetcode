package 灵神.sidingWindow.分组循环;

import java.util.Arrays;

public class FindthePowerofKSizeSubarraysII3255 {
    public static void main(String[] args) {
        int[] nums={1,1,1,2,3};
        int[] rs= resultsArray(nums,3);
        for(int i:rs){
            System.out.println(i);

        }

    }
    public static int[] resultsArray(int[] nums, int k) {

        int[] helper=new int[nums.length];
        int[] rs=new int[nums.length-k+1];
        Arrays.fill(helper,-1);
        helper[0]=1;
        for(int i=1;i<nums.length;i++){//先用helper数组记录下连续上升的长度
            if(nums[i]==nums[i-1]+1){
                helper[i]=helper[i-1]+1;
            }else{
                helper[i]=1;
            }
        }
        for (int i=0;i<helper.length;i++){//再遍历helper数组，即可找到符合条件的结果
            if (i-k+1>=0){
                if (helper[i]-helper[i-k+1]>=k){//用i-k+1就可以定位result的index位置
                    rs[i-k+1]=nums[i];
                }else {
                    rs[i-k+1]=-1;
                }
            }
        }

        return rs;
    }
}
