package 灵神.二分法.二分答案.求最大;

import java.util.PriorityQueue;

public class FurthestBuildingYouCanReach1642 {
    public static void main(String[] args) {
        int[] nums={4,2,7,6,9,14,12};
        System.out.println(furthestBuilding2(nums,5,1));

    }

    //想到用pq但是没写对。
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

    //1/19/2026在想为什么不直接贪心呢？从第0点往右看可知每一个gap的大小，那么就把最大的gap用ladder就行了？那不行，因为可能最大的gap再后面
    //而前面只是用砖的话没法到后面呢。还是没想到二分法的话验证怎么算。
    //现在知道了，应该要从左往右把gap加进pq来，这才是当下需要考虑如何分配这些gap应该是用ladder还是砖，当前范围里最大的gap就用ladder否则用砖，
    //知道这个就知道下面二分法是怎么验证的
    //https://leetcode.cn/problems/furthest-building-you-can-reach/solutions/469774/javasan-chong-jie-fa-yu-shi-jian-fu-za-du-fen-xi-b/
    public static int furthestBuilding3(int[] heights, int bricks, int ladders) {
        PriorityQueue<Integer> pq=new PriorityQueue<>();
        int i=1;
        for (i=1;i<heights.length;i++){
            int gap=heights[i]-heights[i-1];
            if(gap>0){
                pq.offer(gap);
            }
            if(pq.size()>ladders){
                bricks-=pq.poll();
            }
            if(bricks<0){
                break;
            }
        }
        return i-1;
    }
}
