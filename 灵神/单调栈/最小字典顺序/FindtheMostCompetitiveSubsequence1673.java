package 灵神.单调栈.最小字典顺序;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class FindtheMostCompetitiveSubsequence1673 {
    public static void main(String[] args) {
        int[] nums={71,18,52,66,8,80,2};
        int[] rs=mostCompetitive2(nums,3);
        for(int i=0;i<rs.length;i++){
            System.out.println(rs[i]);

        }
    }
    //和402一样的，就是这里是保留k个,还简单一点
    public static int[] mostCompetitive(int[] nums, int k) {
        int[] rs=new int[k];

        int count=nums.length-k;
        Deque<Integer> st=new LinkedList<>();
        for(int i=0;i<nums.length;i++){
            if(st.isEmpty()||count<=0){
                st.push(nums[i]);
                continue;
            }
            while (!st.isEmpty()&&st.peek()>nums[i]&&count>0){
                st.pop();
                count--;
            }
            st.push(nums[i]);
        }

        for(int i=0;i<rs.length;i++){
            rs[i]=st.pollLast();
        }
        return rs;

    }
    //
    public static int[] mostCompetitive2(int[] nums, int k) {
        Stack<Integer> st=new Stack<>();
        int[] rs=new int[k];
        for (int i=0;i<nums.length;i++){
            if(st.isEmpty()){
                st.push(nums[i]);
                continue;
            }
            while (!st.isEmpty()&&st.peek()>nums[i]&&st.size()+(nums.length-i)>k){//开始用(nums.length-i)>=k就错了
                st.pop();                                       //没考虑当前st里的东西，>=也不行
            }
            st.push(nums[i]);
        }
        while (st.size()>k){
            st.pop();
        }
        for(int i=k-1;i>=0;i--){
            rs[i]=st.pop();
        }
        return rs;

    }
}
