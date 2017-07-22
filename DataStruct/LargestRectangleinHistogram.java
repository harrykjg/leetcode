package DataStruct;

import java.util.Stack;

/**
 * Created by 502575560 on 7/11/17.
 */
public class LargestRectangleinHistogram {
    public int largestRectangleArea(int[] height) {
        // 还是得看会以前得解释,要记得图解的话代码应该能写出来,关键是要记得stack只装单调上升的值的索引,还有记得不用两端加0进stack里
        //但其实我们要把最右边看成是有个0的bar
        if(height.length==0){
            return 0;
        }
        int rs=Integer.MIN_VALUE;
        Stack<Integer> st=new Stack<>();
        for(int i=0;i<height.length;i++){
            if(st.isEmpty()||height[i]>=height[st.peek()]){
                st.push(i);
                continue;
            }
            while (!st.isEmpty()&&height[st.peek()]>=height[i]){//这里以前写的是>,也对
                int h=st.pop();
                if(st.isEmpty()){
                    rs=Math.max(height[h]*i,rs);
                }else{//这里还是很巧秒,要画图看,联合while的条件
                    rs=Math.max(height[h]*(i-st.peek()-1),rs);
                }
            }
            st.push(i);
        }
        while (!st.isEmpty()){//实际上这里就是幻想了有最后一个高为0的bar,i是数组的长度
            int h=st.pop();
            if(!st.isEmpty()){
                rs=Math.max(height[h]*(height.length-st.peek()-1),rs);
            }else{
                rs=Math.max(height[h]*height.length,rs);
            }

        }
        return rs;
    }
}
