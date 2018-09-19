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
    //9/16/2018,naive的做法居然一次过，用不着priorityqueue。思路就是把interval一个个加到list里，对于每个新加的interval，遍历已有list，看谁和他有相交，在所有相交的interval中间选最高的，把这个高度
    //加上当前新加进来的interval的高度上就完了，上次写的还不如这次清晰
    public List<Integer> fallingSquares2(int[][] positions) {
        List<Integer> rs=new ArrayList<>();
        ArrayList<Interval> al=new ArrayList<>();
        int max=0;
        for(int i=0;i<positions.length;i++){
            Interval in=new Interval(positions[i][0],positions[i][0]+positions[i][1],positions[i][1]);
            int highestUnder=0;
            for(int j=0;j<al.size();j++){
                if(al.get(j).e<=in.b||al.get(j).b>=in.e){
                    continue;
                }
                Interval under=al.get(j);
                highestUnder=Math.max(highestUnder,under.height);
            }
            in.height+=highestUnder;
            max=Math.max(max,in.height);
            al.add(in);
            rs.add(max);
        }
        return rs;
    }
}
