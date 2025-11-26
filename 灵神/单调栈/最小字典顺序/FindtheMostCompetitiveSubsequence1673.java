package 灵神.单调栈.最小字典顺序;

import java.util.Deque;
import java.util.LinkedList;

public class FindtheMostCompetitiveSubsequence1673 {
    public static void main(String[] args) {
        int[] nums={1,1,50,1,1,0};
        int[] rs=mostCompetitive(nums,6);
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
}
