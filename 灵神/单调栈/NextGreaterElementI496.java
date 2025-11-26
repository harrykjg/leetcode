package 灵神.单调栈;

import java.util.HashMap;
import java.util.Stack;

public class NextGreaterElementI496 {
    public static void main(String[] args) {

    }
    //还不会，挺奇怪的题，开始想的要先找nums1中对应的nums2的值，放入map中，其实不行，那样就是n方复杂度了。
    //应该用单调栈找出每一个nums2中的第一个大于他的值，存入map，其中key是nums2【i】，value是他的后面第一个大于他的值。然后在便利nums1，从map中取答案就行
    //https://leetcode.cn/problems/next-greater-element-i/solutions/1065517/xia-yi-ge-geng-da-yuan-su-i-by-leetcode-bfcoj/ 不知道他们为啥要从右往左找nums2？可能是
    //从右往左找的话就可以省去再便利stack填-1的情况
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> st=new Stack<>();
        HashMap<Integer,Integer> map=new HashMap<>();//题目说nums2没有重复所以可以
        for(int i=0;i<nums2.length;i++){
            if(st.isEmpty()){
                st.push(nums2[i]);
                continue;
            }
            while (!st.isEmpty()&&st.peek()<nums2[i]){
                map.put(st.pop(),nums2[i]);
            }
            st.push(nums2[i]);
        }
        while (!st.isEmpty()){
            map.put(st.pop(),-1);
        }
        int[] rs=new int[nums1.length];
        for(int i=0;i<nums1.length;i++){
            rs[i]=map.get(nums1[i]);
        }
        return rs;
    }
}
