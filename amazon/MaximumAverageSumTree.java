package amazon;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yufengzhu on 10/25/18.
 */
public class MaximumAverageSumTree {
    public static void main(String[] args){
        MaximumAverageSumTree ma=new MaximumAverageSumTree();
        NTree root=new NTree(100);
        NTree n1=new NTree(120);
        NTree n2=new NTree(80);
        NTree n3=new NTree(40);
        NTree n4=new NTree(50);
        NTree n5=new NTree(60);
        NTree n6=new NTree(50);
        NTree n7=new NTree(70);
        root.children.add(n1);
        root.children.add(n2);
        n1.children.add(n3);
        n1.children.add(n4);
        n1.children.add(n5);
        n2.children.add(n6);
        n2.children.add(n7);
        ma.findMax(root);


    }

    /*
    树的问题，每个结点有一个value表示这个结点有多少代码行数被改动了，然后子树就是他依赖的结点，要去找一个结点使得 它当前结点改动的代码行数+他依赖（子树）的所有结点的代码行数 的平均最大。
        比如  100
          /       \
       120        80
      /| \       \   \
   40  50 60     50 70

那么100结点就是(100+120+80+40+50+60+50+70) / 8 = 71.25
120结点就是(120 + 40 + 50 + 60) / 4 = 67.5
80结点就是(80+50+70) / 3 = 66.6667
那么就要返回100这个结点
     */
    double max=0;
    NTree rs;
    public NTree findMax(NTree root){
        if (root==null){
            return rs;
        }
        helper(root);
        return rs;
    }

    pair helper(NTree root){
        if(root==null){
            return null;
        }
        if(root.children.size()==0){
            if(root.val>max){
                max=root.val;
                rs=root;
            }
            return new pair(root.val,1);
        }
        int vals=root.val;
        int count=1;
        for(NTree child:root.children){
            pair p=helper(child);
            vals+=p.val;
            count+=p.count;
        }
        double cur=vals/count;
        if(cur>max){
            rs=root;
            max=cur;
        }

        return new pair(vals,count);

    }

}
class pair{
    int val;
    int count;
    public pair(int val,int count){
        this.val=val;
        this.count=count;
    }
}
class NTree{
    int val;
    List<NTree> children=new ArrayList<>();
    public NTree(int val){
        this.val=val;
    }
}
