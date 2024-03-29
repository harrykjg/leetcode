package GraphAndSearch;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class MinimumAreaRectangle {
    //9/17/2021 看别人的思路就是只需要2个点，一个作为左上一个右下就可以确定一个长方形，那么再通过左上和右下来确定另外2个点是否存在就可以确定面积了
    //https://leetcode.com/problems/minimum-area-rectangle/discuss/192759/Simple-AF-JAVA-solution-with-explanation-O(n2)
    public int minAreaRect(int[][] points) {
        HashSet<Pair> set=new HashSet<>();
        for (int[]point :points){
            set.add(new Pair(point[0],point[1]));
        }
        int rs=Integer.MAX_VALUE;
        for (int i=0;i<points.length;i++){
            for(int j=i+1;j<points.length;j++){
                int x1=points[i][0];
                int y1=points[i][1];
                int x2=points[j][0];
                int y2=points[j][1];
                int x3=x2;//试一下，其实不需要知道谁是左上谁是右下。另外两个点肯定是这样的。
                int y3=y1;
                int x4=x1;
                int y4=y2;
                Pair p3=new Pair(x3,y3);
                Pair p4=new Pair(x4,y4);
                if (Math.abs(x1-x2)==0||Math.abs(y1-y2)==0){//知道面积是Math.abs(x1-x2)*Math.abs(y1-y2）那么如果这两点在一条直线上那就肯定是其中一个是0了
                    continue;
                }
                if (set.contains(p3)&&set.contains(p4)){
                    rs=Math.min(rs,Math.abs(x1-x2)*Math.abs(y1-y2));
                }
            }
        }
        return rs==Integer.MAX_VALUE?0:rs;//还要检测结果可能是0
    }
    class Pair{
        int x;
        int y;
        public Pair (int x,int y){
            this.x=x;
            this.y=y;
        }
        @Override
        public boolean equals(Object obj){
            return ((Pair)obj).x==this.x&&((Pair)obj).y==this.y;
        }
        @Override
        public int hashCode(){//https://blog.csdn.net/neosmith/article/details/17068365 不重写hashcode的后果
            return Objects.hash(x,y);
        }
    }

    //10/13/2021 忘了，看回以前的
    public int minAreaRect2(int[][] points) {
        HashSet<Pair2> set=new HashSet<>();
        for (int i=0;i<points.length;i++){
            Pair2 p=new Pair2(points[i][0],points[i][1]);
            set.add(p);
        }
        int rs=Integer.MAX_VALUE;
        for (int i=0;i<points.length;i++){
            for (int j=i+1;j<points.length;j++){
                int[] leftTop=points[i];
                int[] rightBut=points[j];
                Pair2 rightTop=new Pair2(rightBut[0],leftTop[1]);
                Pair2 leftBut=new Pair2(leftTop[0],rightBut[1]);
                if (set.contains(rightTop)&&set.contains(leftBut)){
                    if (leftTop[0]==rightTop.x||leftTop[1]==leftBut.y){
                        continue;
                    }
                    int area=Math.abs(leftTop[0]-rightTop.x)*Math.abs(leftTop[1]-leftBut.y);
                    rs=Math.min(area,rs);
                }
            }
        }
        return rs==Integer.MAX_VALUE?0:rs;
    }
    class Pair2{
        int x;
        int y;
        public Pair2(int x,int y){
            this.x=x;
            this.y=y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair2 pair2 = (Pair2) o;
            return x == pair2.x &&
                    y == pair2.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
