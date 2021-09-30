package SomeInterviews.karat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Meeting {
    public static void main(String[] args){
        String[][] m={
                {"1300","1500"},
                {"930","1200"},
                {"830","845"},
        };
        Meeting meeting=new Meeting();
        System.out.println(meeting.canFit(m,1450,1500));
        int[][] m2={{900,1100},
                {934,1022},
                {1122,1223},
                {1123,1400},
                {1455,1600}
                };
        System.out.println(meeting.free(m2));

    }
    //看新的meeting能不能插入已有的schedule，直接遍历看每一个有没有overlap就行了。
    public boolean canFit(String[][] meetings,int begin,int end){
        Arrays.sort(meetings, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                int b1=Integer.valueOf(o1[0]);
                int b2=Integer.valueOf(o2[0]);
                return b1-b2;
            }
        });
        for (int i=0;i<meetings.length;i++){
            int b=Integer.valueOf(meetings[i][0]);
            int e=Integer.valueOf(meetings[i][1]);
            if (begin>=e||end<=b){
                continue;
            }else {
                return false;
            }
        }
        return true;
    }

    //简书第二问意思就是一堆meeting，先merge，再输出空闲的就行了
    public List<int[]> free(int[][] meetings){
        List<int[]> rs=new ArrayList<>();
        Arrays.sort(meetings, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0]-o2[0];
            }
        });
        List<int[]> merge=new ArrayList<>();
        merge.add(meetings[0]);
        for (int i=1;i<meetings.length;i++){
            int[] pre=merge.get(merge.size()-1);
            if (meetings[i][0]>=pre[1]){
                merge.add(meetings[i]);
            }else {
                pre[1]=Math.max(pre[1],meetings[i][1]);
            }
        }
        if (merge.get(0)[0]>0){
            rs.add(new int[]{0,merge.get(0)[0]});
        }
        for (int i=1;i<merge.size();i++){
            rs.add(new int[]{merge.get(i-1)[1],merge.get(i)[0]});
        }
        if (merge.get(merge.size()-1)[1]<2400){
            rs.add(new int[]{merge.get(merge.size()-1)[1],2359});
        }
        for (int[] i:rs){
            System.out.print(i[0]+" "+i[1]);
            System.out.println();
        }
        return rs;
    }

}
