package 灵神.单调栈;

import java.util.Arrays;
import java.util.Stack;

public class NextGreaterElementII503 {
    public static void main(String[] args) {
        int[] nums={1,2,3,2,1};
        int[] rs=nextGreaterElements2(nums);
        for(int i:rs){
            System.out.println(i);
        }
    }

    public static int[] nextGreaterElements(int[] nums) {
        int[] rs=new int[nums.length];
        Arrays.fill(rs,-1);
        Stack<Integer> st=new Stack<>();
        for (int i=0;i<nums.length*2;i++){
            if(st.isEmpty()){
                st.push(i%nums.length);
                continue;
            }
            while (!st.isEmpty()&&nums[st.peek()]<nums[i%nums.length]){
                rs[st.peek()]=nums[i%nums.length];
                st.pop();
            }
            if(i<nums.length){//这里没想到，第二遍的就不用加进来了，其实第二轮那样写更好一些
                st.push(i%nums.length);
            }
        }
        return rs;
    }

//1/21/2026，这样写应该比第一次好一些
    public static int[] nextGreaterElements2(int[] nums) {
        int[] rs=new int[nums.length];
        Arrays.fill(rs,-1);
        Stack<Integer> st=new Stack<>();
        for (int i=0;i<nums.length*2-1;i++){
            int index=i%nums.length;

            if(st.isEmpty()||nums[st.peek()]>nums[index]){
                st.push(index);
                continue;
            }
            while (!st.isEmpty()&&nums[st.peek()]<nums[index]){
                int ans=st.pop();
                if(rs[ans]==-1){
                    rs[ans]=nums[index];
                }
            }
            st.push(index);//就算是第二轮也是该放就放，因为他可能是别人的右边最大数,只要上面设rs的时候判断一下是不是-1。试了一下结果上面连-1都不用判断也accept
        }
        return rs;
    }
}
