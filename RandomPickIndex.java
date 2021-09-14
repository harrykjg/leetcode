import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

/**
 * Created by 502575560 on 6/2/17.
 */
public class RandomPickIndex {
    //https://leetcode.com/problems/random-pick-index/discuss/88072/simple-reservoir-sampling-solution
    //https://www.cnblogs.com/grandyang/p/5875509.html
    int[] a;
    Random ran=new Random();
    public RandomPickIndex(int[] nums) {
        a=nums;
    }

    public int pick(int target) {
        int count=0;
        int rs=0;
        for(int i=0;i<a.length;i++){
            if(a[i]==target){
                count++;
                int r=ran.nextInt(count);
                if(r==0){
                    rs=i;
                }
            }
        }
        return rs;
    }

    //8/12/2018 自己想的用map记录同一个数字出现的index的所有位置，居然也accept，但是空间应该太大.还是用回以前的解法
    HashMap<Integer,ArrayList<Integer>> map;
    Random ran=new Random();
    public void RandomPickIndex2(int[] nums) {
        map=new HashMap<>();
        for(int i=0;i<nums.length;i++){
            if(!map.containsKey(nums[i])){
                ArrayList<Integer> al=new ArrayList<>();
                al.add(i);
                map.put(nums[i],al);
            }else{
                map.get(nums[i]).add(i);
            }
        }
    }

    public int pick2(int target) {
        int count=0;
        if(!map.containsKey(target)){
            return -1;
        }

        ArrayList<Integer> al=map.get(target);
        count=al.size();
        int r=ran.nextInt(count);
        return al.get(r);
    }
    //04/14/2020,还是只想到和18年一样的方法，看回别人的方法
    public void RandomPickIndex3(int[] nums) {
        a=nums;
    }

    public int pick3(int target){
        int count=0;
        int rs=-1;
        for(int i=0;i<a.length;i++){
            if(a[i]==target){
                count++;
                int rn=ran.nextInt(count);
                if(rn==0){
                  rs=i;
                }
            }
        }
        return rs;
    }
    //7/12/2021.只能想到用map。Reservoir sampling真的不好想，记也不好记，pick的复杂度是n
    HashMap<Integer, ArrayList<Integer>> map4;
    Random ran4=new Random();
    public void Solution(int[] nums) {
        map4=new HashMap<>();
        for (int i=0;i<nums.length;i++){
            ArrayList<Integer> ls=map4.getOrDefault(nums[i],new ArrayList<>());
            ls.add(i);
            map4.put(nums[i],ls);
        }
    }
    public int pick4(int target) {
        int index=ran4.nextInt(map4.get(target).size());
        return map4.get(target).get(index);
    }
}
