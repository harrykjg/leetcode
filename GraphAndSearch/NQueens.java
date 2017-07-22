package GraphAndSearch;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 502575560 on 7/13/17.
 */
public class NQueens {
    public static void main(String[] args){
        NQueens nq=new NQueens();
        nq.solveNQueens(4);
    }
//这次自己写的还不算差,构造棋盘没有像以前那样用的二维char数组,而是直接一个list of string array,转化成char数组操作
    List<List<String>> rs=new ArrayList<>();
    public List<List<String>> solveNQueens(int n) {
        StringBuilder sb=new StringBuilder();
        for(int i=0;i<n;i++){
            sb.append('.');
        }
        String dots=sb.toString();
        ArrayList<String> queen=new ArrayList<>();
        for(int i=0;i<n;i++){
            queen.add(dots);
        }
        dfs(0,queen,n);
        return rs;
    }
    void dfs(int row,ArrayList<String> q,int n){
        if(row==n){
            rs.add(new ArrayList<>(q));
            return;
        }
        char[] ch=q.get(row).toCharArray();
        for(int i=0;i<n;i++){

            ch[i]='Q';
            q.set(row,new String(ch));
            if(valid(row,i,q)){
                dfs(row+1,q,n);
            }
            ch[i]='.';//这里注意,无论是否valid,是否dfs,这一行都得复原成"....",因为题目求得是所有解法,所以说不管valid与否也要试下一种
            q.set(row,new String(ch));
        }
    }
    boolean valid(int row,int col,ArrayList<String> q){
        for(int i=0;i<row;i++){
            char[] ch=q.get(i).toCharArray();
            if(ch[col]=='Q'){
                return false;
            }
            //左上对角线,比如第row是3而i现在是1,则第1行的这个位置不能是Q,而这个位置就是col-(row和i的差值)
            if(col-(row-i)>=0&&ch[col-(row-i)]=='Q'){
                return false;
            }//右上对角线
            if(col+(row-i)<ch.length&&ch[col+(row-i)]=='Q'){
                return false;
            }
        }
        return true;
    }

}
