import java.util.*;

public class MaximumNumberofVisiblePoints {
    public static void main(String[] args){
        MaximumNumberofVisiblePoints mn=new MaximumNumberofVisiblePoints();
        List<List<Integer>> points=new ArrayList<>();
        List<Integer> p=new ArrayList<>();
        p.add(2);
        p.add(1);
        List<Integer> p2=new ArrayList<>();
        p2.add(2);
        p2.add(-2);
        List<Integer> p3=new ArrayList<>();
        p3.add(-3);
        p3.add(4);
        List<Integer> p4=new ArrayList<>();
        p3.add(-1);
        p3.add(-1);
        points.add(p);
        points.add(p2);
        points.add(p3);
        points.add(p4);
        mn.visiblePoints(points, 80,Arrays.asList(1,1));
    }

    //9/1/2021 思路不难关键是角度的处理难，就是把每个点到location的斜率转化成一个角度，然后这个角度从小到达排序，然后就可以用sliding window找angle范围
    //之内包含多少个点了。
    //https://leetcode.com/problems/maximum-number-of-visible-points/discuss/877845/JAVA-Sliding-Window
    //https://leetcode.com/problems/maximum-number-of-visible-points/discuss/877721/What-is-the-freaking-d-here
    //https://www.youtube.com/watch?v=7i9GMNGxShk  9分05有解释角度的
    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        //角度的问题还是没搞懂，先不写
    }
}
