package 灵神.常用数据结构;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CheckIfArrayPairsAreDivisiblebyk1497 {
    static void main() {

    }
    //原来不是说每一个pair都要是同一个数，其实可以使任何k的倍数，看答案知if （a+b）%k==0则有两种可能，
    // 1：a和b都可以被k整除。2，a和b都不能被整除， 设a%k=x，b%k=y，则需要k=x+y，即y=k-x，即要找到一个数b%k=k-x。因此需要用map记录
    //每一个数%k的值作为key存在map里.还不太好想
    //看下面评论的解法更清晰
    //https://leetcode.cn/problems/check-if-array-pairs-are-divisible-by-k/solutions/310418/jian-cha-shu-zu-dui-shi-fou-ke-yi-bei-k-zheng-chu-/
    public boolean canArrange(int[] arr, int k) {

        Map<Integer,Integer> map=new HashMap<>();
        for(int i:arr){
            int temp=i%k;
            if(temp<0){//处理负数
                temp=i%k+k;
            }
            map.put(temp,map.getOrDefault(temp,0)+1);
        }
        for (Map.Entry<Integer,Integer> entry:map.entrySet()){
            int temp=entry.getKey();
            if(temp==0){
                if(entry.getValue()%2!=0){
                    return false;
                }
            }else{
                int other=k-temp;
                int otherCount=map.getOrDefault(other,0);
                if(entry.getValue()!=otherCount){
                    return false;
                }
            }

        }
        return true;
    }
}
