import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by 502575560 on 6/14/17.
 */
public class QueueReconstructionbyHeight {
    //目测就是找规律,有greedy的tag,规律自己可以找出来但是写起来貌似会concurrent array修改错误,即遍历array的同时又加或删元素,
    //看网上的思路实现简单点但是不好想
    //http://blog.csdn.net/fuxuemingzhu/article/details/68486884 神奇
    //http://www.cnblogs.com/liujinhong/p/6403353.html
    public static void main(String[] args){
        int[][] a={{7,0},{7,1},{4,4},{5,2},{5,0},{6,1}};
        System.out.println( reconstructQueue(a));
    }

    public static int[][] reconstructQueue(int[][] people) {

        ArrayList<int[]> rs=new ArrayList<>();
        //Arrays.sort(people, (a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]); //lamda写法
        Arrays.sort(people, new Comparator<int[]>() {//people里的每一个元素是数组,所以comparator里是int[]
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0]<o2[0]){
                    return 1;
                }
                if(o1[0]==o2[0]){
                    return o1[1]-o2[1];//return arg0-arg1就是升序排序，return arg1-arg0就是降序排序
                }
                return -1;
            }
        });
        for(int[]a:people){
            System.out.println(a[0]+" "+a[1]);
        };
        for(int i=0;i<people.length;i++){
            int h=people[i][0];
            int k=people[i][1];
            rs.add(k,people[i]);
        }

        int[][] rss=new int[rs.size()][2];
        for(int i=0;i<rs.size();i++){
            rss[i][0]=rs.get(i)[0];
            rss[i][1]=rs.get(i)[1];
        }
        return rss;

    }
}
