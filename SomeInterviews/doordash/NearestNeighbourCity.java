package SomeInterviews.doordash;

import java.util.*;

public class NearestNeighbourCity {
  //https://www.1point3acres.com/bbs/thread-780964-1-1.html
    public static void main(String[] args){//思路就是把所有点的坐标分成2个map，xmap的key是x坐标，value是treemap，其key是y坐标，value是这个city的pair class
        NearestNeighbourCity nn=new NearestNeighbourCity();//这样query某个点的时候，和这个点的x相同或者y相同的所有点我都知道了，并且treemap把这些点的对应
        String[] names=new String[]{"a","b","x","d","e","f","k"}; //坐标也排序了，比如query（0，1）点，则xmap.get(0)就得到了和0横坐标相等的所有点的纵坐标，
        int[] x={0,0,1,0,2,5,3};                //并且是排好序的，然后用lowerEntry（1）和higherentry（1）就可以得到离纵坐标最近的这两个点，计算距离即可，同理ymap
        int[] y={0,2,1,7,1,1,1};
        String[] query={"a","b","x","d","e","f"};
        System.out.println(nn.getNearestCities(names,x,y,query));
    }
    /*

                x1,1  e2,1  k3,1  f5,1
            a0,0,     b0,2           d0,7


     */


    //9/14/2021
    public List<String> getNearestCities(String[] names, int[] x, int[] y, String[] query) {
        HashMap<String,Pair> map=new HashMap<>();
        HashMap<Integer, TreeMap<Integer,Pair>> xmap=new HashMap<>();
        HashMap<Integer,TreeMap<Integer,Pair>> ymap=new HashMap<>();
        for (int i=0;i<names.length;i++){
            if (!map.containsKey(names[i])){
                map.put(names[i],new Pair(names[i],x[i],y[i]));
            }
            Pair p=map.get(names[i]);
            if (!xmap.containsKey(p.x)){
                xmap.put(p.x,new TreeMap<>());
            }
            xmap.get(p.x).put(p.y,p);

            if (!ymap.containsKey(p.y)){
                ymap.put(p.y,new TreeMap<>());
            }
            ymap.get(p.y).put(p.x,p);
        }
        List<String> rs=new ArrayList<>();
        for (String city:query){
            Pair p=map.get(city);
            int dist=Integer.MAX_VALUE;
            String nearest="zzz";
            TreeMap<Integer,Pair> tmx=xmap.get(p.x);//lower方法得出的是严格小于这个值的最大值，floor则会返回小于等于的值
            Map.Entry<Integer,Pair> lower1=tmx.lowerEntry(p.y);//注意这里可能是空，
            Map.Entry<Integer,Pair> higher1=tmx.higherEntry(p.y);
            if (lower1!=null){
                Pair pl=lower1.getValue();
                int temp=Math.abs(pl.y-p.y);
                String c=pl.name;
                if (dist>temp||(dist==temp&&c.compareTo(nearest)<0)){
                    dist=temp;
                    nearest=c;
                }
            }
            if (higher1!=null){
                Pair ph=higher1.getValue();
                int temp=Math.abs(ph.y-p.y);
                String c=ph.name;
                if (dist>temp||(dist==temp&&c.compareTo(nearest)<0)){
                    dist=temp;
                    nearest=c;
                }
            }

            TreeMap<Integer,Pair> tmy=ymap.get(p.y);
            Map.Entry<Integer,Pair> lower2=tmy.lowerEntry(p.x);
            Map.Entry<Integer,Pair> higher2=tmy.higherEntry(p.x);
            if (lower2!=null){
                Pair pl=lower2.getValue();
                int temp=Math.abs(pl.y-p.y);
                String c=pl.name;
                if (dist>temp||(dist==temp&&c.compareTo(nearest)<0)){
                    dist=temp;
                    nearest=c;
                }
            }
            if (higher2!=null){
                Pair ph=higher2.getValue();
                int temp=Math.abs(ph.y-p.y);
                String c=ph.name;
                if (dist>temp||(dist==temp&&c.compareTo(nearest)<0)){
                    dist=temp;
                    nearest=c;
                }
            }

            rs.add(nearest);
        }
        return rs;

    }
    class Pair{
        String name;
        int x;
        int y;
        public Pair(String name,int x,int y){
            this.name=name;
            this.x=x;
            this.y=y;
        }
    }
}
