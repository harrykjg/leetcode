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

    //8/16/2018还是不会
    Queue<Character> queue=new LinkedList<>();
    public int read2(char[] buf, int n) {
        if(n==0){
            return 0;
        }
        int cur=0;
        while (cur<n){
            char[] ch=new char[4];
            int index4=read4(ch);
            if(index4==0){
                break;
            }
            int i=0;
            while (i<index4){//奇怪这里不能加cur<n这个条件，加了就错，没搞明白
                queue.offer(ch[i++]);
                cur++;
            }
        }
        int i=0;
        while (!queue.isEmpty()&&i<n){//并且这里一定要有i<n这个条件，没有就错，也没搞明白
            buf[i++]=queue.poll();
        }
        return i;
    }

    //8/22/2021还是不会，看回以前的
    //https://blog.csdn.net/weixin_30949361/article/details/97989997 看他的好懂些
    Queue<Character> q3=new LinkedList<>();
    public int read3(char[] buf, int n) {//他这个返回的数字不是说要返回全局中buf的位置，就是当前读了几个
        int index=0;
        int nn=n;
        while (nn>0&&!queue.isEmpty()){//先读q里的
            buf[index++]= q3.poll(); //这里也不需要找全局的index，就是从0开始就行了
            nn--;
        }
        while (nn>0){
            char[] temp=new char[4];//不够的话再用read4读
            int acutal=read4(temp);
            if (acutal==0){
                break;
            }
            int i=0;
            for (;i<acutal&&index<n;i++){
                buf[index++]=temp[i];
                nn--;
            }
            while (i<acutal){//如果还有剩的加进q里
                q3.offer(temp[i++]);
            }
        }
        return index;
    }
}
