package amazon;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by yufengzhu on 10/23/18.
 */
public class airplan {
    public static void main(String[] args){
        airplan ap=new airplan();
        List<List<Integer>> ls=new ArrayList<>();
        List<Integer> l1=new ArrayList<>();
        l1.add(1);
        l1.add(5000);
        List<Integer> l2=new ArrayList<>();
        l2.add(2);
        l2.add(10000);
        List<Integer> l3=new ArrayList<>();
        l3.add(3);
        l3.add(9000);
        List<Integer> l4=new ArrayList<>();
        l4.add(4);
        l4.add(2000);
        ls.add(l1);
        ls.add(l2);
        ls.add(l3);
        ls.add(l4);

        List<List<Integer>> ls2=new ArrayList<>();
        List<Integer> l5=new ArrayList<>();
        l5.add(1);
        l5.add(7000);
        List<Integer> l6=new ArrayList<>();
        l6.add(2);
        l6.add(12000);
        List<Integer> l7=new ArrayList<>();
        l7.add(3);
        l7.add(8000);
        List<Integer> l8=new ArrayList<>();
        l8.add(4);
        l8.add(5000);
        ls2.add(l5);
        ls2.add(l6);
        ls2.add(l7);
        ls2.add(l8);
        ap.findDistance(ls,ls2,11000);
    }

    public List<List<Integer>> findDistance(List<List<Integer>> forwardRouteList,List<List<Integer>>returnRouteList,int maxTravelDist){
//        List<List<Integer>> rs=new ArrayList<>();
//
//        Collections.sort(ls1, new Comparator<List<Integer>>() {
//            @Override
//            public int compare(List<Integer> o1, List<Integer> o2) {
//                return o1.get(1)-o2.get(1);
//            }
//        });
//        Collections.sort(ls2, new Comparator<List<Integer>>() {
//            @Override
//            public int compare(List<Integer> o1, List<Integer> o2) {
//                return o1.get(1)-o2.get(1);
//            }
//        });
//
//        int gap=Integer.MAX_VALUE;
//        for(int i=0;i<ls1.size();i++){
//            int cur=ls1.get(i).get(1);
////            int curid=ls1.get(i).get(0);
//            int b=0;
//            int e=ls2.size()-1;
//
//            while (b+1<e){
//                int m=b+(e-b)/2;
//                int sec=ls2.get(m).get(1);
////                int secId= ls1.get(m).get(0);
//                if(sec+cur==limit){
//                    gap=0;
//                    break;
//                }
//                if(sec+cur<limit){
//                    gap=Math.min(limit-sec-cur,gap);
//                    b=m;
//                }else if(sec+cur>limit){
//                    e=m;
//                }
//            }
//            if(cur+ls2.get(b).get(1)<=limit){
//                gap=Math.min(limit-cur-ls2.get(b).get(1),gap);
//            }
//            if(cur+ls2.get(e).get(1)<=limit){
//                gap=Math.min(limit-cur-ls2.get(e).get(1),gap);
//            }
//        }
//        for(int i=0;i<ls1.size();i++){
//            int cur=ls1.get(i).get(1);
//            int curid=ls1.get(i).get(0);
//            for(int j=0;j<ls2.size();j++){
//                int sec=ls2.get(j).get(1);
//                int secId= ls2.get(j).get(0);
//                if(cur+sec>limit){
//                    break;
//                }
//                if(limit-cur-sec==gap){
//                    ArrayList<Integer> ls=new ArrayList<>();
//                    ls.add(curid);
//                    ls.add(secId);
//                    rs.add(ls);
//                }
//            }
//        }
//
//        return rs;
        List<List<Integer>> rs=new ArrayList<>();
        if(forwardRouteList.size()==0){
            return rs;
        }
        Collections.sort(forwardRouteList, new Comparator<List<Integer>>() {
            @Override
            public int compare(List<Integer> o1, List<Integer> o2) {
                return 0;
            }
        });
        Collections.sort(returnRouteList,new Comparator<List<Integer>>(){
            public int compare(List<Integer> l1,List<Integer> l2){
                return l1.get(1)-l2.get(1);
            }
        });

        int max=0;
        for(int i=0;i<forwardRouteList.size();i++){
            int dist1=forwardRouteList.get(i).get(1);
            int begin=0;
            int end=returnRouteList.size()-1;
            int dist=0;
            while(begin+1<end){
                int m=begin+(end-begin)/2;
                int dist2=returnRouteList.get(m).get(1);
                dist=dist1+dist2;
                if(dist<=maxTravelDist){
                    max=Math.max(max,dist);
                    begin=m;
                }else{
                    end=m;
                }
            }
            if(dist1+returnRouteList.get(begin).get(1)<=maxTravelDist){
                max=Math.max(max,dist1+returnRouteList.get(begin).get(1));
            }
            if(dist1+returnRouteList.get(end).get(1)<=maxTravelDist){
                max=Math.max(max,dist1+returnRouteList.get(end).get(1));
            }
        }

        for(int i=0;i<forwardRouteList.size();i++){
            int dist1=forwardRouteList.get(i).get(1);
            int id1=forwardRouteList.get(i).get(1);
            for(int j=0;j<returnRouteList.size();j++)  {
                int dist2=returnRouteList.get(j).get(1);
                int id2=returnRouteList.get(j).get(0);
                if(dist1+dist2>maxTravelDist){
                    break;
                }
                if(dist1+dist2==max){
                    ArrayList<Integer> ls=new ArrayList<Integer>();
                    ls.add(id1);
                    ls.add(id2);
                    rs.add(ls);
                }
            }

        }
        return rs;

    }

}
