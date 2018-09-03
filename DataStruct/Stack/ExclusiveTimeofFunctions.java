package DataStruct.Stack;

import java.util.*;

/**
 * Created by yufengzhu on 7/16/18.
 */
public class ExclusiveTimeofFunctions {
    public static void main(String[] a){
        ExclusiveTimeofFunctions et=new ExclusiveTimeofFunctions();
        List<String> ls=new ArrayList<>();
        ls.add("0:start:0");
        ls.add("0:start:2");
        ls.add("0:end:5");
        ls.add("1:start:6");
        ls.add("1:end:6");
        ls.add("0:end:7");
        int[] rs=et.exclusiveTime(2,ls);
        System.out.print("x");
    }
    //举个例子,题目的例子不行，要上面这个例子

//https://leetcode.com/problems/exclusive-time-of-functions/solution/  关键是要维护一个pre时间点，这个时间记录的是时间轴上当前走到的地方
    //https://blog.csdn.net/gqk289/article/details/76062982 看这个代码，其实不需要判断前后两个process是否相同，因为题目意思说了，
// 不可能是前面是一个process的start，后面是另一个process的end，肯定是相同的
    //https://blog.csdn.net/huanghanqian/article/details/77160234
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] rs=new int[n];
        Stack<String> st=new Stack<>();
        HashMap<Integer,Integer> map=new HashMap<>();
        int pre=-1;
        for(String log:logs){
            String[] l=log.split(":");
            Integer task=Integer.parseInt(l[0]);
            String state=l[1];
            Integer time=Integer.parseInt(l[2]);
            if(st.isEmpty()){
                st.push(log);
                pre=time;
                continue;
            }
            String[] preL=st.peek().split(":");
            Integer pretask=Integer.parseInt(preL[0]);
            String prestate=preL[1];
            Integer pretime=Integer.parseInt(preL[2]);
            if(pretask==task){//相同的task，则可能是当前是end，之前的是start，也可能是当前是start，之前也是start，不可能是当前是end，之前也是end,也不可能当前是start，之前是end，因为之前是end的肯定不在stack里的
                if(state.equals("end")&&prestate.equals("start")){
                    if(!map.containsKey(task)){
                        map.put(task,time+1-pre);
                    }else{
                        map.put(task,map.get(task)+time+1-pre);
                    }
                    st.pop();//当前这个log是end所以不需要push，只需要把前面对应的task pop出来
                    pre=time+1;//当前是end所以要+1
                }
                if(state.equals("start")&&prestate.equals("start")){
                    if(!map.containsKey(task)){
                        map.put(task,time-pre);
                    }else{
                        map.put(task,map.get(task)+time-pre);
                    }
                    st.push(log);//当前是start，所以只需要push
                    pre=time;
                }
            }else{//task不同的话，只可能当前是start，因为不可能当前是end而stack.peak是不同task
                if(!map.containsKey(pretask)){
                    map.put(pretask,time-pre);
                }else{
                    map.put(pretask,map.get(pretask)+time-pre);
                }
                st.push(log);
                pre=time;
            }
        }
        for(int i:map.keySet()){
            rs[i]=map.get(i);
        }
        return rs;
    }
}
