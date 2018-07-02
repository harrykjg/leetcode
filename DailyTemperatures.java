import java.util.Stack;

/**
 * Created by yufengzhu on 6/29/18.
 */
public class DailyTemperatures {
    //6/29/2018,卧槽？直接n方的方法就accept了？但是还有O(n）的方法！
    //https://leetcode.com/problems/daily-temperatures/discuss/109832/Java-Easy-AC-Solution-with-Stack 用的stack，吊，
    public int[] dailyTemperatures(int[] temperatures) {//这个是复制别人的代码
        Stack<Integer> stack = new Stack<>();
        int[] ret = new int[temperatures.length];
        for(int i = 0; i < temperatures.length; i++) {
            while(!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) {
                int idx = stack.pop();
                ret[idx] = i - idx;
            }
            stack.push(i);
        }
        return ret;

    }
}
