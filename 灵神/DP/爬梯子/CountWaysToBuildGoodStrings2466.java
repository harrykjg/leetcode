package 灵神.DP.爬梯子;

public class CountWaysToBuildGoodStrings2466 {
    public static void main(String[] args) {
        System.out.println(countGoodStrings(3,3,1,1));
    }
    //https://leetcode.cn/problems/count-ways-to-build-good-strings/solutions/2575172/2466-tong-ji-gou-zao-hao-zi-fu-chuan-de-r5k5n/
    //自己写的，和别人写的差不多
    public static int countGoodStrings(int low, int high, int zero, int one) {
        int rs=0;
        int[] dp=new int[high+1];//开始这里写的high，其实不够。不太好想，比如high是5，如果dp长度为5的话那么下标就是0到4，代表下标0也是append了一个字符串，不符合常理，如果是长度为5+1
        dp[zero]+=1;               //则下标是1到5，那么1就是现在长度是1
        dp[one]+=1;
        int min=Math.min(zero,one);
        int mod=(int)Math.pow(10,9)+7;
        for (int i=min;i<dp.length;i++){//别人不需要先赋值dp【zero】或one，直接行i=1开始
            if(i-zero>=0){
                dp[i]+=dp[i-zero]==0?0:dp[i-zero];
                dp[i]%=mod;
            }
            if(i-one>=0){
                dp[i]+=dp[i-one]==0?0:dp[i-one];
                dp[i]%=mod;
            }
            if(i>=low){
                rs+=dp[i];
                rs%=mod;
            }
        }
        return rs;
    }
}
