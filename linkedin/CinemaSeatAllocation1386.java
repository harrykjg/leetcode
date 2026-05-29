package linkedin;

import java.util.HashSet;
import java.util.Set;

public class CinemaSeatAllocation1386 {
    static void main() {
        int[][] a={{2,1},{1,8},{2,6}};
        System.out.println(maxNumberOfFamilies(2,a));
    }
    //2/9/2026 这样写超时，就是检测每个作为标记编号。检测每行左边，右边，中间可不可以坐，如果左边或者右边都没人坐，则再检测中间的能不能做，
    //看下面评论的做法，其实也差不多，他是用了hashmap去记录有reserved的行，那么没有reserved的行就是n-map.size就直接知道可以rs+=2了，因此快
    //https://leetcode.com/problems/cinema-seat-allocation/solutions/546705/java-explained-comments-simple-hashmap-b-zpoz/
    public static int maxNumberOfFamilies(int n, int[][] reservedSeats) {
         int rs=0;
         Set<Integer> set=new HashSet<>();
         for (int i=0;i<reservedSeats.length;i++){
             int id=reservedSeats[i][0]*10+reservedSeats[i][1];
             set.add(id);
         }
         for (int i=1;i<=n;i++){
             boolean left=false;
             boolean right=false;
             int id1=i*10+2;
             int id2=i*10+3;
             int id3=i*10+4;
             int id4=i*10+5;
             int id5=i*10+6;
             int id6=i*10+7;
             int id7=i*10+8;
             int id8=i*10+9;
             if(!set.contains(id1)&&!set.contains(id2)&&!set.contains(id3)&&!set.contains(id4)){
                 rs++;
                 left=true;
             }
             if(!set.contains(id5)&&!set.contains(id6)&&!set.contains(id7)&&!set.contains(id8)){
                 rs++;
                 right=true;
             }
             if(!left&&!right){
                 if(!set.contains(id3)&&!set.contains(id4)&&!set.contains(id5)&&!set.contains(id6)){
                     rs++;
                 }
             }


         }
         return rs;
    }
}
