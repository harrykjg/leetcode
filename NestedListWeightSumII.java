import java.util.List;

public class NestedListWeightSumII {
    //7/17/2021和上一题比起来就是多加了一个方法先找出max depth。没啥技术含量。别人的解法看不懂
    int rs=0;
    int max=0;
    public int depthSumInverse(List<NestedInteger> nestedList) {
        for (int i=0;i<nestedList.size();i++){
            getMax(nestedList.get(i),1);
        }
        for (int i=0;i<nestedList.size();i++){
            dfs(nestedList.get(i),1);
        }
        return rs;
    }

    void dfs(NestedInteger ni,int level){
        if (ni.isInteger()){
            rs+=ni.getInteger()*(max-level+1);
            return;
        }else {
            List<NestedInteger> list=ni.getList();
            for (int i=0;i<list.size();i++){
                dfs(list.get(i),level+1);
            }
        }

    }

    void getMax(NestedInteger ni,int level){
        if (ni.isInteger()){
            max=Math.max(level,max);
            return;
        }
        List<NestedInteger> list=ni.getList();
        for (int i=0;i<list.size();i++){
            getMax(list.get(i),level+1);
        }
    }
}
