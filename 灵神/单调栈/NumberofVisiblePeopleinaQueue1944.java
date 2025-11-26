package 灵神.单调栈;

import java.util.Stack;

public class NumberofVisiblePeopleinaQueue1944 {
    public static void main(String[] args) {

    }
    //就是找规律,就用题目的两个case
    //https://leetcode.com/problems/number-of-visible-people-in-a-queue/solutions/1359707/java-c-python-stack-solution-next-greater-element/ stack里装的不是int数组也行
    public int[] canSeePersonsCount(int[] heights) {
        int[] rs=new int[heights.length];
        Stack<int[]> st=new Stack<>();
        //
        for(int i=0;i<heights.length;i++){
            while (!st.isEmpty()&&st.peek()[1]<heights[i]){
                int[] p=st.pop();
                rs[p[0]]++;
            }
            if(!st.isEmpty()){
                rs[st.peek()[0]]++;
            }
            st.push(new int[]{i,heights[i]});
        }
        return rs;
    }

}
