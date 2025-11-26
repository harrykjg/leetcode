package 灵神.单调栈;

import java.util.Arrays;
import java.util.Stack;

public class NextGreaterElementII503 {
    public static void main(String[] args) {
        int[] nums={1,2,1};
        int[] rs=nextGreaterElements(nums);
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
            if(i<nums.length){//这里没想到，第二遍的就不用加进来了
                st.push(i%nums.length);
            }
        }
        return rs;
    }

}
