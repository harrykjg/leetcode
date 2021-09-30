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
        int[] rs=et.exclusiveTime4(2,ls);
        System.out.print(rs[0]);
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

    //9/11/2018,还是做不出来，忘了要维护一个pre,而写还是很不好写，要记着
    // 举个例子["0:start:0","1:start:2","1:end:3","2:start:5","2:end:6","0:end:8"]，答案是5，2，2
    public int[] exclusiveTime2(int n, List<String> logs) {
        int[] rs=new int[n];
        Stack<String> st=new Stack<>();
        int pre=0;//pre是用来记录上一个元素的time的，无论是开始还是结束
        for(int i=0;i<logs.size();i++){
            String s=logs.get(i);
            String[] cur=s.split(":");
            int id=Integer.parseInt(cur[0]);
            String state=cur[1];
            int time=Integer.parseInt(cur[2]);
            if(st.isEmpty()){
                st.push(s);
                pre=time;
                continue;
            }
            String pres=st.peek();
            String[] press=pres.split(":");
            int preid=Integer.parseInt(press[0]);
            String prestate=press[1];
            int pretime=Integer.parseInt(press[2]);
            if(state.equals("start")){
                if(prestate.equals("start")){
                    rs[preid]+=time-pre;
                    st.push(s);
                    pre=time;
                }else{

                }
            }else{
                st.pop();
                rs[id]+=time+1-pre;
                pre=time+1;//这里是end的情况，所以pre要+1
            }
        }
        return rs;
    }

    //9/16/2018,靠记忆,居然一次过，就是多了判断name和prename是否相同，其实根本不需要，不过这样写比较顺
    public int[] exclusiveTime3(int n, List<String> logs) {
        Stack<String> st=new Stack<>();
        int pre=0;
        int[] rs=new int[n];
        for(int i=0;i<logs.size();i++){
            String[] curlog=logs.get(i).split(":");
            int name=Integer.parseInt(curlog[0]);
            String state=curlog[1];
            int time=Integer.parseInt(curlog[2]);
            if(st.isEmpty()){
                pre=time;
                st.push(logs.get(i));
                continue;
            }
            String[] prelog=st.peek().split(":");

            int prename=Integer.parseInt(prelog[0]);
            String prestate=prelog[1];
            int pretime=Integer.parseInt(prelog[2]);

            if(name==prename){
                if(state.equals("start")){
                    rs[name]+=time-pre;
                    pre=time;
                    st.push(logs.get(i));
                }else{
                    rs[name]+=time+1-pre;
                    pre=time+1;
                    st.pop();
                }

            }else{
                if(state.equals("start")){
                    rs[prename]+=time-pre;
                    pre=time;
                    st.push(logs.get(i));
                }
            }

        }
        return rs;
    }
//7/7/2021,就是用stack模拟，写的不好，看回上一个的写法，用一个pretime会免去从stack里pop东西找这段时间属于谁的容易。真巧妙
    public int[] exclusiveTime4(int n, List<String> logs) {
        int[] rs=new int[n];
        Stack<String> st=new Stack<>();
        int pretime=0;
        for (int i=0;i<logs.size();i++){
            String cur=logs.get(i);
            String[] split=cur.split(":");
            int task=Integer.valueOf(split[0]);
            String action=split[1];
            int time=Integer.valueOf(split[2]);
            if (st.isEmpty()){
                pretime=time;
                st.push(cur);
                continue;
            }
            String[] prelog=st.peek().split(":");
            int pretask=Integer.valueOf(prelog[0]);
            if (action.equals("start")){//由于不会把end push进来，所以上一个task肯定是start，所以这段时间肯定是属于pretask的
                rs[pretask]+=time-pretime;//确定了pretask之后，还需要用pretime来确认时间。
                pretime=time;
                st.push(cur);
            }else{//如果当前是end，则上一个肯定是同个task的start，记录下时间，然后pretime设成当前time+1，因为end的time是这一秒的结束，
                rs[task]+=time+1-pretime;//所以下一个来的task只能是从+1开始,并且st。pop，因为pretime记录了他的结束时间，所以他已经没有用了。
                pretime=time+1;
                st.pop();
            }
        }
        return rs;
    }

    //8/12/2021 这个写的改了几次对了，写的不太好，看回上一个。这里多了pre的判断，这里想的是遇到end的话就从stack里找之前的时间点，而如果遇到的是start，
    //且start不为空的话，则用pre来确定时间，且只有遇到end的时候才会设置pre。而之前的不管现在是start还是end都设置成pre，所欲遇到end的时候都不需要
    //用stack里的pretask来确定时间，都直接用pre来确定。
    public int[] exclusiveTime5(int n, List<String> logs) {
        Stack<String> st=new Stack<>();
        int[] rs=new int[n];
        int pre=-1;
        for(int i=0;i<logs.size();i++){
            String now=logs.get(i);
            String[] ss=now.split(":");
            if(ss[1].equals("start")){
                if(st.isEmpty()){
                    st.push(now);
                }else{
                    String preT=st.peek();
                    String[] pres=preT.split(":");
                    if(pre!=-1){
                        int time=Integer.parseInt(ss[2])-pre;
                        rs[Integer.parseInt(pres[0])]+=time;
                        pre=-1;
                    }else{
                        int time=Integer.parseInt(ss[2])-Integer.parseInt(pres[2]);
                        rs[Integer.parseInt(pres[0])]+=time;
                    }
                    st.push(now);
                }
            }else{
                String[] pres=st.pop().split(":");
                if(pre!=-1){
                    int time=Integer.parseInt(ss[2])-pre+1;
                    rs[Integer.parseInt(ss[0])]+=time;
                    pre=Integer.parseInt(ss[2])+1;
                }else{
                    int time=Integer.parseInt(ss[2])-Integer.parseInt(pres[2])+1;
                    rs[Integer.parseInt(ss[0])]+=time;
                    pre=Integer.parseInt(ss[2])+1;
                }
            }
        }
        return rs;
    }
//8/24/2021 这个写法就好了，改了一下就ac，就是只用pre来计算时间，不需要看stack里的时间。其实stack里只需要装integer代表他是哪一个task就行
    //https://leetcode.com/problems/exclusive-time-of-functions/discuss/105062/Java-Stack-Solution-O(n)-Time-O(n)-Space
    public int[] exclusiveTime6(int n, List<String> logs) {
        int pre=0;
        Stack<String> st=new Stack<>();
        int[] rs=new int[n];
        for(int i=0;i<logs.size();i++){
            String[] log=logs.get(i).split(":");
            int task=Integer.parseInt(log[0]);
            String status=log[1];
            int time=Integer.parseInt(log[2]);
            if(status.equals("start")){
                if(!st.isEmpty()){
                    String[] prelog =st.peek().split(":");
                    int pretask=Integer.parseInt(prelog[0]);
                    rs[pretask]+=time-pre;
                    pre=time;
                    st.push(logs.get(i));
                }else{
                    st.push(logs.get(i));
                    pre=time;
                }
            }else{
                String[] prelog=st.pop().split(":");
                rs[task]+=time+1-pre;
                pre=time+1;
            }
        }

        return rs;
    }
    //9/28/2021
    public int[] exclusiveTime7(int n, List<String> logs) {
        int pre=0;
        Stack<Integer> st=new Stack<>();//stack里只需要装task的number
        int[] rs=new int[n];
        for(int i=0;i<logs.size();i++){
            String[] log=logs.get(i).split(":");
            int task=Integer.parseInt(log[0]);
            String action=log[1];
            int time=Integer.parseInt(log[2]);
            if(action.equals("start")){
                if(!st.isEmpty()){
                    int t=st.peek();
                    rs[t]+=time-pre;
                }
                st.push(task);
                pre=time;
            }else{
                rs[task]+=time-pre+1;
                st.pop();
                pre=time+1;
            }
        }
        return rs;
    }
}
