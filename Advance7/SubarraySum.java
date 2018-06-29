package Advance7;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 502575560 on 8/9/17.
 */
public class SubarraySum {
    public static void main(String[] args){
        subarraySum(new int[]{-3,1,2,-3,4});
    }
    //这题自己写只能写出n方的方法,Hashmap的方法太屌了很难想到
    //https://segmentfault.com/a/1190000004883239
    //http://www.jianshu.com/p/66f2d73abd1d 要自己举例子，如4，5，3，-3，当连加到3时，map里已经有了sum为4，9，12，当继续连加到
    //-3时，sum又是9了，出现了2次9，说明中间有一段subarray相互抵消了。初始化的时候先put了一个0，如果后面也出现一次sum为0则说明这一段数和为0
    public static ArrayList<Integer> subarraySum(int[] nums) {
        // write your code here

        int sum=0;
        for(int i=0;i<nums.length;i++){
            ArrayList<Integer> al=new ArrayList<>();
            al.add(i);
            sum=0;
            sum+=nums[i];
            if(sum==0){
                al.add(i);
                return al;
            }
            for(int j=i+1;j<nums.length;j++){
                if(sum+nums[j]==0){
                    al.add(j);
                    return al;
                }
                sum+=nums[j];
            }
        }
        return new ArrayList<Integer>();
    }
//6/19/2018这次勉强记得可能用前缀和可能用map，但是还是写不出来,懂了之后代码还是很好写的
    public static ArrayList<Integer> subarraySum2(int[] nums) {
        Map<Integer,Integer> map=new HashMap<>();
        ArrayList<Integer> rs=new ArrayList<>();
        map.put(0,-1);
        int sum=0;
        for(int i=0;i<nums.length;i++){
            sum+=nums[i];
            if(map.containsKey(sum)){
                rs.add(map.get(sum)+1);
                rs.add(i);
                return rs;
            }
            map.put(sum,i);
        }
        return rs;

    }
}
