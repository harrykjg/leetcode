import java.util.*;

/**
 * Created by yufengzhu on 9/2/18.
 */
public class FallingSquares {
    public static void main(String[] args){
        FallingSquares fs=new FallingSquares();
        int[][] a={{3,2},{9,8},{4,4}};
        fs.fallingSquares(a);
    }
    //看了一次huahua的视频之后还是不会，这个输入参数不一定是按着从左往右的顺序掉进来的
    //https://www.youtube.com/watch?v=UeuV-6Ygxs4
    //https://leetcode.com/problems/falling-squares/discuss/108775/Easy-Understood-TreeMap-Solution
    //线段树专题 https://vjudge.net/contest/66989#overview
    public List<Integer> fallingSquares(int[][] positions) {//只写了n平方的解法，nlogn的还不知道怎么写
        List<Integer> rs=new ArrayList<>();
        List<Interval> ls=new ArrayList<>();

        Interval i1=new Interval(positions[0][0],positions[0][0]+positions[0][1]-1,positions[0][1]);
        ls.add(i1);
        rs.add(positions[0][1]);
        int max=positions[0][1];
        for(int i=1;i<positions.length;i++){
            int b=positions[i][0];
            int e=b+positions[i][1]-1;
            int h=positions[i][1];
            Interval cur=new Interval(b,e,h);
            for(Interval in:ls){
                if(in.e<cur.b){
                    continue;
                }
                if(in.b>cur.e){
                    continue;
                }
                int curHeight=in.height+h;
                if(curHeight>=cur.height){//妈的这里debug了很久，cur。height和max是要分开判断的，开始写成了curHeight和max去比了
                    cur.height=curHeight;
                }
            }
            max=Math.max(cur.height,max);
            ls.add(cur);
            rs.add(max);
        }
        return rs;
    }
    class Interval{
        int b;
        int e;
        int height;
        public Interval(int b,int e,int c){
            this.b=b;
            this.e=e;
            height=c;
        }
    }
}
