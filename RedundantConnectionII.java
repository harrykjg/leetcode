import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by yufengzhu on 10/9/18.
 */
public class RedundantConnectionII {
    public static void main(String[] args){
        RedundantConnectionII rc=new RedundantConnectionII();

        rc.findRedundantDirectedConnection3(new int[][]{{3,4},{4,1},{1,2},{2,3},{5,1}});//这个例子很精髓
    }

    //和第一问的区别在于这里的不合法情况有3种,真的很难写，知道思路之后还是很难写。
    //https://www.youtube.com/watch?v=lnmJT5b4NlM 思路看他
    //https://leetcode.com/problems/redundant-connection-ii/discuss/108045/C++Java-Union-Find-with-explanation-O(n)
    //https://leetcode.com/problems/redundant-connection-ii/discuss/141897/Logical-Thinking-with-Java-Code-Beats-96.71
    //他们的方法就是其实要维护2个array去记录每一个节点的父节点，第一个array记录某个节点的直接父节点，第二个array记录union find之后的某个节点的父节点。用unionfind是因为要去找环。
    //循环的时候先去找某个节点有没有两个父节点，有的话记录这两条边，这里的记录就得用某个节点的直接父节点，而不是unionfind之后的父节点，因为unionfind之后某个点的父节点就可能不是直接父节点了，而题目
    // 要求的是返回要删除掉的那个edge！这就是为啥要2个array去记录直接父节点和unionfind之后的节点。
    public int[] findRedundantDirectedConnection(int[][] edges) {
       //自己的写的也写不对删掉了，看下面两个别人的答案吧，都不太好懂
    }

    int find(int[] ids,int a){
        if(ids[a]==a){
            return a;
        }
        int ap=find(ids,ids[a]);
        ids[a]=ap;
        return ap;
    }

    public int[] findRedundantDirectedConnection2(int[][] edges) {
        int n = edges.length;
        int indexFirst = -1, indexSecond = -1, indexMakeCycle = -1;
        int[] incomingIndex = new int[n + 1];
        Arrays.fill(incomingIndex, -1);
        int[] parent = new int[n + 1]; // for Disjoint Set
        for (int i = 1; i <= n; i++) parent[i] = i;

        for (int i = 0; i < n; i++) {
            int u = edges[i][0];
            int v = edges[i][1];
            if (incomingIndex[v] != -1) {
                indexFirst = incomingIndex[v];
                indexSecond = i; // we do not update incomingIndex[v] with indexSecond, i.e., we ignore edges[indexSecond]
            } else {
                incomingIndex[v] = i;
                if (find(parent, u) == v) { // cyclic
                    indexMakeCycle = i;
                } else {
                    parent[v] = find(parent, u);
                }
            }
        }

        if (indexMakeCycle == -1) { // case 1
            return edges[indexSecond];
        }
        if (indexSecond == -1) { // case 2
            return edges[indexMakeCycle];
        }
        return edges[indexFirst]; // case 3

    }

    public int[] findRedundantDirectedConnection3(int[][] edges) {
        int[] can1 = {-1, -1};
        int[] can2 = {-1, -1};
        int[] parent = new int[edges.length + 1];
        for (int i = 0; i < edges.length; i++) {
            if (parent[edges[i][1]] == 0) {
                parent[edges[i][1]] = edges[i][0];
            } else {
                can2 = new int[] {edges[i][0], edges[i][1]};
                can1 = new int[] {parent[edges[i][1]], edges[i][1]};
                edges[i][1] = 0;//有双重父节点的这个点，把它设为0作为一个标记，标记他不合法所以后面的unionfind的时候就不要union他了
            }
        }
        for (int i = 0; i < edges.length; i++) {//reset parent数组
            parent[i] = i;
        }
        for (int i = 0; i < edges.length; i++) {
            if (edges[i][1] == 0) {
                continue;
            }
            int child = edges[i][1], father = edges[i][0];
            if (root(parent, father) == child) {
                if (can1[0] == -1) {
                    return edges[i];
                }
                return can1;
            }
            parent[child] = father;
        }
        return can2;
    }

    int root(int[] parent, int i) {
        while (i != parent[i]) {
            parent[i] = parent[parent[i]];
            i = parent[i];
        }
        return i;
    }

}
