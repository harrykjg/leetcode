package 灵神.sidingWindow.三指针;

import javax.swing.plaf.IconUIResource;

public class NumberofSubarrayswithBoundedMaximum795 {
    public static void main(String[] args) {
        int[] a={2,1,4,3};
        System.out.println(numSubarrayBoundedMax(a,2,3));
    }

    //还是不会，看以前的里的笔记，在ArrayListAndNumbers package
// 找规律发现连续长度为1，2，3，4，5的subarray并且所有制都是符合条件的，他们的subarry的数量是1，3，6，10，15，即count（n）=count（n-1）+n,
//进一步看规律实际上就是0+1，1+2，3+3，6+4.但是如果有一个数不符合规定了如2，2，2，1，其中2都是符合规定的，1不符合了，则是0+1，1+2，3+3，6+3
    //即最后一个还是还是连续符合条件的长度，如果是2，2，2，1，1也是这样0+1，1+2，3+3，6+3，12+3
    //如果是2，2，1，2，则是0+1，1+2，3+2，5+4 这个规律容易晕
    public static int numSubarrayBoundedMax(int[] nums, int left, int right) {
        int rs=0;
        int b=0;
        int e=0;
        int preLen=0;
        while (e<nums.length){
            if (nums[e]>=left&&nums[e]<=right){
                e++;
                preLen=e-b;
                rs+=preLen;
            }else if(nums[e]<left){
                e++;
                rs+=preLen;
            }else{
                e++;
                b=e;
                preLen=0;
            }

        }
        return rs;
    }

}
