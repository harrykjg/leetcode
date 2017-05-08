/**
 * Created by 502575560 on 5/6/17.
 */
public class WiggleSubsequence {
    //这题做不出,不太好想,这题贪心法可以,开始在纠结是不是一定所有的起伏都应该跟从第1,2个数的大小关系,还是说可以有机会从第2,3,或者3,4,或者第1,3大小关系
    //可以产生更长的结果,其实就是从第一对差值是负的和第一对差值是正的开始,分别算出2个结果看谁大就是结果,贪心法可行应该是因为这题可以删掉不想要的数

    //https://discuss.leetcode.com/topic/51946/very-simple-java-solution-with-detail-explanation 贪心法,其实就是找到每一段上升或者下降段的第一个和
    // 最后一个值的位置,得出的就是结果
//https://discuss.leetcode.com/topic/52076/easy-understanding-dp-solution-with-o-n-java-version 动态规划,nb
    //https://discuss.leetcode.com/topic/52153/java-greedy-solution-o-n-time-complexity-o-1-space-complexity O(1)空间动态规划
    //http://blog.csdn.net/xinyuehuixin/article/details/51995791 这个想法和别人的都不同
    public static void main(String[] args){
        int[] n={17,11,5,10,13,15,10,5,16,8};//按第一个链接的思想理解这个例子,到了17,11,5这,是个单调下降,,可为什么要删掉11拿5当作pre而不是删掉5拿11做pre呢?
        System.out.println(wiggleMaxLength(n)); //原因是因为选5的话比起选11则会有更大机会让下个数字超越他,比如这里选5的话那么5和10就可以构成上升了,如果选11的话就不行
    }

    public static int wiggleMaxLength(int[] nums) {//这个就是按着https://discuss.leetcode.com/topic/51946/very-simple-java-solution-with-detail-explanation
        if (nums.length <= 1) return nums.length; //的思路自己写的,改了一次accept了
        boolean needUp=false;
        boolean first=true;
        int pre=-1;
        int rs=1;
        for(int i=1;i<nums.length;i++){
            if (nums[i]==nums[i-1]){
                continue;
            }
            if(first){
                needUp=nums[i]-nums[i-1]>0?false:true;
                pre=nums[i];
                rs++;
                first=false;
                continue;

            }
            if (needUp){
                if(nums[i]>pre){
                    rs++;
                    needUp=false;
                }
            }else{
                if (nums[i]<pre){
                    rs++;
                    needUp=true;
                }
            }
            pre=nums[i];


        }
        return rs;
    }
}
