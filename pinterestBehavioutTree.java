import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by yufengzhu on 9/28/18.
 */
public class pinterestBehavioutTree {
    public static void main(String[] args){
        pinterestBehavioutTree pb=new pinterestBehavioutTree();
        Log[] logs=new Log[7];
        logs[0]=new Log(1000,100,"a");
        logs[1]=new Log(1001,200,"a");
        logs[2]=new Log(1002,300,"b");
        logs[3]=new Log(1003,100,"b");
        logs[4]=new Log(1004,100,"c");
        logs[5]=new Log(1005,200,"b");
        logs[6]=new Log(1006,200,"a");
        pb.print(logs);

    }
    //pinterest面经
    /*
    A log System with structure like this
|---register_button (10)
|    |---register_email (4)
|    |    |---email_already_exists (1)
|    |    |---register_success (3)
|    |---register_facebook (4)
|    |    |---register_success (4)
|    |---dropoff (2)
|---login_button (10)
|    |---login_email (4)
|    |    |---login_success (4)
|    |---login_facebook (4)
|    |    |---login_success (3)
|    |    |---login_failure (1).
|    |---dropoff (2)
now we have data with 3 properties
user_id, timestamp, action
100, 1000, A.
200, 1003, A
300, 1009, B
100, 1026, B
100, 1030, C
200, 1109, B
200, 1503, A
We want to output a graph to visualize it.
Graph from input:
|---A (2)
|    |---B (2)
|    |    |---C (1)
|    |    |---A (1)
|---B (1)                                          

1. define data structure and classes
2. construct the graph from logfile
3. print out the graph similar to above

第二题问用一个字符代表pinterest的一个网页， 有一连串的sequence来代表浏览顺序，问用什么data structure来implement。
简单的trieNode.
     */
    public void print(Log[] logs){
        HashMap<Integer,String> paths=new HashMap<>();//用来统计每个用户的行为路径，再依据他们建一个多叉树，数节点的有两个field，行为和count
        Node root=new Node(' ');
        for(int i=0;i<logs.length;i++){
            if(!paths.containsKey(logs[i].id)){
                paths.put(logs[i].id,"");
            }
            paths.put(logs[i].id,paths.get(logs[i].id)+logs[i].behav);
        }
        for(String s:paths.values()){
            insert(s,root);
        }
        calculateChildren(root);
        System.out.print("x");
        dfsPrint(root);

    }
    class Node{
        int count;
        Character val;
        int ccount;
        HashMap<Character,Node> children;
        public Node(Character val){
            this.val=val;
            this.children=new HashMap<>();
        }

    }
    void insert(String s,Node root){
        if(s.length()==0){
            return;
        }
        char[] ch=s.toCharArray();
        Node n=root;
        for(int i=0;i<ch.length;i++){
            if(!n.children.containsKey(ch[i])){
                Node nn=new Node(ch[i]);
                n.children.put(ch[i],nn);
            }
            n.children.get(ch[i]).count++;
            n=n.children.get(ch[i]);
        }
    }
    void calculateChildren(Node root){
        helper(root);
    }
    int helper(Node root){
        if(root.children==null||root.children.size()==0){
            return 0;
        }
        int rs=0;
        for(Character c:root.children.keySet()){
            rs+=helper(root.children.get(c))+1;
        }
        root.ccount=rs;
        return rs;
    }
    void dfsPrint(Node root){
        if(root.val==' '){
            for(Node nn:root.children.values()){
                dfsPrint(nn);
            }
        }else {
            System.out.println("-"+root.val+"("+root.count+")");
            for(Node nn:root.children.values()){
                dfsPrint(nn);
            }
        }
    }
}

