package DataStruct.Stack;

import java.util.Stack;

/**
 * Created by yufengzhu on 7/7/18.
 */
public class NextGreaterElementII {
    public static void main(String[] a){
        NextGreaterElementII ng=new NextGreaterElementII();
        int[] n={1,2,3,4,5};
        ng.nextGreaterElements(n);
    }
    //类似739 Daily Temparature
    //感觉就是见过类似的题，用stack但是就是想不出来,看回daily temparature的答案再自己想的
    public int[] nextGreaterElements(int[] nums) {
        Stack<Integer> st=new Stack<>();
        int[] rs=new int[nums.length];
        for(int i=0;i<nums.length;i++){
            while (!st.isEmpty()&&nums[st.peek()]<nums[i]){
                int temp=st.pop();
                rs[temp]=nums[i];
            }
            st.push(i);
        }
        int end=0;
        if(!st.isEmpty()){
            end=st.peek();
        }

        for(int i=0;i<end;i++){
            while (!st.isEmpty()&&nums[st.peek()]<nums[i]){
                int temp=st.pop();
                rs[temp]=nums[i];
            }

        }
        while (!st.isEmpty()){
            rs[st.pop()]=-1;
        }
        return rs;
    }
}
