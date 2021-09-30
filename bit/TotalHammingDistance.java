package bit;

public class TotalHammingDistance {
    public static void main(String[] args){
        TotalHammingDistance th=new TotalHammingDistance();
        System.out.println(th.totalHammingDistance(new int[]{1337,7331}));
    }
    //9/28/2021,自己想的改了几次tle了。就是两个两个数比，比的时候取最后一位，怎么取最后一位？通过&1，得出的数在异或，得出1的话说明不同，rs+1，while循环条件不那么
    //好想，只要不是两个都为0就可以继续。其实可以2个数先异或的出来一个数，再看这个数的每一位&1是否为1。
    //https://www.youtube.com/watch?v=fH9clXXrS2Q 好的解法是每次看32位的其中1位，算所有数字的这一位有几个1和几个0。那么知道了这一位有x个0和y个1就可以
    //知道这一位上所有数的distance就是x*y
    public int totalHammingDistance(int[] nums) {//这是超时的解法
        int rs=0;
        for (int i=0;i<nums.length;i++){
            for (int j=i+1;j<nums.length;j++){//0101 0110
                int n1=nums[i];
                int n2=nums[j];
                int temp=n1^n2;
                while (temp!=0){
                    int diff=temp&1;
                    rs+=diff;
                    temp=temp>>1;
                }
            }
        }
        return rs;
    }

    public int totalHammingDistance2(int[] nums) {
        int rs=0;
        int mask=1;
        for (int i=0;i<32;i++){
            int zeros=0;
            int ones=0;
            for (int n:nums){
                int diff=n&mask;
                if (diff==0){
                    zeros++;
                }else{
                    ones++;
                }
            }
            rs+=zeros*ones;
            mask=mask<<1;
        }
        return rs;
    }
}
