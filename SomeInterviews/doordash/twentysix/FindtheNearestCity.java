package SomeInterviews.doordash.twentysix;

import java.util.*;

public class FindtheNearestCity {

    //自己的话只能想到装一个city的x和y相同的邻居分别在两个map里，然后这两个map对应的x或y相等的邻居再合在一起找.
    //hack2hire答案说的是把所有邻居排序，然后最近的邻居肯定是当前城市的左边或者右边的那一个，就是二分法找这个城市钙插入的点。而gpt说的是构造的时候是把邻居和自己排序
    // 但是不需要二分法，直接遍历邻居的同时，对于第i个邻居，我看i和i-1和i+1谁的距离小，那么城市i的最近城市我就找到了，直接存memo里，那么所有城市
    //的所有最近节点都知道了

    Map<Integer, List<City>> xmap=new HashMap<>();
    Map<Integer,List<City>> ymap=new HashMap<>();
    Map<String,City> rs=new HashMap<>();
    public FindtheNearestCity(String[] cities, int[] xCoordinates, int[] yCoordinates) {
        for (int i=0;i<cities.length;i++){
            xmap.putIfAbsent(xCoordinates[i],new ArrayList<>());
            ymap.putIfAbsent(yCoordinates[i],new ArrayList<>());
            List<City> xlist=xmap.get(xCoordinates[i]);
            List<City> ylist=ymap.get(yCoordinates[i]);
            xlist.add(new City(cities[i],xCoordinates[i],yCoordinates[i]));
            ylist.add(new City(cities[i],xCoordinates[i],yCoordinates[i]));
        }
        for(List<City> list:xmap.values()){
            Collections.sort(list,(a,b)->a.y-b.y);
            for (int i=0;i<list.size();i++){
                String cur=list.get(i).name;
                if(i>0){
                    rs.putIfAbsent(cur, list.get(i-1));//这里有点恶心
                    int leftDist=Math.abs(list.get(i-1).y-list.get(i).y);
                    int curDist=Math.abs(rs.get(cur).y-list.get(i).y);
                    if(leftDist<curDist){
                        rs.put(cur,list.get(i-1));
                    }else if(leftDist==curDist&&rs.get(cur).name.compareTo(list.get(i-1).name)>0){//同样距离按字符顺序排
                        rs.put(cur,list.get(i-1));
                    }
                }
                if(i+1<list.size()){
                    int rightDist=Math.abs(list.get(i+1).y-list.get(i).y);
                    int curDist=Math.abs(rs.get(cur).y-list.get(i).y);
                    if(curDist>rightDist){
                        rs.put(cur,list.get(i+1));
                    }else if(rightDist==curDist&&rs.get(cur).name.compareTo(list.get(i+1).name)>0){//同样距离按字符顺序排
                        rs.put(cur,list.get(i+1));
                    }
                }
            }
        }

        for(List<City> list:ymap.values()){
            Collections.sort(list,(a,b)->a.x-b.x);
            for (int i=0;i<list.size();i++){
                String cur=list.get(i).name;
                if(i>0){
                    rs.putIfAbsent(cur, list.get(i-1));//这里用putifabsent有点别扭，还是看map有没有写好点
                    int leftDist=Math.abs(list.get(i-1).x-list.get(i).x);
                    int curDist=Math.abs(rs.get(cur).x-list.get(i).x);
                    if(leftDist<curDist){
                        rs.put(cur,list.get(i-1));
                    }else if(leftDist==curDist&&rs.get(cur).name.compareTo(list.get(i-1).name)>0){//同样距离按字符顺序排
                        rs.put(cur,list.get(i-1));
                    }
                }
                if(i+1<list.size()){
                    int rightDist=Math.abs(list.get(i+1).x-list.get(i).x);
                    int curDist=Math.abs(rs.get(cur).x-list.get(i).x);
                    if(curDist>rightDist){
                        rs.put(cur,list.get(i+1));
                    }else if(rightDist==curDist&&rs.get(cur).name.compareTo(list.get(i+1).name)>0){//同样距离按字符顺序排
                        rs.put(cur,list.get(i+1));
                    }
                }
            }
        }
    }

    public String getNearestCity(String query) {
        if(rs.get(query)==null){
            return "";
        }
        return rs.get(query).name;
    }
}

class City {
    String name;
    int x;
    int y;

    public City(String name, int x, int y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }
}
