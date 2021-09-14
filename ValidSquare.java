import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ValidSquare {
    public static void main(String[] args){
        ValidSquare vs=new ValidSquare();
        System.out.println(vs.validSquare(new int[]{0,0},new int[]{13,0},new int[]{5,12},new int[]{18,12}));
    }

    //9/11/2021 自己想的是用斜率确定两条平行线，再确定某3条边是否a方+b方=c方从而确定直角，有点麻烦而且double的精度导致不能正确比较a方加b方=c方
    //https://leetcode.com/problems/valid-square/discuss/103448/Simple-python-solution-by-comparing-distance
    //这个方法吊，就是计算出所有的点之间的距离，如果是正方形的话肯定是四条边相等，然后两条对角线也相等。这6个距离排序一下，前四个肯定相等，后2个肯定相等
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        if (p1[0]==p2[0]&&p2[0]==p3[0]&&p3[0]==p4[0]&&p1[1]==p2[1]&&p2[1]==p3[1]&&p3[1]==p4[1]){
            return false;
        }
        List<Double> ls=new ArrayList<>();
        ls.add(getDist(p1,p2));
        ls.add(getDist(p1,p3));
        ls.add(getDist(p1,p4));
        ls.add(getDist(p2,p3));
        ls.add(getDist(p2,p4));
        ls.add(getDist(p3,p4));
        Collections.sort(ls);
//        return ls.get(0)==ls.get(1)&&ls.get(1)==ls.get(2)&&ls.get(2)==ls.get(3);//貌似java 的double直接比较是不对的，让他们相减等于0才对
        if (ls.get(0)-ls.get(1)==0&&ls.get(1)-ls.get(2)==0&&ls.get(2)-ls.get(3)==0){
            if (ls.get(4)-ls.get(5)==0){
                return true;
            }
        }
        return false;
    }

    double getDist(int[] p1,int[] p2){
        return Math.pow(p1[0]-p2[0],2)+Math.pow(p1[1]-p2[1],2);
    }

}
