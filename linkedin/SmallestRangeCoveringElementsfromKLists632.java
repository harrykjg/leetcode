package linkedin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class SmallestRangeCoveringElementsfromKLists632 {
    static void main() {

    }
    //2/6/2026不会
    /*
     开始的想法是按每个数列的第一个元素排列，最后一个队列的头元素作为终点，然后第一个队列的头元素一直往后挪，其实是不行的，比如
     这个例子如果只挪a点的话那么只能算到a和c的距离，然而答案有可能是d 和e的距离，因此只看最后一个队列的头元素不行
                     ------a|---d-
                          --|--------
                            |----e------
                            c
      正确做法应该是维护这几个数列的index1，index2，index3，然后找出最小的和最大的，gap就是目前最小的gap，然后把小的index++，再找最小和最大的
      元素算gap。那么怎么在每一轮找最小和最大的元素呢？其实只要第放进pq可得最小的，并且一轮找手动找到最大的，然后每一轮实际上只是把最小的元素的index++，
      即只改变了一个元素，那么自然可以拿新的这个元素对比最小和最大的，就可以更新最小或最大了
     参考
     https://leetcode.cn/problems/smallest-range-covering-elements-from-k-lists/solutions/2982588/liang-chong-fang-fa-dui-pai-xu-hua-dong-luih5/
     */
    public int[] smallestRange(List<List<Integer>> nums) {
        int[] rs=new int[2];
        if(nums.size()==1){
            return new int[]{nums.get(0).get(0),nums.get(0).get(0)};
        }
        //里面装的是一个数组分别是【元素，元素在本list中的index，该list的index】
        PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->a[0]-b[0]);
        int start=nums.get(0).get(0);
        int end=nums.get(0).get(0);
        for (int i=0;i<nums.size();i++){
            pq.offer(new int[]{nums.get(i).get(0),0,i});
            end=Math.max(end,nums.get(i).get(0));
        }

        int gap=Integer.MAX_VALUE;
        //这里其实start和end是动态变的，要另设一个答案的start end记录答案
        int ans1=start;
        int ans2=end;
        while (pq.size()==nums.size()){//只要有一个list遍历完了就完事了。
            int[] min=pq.poll();
            if(Math.abs(min[0]-end)<gap){
                gap=Math.min(gap,Math.abs(min[0]-end));

                ans1=min[0];
                ans2=end;
            }
            if(min[1]+1<nums.get(min[2]).size()){
                int newValue=nums.get(min[2]).get(min[1]+1);
                pq.offer(new int[]{newValue,min[1]+1,min[2]});
                end=Math.max(end,newValue);//更新最大元素
            }
        }
        rs[0]=ans1;
        rs[1]=ans2;
        return rs;
    }
}
