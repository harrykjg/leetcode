import java.util.*;

public class DotProductofTwoSparseVectors {
    //8/16/2021 开始有点不理解直接暴力为啥不行。看了答案知道。调用的时候先初始化两个nums，然后用一个n1.dotProduct(n2).并且是多次调用，因此可以初始化的时候
    //只把有非零值的index和value保存，然后遍历另一个map，看index在map中是否存在，存在就算不存在就是0
    //https://www.youtube.com/watch?v=Ns_gNGRhGd8
    Map<Integer,Integer> map=new HashMap<>();
    DotProductofTwoSparseVectors(int[] nums) {
        for (int i=0;i<nums.length;i++){
            if (nums[i]!=0){
                map.put(i,nums[i]);
            }
        }
    }

    // Return the dotProduct of two sparse vectors
    public int dotProduct(DotProductofTwoSparseVectors vec) {
        int rs=0;
        for (int index:this.map.keySet()){
            if (vec.map.containsKey(index)){
                rs+=vec.map.get(index)*this.map.get(index);
            }
        }
        return rs;
    }
//答案3的解法就是用array装index和value，然后2pointer，复杂度我看也没比用map好
    List<int[]> al=new ArrayList<>();
    void DotProductofTwoSparseVectors(int[] nums) {
        for (int i=0;i<nums.length;i++){
            if (nums[i]!=0){
                al.add(new int[]{i,nums[i]});
            }
        }
    }

    // Return the dotProduct of two sparse vectors
    public int dotProduct2(DotProductofTwoSparseVectors vec) {
        int rs=0;
        int i=0;
        int j=0;
        while (i<this.al.size()&&j<vec.al.size()){
            if (this.al.get(i)[0]==vec.al.get(j)[0]){
                rs+=this.al.get(i)[1]*vec.al.get(j)[1];
                i++;
                j++;
            }else if (this.al.get(i)[0]>vec.al.get(j)[0]){
                j++;
            }else {
                i++;
            }
        }
        return rs;
    }
}
