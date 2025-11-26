package 灵神.二分法;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RangeFrequencyQueries2080 {

    //没想到好方法,看他写lower bound的模版，好的话可以学
    //https://leetcode.cn/problems/range-frequency-queries/solutions/1113439/tong-ji-wei-zhi-er-fen-wei-zhi-by-endles-8l9u/

    Map<Integer, List<Integer>> map=new HashMap<>();
    public RangeFrequencyQueries2080(int[] arr) {

        for(int i=0;i<arr.length;i++){
            if(!map.containsKey(arr[i])){
                map.put(arr[i],new ArrayList<>());
            }

            List<Integer> al=map.get(arr[i]);
            al.add(i);
        }
    }

    public int query(int left, int right, int value) {
        if(!map.containsKey(value)){
            return 0;
        }
        List<Integer> al=map.get(value);//比如这个value出现的al是【1,4,5】，现在left=3 right=5，那么就是找3该插入的点，和5+1=6插入的点，会得到1和3（这是下标），
        //那么答案就是3-1=2就是答案
        return helper(al,right+1)-helper(al,left);

    }
    int helper(List<Integer> al,int key){
        int b=-1;
        int e=al.size();//注意这里b是-1，e是al。size,不这样写真不行，可能只是对于找插入index的情况可以这样
        while (b<e-1){
            int m=e-(e-b)/2;

            if(al.get(m)<key){
                b=m;
            }else{
                e=m;
            }
        }
        return e;//他这个模版这样就行，当循环结束时，left+1=right，此时nums[left]是小于key的，nums【right】是》=key
        // 具体解释https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/solutions/1980196/er-fen-cha-zhao-zong-shi-xie-bu-dui-yi-g-t9l9/
    }

}
