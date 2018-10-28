package amazon;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Created by yufengzhu on 10/26/18.
 */
public class steakHouse {
    public static void main(String[] a){
        steakHouse sh=new steakHouse();
        List<List<Integer> > ls=new ArrayList<>();
        List<Integer> l1=new ArrayList<>();
        l1.add(1);
        l1.add(-3);
        List<Integer> l2=new ArrayList<>();
        l1.add(1);
        l1.add(2);
        ls.add(l1);
        ls.add(l2);
        sh.nearestXsteakHouses(3,ls,1);

    }

    List<List<Integer>> nearestXsteakHouses(int totalSteakhouses, List<List<Integer>> allLocations,
                                            int numSteakhouses)
    {
        List<List<Integer>> rs=new ArrayList<>();
        if(allLocations==null||allLocations.size()==0){
            return rs;
        }

        PriorityQueue<Pair> pq=new PriorityQueue<>(new Comparator<Pair>(){
            public int compare(Pair p1,Pair p2){
                double d1=p1.dist;
                double d2=p2.dist;
                return (int)(d1-d2);
            }
        });
        for(List<Integer> ls: allLocations){
            double dist=Math.sqrt((Math.pow(ls.get(0),2.0)+Math.pow(ls.get(1),2.0)));
            Pair p=new Pair(dist);
            p.cord.add(ls.get(0));
            p.cord.add(ls.get(1));
            pq.offer(p);
        }
        int index=0;
        while(!pq.isEmpty()&&index<numSteakhouses){
            Pair p=pq.poll();
            rs.add(p.cord);
            index++;
        }

        return rs;

    }
    // METHOD SIGNATURE ENDS
    class Pair{
        double dist;
        List<Integer> cord;
        public Pair(double dist){
            this.dist=dist;
            cord=new ArrayList<Integer>();
        }
    }
}
