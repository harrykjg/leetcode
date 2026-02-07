package linkedin;

import java.util.List;
import java.util.Stack;

public class ExclusiveTimeofFunctions636 {
    static void main() {

    }
    //就是用stack，注意end 1是指整个到1.99999秒才结束，而start 1是指1秒的开头，因此 start2， end 5实际上是用了4秒
    //还是写的复杂了,看回以前的，就是stack里只需要装process的id就行了，并且之后start会入栈，遇到end的时候直接pop start算时间，end
    //就不需要入栈，那么问题来了如果是0：start：0，1：start：3， 1：end:5, 0:start:8这个例子，现在到了最后这个log了，那我怎么知道上一个
    //end在哪呢，这就用一个pre变量记住上一个end是时刻就行了，同理遇到start时也可以用pre记录
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] rs=new int[n];
        Stack<Integer> st=new Stack<>();
        int pre=0;
        for (int i=0;i<logs.size();i++){
            String[] cur=logs.get(i).split(":");
            int curId=Integer.valueOf(cur[0]);
            int curTime=Integer.valueOf(cur[2]);
            if(cur[1].equals("start")){
                if(!st.isEmpty()){//stack里面不肯能是end，只会是start
                    rs[st.peek()]+=curTime-pre;
                }
                st.push(curId);
                pre=curTime;
            }else{
                //stack里肯定是start
                rs[st.pop()]+=curTime+1-pre;//注意endtime的话就是要+1，设pre也是要加1
                pre=curTime+1;
            }

        }
        return rs;

    }
}
