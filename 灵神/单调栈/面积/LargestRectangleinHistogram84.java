package 灵神.单调栈.面积;

import org.w3c.dom.ls.LSOutput;

import java.util.Stack;

public class LargestRectangleinHistogram84 {
    public static void main(String[] args) {
        int[] nums={0,0};
        System.out.println(largestRectangleArea(nums));
    }


    //还是不会，看回以前的写法。记住单调栈存的事单调上升（或等于）的下标，遇到小的就开始pop
    public static int largestRectangleArea(int[] heights) {
        Stack<Integer> st=new Stack<>();
        int rs=0;
        for(int i=0;i<heights.length;i++){
            if(st.isEmpty()||heights[st.peek()]<=heights[i]){
                st.push(i);
                continue;
            }
            while (!st.isEmpty()&&heights[st.peek()]>heights[i]){
                int temp=st.pop();
                if(!st.isEmpty()){
                    rs=Math.max(rs,(i-st.peek()-1)*heights[temp]);
                }else{
                    rs=Math.max(rs,i*heights[temp]);//为啥不是i-temp？因为stack里不会存在比temp这个bar还矮的bar了
                }
            }
            st.push(i);
        }
        while (!st.isEmpty()){
            int temp=st.pop();
            if(!st.isEmpty()){
               rs=Math.max(rs,(heights.length-1-st.peek())*heights[temp]);
            }else{
                rs=Math.max(rs,heights.length*heights[temp] );
            }
        }
        return rs;

    }

}
