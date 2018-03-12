package GraphAndSearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by 502575560 on 7/13/17.
 */
public class NQueens {
    public static void main(String[] args){
        NQueens nq=new NQueens();
        nq.solveNQueens2(4);
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

    //3/11/2018,九章第二轮，写的不好，debug了挺久才accept，还是看回之前的code，关键是ch[][]数组的还原，不好想
    public List<List<String>> solveNQueens2(int n) {
        if(n<=0){
            return rs;
        }
        StringBuilder sb=new StringBuilder();
        char[][] ch=new char[n][n];
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                ch[i][j]='.';
            }
        }
        helper(ch,0);
        return rs;
    }
    void helper(char[][] ch,int b){
        StringBuilder sb=new StringBuilder();
        for(int i=b;i<ch.length;i++){
            for(int j=0;j<ch.length;j++){
                ch[i][j]='Q';
                if(valid2(i,j,ch)){
                    helper(ch,i+1);
                    if(i==ch.length-1){//构造结果
                        List<String> list=new ArrayList<>();

                        for(int k=0;k<ch.length;k++){
                            String s="";
                            sb=new StringBuilder();
                            for(int l=0;l<ch[0].length;l++){
                                sb.append(ch[k][l]);
                            }
                            s=sb.toString();
                            list.add(s);
                        }
                        rs.add(new ArrayList<>(list));
                    }
                    ch[i][j]='.';
                }
                ch[i][j]='.';
                if(j==ch.length-1){//这里是关键，比如第一第二行都valid了，到了第三行，所有位置都试了都不valid，那么就不应该再去forloop试第四行了，
                        //而应该返回，到了第二行，再试第二行的下一个位置
                    return;
                }



            }
        }
    }

    boolean valid2(int row,int col,char[][] ch){
        for(int i=0;i<=row;i++){
            if(i==row){
                continue;
            }
            if(ch[i][col]=='Q'){
                return false;
            }
        }
        for(int i=0;i<=row;i++){
            if(i==row){
                continue;
            }
            if((col-(row-i)>=0&&ch[i][col-row+i]=='Q')||col+(row-i)<ch.length&&ch[i][col+row-i]=='Q'){
                return false;
            }
        }
        return true;
    }

}
