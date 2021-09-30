package SomeInterviews.karat;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class SparseVector {
    public static void main(String[] args){
//        SparseVector sv=new SparseVector(100);
//        sv.set(0,1d);
//        sv.set(3,2d);
//        sv.set(80,-4.5d);
//        System.out.println(sv.get(80));
//        System.out.println(sv.get(40));
//        System.out.println(sv.toString());

        SparseVector v1=new SparseVector(5);
        v1.set(0,4d);
        v1.set(1,5d);

        SparseVector v2=new SparseVector(5);
        v2.set(1,2d);
        v2.set(3,3d);

        SparseVector v3=new SparseVector(2);
//        System.out.println(v1.add(v2));


        System.out.println(v1.dot(v2));

        System.out.println(v1.cos(v2));

    }
    //就直接用一个map就好了，不知道为啥简书那个要搞个linkedlist
    int cap;
    TreeMap<Integer,Double> map=new TreeMap<>();
    public SparseVector(int cap){
        this.cap=cap;
    }
    public void set(int index,double val){
        if (!map.containsKey(index)){
            map.put(index,val);
        }
        map.put(index,val);
    }
    public double get(int index){
        if (index>=cap||index<0){
            throw new RuntimeException("error");
        }
        if (!map.containsKey(index)){
            return 0d;
        }
        return map.get(index);
    }

    public String toString(){
        StringBuilder sb=new StringBuilder();
        sb.append('[');
        for (int i=0;i<cap;i++){
            if (map.containsKey(i)){
                sb.append(map.get(i));
                sb.append(",");
            }else {
                sb.append(0d);
                sb.append(",");
            }
        }
        sb.append(']');
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    String add(SparseVector v){
        if (cap!=v.cap){
            throw new RuntimeException("xa");
        }
        for (int i=0;i<cap;i++){//https://www.geeksforgeeks.org/implementing-sparse-vector-in-java/ 或者可以像这个这样检测一个map再检测另一个map，否则用treemap就没意义了
            if (map.containsKey(i)&&v.map.containsKey(i)){//题目意思可能是是不改变原来vector，所以要见一个新的vector存结果
                map.put(i,map.get(i)+v.map.get(i));
            }else if (v.map.containsKey(i)){
                map.put(i,v.map.get(i));
            }
        }
        return toString();
    }
    long dot(SparseVector v){
        if (cap!=v.cap){
            throw new RuntimeException("xa");
        }
        long rs=0;
        for (int i=0;i<cap;i++){
            if (map.containsKey(i)&&v.map.containsKey(i)){
                rs+=map.get(i)*v.map.get(i);
            }
        }
        return rs;
    }

    double cos(SparseVector v){
        double rs=this.dot(v);
        double norm1=this.getnorm();
        double norm2=v.getnorm();
        return rs/(norm1*norm2);
    }
    double getnorm(){
        double sum=0;
        for (int i=0;i<cap;i++){
            if (map.containsKey(i)){
                sum+=Math.pow(map.get(i),2);
            }
        }
        return Math.sqrt(sum);
    }



//    class Node{
//        int index;
//        int val;
//        Node next;
//        public Node(int index,int val){
//            this.index=index;
//            this.val=val;
//        }
//    }
}
