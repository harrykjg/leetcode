package linkedin;

import java.util.List;

public class NestedListWeightSumII364 {
    //很明显就是可以先dfs找max depth再dfs和339一样。但是怎么弄成直走一遍呢？简单一点可以一边dfs/bfs的同时把元素和深度做成in[]记录下来放在
    //list里，找到最大深度之后再遍历这个list计算。但是这就要o(n)空间.答案更好的方法实际上就是遍历的同时先把所有元素直接加起来算一个sum1，同时
    //也算一个sum2是所有元素乘以当前深度的和。那么遍历完成之后就把sum1*（最大深度+1）-sum2.这个最大深度+1应该就是题目说的weight
    //https://leetcode.com/problems/nested-list-weight-sum-ii/solutions/1558843/nested-list-weight-sum-ii-by-leetcode-a8o6/
    int sums1=0;
    int sums2=0;
    int maxDepth=0;
    public int depthSumInverse(List<NestedInteger> nestedList) {

        for (int i=0;i<nestedList.size();i++){
            dfs(nestedList.get(i),1);
        }
        return (maxDepth+1)*sums1-sums2;
    }
    void dfs(NestedInteger ni,int depth){
        if(ni.isInteger()){
            sums1+=ni.getInteger();
            sums2+=ni.getInteger()*depth;
            maxDepth=Math.max(maxDepth,depth);
        }else{
            for (NestedInteger i:ni.getList()){
                dfs(i,depth+1);
            }
        }
    }

}
