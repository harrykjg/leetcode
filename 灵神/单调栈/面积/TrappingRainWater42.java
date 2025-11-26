package 灵神.单调栈.面积;

import java.util.Arrays;
import java.util.Stack;

public class TrappingRainWater42 {
    public static void main(String[] args) {

    }

    //自己写的对了但是不太好，如果是找左右最大的数，那么直接forloop边扫边记录目前的最大值就行了，并不需要stack
    //https://leetcode.cn/problems/trapping-rain-water/solutions/1974340/zuo-liao-nbian-huan-bu-hui-yi-ge-shi-pin-ukwm/ 他有单调栈的做法但是感觉没必要，他说如果input是stream的话
    //既数组不能从右到左的话就可以用单调栈
    public int trap(int[] height) {
        int rs=0;
        Stack<Integer> st=new Stack<>();
        int[] right=new int[height.length];
        int[] left=new int[height.length];//第i位数往左看的最高的bar的高度

        for(int i=0;i<height.length;i++){
            if(!st.isEmpty()&&height[st.peek()]>height[i]){//遇到比栈顶小的时候，就是找到i的左边的最大的元素了，就是栈顶的元素，这个元素不会入栈，因为栈存的是单调上升的数
                left[i]=height[st.peek()];
                continue;
            }
            st.push(i);//存的是更大的元素
        }
        st=new Stack<>();
        for(int i=height.length-1;i>=0;i--){
            if(!st.isEmpty()&&height[st.peek()]>height[i]){
                right[i]=height[st.peek()];
                continue;
            }
            st.push(i);
        }
        for(int i=0;i<right.length;i++){
            int min=Math.min(left[i],right[i]);
            if(min>0){
                rs+=min-height[i];
            }
        }
        return rs;

    }
}
