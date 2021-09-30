package SomeInterviews.facebook;

import java.util.*;

public class Mergetwointervallists {
    public static void main(String[] args){
        Mergetwointervallists mi=new Mergetwointervallists();
        List<Interval> l1=new ArrayList<>();
        l1.add(new Interval(1,5));
        l1.add(new Interval(10,14));
        l1.add(new Interval(16,18));
        List<Interval> l2=new ArrayList<>();
        l2.add(new Interval(2,6));
        l2.add(new Interval(8,10));
        l2.add(new Interval(11,20));

//        List<Interval> l1 = new ArrayList<>();
//        l1.add(new Interval(1, 5));
//        l1.add(new Interval(10, 14));
//        l1.add(new Interval(16, 18));
//        l1.add(new Interval(20, 24));
//        l1.add(new Interval(30, 38));
//        List<Interval> l2 = new ArrayList<>();
//        l2.add(new Interval(2, 6));
//        l2.add(new Interval(8, 10));
//        l2.add(new Interval(11, 20));

        List<Interval>rs=mi.mergeList(l1,l2);
        for (Interval in:rs){
            System.out.println(in);
        }
    }
//9/21/2021
    //https://leetcode.com/discuss/interview-question/124616/Merge-two-interval-lists
    //自己写的应该对吧，就是排序之后，2 pointer，判断如果l1在前且没交集，则把l1放进结果集，index1++，否则l2在前且没交集，把l2放进结果集。否则是有交集，
    //则把end比较小的那个融入end比较大的那个，然后end比较小的那个index++，大的这个只要更新start就行了，并且不需要假如结果集，要继续下一轮while循环看，
    //因为题目说了2个list都各自没有overlap，因此end大的那个也不会和自己后面的element overlap，可以继续while循环。
    public List<Interval> mergeList(List<Interval> l1, List<Interval> l2) {
        Collections.sort(l1, new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.start-o2.start;
            }
        });
        Collections.sort(l2,(a,b)->a.start-b.start);
        int i1=0;
        int i2=0;
        List<Interval> rs=new ArrayList<>();
        while (i1<l1.size()&&i2<l2.size()){
            Interval in1=l1.get(i1);
            Interval in2=l2.get(i2);

            if (in1.end<in2.start){//第一个在前
                rs.add(in1);
                i1++;
            }else if (in2.end<in1.start){//第二个在前
                rs.add(in2);
                i2++;
            }else {//有交集
                if (in1.end<in2.end){
                    in2.start=Math.min(in1.start,in2.start);
                    i1++;
                }else {
                    in1.start=Math.min(in1.start,in2.start);
                    i2++;
                }
            }
        }
        while (i1<l1.size()){
            rs.add(l1.get(i1++));
        }
        while (i2<l2.size()){
            rs.add(l2.get(i2++));
        }
        return rs;
    }
    static class Interval {
        int start;
        int end;
        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
        @Override
        public String toString(){
            return start+" "+end+" ";
        }
    }
}
