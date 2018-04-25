package Advance4;

import java.util.*;

/**
 * Created by yufengzhu on 4/18/18.
 */
public class TheSkylineProblem {
    public static void main(String[]args){
        int[][] in={{1,3,3},{3,5,3}};
        TheSkylineProblem ts=new TheSkylineProblem();
        ts.buildingOutline(in);
    }

    //九章的题目返回的是三个数，leetcode返回的是点就行了，不一样，lintcode应该accept但是貌似会超时？
    //4／18／2018，九章第二轮，有一点思路，不好写,特别是comparator那里，要想好应该进出的边应该谁排前面

    List<List<Integer>> buildingOutline(int[][] b) {
        List<List<Integer>> rs=new ArrayList<>();
        if(b.length==0){
            return rs;
        }
        List<cord> ls=new ArrayList<>();
        for(int i=0;i<b.length;i++){
            ls.add(new cord(b[i][0],b[i][2],1));
            ls.add(new cord(b[i][1],b[i][2],0));
        }
        PriorityQueue<Integer> pq=new PriorityQueue<>(Collections.reverseOrder());

        Collections.sort(ls, new Comparator<cord>() {
            @Override
            public int compare(cord o1, cord o2) {
                if(o1.x==o2.x){
                    if(o1.flag==1&&o2.flag==1){
                        return o2.height-o1.height;
                    }
                    if(o1.flag==0&&o2.flag==0){
                        return o1.height-o2.height;
                    }
                    return o1.flag==0?-1:1;//o1是出去的话，那么返回-1，说明o1小，即排在前面，即出去的的排在前面
                }
                return o1.x-o2.x;
            }
        });
        int pre=0;
        int preh=0;
        for(int i=0;i<ls.size();i++){
            if(pq.isEmpty()){
                pre=ls.get(i).x;
                preh=ls.get(i).height;
                pq.offer(ls.get(i).height);
                continue;
            }
            if(ls.get(i).flag==0){//出去的边
                if(ls.get(i).height>=pq.peek()){
                    if(rs.size()>0&&rs.get(rs.size()-1).get(2)==ls.get(i).height){//用来处理两个相邻的同高度的building如{{1,3,3},{3,5,3}};，不把他们断开，连在一起
                        rs.get(rs.size()-1).set(1,ls.get(i).x);
                    }else{
                        List<Integer> in=new ArrayList<>();
                        in.add(pre);
                        in.add(ls.get(i).x);
                        in.add(preh);
                        rs.add(in);
                    }

                    pq.remove(ls.get(i).height);
                    pre=ls.get(i).x;
                    preh=pq.isEmpty()?0:pq.peek();

                }else{
                    pq.remove(ls.get(i).height);
                }

            }else{//进来的边
                if(ls.get(i).height>pq.peek()){
                    List<Integer> in=new ArrayList<>();
                    in.add(pre);
                    in.add(ls.get(i).x);
                    in.add(preh);
                    rs.add(in);
                    pre=ls.get(i).x;
                    preh=ls.get(i).height;
                }
                pq.offer(ls.get(i).height);
            }
        }
        return rs;
    }
    class cord{
        int x;
        int height;
        int flag;
        public cord(int a,int b,int c){
            x=a;
            height=b;
            flag=c;
        }
    }
}
