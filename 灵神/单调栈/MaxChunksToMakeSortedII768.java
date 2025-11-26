package 灵神.单调栈;

import java.util.Stack;

public class MaxChunksToMakeSortedII768 {
    public static void main(String[] args) {

    }

    //不会，以下是解法2，解法1也可以看看，貌似解法一更容易理解
    //https://leetcode.cn/problems/max-chunks-to-make-sorted-ii/solutions/1741851/zui-duo-neng-wan-cheng-pai-xu-de-kuai-ii-w5c6/
    public int maxChunksToSorted(int[] arr) {
        Stack<Integer> st=new Stack<>();
        for(int i=0;i<arr.length;i++){
            if(st.isEmpty()||st.peek()<=arr[i]){
                st.push(arr[i]);
                continue;
            }
            //自己想很难想出来，看答案
            int max=st.pop();
            while (!st.isEmpty()&&st.peek()>arr[i]){
                st.pop();
            }
            st.push(max);
        }
        return st.size();
    }
}
