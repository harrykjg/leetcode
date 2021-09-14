import java.util.List;

public class NestedListWeightSum {
    //7/17/2021 一次过dfs
    int rs=0;
    public int depthSum(List<NestedInteger> nestedList) {
        if (nestedList==null||nestedList.size()==0){
            return rs;
        }
        for (int i=0;i<nestedList.size();i++){
            dfs(nestedList.get(i),1);
        }
        return rs;
    }
    void dfs(NestedInteger ni,int level){
        if (ni.isInteger()){
            rs+=ni.getInteger()*level;
            return;
        }else {
            List<NestedInteger> list=ni.getList();
            for (int i=0;i<list.size();i++){
                dfs(list.get(i),level+1);
            }
        }

    }
}
