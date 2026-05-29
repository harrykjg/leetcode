package SomeInterviews.purestorage;

import java.util.ArrayList;
import java.util.List;

public class BuddyBitmap {

    //https://leetcode.com/discuss/post/527278/pure-storage-interview-question-need-hel-jny2/
    //这个理解和gpt说的应该是对的，他那个offset是指leave节点那一行开始算。而不是说从整个树开始算
    //https://massivealgorithms.blogspot.com/2016/06/buttercola-buddy-system.html
    //如果题目说的是complete tree则是最后一行不一定是满的，但是是从左到右填的。
    /*
             0
          /     \
         1        2
       /  \      /  \
      3    4    5    6
     /\   / \   /
    7  8 9   10 11
    先求出所有叶子节点，注意第一个叶子节点不是7，而是6！因此长度为n的tree的叶子节点起始值是12/2=6是对的
     */
    int[] tree;
    int firstLeaf;
    public BuddyBitmap(int[] tree){
        this.tree=tree;
        firstLeaf=tree.length/2;
    }
    public  void setBit( int offset, int length){
        helper(offset,length,1);
    }
    public  void clearBit( int offset, int length) {
        helper(offset,length,0);
    }
    void helper(int offset,int length,int val){ //这里还应该检测length是否越界，这里懒得写了
        for (int i=offset+firstLeaf;i<firstLeaf+offset+length&&i<tree.length;i++){
            tree[i]=val;
        }
        //原来暴力法就是每一个leaf都去更新parent，那么两个叶子就会更新同一个parent两次，因此更好的解法是定位到每一个parent，再定位这个parent的左右孩子，就可以
        //更新这个parent了，就是每个parent只更新一次。那么怎么定位parent呢，已知left=i*2+1，right=i*2+2,推出i=（left-1）/2，或(right-2）/2，但是正好java整除
        //的话parent=（i-1）/2 就是对左右孩子都成立！这个真的很难想只能记
        int firstParent=(firstLeaf+offset-1)/2;
        int lastParent=(firstLeaf+offset+length-1)/2;//这里开始写错了，写成lastleaf了
        //现在就是要一层一层网上更行parent了，因此用一个while判断firstparent是否>=0，也挺巧妙
        while (firstParent>=0){
            for(int i=firstParent;i<=lastParent;i++){
                int left=i*2+1;
                int right=i*2+2;
                int leftval=tree[left];
                int rightval=right<tree.length?tree[right]:1;//空的话就是1才符合题意说的parent如果是1则其所有子树都是1
                tree[i]=leftval&rightval;
            }
            if(firstParent==0){
                break;
            }
            firstParent=(firstParent-1)/2;
            lastParent=(lastParent-1)/2;
        }

    }
    //follow up/变形，如何查找连续的2或4。。长度的leaf节点为1的节点
    //思路就是长度为2的连续1就说明其parent肯定是1，那如果说一个叶子节点是右子树，和他右边的节点是连续的1，但是他们的parent不是同一个node，则样的话题目意思可能
    //不算，得是同一个parent才算吧。那么做法就是从下到上可以定位到应该扫描的那一层，比如说找长度为2的则找倒数第二层，长度为4的找倒数第三层。然后在这一层从左到右
    //找值为1的就是找到对应叶子节点连续长度为k的起点啦
    int findConsecutive(int[] tree,int k){
        int firstLeaf=tree.length/2;
        int leafSize=tree.length-firstLeaf;
        int length=1;
        while(length<k){
            //一开始firstLeaf就是长度为1length，不够的话就找firstleaf的parent
            firstLeaf=(firstLeaf-1)/2;//实际上指的是当前层的第一个节点
            leafSize=leafSize/2;//这一段的leafsize是多少？如果是perfect tree的话应该是这样吧
            length*=2;
        }
        for (int i=0;i<leafSize;i++){//由于要返回那一层的offset，因此i从这一行的0开始扫，
            int idx=i+firstLeaf;//对应的节点index
            if(tree[idx]==1){
                return i*k;//应该返回最后一行的offset，而不是第一个节点的编号（2*i+1）
            }
        }
        return -1;
    }
}
