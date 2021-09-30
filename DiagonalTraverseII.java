import java.util.*;

public class DiagonalTraverseII {
    //9/19/2021 看提示写的。就是行数和列数加起来的值相等的都是在同一对角线上的，那么我就维护一个map，key是行列的和，value是list
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        HashMap<Integer,List<Integer>> map=new HashMap<>();//开始写的是treemap，后来看别人发现不需要treemap，hashmap就行，反正map里的key是0，1，2，3。。
        int count=0;                                    //最后到map。size的，到时按顺序取就行了
        for (int i=0;i<nums.size();i++){
            for (int j=0;j<nums.get(i).size();j++){
                int cor=i+j;
                if (!map.containsKey(cor)){
                    map.put(cor,new ArrayList<>());
                }
                map.get(cor).add(nums.get(i).get(j));//这里开始写的是insert到0位置，其实不用，后面取的时候反着取就行了
                count++;
            }
        }
        int[] rs=new int[count];
        int index=0;
        for (int i=0;i<map.size();i++){
            List<Integer> ls=map.get(i);
            for (int j=map.get(i).size()-1;j>=0;j--){
                rs[index++]=ls.get(j);
            }
        }
        return rs;
    }
}
