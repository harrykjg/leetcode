package 灵神.sidingWindow.不定长window;

import java.util.*;

public class FindtheLongestEqualSubarray2831 {
    public static void main(String[] args) {
        int[] nums={1,1,2,2,1,1};
        ArrayList<Integer> al=new ArrayList<>();
        for (int i:nums){
            al.add(i);
        }
        System.out.println(longestEqualSubarray(al,2));
    }
    //想法就是while的时候先检测e能不能放进来。能放进来的条件是map为空，或者最大的元素加别的元素<=k。那么如果这个新的元素加进来的话会使得有两个元素都是最大的呢？那也不用管，是他大还是别的大
    //反正无论哪一个大，其余剩余元素和最大元素之和要<=k。但是写到后面几个case出错，比较麻烦，尤其是当b缩掉之后要如何更新curmax.这样可以但是最后超时
    //而别人一般都是先把e加进来再缩，这样的话可以用maxfrequence记录最大的frequence，而不用理到底谁是最大的
    //https://leetcode.com/problems/find-the-longest-equal-subarray/solutions/3935399/easy-video-solution-cpp-java-python-sliding-window/代码参考他的
    public static int longestEqualSubarray(List<Integer> nums, int k) {
        int b=0;
        int e=0;
        int maxFrequence=0;
        Map<Integer,Integer> map=new HashMap<>();
        int rs=0;

        while (b<=e&&e<nums.size()) {
            map.put(nums.get(e),map.getOrDefault(nums.get(e),0)+1);
            maxFrequence=Math.max(maxFrequence,map.get(nums.get(e)));

            while (b<e){
                int len=e-b+1;
                if(len-maxFrequence>k){
                    map.put(nums.get(b),map.get(nums.get(b))-1);
                    //那个人的代码是这里再检测一下maxFrequence的，其实不需要，因为如果新加进来的e和这个b是一样的话，那肯定是不需要缩的，而这里是要缩的情况肯定是把不是maxFrequent的那个人缩掉了
                    //比如【1,1,2,2,1】k=2这个例子的话进行到e=3的时候，1和2都是出现2次，也不用缩，e=4的时候要也不用缩
                    b++;
                }else {
                    break;
                }
            }
            rs=Math.max(rs,maxFrequence);

            e++;
        }
        return rs;

    }

}
