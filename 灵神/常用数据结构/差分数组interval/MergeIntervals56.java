package 灵神.常用数据结构.差分数组interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeIntervals56 {
    static void main() {

    }
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals,((a,b)-> {
            if(a[0]==b[0]){//其实相等的时候谁排先都行
                return a[1]-b[1];
            }
            return a[0]-b[0];
            }));
        List<int[]> al=new ArrayList<>();
        for (int i=0;i<intervals.length;i++){
            if(al.size()==0){
                al.add(intervals[i]);
                continue;
            }
            if(al.get(al.size()-1)[1]<intervals[i][0]){
                al.add(intervals[i]);
            }else{
                al.get(al.size()-1)[1]=Math.max(al.get(al.size()-1)[1],intervals[i][1]);
            }
        }
        return al.toArray(new int[al.size()][]);//居然还有这种写法
    }
}
