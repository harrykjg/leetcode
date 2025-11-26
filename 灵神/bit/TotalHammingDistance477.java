package 灵神.bit;

public class TotalHammingDistance477 {
    public static void main(String[] args) {
        int[] nums={4,12,2};
        System.out.println(totalHammingDistance(nums));
    }
    //https://leetcode.cn/problems/total-hamming-distance/description/
    public static int totalHammingDistance(int[] nums) {
        int rs=0;
        for(int i=0;i<=31;i++){
            int one=0;
            for(int j=0;j<nums.length;j++){//只要统计nums的每个元素的i位上有多少个0，多少个1，就这道这一位上的haming dist了
                if((nums[j]&(1<<i))!=0){//注意这里是把1左移i位，因此计算结果是0或者2,4,8.。。他的代码是nums右移i位再&1，因此答案是0或1
                    one++;
                }
            }
            int zero=nums.length-one;
            rs+=one*zero;//举例发现就是1个数量乘以0的数量
        }
        return rs;
    }
}
