package Advance3;

import java.util.Stack;

/**
 * Created by yufengzhu on 4/8/18.
 */
public class LargestRectangleinHistogram {
    public static void main(String[] args){
        int[] n=new int[]{2,1,5,6,2,3};
        LargestRectangleinHistogram lr=new LargestRectangleinHistogram();
        lr.largestRectangleArea(n);
    }
    //4/8/2018,只记得一半，具体细节记不清楚
    public int largestRectangleArea(int[] heights) {
        if(heights.length==0){
            return 0;
        }
        int rs=Integer.MIN_VALUE;
        Stack<Integer> st=new Stack<>();
        for(int i=0;i<heights.length;i++){
            if(st.isEmpty()||heights[st.peek()]<=heights[i]){
                st.push(i);
                continue;
            }
            while (!st.isEmpty()&&heights[st.peek()]>=heights[i]){
                int temp=st.pop();
                if(st.isEmpty()){
                    rs=Math.max(i*heights[temp],rs);
                }else{
                    rs=Math.max((i-1-st.peek())*heights[temp],rs);//这个还是要画图，如果height数组到了某一步后连续递减，就看得出道理是什么了
                }

            }
            st.push(i);
        }

        while (!st.isEmpty()){
            int temp=st.pop();
            if(st.isEmpty()){
                rs=Math.max(heights.length*heights[temp],rs);
            }else{
                rs=Math.max(rs,(heights.length-1-st.peek())*heights[temp]);
            }

        }
        return rs;
    }

    //11／4／2018,还是写不对
    public int largestRectangleArea2(int[] heights) {
        Stack<Integer> st=new Stack<>();
        int i=0;
        int max=0;
        while (i<heights.length){
            if(st.isEmpty()||heights[st.peek()]<=heights[i]){
                st.push(i++);
                continue;
            }
            while (!st.isEmpty()&&heights[st.peek()]>=heights[i]){//当前的height【i】是不会用到的，都是看i之前的bar
                int pre=st.pop();
                if(st.isEmpty()){
                    int cur=i*heights[pre];
                    max=Math.max(max,cur);
                }else{
                    int cur=(i-1-st.peek())*heights[pre];//还是相当不好理解，画图看，注意是乘以heights[pre]而不是heights[st.peek]
                    max=Math.max(max,cur);

                }
            }
            st.push(i++);
        }
        while (!st.isEmpty()){
            int pre=st.pop();
            if(st.isEmpty()){
                int cur=heights.length*heights[pre];
                max=Math.max(max,cur);
            }else{
                int cur=(heights.length-1-st.peek())*heights[pre];
                max=Math.max(max,cur);

            }

        }
        return max;
    }
}
