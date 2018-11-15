package GraphAndSearch;

import java.util.*;

/**
 * Created by yufengzhu on 10/28/18.
 */
public class CutOffTreesforGolfEvent {

    public static void main(String[] args){
        CutOffTreesforGolfEvent co=new CutOffTreesforGolfEvent();
        List<List<Integer>> ls=new ArrayList<>();
        List<Integer> ls1=new ArrayList<>();
        ls1.add(3);
        ls1.add(4);
        ls1.add(5);
        ls.add(ls1);
        List<Integer> ls2=new ArrayList<>();
        ls2.add(0);
        ls2.add(0);
        ls2.add(2);
        ls.add(ls2);
        List<Integer> ls3=new ArrayList<>();
        ls3.add(8);
        ls3.add(7);
        ls3.add(6);
        ls.add(ls3);
        System.out.print(co.cutOffTree(ls));

    }

    //看了花花的视频才懂题意https://www.youtube.com/watch?v=OFkLC30OxXM
    //就是找每一个点都是一个bfs，算出所有的步数
    int rs=0;
    public int cutOffTree(List<List<Integer>> forest) {
        PriorityQueue<Pair> pq=new PriorityQueue<Pair>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o1.height-o2.height;
            }
        });

        for(int i=0;i<forest.size();i++){
            for(int j=0;j<forest.get(i).size();j++){
                if(forest.get(i).get(j)==0){
                    continue;
                }
                Pair p=new Pair(forest.get(i).get(j));
                p.cord.add(i);
                p.cord.add(j);
                pq.offer(p);
            }
        }
        if(pq.size()==0){
            return -1;
        }
        helper(0,0,pq,forest);
        if(!pq.isEmpty()){
            return -1;
        }
        return rs;

    }

    void helper(int row,int col,PriorityQueue<Pair> pq,List<List<Integer>> forest){
        if(pq.isEmpty()){
            return;
        }
        Queue<Pair> q=new LinkedList<>();
        Pair first=new Pair(forest.get(row).get(col));
        first.cord.add(row);
        first.cord.add(col);
        q.offer(first);
        int target=pq.peek().height;
        boolean[][] memo=new boolean[forest.size()][forest.get(0).size()];
        memo[row][col]=true;
        int step=0;
        int count1=1;
        int count2=0;

        while (!q.isEmpty()){
            Pair cur=q.poll();
            int r=cur.cord.get(0);
            int c=cur.cord.get(1);
            count1--;
            if(cur.height==target){
                rs+=step;
                pq.poll();
                forest.get(r).set(c,1);
                helper(r,c,pq,forest);
                return;
            }

            if(r>0&&forest.get(r-1).get(c)!=0&&memo[r-1][c]==false){
                Pair next=new Pair(forest.get(r-1).get(c));
                next.cord.add(r-1);
                next.cord.add(c);
                q.offer(next);
                memo[r-1][c]=true;
                count2++;
            }
            if(c+1<forest.get(r).size()&&forest.get(r).get(c+1)!=0&&memo[r][c+1]==false){
                Pair next=new Pair(forest.get(r).get(c+1));
                next.cord.add(r);
                next.cord.add(c+1);
                q.offer(next);
                memo[r][c+1]=true;
                count2++;
            }
            if(r+1<forest.size()&&forest.get(r+1).get(c)!=0&&memo[r+1][c]==false){
                Pair next=new Pair(forest.get(r+1).get(c));
                next.cord.add(r+1);
                next.cord.add(c);
                q.offer(next);
                memo[r+1][c]=true;
                count2++;
            }
            if(c>0&&forest.get(r).get(c-1)!=0&&memo[r][c-1]==false){
                Pair next=new Pair(forest.get(r).get(c-1));
                next.cord.add(r);
                next.cord.add(c-1);
                q.offer(next);
                memo[r][c-1]=true;
                count2++;
            }
            if(count1==0){
                step++;
                count1=count2;
                count2=0;
            }
        }

    }
    class Pair{
        int height;
        List<Integer> cord;
        public Pair(int height){
            this.height=height;
            cord=new ArrayList<>();
        }
    }
}

