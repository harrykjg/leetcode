

import java.util.HashMap;
import java.util.function.BooleanSupplier;

/**
 * Created by 502575560 on 6/25/16.
 */
public class HouseRobberIII {
    //https://leetcode.com/problems/house-robber-iii/discuss/79330/Step-by-step-tackling-of-the-problem 大神解释
    //http://www.cnblogs.com/grandyang/p/5275096.html
    //http://bookshadow.com/weblog/2016/03/13/leetcode-house-robber-iii/?utm_source=tuicool&utm_medium=referral 这个dfs也accept,比较绕
    //自己不会,自己想的是层次遍历然后找规律啥的看这个节点的往下哪个节点不能取那样取做,没想出来
    //看网上的就是dfs,往下递归,看这个节点取不取,取的话就是取当前节点的值加上左右两个字节点的子树的最大值.如果当前节点可以取也可以不取要注意取max值
    //这个代码改了2次之后超时,后来我加了一个2层的map搞了挺久才accept,应该是写复杂了,别人不用这样的,其实内层用个长度为2的数组就行了,或者说就一层map的key是integer就行了?
    static int rs=0;
    public static void main(String[] args){
        TreeNode n=new TreeNode(1);
        n.right=new TreeNode(4);
        n.right.left=new TreeNode(3);
        n.right.left.right=new TreeNode(2);
        System.out.println(rob(n));
    }
    public static int rob(TreeNode root) {
        if(root==null){
            return 0;
        }
        HashMap<TreeNode,HashMap<Boolean,Integer>> map=new HashMap<>();
        int temp1=dfs(root,true,map);
        int temp2=dfs(root,false,map);
        rs=Math.max(temp1,temp2);//我的想法就是这个用一个true和false区分取不取当前节点
        return rs;

    }
    public static int dfs(TreeNode root,boolean take,HashMap<TreeNode,HashMap<Boolean,Integer>> map){
        int rs2=0;
        if(root==null){
            return 0;
        }
        if(take){//关键点在这,如果这个点可以取,那也不一定代表一定要取,也有可能不取的话最后得到的结果反而更大,所以这里有2中可能

            int temp1=0;
            int temp2=0;
            if(map.containsKey(root)&&map.get(root).containsKey(true)){
                temp1=map.get(root).get(true);
            }else {
                HashMap<Boolean,Integer> h2=new HashMap<>();
                temp1=root.val+dfs(root.left,false,map)+dfs(root.right,false,map);
                h2.put(true,temp1);
                map.put(root,h2);
            }
            if(map.containsKey(root)&&map.get(root).containsKey(false)){
                temp2=map.get(root).get(false);
            }else{
                HashMap<Boolean,Integer> h2=new HashMap<>();
                temp2=dfs(root.left,true,map)+dfs(root.right,true,map);
                h2.put(false,temp2);
                map.put(root,h2);
            }
            return Math.max(temp1,temp2);

        }
        else{//而take是false的话则这个节点肯定是不能取的了
            int temp=0;
            if(map.containsKey(root)&&map.get(root).containsKey(false)){
                temp=map.get(root).get(false);
            }else {
                HashMap<Boolean,Integer> h2=new HashMap<>();
                temp=dfs(root.left,true,map)+dfs(root.right,true,map);
                h2.put(false,temp);
                map.put(root,h2);
            }
            return temp;
        }
    }
    //9／9／2018，还是不会，见大神解释

    public  int rob2(TreeNode root) {
        if(root==null){
            return 0;
        }
        HashMap<TreeNode,Integer> map=new HashMap<>();
        return helper(root,map);
    }
    int helper(TreeNode root, HashMap<TreeNode, Integer> map){
        if(root==null){
            return 0;
        }
        if(map.containsKey(root)){
            return map.get(root);
        }
        int val=0;
        if(root.left!=null){
            val+=helper(root.left.left,map)+helper(root.left.right,map);
        }
        if(root.right!=null){
            val+=helper(root.right.left,map)+helper(root.right.right,map);
        }
        int rs= Math.max(root.val+val,helper(root.left,map)+helper(root.right,map));
        map.put(root,rs);
        return rs;
    }
}
