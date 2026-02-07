package 灵神.单调栈.贡献法;

import java.util.Arrays;
import java.util.Stack;

public class SumofSubarrayMinimums907 {
    public static void main(String[] args) {
//        int[] arr={1,2,4,3,2};
        int[] arr={3,1,2,4};
        System.out.println(sumSubarrayMins(arr));
    }
    //自己找规律思路是对的，就是判断重复数字如1,2,4,3,2这个例子的时候想不通。
    //https://leetcode.cn/problems/sum-of-subarray-minimums/solutions/1929461/zi-shu-zu-de-zui-xiao-zhi-zhi-he-by-leet-bp3k/
    //看下面的评论
    /*
    正常思路应该是先对每个元素从自身向左右延伸，找到一个最小值为当前元素的最大区间，时间复杂度O（n^2）,测试用例超时，
    然后再想到用单调栈去找左边界和有边界
     */
    public static int sumSubarrayMins(int[] arr) {
        int[] left=new int[arr.length];
        int[] right=new int[arr.length];
        Arrays.fill(left,-1);//不fill的话假如{1,2，xxx}，这样1的left数组的值0,2的left数组对应的的值也是0，就没法区分长度。
        Arrays.fill(right,arr.length);
        long rs=0;
        long mod=(long)Math.pow(10,9)+7;
        Stack<Integer> st=new Stack<>();
        for(int i=0;i<arr.length;i++){//是找每一个数往左看第一个比他小的数，并不是找左边最小的，所以用单调栈
            if(st.isEmpty()){
                st.push(i);
                continue;
            }
            while (!st.isEmpty()&&arr[st.peek()]>=arr[i]){//这里不好想，把等于的pop出去，说明这个i如果左边遇到重复的数字，是把他pop出去，既后面算leftlen会把他报进来。
                st.pop();
            }
            if(!st.isEmpty()&&arr[st.peek()]<arr[i]){
                left[i]=st.peek();
            }
            st.push(i);
        }

        st=new Stack<>();
        for(int i=arr.length-1;i>=0;i--){
            if(st.isEmpty()){
                st.push(i);
                continue;
            }
            while (!st.isEmpty()&&arr[st.peek()]>arr[i]){//遇到重复的不pop，因此下面right[i]=st.peek()时要用小于等于号
                st.pop();
            }
            if(!st.isEmpty()&&arr[st.peek()]<=arr[i]){
                right[i]=st.peek();
            }
            st.push(i);
        }

        for(int i=0;i<arr.length;i++){
            int rightlen=right[i]-i;

            int leftlen=1;
            if(left[i]==-1){
                leftlen=i+1;
            }else{
                leftlen=i-left[i];
            }
            rs+=((long)arr[i]*rightlen*leftlen)%mod;


        }

        return (int)(rs%mod);
    }
    //1/22/2026还是不会，很难想到时单调栈。并且常规想法也不好想。还是看上面的答案结合代码，实际上是求某个位置i，假设这个数是某个subarray的最小
    //的数，那么现在求i往左边第一个小于他的数j，说明i到j这一段都是i最小。同理找i往右看第一个小于i的数k，则i到k这段也是i最小，那么i就是贡献了
    //leftlen*rightlen*arr[i]给答案。如果是普通找法就是每一个数找n次，找n个数就是n方，而这里用了单调栈就可以在n时间里找到这个数单边的
    //第一个小于他的值了
}
