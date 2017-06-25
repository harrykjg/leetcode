import java.util.ArrayList;
import java.util.List;

/**
 * Created by 502575560 on 6/24/17.
 */
public class FizzBuzz {
    //简单题
    //https://discuss.leetcode.com/topic/63995/java-4ms-solution-not-using-operation  这个思路叼
    public List<String> fizzBuzz(int n) {
        List<String> rs=new ArrayList<>();
        for(int i=1;i<=n;i++){
            if(i%3==0&&i%5==0){
                rs.add("FizzBuzz");
                continue;
            }
            if(i%3==0){
                rs.add("Fizz");
                continue;
            }
            if(i%5==0){
                rs.add("Buzz");
                continue;
            }
            rs.add(i+"");
        }
        return rs;
    }
}
