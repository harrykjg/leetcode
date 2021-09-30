public class PartitionArrayIntoThreePartsWithEqualSum {
    //8/31/2021 只能想到dfs，最好的方法还不是很好想
    //https://leetcode.com/problems/partition-array-into-three-parts-with-equal-sum/discuss/260895/JavaPython-3-two-O(n)-time-O(1)-space-codes-w-brief-explanation-and-analysis. 看他下面评论的
    //
    public boolean canThreePartsEqualSum(int[] arr) {
        int sum=0;
        for (int i=0;i<arr.length;i++){
            sum+=arr[i];
        }
        int target=sum/3;
        if (sum%3!=0){
            return false;
        }
        int count=0;
        int cur=0;
        for (int i=0;i<arr.length;i++){//开始想的只能dfs，如0到i的sum是target，那么后面这一段i+1到j也是target，但是j到最后这一段不是target的话，只能
            cur+=arr[i];           //backtrack去尝试中间这段i+1到另一个j，那么j+1到最后就可能对了。其实不是，因为如果i+1到j1是target，i+1到j2也是target
            if (cur==target){     //就说明j1和j2中间这段肯定是0，因此j1到最后和j2到最后肯定sum是一样的，因此只要for循环找到一个符合target的区间，再继续找
                count++;          //第二段，第三段。那么会不会找到第三段的时候数组还没有结束，后面还有一段东西呢？也不会，因为如果剩下的东西的sum是0的话，也肯定对，
                cur=0;            //如果剩下的东西的sum不是0的话，那么前三段的sum肯定不符合target。
                if (count==3){
                    return true;
                }
            }
        }
        return false;

    }
//9/21/2021 这次想的用prefix sum，先从左到右找到第一个区间0到i的sum==target，然后看i+1到最后的sum是否等于2倍target，是的话再在这i+1开始看能否找到一个点使得
    //中间这段的sum是否等于target，下标有点不好想。
    public boolean canThreePartsEqualSum2(int[] arr) {
        int sum=0;
        for(int i:arr){
            sum+=i;
        }
        int target=sum/3;
        int[] prefix=new int[arr.length+1];
        for(int i=1;i<prefix.length;i++){
            prefix[i]=prefix[i-1]+arr[i-1];
        }
        for(int i=0;i<arr.length-2;i++){
            if(prefix[i+1]-prefix[0]==target){
                if(prefix[prefix.length-1]-prefix[i+1]==2*target){
                    for(int j=i+1;j<arr.length-1;j++){
                        if(prefix[j+1]-prefix[i+1]==target){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
