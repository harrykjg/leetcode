public class SplitArraywithEqualSum {
    public static void main(String[] args){
        SplitArraywithEqualSum sa=new SplitArraywithEqualSum();
        System.out.println(sa.splitArray(new int[]{1,2,1,2,1,2,1}));
    }
    //8/19/2021 只能想到dfs.但是他这个dfs还不好想怎么定义参数
    //https://leetcode.com/problems/split-array-with-equal-sum/discuss/101481/Simple-Java-solution-O(n2) 好的解法
    //https://leetcode.com/problems/split-array-with-equal-sum/discuss/101484/Java-solution-DFS dfs参考他的
    public boolean splitArray(int[] nums) {
        int[] sum=new int[nums.length+1];
        for (int i=1;i<sum.length;i++){
            sum[i]=nums[i-1]+sum[i-1];
        }
        if (nums.length<7){
            return false;
        }
        for (int i=1;i<nums.length-5;i++){//一定要四分，第四份不能是空的
            if (i != 1 && nums[i - 1] == 0  && nums[i] == 0) continue;//不加这个就超时了，没搞懂是干嘛
            int s=sum[i]-sum[0];//这个下标也很恶心，本来sum的下标和nums的就应该是shift了一位的，一般是取sum[i+1]去得到包括nums[i]上的那个前缀和，
            if (dfs(1,i+1,i+2,nums,sum,s)){//但是这里刚好不用取当前的i
                return true;
            }
        }
        return false;
    }
    boolean dfs(int count,int begin,int nextCut,int[] nums,int[] sums,int curSum){//count代表已经取了一个了，begin和nextCut代表现在[begin,nextCut),这个左开右闭的区间里可以取sum去看能不能match上层传下来的cursum
        if (count==3){//已经有了前3分切好了，看剩余的
            if (sums[sums.length-1]-sums[begin]==curSum){//下标很恶心
                return true;
            }
            return false;
        }
        for (int i=nextCut;i<nums.length-5+count*2;i++){
            int xx=sums[i]-sums[begin];
            if (xx!=curSum){
                continue;
            }
            if(dfs(count+1,i+1,i+2,nums,sums,curSum)){
                return true;
            }
        }
        return false;
    }
}
