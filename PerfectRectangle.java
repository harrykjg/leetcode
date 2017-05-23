import java.util.HashSet;
import java.util.Set;

/**
 * Created by 502575560 on 5/19/17.
 */
public class PerfectRectangle {
    public static void main(String[] args){
        PerfectRectangle pr=new PerfectRectangle();
        int[][] r=new int[][]{{0,0,4,1},{7,0,8,2},{6,2,8,3},{5,1,6,3},{4,0,5,1},{6,0,7,2},{4,2,5,3},{2,1,4,3},{0,1,2,2},{0,2,2,3},{4,1,5,2},{5,0,6,1}};
        System.out.println(pr.isRectangleCover(r));
    }

    //自己想了一个暴力法,先算出整个数组最左下角和最右上角(就是x坐标最小,y坐标也最小的点,和x坐标最大,y坐标也最大的一个点,得出一个最大的正方形
    //如果时cover的话则这个大正方形分成一个个面积为1小正方形的面积肯定时全部cover的,然后设一个set,set存的是这个大正方形分成的面积为1 的所有小正方形
    //的坐标,如(1,1,2,2),然后再遍历整个数组,把数组每一行表示的正方形也分成小正方形,存在的话就再set里去掉,set里不存在的话就说明是重叠了(已经被去掉一次了),
    //最后set里还有元素的话说明是没有被cover,改了几下超时了
    //http://www.cnblogs.com/grandyang/p/5825619.html
    //http://blog.csdn.net/MebiuW/article/details/52354018
    //http://www.cnblogs.com/lishiblog/p/5829410.html
    public boolean isRectangleCover(int[][] r) {
        HashSet<String> set=new HashSet<>();
        int minx=Integer.MAX_VALUE;
        int miny=Integer.MAX_VALUE;
        int maxx=Integer.MIN_VALUE;
        int maxy=Integer.MIN_VALUE;
        long area=0;
        for(int i=0;i<r.length;i++){
            minx=Math.min(minx,Math.min(r[i][0],r[i][2]));
            miny=Math.min(miny,Math.min(r[i][1],r[i][3]));
            maxx=Math.max(maxx,Math.max(r[i][0],r[i][2]));
            maxy=Math.max(maxy,Math.max(r[i][1],r[i][3]));
            area+=(r[i][2]-r[i][0])*(r[i][3]-r[i][1]);
            String lt=r[i][0]+" "+r[i][3];
            String lb=r[i][0]+" "+r[i][1];
            String rt=r[i][2]+" "+r[i][3];
            String rb=r[i][2]+" "+r[i][1];
            if(!set.contains(lt)){
                set.add(lt);
            }else{
                set.remove(lt);
            }
            if(!set.contains(lb)){
                set.add(lb);
            }else{
                set.remove(lb);
            }
            if(!set.contains(rt)){
                set.add(rt);
            }else{
                set.remove(rt);
            }
            if(!set.contains(rb)){
                set.add(rb);
            }else{
                set.remove(rb);
            }
        }
        if((maxx-minx)*(maxy-miny)!=area){
            return false;
        }
        if(set.size()!=4||!set.contains(minx+" "+maxy)||!set.contains(minx+" "+miny)||!set.contains(maxx+" "+miny)||!set.contains(maxx+" "+maxy)){
            return false;
        }
        return true;


    }
    public boolean isRectangleCover2(int[][] r) {
        int min=Integer.MAX_VALUE;
        for(int i=0;i<r.length;i++){
            for(int j=0;j<r[0].length;j++){
                min=Math.min(min,r[i][j]);
            }
        }
        if(min<0){//如果数组有负数,则全部都加上这个最小的负数时所有坐标都是正数再处理
            for(int i=0;i<r.length;i++){
                for(int j=0;j<r[0].length;j++){
                    r[i][j]+=min;
                }
            }
        }
        int minx=Integer.MAX_VALUE;
        int miny=Integer.MAX_VALUE;
        int maxx=Integer.MIN_VALUE;
        int maxy=Integer.MIN_VALUE;
        for(int i=0;i<r.length;i++){

            minx=Math.min(minx,Math.min(r[i][0],r[i][2]));
            miny=Math.min(miny,Math.min(r[i][1],r[i][3]));
            maxx=Math.max(maxx,Math.max(r[i][0],r[i][2]));
            maxy=Math.max(maxy,Math.max(r[i][1],r[i][3]));

        }
        Set<points> set=new HashSet<>();
        //把最左下和最右上的点加进set里
        for(int i=minx;i<maxx;i++){
            for(int j=miny;j<maxy;j++){
                points p=new points(i,j,i+1,j+1);
                set.add(p);
            }
        }

        for(int i=0;i<r.length;i++){
            for(int j=r[i][0];j<r[i][2];j++){
                for(int k=r[i][1];k<r[i][3];k++){
                    points p=new points(j,k,j+1,k+1);
                    if(set.contains(p)){
                        set.remove(p);
                    }else{
                        return false;
                    }
                }
            }
        }

        return set.size()==0?true:false;
    }
    class points{
        int x1,x2,y1,y2;
        public points(int a,int b,int c,int d){
            x1=a;
            x2=b;
            y1=c;
            y2=d;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            points points = (points) o;

            if (x1 != points.x1) return false;
            if (x2 != points.x2) return false;
            if (y1 != points.y1) return false;
            return y2 == points.y2;

        }

        @Override
        public int hashCode() {
            int result = x1;
            result = 31 * result + x2;
            result = 31 * result + y1;
            result = 31 * result + y2;
            return result;
        }

    }
}
