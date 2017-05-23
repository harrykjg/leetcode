import java.util.HashSet;
import java.util.Set;

/**
 * Created by 502575560 on 5/19/17.
 */
public class hashsettest {
        public static void main(String[] args){
            Set<points> set=new HashSet<>();
//            Integer[] a1=new Integer[]{1,2,3}; 用数组的话好想没法重写equals和hashcode方法?
//            set.add(a1);
//            Integer[] a2=new Integer[]{1,2,3};
            points p1=new points(1,2,3,4);
            points p2=new points(1,2,3,4);
            set.add(p1);
            System.out.println(set.add(p2));
            System.out.println(  p1.hashCode());
            System.out.println(  p2.hashCode());

        }

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