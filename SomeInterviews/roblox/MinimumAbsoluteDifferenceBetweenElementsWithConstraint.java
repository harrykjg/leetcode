package SomeInterviews.roblox;

import java.util.List;
import java.util.TreeSet;

public class MinimumAbsoluteDifferenceBetweenElementsWithConstraint {

    //4/5/2026看着挺简单，但是还不好想！怎么找最小的差值？想着是从0开始，那么i+x右边所有元素可以纳入的元素假如set，i往右移的话set里元素要删掉一个不符合最小距离的
    //那样的话不好操作，因为如果你有多个相同的元素加进set里，刚好要删的那个是出现两次的，那么你现在删了不就等于多删了吗.anyway很神奇的是如果i从x出发，把左边的元素
    //入set，然后i++继续往右挪并且左边继续入set，就能完美解决。如 5，3，2，10，15，x=1，从3开始，把5入set，可得diff=2，然后扫到2，把3入栈，然后的diff=1.。

    public int minAbsoluteDifference(List<Integer> nums, int x) {
        TreeSet<Integer> set=new TreeSet<>();
        int rs=Integer.MAX_VALUE;
        for (int i=x;i<nums.size();i++){
            set.add(nums.get(i-x));
            Integer floor=set.floor(nums.get(i));
            if(floor!=null){
                rs=Math.min(rs,Math.abs(floor-nums.get(i)));
            }
            Integer celi=set.ceiling(nums.get(i));
            if(celi!=null){
                rs=Math.min(rs,Math.abs(celi-nums.get(i)));
            }

        }
        return rs;
    }
}
