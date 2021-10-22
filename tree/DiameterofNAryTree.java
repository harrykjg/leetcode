package tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class DiameterofNAryTree {
    //8/18/2021 思路是有的，跟那个Binary Tree Maximum Path Sum 像。但是返回的数有些不同
    int rs=0;
    public int diameter(Node root) {
        if (root==null){
            return 0;
        }
        helper(root);
        return rs;
    }
    int helper(Node n){
        if (n==null){
            return 0;
        }

        PriorityQueue<Integer> pq= new PriorityQueue<>(Collections.reverseOrder());////其实只找2个最大的数的话不用pq，减helper2方法
        for (Node child:n.children){
            int depth=helper(child);
            pq.offer(depth);
        }
        if (pq.size()>=2){//取最大的两个孩子试试他们的path
            int a=pq.poll();
            int b=pq.poll();
            rs=Math.max(rs,a+b);//开始写了a+b+1，其实节点本身是不算的，但是这里返回的时候却要返回a+1，不太好想，如果返回是a的话，那么这个节点的父节点和这个
            return a+1;    //节点拥有相同深度个孩子，也说不过去
        }else if (pq.size()==1){
            int a=pq.poll();
            rs=Math.max(rs,a);
            return a+1;
        }
        return 1;//这里开始写了max=Math。max（rs，1）就错了，比如输入就是1个节点没有孩子，那么他的diameter就是0
    }

    //其实只找2个最大的数的话不用pq
    int helper2(Node n){
        if (n==null){
            return 0;
        }
        int max1=0;
        int max2=0;
        for (Node child:n.children){
            int depth=helper2(child);
            if(depth>=max1){
                if(max1!=0){//这里想清楚先来一个1，再来一个1会怎样
                    max2=max1;
                }
                max1=depth;
            }else if(depth>max2){
                max2=depth;
            }
        }

        rs=Math.max(rs,max1+max2);
        return max1+1;

    }

    //10/8/2021这个写的最好，看这个。
    int rs3=0;
    public int diameter3(Node root) {
        if(root==null){
            return 0;
        }
        helper3(root);
        return rs;
    }
    int helper3(Node n){
        if(n==null){
            return 0;
        }
        int first=0;
        int second=0;
        for(Node child:n.children){//判断最大的两个数更好一些
            int next=helper3(child);
            if(next>first){//大于第一个的话，不管第一本来是不是0，值直接给第二个就行
                second=first;
                first=next;
            }else if(next==first){
                second=first;
            }else if(next>second){
                second=next;
            }
        }
        rs=Math.max(first+second,rs);//注意这里容易多写first+second+1
        return Math.max(first,second)+1;

    }
    class Node {
        public int val;
        public List<Node> children;


        public Node() {
            children = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            children = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _children) {
            val = _val;
            children = _children;
        }
    };
}
