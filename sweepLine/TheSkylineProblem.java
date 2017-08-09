package sweepLine;

import java.util.*;

/**
 * Created by 502575560 on 7/29/17.
 */
public class TheSkylineProblem {
    public static void main(String[] args){
        int[][] b={{1,2,1},{1,2,2},{1,2,3}};
        TheSkylineProblem sk=new TheSkylineProblem();
        sk.getSkyline(b);
    }
    //不会,
    //http://blog.csdn.net/qq508618087/article/details/51311778  看他第二个解法的思路
    //https://codesolutiony.wordpress.com/2015/06/01/leetcode-the-skyline-problem-lintcode-building-outline/ 代码可以看他的
    //http://blog.csdn.net/xudli/article/details/46349383   它的compartor不同,而且edge的高度可以是负的
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> rs=new ArrayList<>();
        PriorityQueue<edge> q=new PriorityQueue<>(10, new Comparator<edge>() {
            @Override
            public int compare(edge o1, edge o2) {
                if(o1.x!=o2.x){//这个comparator是按第二个链接写的,有了isright之后comparator就复杂了
                    return o1.x-o2.x;//如果横坐标不同则就按横坐标排
                }
                if(!o1.isRight&&!o2.isRight){//如果横坐标相同,还要分是做还是右edge,如果都是左edge则按高度降序排,因为假如有2个edge横坐标相同,
                    return o2.h-o1.h;//我要先poll出纵坐标高的,否则不对,举个例子看
                }
                if(o1.isRight&&o2.isRight){//如果都是右edge则要按高度升序排,否则也不行
                    return o1.h-o2.h;
                }
                //横坐标相同,o1 和o2是一左一右,则左的排前面
                return o1.isRight?1:-1;//想,一般降序就是o1-o2,则如果o1-o2>0则o1就排去后面了
            }
        });
        PriorityQueue<Integer> heap=new PriorityQueue<>(10, Collections.reverseOrder());
        for(int i=0;i<buildings.length;i++){
            q.offer(new edge(buildings[i][0],buildings[i][2],false));
            q.offer(new edge(buildings[i][1],buildings[i][2],true));
        }
        while (!q.isEmpty()){
            edge e=q.poll();
            if(!e.isRight){
                if(heap.isEmpty()||heap.peek()<e.h){
                    rs.add(new int[]{e.x,e.h});
                }
                heap.offer(e.h);
            }else{
                int temp=e.h;
                heap.remove(e.h);
                if(heap.isEmpty()){
                    rs.add(new int[]{e.x,0});
                    continue;
                }
                if(!heap.isEmpty()&&heap.peek()<temp){
                    rs.add(new int[]{e.x,heap.peek()});
                }

            }
        }
        return rs;
    }
    class edge{
        int x;
        int h;
        boolean isRight;
        public  edge(int x, int h, boolean isRight){
            this.x=x;
            this.h=h;
            this.isRight=isRight;
        }
    }
}
