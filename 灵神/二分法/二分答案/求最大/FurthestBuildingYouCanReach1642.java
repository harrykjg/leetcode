package 灵神.二分法.二分答案.求最大;

import java.util.PriorityQueue;

public class FurthestBuildingYouCanReach1642 {
    public static void main(String[] args) {
        int[] nums={4,2,7,6,9,14,12};
        System.out.println(furthestBuilding2(nums,5,1));

    }

    //想到用pq但是没写对。
    //https://leetcode.com/problems/furthest-building-you-can-reach/ 他的方法是只要一个循环，我的是先这是pq的，不行的原因见下面
    //二分法的想法不好想https://leetcode.cn/problems/furthest-building-you-can-reach/solutions/2895183/er-fen-qiu-zui-da-by-answerui-i20t/
    public static int furthestBuilding(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> pq=new PriorityQueue<>();
        for(int i=1;i<heights.length;i++){//这是错的不要参考。因为这里是把所有的gap都加进来，得到最大的那些，但是爬楼的过程中不一定能跑到后面
            int gap=heights[i]-heights[i-1];
            if (gap>0){
                if(pq.size()<ladders){
                    pq.offer(gap);
                }
                if(pq.size()>ladders){
                    pq.poll();
                }
            }
        }
        int rs=0;
        for(int i=1;i<heights.length;i++) {
            int gap = heights[i] - heights[i - 1];
            if(gap<=0){
                continue;
            }
            if(!pq.isEmpty()&&gap>=pq.peek()){
                pq.poll();
                continue;
            }
            bricks-=gap;
            if (bricks<0){
                return i-1;
            }
        }

        return heights.length-1;
    }

    public static int furthestBuilding2(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i < heights.length; i++) {
            int gap=heights[i]-heights[i-1];
            if (gap<=0){
                continue;
            }
            pq.offer(gap);//注意不是说看这个gap是否大于pq的peek才加进去的，而是每一个都要先加进去试试，后面不行的话自然的poll出来让砖块处理

            if(pq.size()>ladders){
                bricks-=pq.poll();
            }
            if (bricks<0){
                return i-1;
            }
        }
        return heights.length-1;
    }
}
