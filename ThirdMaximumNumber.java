/**
 * Created by 502575560 on 6/25/17.
 */
public class ThirdMaximumNumber {
    //直接在leetcode上面写的，就三个循环，弄到前三个数，这个方法写的比较傻
    //https://discuss.leetcode.com/topic/63715/java-neat-and-easy-understand-solution-o-n-time-o-1-space 这个方法好，想起来有点绕
    public int thirdMax(int[] nums) {
        int rs1=Integer.MIN_VALUE;
        int rs2=Integer.MIN_VALUE;
        int rs3=Integer.MIN_VALUE;
        for(int i=0;i<nums.length;i++){
            rs1=Math.max(rs1,nums[i]);
        }
        for(int i=0;i<nums.length;i++){
            if(nums[i]==rs1){
                continue;
            }
            rs2=Math.max(rs2,nums[i]);
        }
        boolean flag=false;
        for(int i=0;i<nums.length;i++){
            if(nums[i]==rs1||nums[i]==rs2){
                continue;
            }
            flag=true;//如果不存在加入是1，1,1这样的数组rs3不存在，那么flag就是false，所以后面就返回rs1
            rs3=Math.max(rs3,nums[i]);
        }
        if(!flag){

            return rs1;

        }
        return rs3;
    }
}
