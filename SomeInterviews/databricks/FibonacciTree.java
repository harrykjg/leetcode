package SomeInterviews.databricks;

public class FibonacciTree {
    /*
    Fibonacci trees are binary trees which are recursively defined as follows:

* T_0 is empty
* T_1 consists of a single node.
* T_n consists of a root node, with T_{n-2} as its left child, and T_{n-1} as its right child.
Examples:

T_0:

T_1:
   *

T_2:
   *
    \
     *

T_3:
   *
  / \
 *   *
      \
       *
Now, in order to be able to identify each node within tree, we enumerated the nodes in each tree using DFS pre-order.

Write a function that given nodes s and e in a Fibonacci tree of order n returns the shortest path from s to e in the form of a sequence of moves: "U" (up), "L" (left), and "R" (right).

Example output:

fibPath(order = 3, start = 1, end = 3) == "URR"
fibPath(order = 4, start = 1, end = 4) == "URL"
fibPath(order = 5, start = 3, end = 7) == "UURLR"
     */
    static void main() {
        FibonacciTree ft=new FibonacciTree();

        System.out.println(ft.fibPath(3, 1, 3)); // URR
        System.out.println(ft.fibPath(4, 1, 4)); // URL
        System.out.println(ft.fibPath(5, 3, 7)); // UURLR

        System.out.println(ft.fibPath(3, 2, 2)); // ""
        System.out.println(ft.fibPath(5, 7, 7)); // ""

        System.out.println(ft.fibPath(4, 0, 1)); // L
        System.out.println(ft.fibPath(4, 0, 4)); // RL
        System.out.println(ft.fibPath(4, 0, 6)); // RRR

        System.out.println(ft.fibPath(4, 1, 0)); // U
        System.out.println(ft.fibPath(4, 4, 0)); // UU
        System.out.println(ft.fibPath(4, 6, 0)); // UUU
//
        System.out.println(ft.fibPath(4, 4, 6)); // URR
        System.out.println(ft.fibPath(4, 5, 6)); // R
        System.out.println(ft.fibPath(4, 2, 4)); // UURL

    }

//和lc那题不一样的是这个是斐波那契树，而不是一个给定的任意的树，因此他给一个数的大小就是一个确定的树，就是找root到start和root到end的路径，再变成start
    //到end的路径，难点是怎么找到从root到start。 不好想，看的gpt的解释.如果start和end分别在左右子树上则答案是start到root再到end，如果start和end
    //都在左或者右子树则答案是去掉公共prefix那段path
    //这里start和end应该是0based的编号
    public String fibPath(int n,int start,int end){
        if(start==end){
            return "";//漏了会不对
        }
        int[] size=new int[n+1];//这里开始写错了，因为有size【0】
        size[1]=1;
        for (int i=2;i<size.length;i++){
            size[i]=1+size[i-2]+size[i-1];
        }

        String path1=path(n,size,start);
        String path2=path(n,size,end);

        if(path1.length()==0){
            return path2;
        }
        if(path2.length()==0){//注意path2为空的话，说明end是root，因此答案是UUUU，不是path1
            String rs="";
            for (int i=0;i<path1.length();i++){
                rs+="U";
            }
            return rs;
        }
        StringBuilder sb=new StringBuilder();
        if(path1.charAt(0)==path2.charAt(0)){//在同一个子树上
            int i=0;
            while (i<path1.length()&&i<path2.length()&&path1.charAt(i)==path2.charAt(i)){
                i++;
            }
           //注意这里不是对称的，如果lca是end，则答案是从start往上走
            for (int j=i;j<path1.length();j++){//如果i已经到path1的末尾也没错,注意不能直接用i，否则会改变了i，因为后面path2还要靠i呢
                sb.append("U");
            }
            sb.append(path2.substring(i));
            return sb.toString();
        }else{
            for (int i=0;i<path1.length();i++){
                sb.append("U");
            }
            sb.append(path2);
            return sb.toString();
        }

    }
    //由于树是斐波那契，看规律可知size[n]=1+size[n-2]+size[n-1]，即大小为n的树的节点数量为根节点加左和右子树的节点，例如n=4，则一共有1+2+4=7
    //个节点,由于题目说了用preorder去标记树，可知节点是0-6，第一个节点就是根节点，左边是1，2，右边是3,4，5，6即给定一个tree的size和一个target
    //节点，可以具体定位那个的位置，是根节点或者在左或右子树，可知在【1，2】就是左，在【3,4，5，6】就是右，比如找5，则还需要递归进右子树再找4,而你递归
    //过去右子树的时候，就是在3,4，5，6里面找第二个节点，自己不太好想
    /*
                0*
             1*     3*
               2*  4*  5*
                          6*
     */
    String path(int n,int[] size,int x){
        if(x==0){
            return "";//就root
        }
        if(x<=size[n-2]){//在左边
            return "L"+path(n-2,size,x-1);//这里不好想，1.怎么知道x是子树里的第几个？其实就是x减去根（0的节点）节点，就是x-1.
                 //2:n居然是n-2，我开始写的是size【n-2】代表左树有几个节点，其实不对，应该是n-2代表斐波那契树的阶数，而不是节点数
        }else{
            return "R"+path(n-1,size,x-1-size[n-2]);//同上，举例x=5，则是右边[3,4,5,6]里的第三个，即2.验证5-1-size【n-2】=2正确
        }

    }
}
