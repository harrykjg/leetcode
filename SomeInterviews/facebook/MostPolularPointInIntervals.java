package SomeInterviews.facebook;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MostPolularPointInIntervals {
    public static void main(String[] args){
        MostPolularPointInIntervals mp=new MostPolularPointInIntervals();
        System.out.println(mp.find(new int[][]{{1,3},{2,3},{3,4}}));
    }
    //https://www.1point3acres.com/bbs/thread-803743-1-1.html
//10/1/2021找intervals里重叠最大的那个点
    //[-5,1],[1,4],[2,3] 答案是1，2，3都行，-5，1是左右inclusive，所以1上面也算是右2个overlap。[1, 3] [2, 3], [3, 4]答案是3
    //naive想法就是每个区间的每个点作为在数轴上的一个点，统计所有区间的所有点在数轴上出现的次数就行了。他说面试官的hint是返回区间的两端就行，那么因该就是线扫描
    //但是和number of airplanes in the sky不同的是这里结束点是inclusive的，因此一个结束的1和一个开始的1应该是开始的1在前面，这样扫的时候会算上开始的先
    //如 -5 1（开始）1结束，到1开始的时候count是2
    public int find(int[][] intervals){
        PriorityQueue<int[]> pq=new PriorityQueue<>((a,b)->(a[0]==b[0]?b[1]-a[1]:a[0]-b[0]));//pq里二维数组第一位是位置，第二位是+1-1代表开始和结束
                                                                    //要-1拍前面的话就是a[1](值是1)-b[1](值是-1)=2>0代表a大，拍后面，这里要反过来
        for (int[] i:intervals){
            pq.offer(new int[]{i[0],1});
            pq.offer(new int[]{i[1],-1});
        }
        int count=0;
        int max=0;
        int rs=0;
        while (!pq.isEmpty()){
            int[] cur=pq.poll();
            if (cur[1]==1){
                count++;
            }else {
                count--;
            }
            if (count>max){
                max=count;
                rs=cur[0];
            }
        }
        return rs;
    }
}
