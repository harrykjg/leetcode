package SomeInterviews.purestorage;

import java.util.*;

public class FormSqure {

    //https://leetcode.com/discuss/post/1030039/pure-storage-technical-round-member-of-t-tfz6/
    public boolean isSquare(int[][] arr) {
        List<Long> al=new ArrayList<>();//最好直接用long
        for (int i=0;i<arr.length;i++){
            for(int j=i+1;j< arr.length;j++){
                int x1=arr[i][0];
                int y1=arr[i][1];
                int x2=arr[j][0];
                int y2=arr[j][1];
                long dist=(x1-x2)*(x1-x2)+(y1-y2)*(y1-y2);//没必要sqrt，省的不准确
                al.add(dist);
            }
        }
        //一共有6个dist
        Collections.sort(al);
        //直接看前四个是否相等,且不能等于0，否则就是重合点
        for(int i=1;i<4;i++){
            if(al.get(0)==0||(al.get(i)!=al.get(0))){
                return false;
            }
        }
        if(al.get(4)!=al.get(5)){
            return false;
        }
        //还要验证对角线和边的关系
        if(al.get(4)==2*al.get(0)){//注意这里都是已经平方了的，容易写成2*al.get(0)*al.get(0)
            return true;
        }
        return false;
    }
    //follow up是给n个点，问可以组成多少个valid square，n方的复杂度是枚举每两个点组成的线，假设已知AB边，要组成一个正方形就有下面两种CD点的可能
    /*               D(3,6)
    C(0,5)


                         B(4,2)
         A(1,1)

                            D
              C
        这里很容易想错，已知ab边，现在要推出c和d的点的坐标，可知a和b的横坐标和纵坐标差值是（dx=3，dy=1），
        那么容易想错c=（a的横坐标+dx，a的中坐标+dy），其实不对，应该是c=(a横坐标-dy，a中坐标+dx）或者c=(a横坐标+dy，a纵坐标-dx），d同理。
        因为由ab推出ac的话，ac是转了90度的，几何意义就是ac和ab要垂直，则ac的方向和ab的方向可以表示为两个向量：
        u = (dx1, dy1)
        v = (dx2, dy2)，其乘积必须为0才是垂直，即dx1*dx2+dy1*dy2=0，不太好想，得记
     */
    public int isSquare2(int[][] arr) {
        Set<String> set=new HashSet<>();
        int rs=0;
        for (int i=0;i<arr.length;i++){
            String cord=arr[i][0]+","+arr[i][1];
            set.add(cord);
        }
        for (int i=0;i<arr.length;i++){
            for (int j=i+1;j<arr.length;j++){
                int dx=arr[i][0]-arr[j][0];
                int dy=arr[i][1]-arr[j][1];
                int cx1=arr[i][0]-dy;
                int cy1=arr[i][1]+dx;
                int dx1=arr[j][0]-dy;
                int dy1=arr[j][1]+dx;
                String cordC1=cx1+","+cy1;
                String cordD1=dx1+","+dy1;
                if(set.contains(cordC1)&&set.contains(cordD1)){
                    rs++;
                }

                int cx2=arr[i][0]+dy;
                int cy2=arr[i][1]-dx;
                int dx2=arr[j][0]+dy;
                int dy2=arr[j][1]-dx;
                String cordC2=cx2+","+cy2;
                String cordD2=dx2+","+dy2;
                if(set.contains(cordC2)&&set.contains(cordD2)){
                    rs++;
                }

            }
        }
        return rs/4;//容易漏腰除以4
    }
}
