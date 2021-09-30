package GraphAndSearch;

import java.util.HashSet;

public class ValidateBinaryTreeNodes {
    //9/19/2021 这题开始以为就是root是0，然后dfs就好了，结果root不一定是0。
    //思路就是dfs，维护一个count=n，遇到一个node就count减1，最后count如果不等于1就肯定不valid，看那几个例子，count大于1说明有点没连接上，小于等于0说明有环。
    //开始要多一步找到底谁是root，就是遍历n，遇到谁就是访问过的，然后再遍历，没访问过的就是root。然后开始dfs,这个dfs和以前的有点不一样，是返回boolean的，
    //要是dfs返回false则直接返回false，否则就看他left/right是否返回false，有一个是false就返回false，left/right都没毛病才返回true。
    int count=0;
    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        boolean[] memo=new boolean[n];
        for (int i=0;i<leftChild.length;i++){
            if (leftChild[i]!=-1){
                memo[leftChild[i]]=true;
            }
            if (rightChild[i]!=-1){
                memo[rightChild[i]]=true;
            }
        }
        int root=0;
        for (int i=0;i<n;i++){
            if (!memo[i]){
                root=i;
                break;
            }
        }
        HashSet<Integer> set=new HashSet<>();
        count=n;
        set.add(root);
        if(!dfs(root,leftChild,rightChild,set)){
            return false;
        }
        if (count!=1){
            return false;
        }
        return true;
    }
    boolean dfs(int cur,int[] left,int[] right,HashSet<Integer> set){
        if (left[cur]!=-1){
            if (!set.contains(left[cur])){
                set.add(left[cur]);
                count--;
                if(!dfs(left[cur],left,right,set)){
                    return false;
                }
            }else {

                return false;
            }
        }
        if (right[cur]!=-1){
            if (!set.contains(right[cur])){
                set.add(right[cur]);
                count--;
                if(!dfs(right[cur],left,right,set)){
                    return false;
                }
            }else {

                return false;
            }
        }
        return true;
    }
}
