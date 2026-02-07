package linkedin;

import java.util.List;

public class NestedListWeightSum339 {
    int rs=0;
    public int depthSum(List<NestedInteger> nestedList) {
        for (int i=0;i<nestedList.size();i++){
            dfs(nestedList.get(i),1);
        }
        return rs;
    }
    void dfs(NestedInteger ni,int depth){
        if(ni.isInteger()){
            rs+=depth*ni.getInteger();
            return;
        }
        for(NestedInteger n:ni.getList()){
            dfs(n,depth+1);
        }
    }

}
  interface NestedInteger {

              // @return true if this NestedInteger holds a single integer, rather than a nested list.
              public boolean isInteger();

              // @return the single integer that this NestedInteger holds, if it holds a single integer
              // Return null if this NestedInteger holds a nested list
              public Integer getInteger();
      // Set this NestedInteger to hold a single integer.
              public void setInteger(int value);

              // Set this NestedInteger to hold a nested list and adds a nested integer to it.
              public void add(NestedInteger ni);
      // @return the nested list that this NestedInteger holds, if it holds a nested list
              // Return empty list if this NestedInteger holds a single integer
              public List<NestedInteger> getList();
  }
