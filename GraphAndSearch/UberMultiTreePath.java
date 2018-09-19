package GraphAndSearch;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by yufengzhu on 9/12/18.
 * 第二轮是组里的一个印度哥哥，感觉不太喜欢说话的样子，出得题是多叉树的题，最后要求出整个多叉树里距离root为n的点。
 输入是一个list还有一个n，list里面会有一些value，如果一个node的值等于这个list里面的某一个，表明以这个node的subtree都不能参与后续计算。. visit 1point3acres for more.
 举个&#127792;        输入的list是｛3，8｝，n是2
                  1
             4         8
         3  5  6    7  8  10  
 在这个树里，4和8距离root的距离为1，3，5，6，7，8，10 距离root的距离为2，但是3和8出现在list里面，所以3，以及8的child7，8，10（注意这里node的值可以相同）都不能算，所以结果输出5，6
 因为在面试前刷了蛮多trie的题，所以写的蛮顺利的,小哥限定了每一个parent的child不超过20个，蛮顺利的写完了
 之后小哥运行了几个test case，结果是对的，就问了follow up。 这次的input是一个距离n，一个list，还多了一个m。m也是一个整数，现在是要求出整个多叉树里距离值为m的点（可以有多个）为n的点。感觉时间不够我写完了，我

 */
public class UberMultiTreePath {
    public static void main(String[] args){
        UberMultiTreePath um=new UberMultiTreePath();
        MultiTree root=new MultiTree(1);
        MultiTree r1=new MultiTree(4);
        r1.children.add(new MultiTree(3));
        r1.children.add(new MultiTree(5));
        r1.children.add(new MultiTree(6));
        MultiTree r2=new MultiTree(8);
        r2.children.add(new MultiTree(7));
        r2.children.add(new MultiTree(8));
        r2.children.add(new MultiTree(10));
        root.children.add(r1);
        root.children.add(r2);
        HashSet<Integer> set=new HashSet<>();
        set.add(3);
        set.add(8);
        List<Integer> rs=um.find(root,set,2);

    }
    public List<Integer> find(MultiTree root,Set<Integer> set,int n){
        List<Integer> rs=new ArrayList<>();
        if(root==null||set.contains(root.val)){
            return rs;
        }
        helper(0,n,root,set,rs);
        return rs;
    }
    void helper(int cur, int target,MultiTree node, Set<Integer> set,List<Integer> rs){
        if(cur==target&&!set.contains(node.val)){
            rs.add(node.val);
            return;
        }
        if(cur>target){
            return;
        }
        for(MultiTree t:node.children){
            if(set.contains(t.val)){
                continue;
            }
            helper(cur+1,target,t,set,rs);
        }
    }

}
class MultiTree{
    int val;
    List<MultiTree> children;
    public MultiTree(int val){
        this.val=val;
        children=new ArrayList<>();
    }
}
