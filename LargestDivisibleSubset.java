import sun.nio.cs.ext.MacHebrew;

import java.util.*;

/**
 * Created by 502575560 on 4/28/17.
 */
public class LargestDivisibleSubset {

    public static void main(String[] args){
        LargestDivisibleSubset ld=new LargestDivisibleSubset();
        int[] n={4,8,10,240};
        List<Integer> al=ld.largestDivisibleSubset(n);
        for(int i=0;i<al.size();i++){
            System.out.println(al.get(i));
        }
    }

    //动态规划的思路自己想不出来, 记录路径这里不好想也不太好理解
    //http://blog.csdn.net/lmy690858904/article/details/51785238
    //http://www.cnblogs.com/godlei/p/5621990.html
    //https://segmentfault.com/a/1190000006025628
    public List<Integer> largestDivisibleSubset(int[] nums) {
        List<Integer> rs=new ArrayList<>();
        if(nums.length==0){
            return rs;
        }
        if(nums.length==1){
            rs.add(nums[0]);
            return rs;
        }
        Arrays.sort(nums);
        int[] pre=new int[nums.length];
        int[] dp=new int[nums.length];
        dp[0]=1;
        for(int i=0;i<pre.length;i++){//dp的意义开始也优点偏差,我以为是dp[i]代表以i结尾的数组有 LargestDivisibleSubset的长度的值
                                        //如{16,112,170},按我原来这样想的话dp[2]是2,其实这样不对,因为如果变成{16,112,170,340}的话
                                        //那么dp[3]就变成3了,就错了,所以如果说这里要初始化dp默认值为1,后面的逻辑如果当前数和前面的数
                                        //不能互余那么那么当前数就是1好了
            dp[i]=1;//这里为什么要初始dp不好想,如果不初始的话后面写逻辑update dp会容易出错,比如{4,8,10,240}这个例子,
            pre[i]=-1;//意义是默数组里所有数都不能和别人互余
        }
        int max=Integer.MIN_VALUE;
        int index=-1;
        for(int i=1;i<nums.length;i++){
            for(int j=i-1;j>=0;j--){
                if(nums[i]%nums[j]==0&&dp[j]+1>dp[i]){//这里想清楚,只有互余才并且p[j]+1>dp[i]采取更新dp[i],否则写dp[i]=Math.max(dp[i],dp[i-1])的话意义是错的
                    dp[i]=dp[j]+1;
                    pre[i]=j;//这里注意pre赋值的位置,只有找到了这个较长的路径才把前继update

                }
                if(dp[i]>max){
                    max=dp[i];
                    index=i;
                }
            }
        }
        while(true){
            if(pre[index]==-1){//如果这个要加入rs的值没有的前任,那么就把这个值加进去就完事
                rs.add(nums[index]);
                break;
            }else{
                rs.add(nums[index]);
                index=pre[index];
            }
        }
        return rs;
    }


        //自己想的一个思路,运行到第23个test case错了,然后我加了arrays.sort(nums)就变成超时了,思路是先两个for循环,对每一个nums[i]找出其不能互余的数的集合存在map里.然后在从新扫描数组,对每一个nums[i],扫描数组
    public List<Integer> largestDivisibleSubset2(int[] nums) {  //里其他的数,如果nums[j]和nums[i]互余并且不存在于和nums[i]不互余的set内,说明nums[j]可以和
        List<Integer> rs=new ArrayList<>();           //当前的与nums[i]互余的所有数互余
        if(nums.length==0){
            return rs;
        }
        Arrays.sort(nums);
        HashMap<Integer,HashSet<Integer>> map=new HashMap<>();
        for(int i=0;i<nums.length;i++){
            if(!map.containsKey(nums[i])){
                HashSet<Integer> set=new HashSet<>();
                map.put(nums[i],set);
            }
            for(int j=0;j<nums.length;j++){
                if(j==i){
                    continue;
                }
                if(!isGC(nums[i],nums[j])){
                    map.get(nums[i]).add(nums[j]);
                }
            }
        }
        boolean hasOne=false;
        for(int i=0;i<nums.length;i++){
            ArrayList<Integer> al=new ArrayList<>();
            al.add(nums[i]);
            for(int j=0;j<nums.length;j++){
                if(j==i||!isGC(nums[i],nums[j])){
                    continue;
                }
                boolean hasConflig=false;
                for(int k=0;k<al.size();k++){
                    HashSet<Integer> set=map.get(al.get(k));
                    if(set.contains(nums[j])){
                        hasConflig=true;
                        break;
                    }
                }
                if(!hasConflig){
                    al.add(nums[j]);
                }

            }
            if(al.size()>rs.size()){
                rs=al;
            }
        }
        return rs;


    }
    private boolean isGC(int a,int b){
        int big= Math.max(a,b);
        int sm=Math.min(a,b);
        return big%sm==0;

    }
}
