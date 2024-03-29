import java.util.*;

/**
 * Created by yufengzhu on 7/8/18.
 */
public class ClosestBinarySearchTreeValueII {
    //像MinimumDistanceBetweenBSTNodes
    //https://www.cnblogs.com/grandyang/p/5247398.html
    //https://leetcode.com/problems/closest-binary-search-tree-value-ii/discuss/70499/Java-5ms-iterative-following-hint-O(klogn)-time-and-space
    public List<Integer> closestKValues(tree.TreeNode root, double target, int k) {//自己想的用最大堆，存的是最小的k个元素，一次过了，复杂度是O(n），follow up要klgn的不会
        PriorityQueue<pair> heap=new PriorityQueue<>(new Comparator<pair>() {
            @Override
            public int compare(pair o1, pair o2) {
                return (int)(o2.diff-o1.diff);
            }
        });
        List<Integer> rs=new ArrayList<>();
        Stack<tree.TreeNode> st=new Stack<>();
        st.push(root);
        while (!st.isEmpty()){
            tree.TreeNode temp=st.peek();
            if(temp.left!=null){
                st.push(temp.left);
                temp.left=null;
                continue;
            }
            tree.TreeNode cur=st.pop();

            double diff=Math.abs(target-cur.val);
            if (heap.size()<k){
                heap.offer(new pair(diff,cur.val));
            }else{
                if(heap.peek().diff>diff){
                    heap.poll();
                    heap.offer(new pair(diff,cur.val));
                }
            }

            if(cur.right!=null){
                st.push(cur.right);
            }
        }
        while (!heap.isEmpty()){
            rs.add(rs.size(),heap.poll().val);//注意这里之前写的rs.add(rs.size()-1,val)就outboundexception了
        }

        return rs;
    }
//7/16/2021想不到好方法，连以前这个方法都想不到
    public List<Integer> closestKValues2(TreeNode root, double target, int k) {
        List<Integer> rs=new ArrayList<>();
        if (root==null){
            return rs;
        }
        PriorityQueue<pair> pq=new PriorityQueue<>(new Comparator<pair>() {
            @Override
            public int compare(pair o1, pair o2) {
                return (int)(o2.diff-o1.diff);
            }
        });
        Stack<TreeNode> st=new Stack<>();
        st.push(root);
        while (!st.isEmpty()){
            TreeNode temp=st.peek();
            if (temp.left!=null){
                st.push(temp.left);
                temp.left=null;
            }else {
                st.pop();
                double diff=Math.abs(temp.val-target);
                pair p=new pair(diff,temp.val);
                if (pq.size()<k){
                    pq.offer(p);
                }else if (pq.peek().diff>diff){
                    pq.poll();
                    pq.offer(p);
                }
                if (temp.right!=null){
                    st.push(temp.right);
                }
            }
        }
        while (!pq.isEmpty()){
            rs.add(pq.poll().val);
        }
        return rs;
    }
    class pair{
        double diff;
        int val;
        public pair(double diff,int val){
            this.diff=diff;
            this.val=val;
        }
    }
}
