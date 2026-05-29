package SomeInterviews.purestorage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DrawCircle {

    //应该是指画圆的那一圈，不是整个圆吧。
    //原始写法，能用sqrt，就用圆的公式x方+y方=r方。而且圆可以分成1/8，即给一个x,y，另外7个点就是
    /*
    ( y,  x)
    (-x,  y)
    (-y,  x)
    ( x, -y)
    ( y, -x)
    (-x, -y)
    (-y, -x)
    于是就画第一象限的上半个圆，从(0,y)开始，y=r满足在圆上，而下一个点就是(x+1,y)或者(x+1,y-1),就拿画圆公式看谁更接近就取谁.当x=y的时候就画完了1/8个圆
     */
    public List<int[]> draw(int r,int beginx,int beginy){
        List<int[]> rs=new ArrayList<>();
        Set<String> set=new HashSet<>();
        for (int i=0;i<=r;i++){//开始写了i<=r其实那就是1/4圆了，应该是1/8就够了
            int y=(int)Math.sqrt(r*r-i*i);
            //现在这个y是整数，由于是往下取整的，因此这个数可能是y+1才更接近圆
            double d1=Math.abs(r*r-i*i-y*y);
            double d2=Math.abs(r*r-i*i-(y+1)*(y+1));
            int min=d1<d2?y:y+1;
            if(min>i){//就是y>x的时候就到了1/8了
                break;
            }
            addPoint(i+beginx,min+beginy,rs,set);
        }
        return rs;

    }
    void addPoint(int x,int y,List<int[]> rs,Set<String> set){
        int[][] points = {
                { x,  y},
                { y,  x},
                { x, y},
                {y,  x},
                { x, y},
                {y, -x},
                {-x, -y},
                {-y, -x}
        };

        for (int[] p : points) {
            String key = p[0] + "," + p[1];
            if (set.add(key)) {
                rs.add(p);
            }
        }
        rs.add(new int[]{x,y});
        rs.add(new int[]{y,x});
        rs.add(new int[]{-x,y});
        rs.add(new int[]{-y,x});
        rs.add(new int[]{x,-y});
        rs.add(new int[]{y,-x});
        rs.add(new int[]{-x,-y});
        rs.add(new int[]{-y,-x});
    }
    //假如说不能用sqrt，那就只能二选一
    public List<int[]> draw2(int r,int beginx,int beginy){
        List<int[]> rs=new ArrayList<>();
        Set<String> set=new HashSet<>();
        int x=0;
        int y=r;
        while (x<=y){
            addPoint(x,y,rs,set);
            x++;
            int d1=Math.abs(r*r-x*x-y*y);
            int d2=Math.abs(r*r-x*x-(y-1)*(y-1));
            int min=Math.min(d1,d2);
            y=min;
        }
    }

    //follow up r很小比如1咋办，应该就是这两个值都取吧
    /*
    double d1=Math.abs(r*r-i*i-y*y);
    double d2=Math.abs(r*r-i*i-(y+1)*(y+1));
     */
    //如何尽量减少乘法
    /*
    每次还是在比较 (x+1, y) 和 (x+1, y-1)。为了省事，不直接比两个点，而是看它们中间的 midpoint：
    M = (x+1, y-0.5) 定义决策值：
    d = f(x+1, y-0.5)
    d < 0：中点在圆内，选 (x+1, y)
    d >= 0：中点在圆外，选 (x+1, y-1)
    就是说不用计算d1和d2了，就计算一次d就能判断取哪个,这个写法直观，但是还是每一轮都用了乘法，而最优解更nb，懒得研究了
     */
    public List<int[]> draw23(int r,int beginx,int beginy){
        List<int[]> rs=new ArrayList<>();
        Set<String> set=new HashSet<>();
        int x=0;
        int y=r;
        int rr=r*r;
        while (x<=y){
            addPoint(x+beginx,y+beginy,rs,set);
            x++;
            double d=x*x+(y-0.5)*(y-0.5)-rr;
            if(d>=0){
                y=y-1;
            }
        }
        return rs;
    }
}
