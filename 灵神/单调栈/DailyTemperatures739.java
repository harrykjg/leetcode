package 灵神.单调栈;

import java.util.Stack;

public class DailyTemperatures739 {
    public static void main(String[] args) {
        int[] temperature={73,74,75,71,69,72,76,73};
        int[] rs=dailyTemperatures(temperature);
        for(int i:rs){
            System.out.println(i);
        }
    }
    //1/20/2026没问题
    public static int[] dailyTemperatures(int[] temperatures) {
        int[] rs=new int[temperatures.length];
        Stack<Integer> st=new Stack<>();
        for (int i=0;i<temperatures.length;i++){
            if(st.isEmpty()){
                st.push(i);
                continue;
            }
            if (!st.isEmpty()&&temperatures[st.peek()]>temperatures[i]){
                st.push(i);
                continue;
            }
            while (!st.isEmpty()&&temperatures[st.peek()]<temperatures[i]){
                rs[st.peek()]=i-st.peek();
                st.pop();
            }
            st.push(i);
        }
        return rs;
    }
}
