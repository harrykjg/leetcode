package SomeInterviews.databricks;

import java.util.ArrayList;
import java.util.List;

public class RemoveCoveredPoint {

    //比如【1，4】【5，8】即1，2，3，5，6，7如果idx=3则是要删掉5，那么[5,8]就变成[6,8]，那么就遍历intervals，第一个interval时cur=4-1-1<3，说明
    //当前i加上当前interval的这一段的长度还没够到idx=3，现在cur变成3,遇到第二个interval，他的终点是3+（8-5）-1=5>=3说明
    //idx就是落到这一段上了，要开始处理这个interval了
    // 说明要删掉intervals[0]的end，
    public int[][] deleteCoveredPoint(int[][] intervals, int idx) {
        List<int[]> al=new ArrayList<>();
        int cur=0;
        boolean found=false;
        for (int i=0;i<intervals.length;i++){
            if(found){
                al.add(intervals[i]);
                continue;
            }
            //终点小于index，继续
            if(cur+intervals[i][1]-intervals[i][0]-1<idx){
                al.add(intervals[i]);
                cur+=intervals[i][1]-intervals[i][0];//下标很恶心，cur的值代表还不包括第cur位的值，和cur=0时不包括第一个值的意义统一
                continue;
            }
            //找到要处理的区间了
            if(cur+intervals[i][1]-intervals[i][0]-1>=idx){
                found=true;
                int start=cur;
                int end=cur+intervals[i][1]-intervals[i][0]-1;//举例子看才能写对index
                if(start==idx){//要删第一个数
                    //只包含一个数，那就整个删掉了
                    if(intervals[i][1]-intervals[i][0]==1){
                        continue;
                    }else{
                        al.add(new int[]{intervals[i][0]+1,intervals[i][1]});
                    }
                }else if(end==idx){//删掉终点
                    if(intervals[i][1]-intervals[i][0]==1){
                        continue;
                    }else{
                        al.add(new int[]{intervals[i][0],intervals[i][1]-1});
                    }
                }else{//中间分开
                    int mid=intervals[i][0]+(idx-start);
                    al.add(new int[]{intervals[i][0],mid});
                    al.add(new int[]{mid+1,intervals[i][1]});
                }
            }

        }
        int[][] rs=new int[al.size()][2];
        for (int i=0;i<al.size();i++){
            rs[i]=al.get(i);
        }
        return rs;
    }
}
