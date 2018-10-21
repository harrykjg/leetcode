import java.util.ArrayList;
import java.util.List;

/**
 * Created by yufengzhu on 8/6/18.
 */
public class MajorityElementII {
    public static void main(String[] args){
        MajorityElementII me=new MajorityElementII();
        int[]a={0,-1,2,-1};
        me.majorityElement2(a);
    }
    //以前的代码, 还是很神奇
    //https://blog.csdn.net/xudli/article/details/46784149
    //https://blog.csdn.net/novostary/article/details/47680171 有更高级的follow up

    //下面这两个扩展到n／k的情况
    //https://www.geeksforgeeks.org/given-an-array-of-of-size-n-finds-all-the-elements-that-appear-more-than-nk-times/  这个解释好
    //https://leetcode.com/problems/majority-element-ii/discuss/124362/Java-solution-and-generalization-to-find-elements-appear-more-than-nk-times
    //https://leetcode.com/problems/majority-element-ii/discuss/63524/Java-solution-for-generalized-n-k-case
    public static List<Integer> majorityElement(int[] n) {
        List<Integer> rs = new ArrayList<Integer>();
        if (n.length < 1) {

            return rs;
        }
        if (n.length < 2) {
            rs.add(n[0]);
            return rs;
        }

        int m1 = n[0];
        int m2 = n[0];
        int count1 = 0;
        int count2 = 0;
        for (int i = 0; i < n.length; i++) {
            if (n[i] == m1) {
                count1++;
                continue;
            }
            if (n[i] == m2) {
                count2++;
                continue;
            }
            if (count1 == 0) {
                m1 = n[i];
                count1 = 1;
                continue;
            }
            if (count2 == 0) {
                m2 = n[i];
                count2 = 1;
                continue;
            }
            count1--;
            count2--;
        }
        count1=0;
        count2=0;
        for (int i = 0; i < n.length; i++) {
            if (n[i] == m1) {
                count1++;
            } else if (n[i] == m2) {
                count2++;
            }
        }
        if(count1>(n.length/3)){
            rs.add(m1);
        }
        if(count2>(n.length/3)){
            rs.add(m2);
        }

        return rs;

    }

    //记不清，他的candidate1和2要先初始化的一个数的,而且如果下一个进来的数不是can1或can2，就会把can1和can2的counter都减1
    public static List<Integer> majorityElement2(int[] n) {
        List<Integer> rs=new ArrayList<>();
        if (n.length < 1) {

            return rs;
        }
        if (n.length < 2) {
            rs.add(n[0]);
            return rs;
        }
        int can1=n[0];
        int can2=n[0];
        int c1=0;
        int c2=0;
        for(int i=0;i<n.length;i++){
            if(can1==n[i]){
                c1++;
                continue;
            }
            if(can2==n[i]){
                c2++;
                continue;
            }
            if(c1==0){
                can1=n[i];
                c1=1;
                continue;
            }
            if(c2==0){
                can2=n[i];
                c2=1;
                continue;
            }
            c1--;
            c2--;
        }
        c1=0;
        c2=0;
        for(int i=0;i<n.length;i++){
            if(can1==n[i]){
                c1++;
            }
            else if(can2==n[i]){//注意这里要有个else才行
                c2++;
            }
        }
        if(c1>n.length/3){
            rs.add(can1);
        }
        if(c2>n.length/3){
            rs.add(can2);
        }
        return rs;
    }
}
