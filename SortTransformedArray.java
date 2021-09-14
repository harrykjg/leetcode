import java.util.Arrays;

public class SortTransformedArray {
    //8/5/2021这题无脑做也ac，但关键是有更好的方法不用sort结果。由于双曲线性质，a大于0时是向上开口，a小于0是向下开口，而且输入nums是sorted的，因此可以
    //用2pointer分别指向nums第一个和最后一个数，再结合双曲线想象，如果是开口向上，则一段连续上升的x数组可能是在双曲线上得出的值是先大后小然后再大（因为是向上开口）
    //所以说用2个pointer指向这些x的两端时，你不能知道啥时候遇到最小值，所以不能从小开始填结果数组，但是通过比较2个pointer的值你可以选出最大，第二大。。
    //因此可以从大到小的填结果数组。开口向下的同理相反。还是挺难想的
    //https://leetcode.com/problems/sort-transformed-array/discuss/83322/Java-O(n)-incredibly-short-yet-easy-to-understand-AC-solution
    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        int[] rs=new int[nums.length];
        int index1=0;
        int index2=rs.length-1;
        int i=0;
        int j=nums.length-1;
        while (i<=j){
            int temp1=helper(nums[i],a,b,c);
            int temp2=helper(nums[j],a,b,c);
            if(a>0){
                if (temp1>temp2) {
                    rs[index2--]=temp1;
                    i++;
                }else {
                    rs[index2--] = temp2;
                    j--;
                    }
            }else {
                if (temp1>temp2){
                    rs[index1++]=temp2;
                    j--;//这里要想清楚，开始写成i++了
                }else {
                    rs[index1++]=temp1;
                    i++;
                }
            }

        }
        return rs;
    }
    int helper(int x,int a, int b,int c){
        return x*x*a+x*b+c;
    }
}
