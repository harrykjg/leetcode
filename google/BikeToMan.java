package google;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yufengzhu on 10/13/18.
 */
public class BikeToMan {
    public static void main(String[] a){

    }

    /*
    在二维的坐标里面，输入两类点，可以看成人和自行车。要求每个人取离他最近的自行车。最后输出这些人和自行车配对的点的坐标。没有距离相等的情况。
eg, p1(0, 0), p2(1, 0)    b1(1, 0), b2(1, 1)
输出 [((0, 0), (1, 0)), ((1, 0), (1, 1))]
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=443706&extra=page%3D1%26filter%3Dsortid%26sortid%3D311%26searchoption%5B3089%5D%5Bvalue%5D%5B2%5D%3D2%26searchoption%5B3089%5D%5Btype%5D%3Dcheckbox%26searchoption%5B3046%5D%5Bvalue%5D%3D1%26searchoption%5B3046%5D%5Btype%5D%3Dradio%26sortid%3D311
http://www.1point3acres.com/bbs/forum.php?mod=viewthread&tid=447853
     */
    //只能写个暴力的
    public List<List<int[]>> findPath(int[][] man,int[][] bike){
        boolean[] memo=new boolean[bike.length];
        List<List<int[]>> rs=new ArrayList<>();
        for(int[] m:man){
            int[] mb=new int[2];
            double dist=Double.MAX_VALUE;
            for(int i=0;i<bike.length;i++){
                if(memo[i]==false){
                    double cur=Math.sqrt(Math.pow((double)(m[0]-bike[i][0]),2)+Math.pow((double)(m[1]-bike[i][1]),2));
                    if(cur<dist){
                        dist=cur;
                        mb[0]=bike[i][0];
                        mb[1]=bike[i][1];
                    }
                    memo[i]=true;
                }
            }
            ArrayList<int[]> aa=new ArrayList<>();
            aa.add(m);
            aa.add(mb);
            rs.add(aa);
        }
        return rs;
    }
}
