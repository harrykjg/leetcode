package sweepLine;

import java.util.*;

/**
 * Created by 502575560 on 7/29/17.
 */
public class TheSkylineProblem {
    public static void main(String[] args){
        int[][] b={{1,2,1},{1,2,2},{1,2,3}};
        TheSkylineProblem sk=new TheSkylineProblem();
        sk.getSkyline3(b);
    }
    //不会,九章的题目返回的是三个数，leetcode返回的是点就行了，不一样
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
                if(o1.isRight&&o2.isRight){//如果都是右edge则要按高度升序排,否则也不行。因为先处理小的时候，把它remove之后，pq还不是空，
                    return o1.h-o2.h;   //所以不会加进一个高度为0 的点，只会再最后一个被remove的时候才会产生一个
                }
                //横坐标相同,o1 和o2是一左一右,则左的排前面
                return o1.isRight?1:-1;//想,一般升序就是o1-o2,则如果o1-o2>0则o1就排去后面了
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
//1/20/2018 九章第二轮还是不会,看回以前的分析
    public List<int[]> getSkyline2(int[][] b) {
        List<int[]> rs=new ArrayList<>();

        PriorityQueue<edge> pq=new PriorityQueue<>(new Comparator<edge>() {
            @Override
            public int compare(edge o1, edge o2) {
                if(o1.x!=o2.x){
                    return o1.x-o2.x;
                }
                if(!o1.isRight&&!o2.isRight){//这里要注意，比较难想到了,看回之前的解释
                    return o2.h-o1.h;
                }
                if(o1.isRight&&o2.isRight){//一样不好想，画图可以理解
                    return o1.h-o2.h;
                }
                return o1.isRight?1:-1;
            }
        });
        for(int i=0;i<b.length;i++){
            edge e1=new edge(b[i][0],b[i][2],false);
            edge e2=new edge(b[i][1],b[i][2],true);
            pq.offer(e1);
            pq.offer(e2);
        }
        PriorityQueue<Integer> heap=new PriorityQueue<>(Comparator.reverseOrder());
        while (!pq.isEmpty()){
            edge cur=pq.poll();
            if(cur.isRight){//注意，还就是要先按isRight与否来判断，否则判断很复杂而且容易错
                heap.remove(cur.h);//在想如果有两栋楼有高度，那么remove了一个高度那不就把另一个也remove了？结果不会，因为heap可以有重复的元素。
                if(heap.isEmpty()){//我们根本不关心删掉的是“第一个楼”还是“第二个楼”，只关心“高度 10 的楼数量少了一个”。
                                    //从“当前最高高度”的角度来看，这两栋楼是完全等价的。因为就算删的是第一个楼的高度，那么pq peek出来还是有一个
                                    //高度是10 的楼
                    rs.add(new int[]{cur.x,0});
                }else{
                    if(heap.peek()<cur.h){
                        rs.add(new int[]{cur.x,heap.peek()});
                    }
                }
            }else{
                if(heap.isEmpty()||heap.peek()<cur.h){//这写成<=也错了
                    rs.add(new int[]{cur.x,cur.h});
                }
                heap.offer(cur.h);
            }
        }

        return rs;
    }

    //6/19/2021,还是得话很长时间想，comarator那里不好想. 就按以前的记吧，很难现场搞好
    public List<List<Integer>> getSkyline3(int[][] b) {
        PriorityQueue<Integer> pq=new PriorityQueue<>(Collections.reverseOrder());
        List<edge2> al=new ArrayList<>();
        for (int i=0;i<b.length;i++){
            edge2 e1=new edge2(b[i][0],b[i][2],0);
            edge2 e2=new edge2(b[i][1],b[i][2],1);
            al.add(e1);
            al.add(e2);
        }
        Collections.sort(al, new Comparator<edge2>() {
            @Override
            public int compare(edge2 o1, edge2 o2) {
                if (o1.x!=o2.x){
                    return o1.x-o2.x;
                }
                //考虑2个因素，高度和isRight,需要列一个decision tree看
                if(o1.x!=o2.x){
                    return o1.x-o2.x;
                }
                if(o1.isRight==0&&o2.isRight==0){
                    return o2.h-o1.h;
                }
                if(o1.isRight==1&&o2.isRight==1){
                    return o1.h-o2.h;
                }
                return o1.isRight==1?1:-1;
            }
        });

        List<List<Integer>> rs=new ArrayList<>();
        for (int i=0;i<al.size();i++){
            edge2 cur=al.get(i);
            if (pq.isEmpty()){
                pq.offer(cur.h);
                List<Integer> a=new ArrayList<>();
                a.add(cur.x);
                a.add(cur.h);
                rs.add(a);
                continue;
            }
            if (cur.isRight==0){//左边的先遇到的是高最大的，肯定要画个点
                if (cur.h>pq.peek()){
                    List<Integer> a=new ArrayList<>();
                    a.add(cur.x);
                    a.add(cur.h);
                    rs.add(a);
                }
                pq.offer(cur.h);
            }else {//来了个右边的，
                if (pq.peek()>cur.h){//如果这个右边的本来就矮，不会形成点，直接remove
                    pq.remove(cur.h);
                }else {//如果来了个高的，则会形成一个点，这个点就是这个高的和第二高的交点
                    pq.poll();
                    List<Integer> a=new ArrayList<>();
                    if (pq.isEmpty()){
                        a.add(cur.x);
                        a.add(0);
                        rs.add(a);
                    }else if (pq.peek()<cur.h){//少了这个条件也不对
                        a.add(cur.x);
                        a.add(pq.peek());
                        rs.add(a);
                    }
                }
            }

        }
        return rs;

    }

    class edge2{
        int x;
        int h;
        int isRight;
        public  edge2(int x, int h, int isRight){
            this.x=x;
            this.h=h;
            this.isRight=isRight;
        }
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
