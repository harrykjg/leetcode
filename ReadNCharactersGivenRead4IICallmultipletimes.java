import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by yufengzhu on 7/6/18.
 */
public class ReadNCharactersGivenRead4IICallmultipletimes {
    //看这个才知道题目啥意思https://leetcode.com/problems/read-n-characters-given-read4-ii-call-multiple-times/discuss/49607/The-missing-clarification-you-wish-the-question-provided
    //注意他意思是read方法可以调用多次，而且这一次调用会下一次调用的结果，所以要一些全局变量,下面的代码是参考的accpet速度里面example4的，答案那个我不喜欢
    Queue<Character> queue=new LinkedList<>();
    public int read(char[] buf, int n) {
        if(n==0){
            return 0;
        }
        int cur=0;
        while (cur<n){
            char[] buff4=new char[4];
            int cur4=read4(buff4);
            if(cur4==0){
                break;
            }
            int localcur=0;
            while (localcur<cur4){
                queue.offer(buff4[localcur++]);
                cur++;
            }

        }
        int index=0;
        while (!queue.isEmpty()&&index<n){
            buf[index++]=queue.poll();
        }
        return index;
    }
}
