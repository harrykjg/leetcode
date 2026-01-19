package 灵神.链表二叉树回溯.回溯;

import java.util.ArrayList;
import java.util.List;

public class NQueens51 {
    static void main() {
        List<List<String>> rs=solveNQueens(4);
        for(int i=0;i<rs.size();i++){
            for(String s:rs.get(i)){
                System.out.println(s);
            }
        }
        System.out.println();
    }
    //12/28/2025 改了几次对了
    public static List<List<String>> solveNQueens(int n) {
        List<List<String>> rs=new ArrayList<>();
        List<String> al=new ArrayList<>();
        StringBuilder sb=new StringBuilder();
        for (int i=0;i<n;i++){
            sb.append('.');
        }
        dfs(sb,al,rs,n);
        return rs;
    }
    static void dfs(StringBuilder cur,List<String> al,List<List<String>> rs,int n){
        if(al.size()==n){
            rs.add(new ArrayList<>(al));
            return;
        }
//        String ori=cur.toString();
        for(int i=0;i<cur.length();i++){
            cur.setCharAt(i,'Q');
            al.add(cur.toString());
            cur.setCharAt(i,'.');
            if(good(al,i)){
                dfs(cur,al,rs,n);
            }
                al.removeLast();

        }

    }
    static boolean good(List<String> al,int index){

        int row=al.size()-1;
        for(int i=al.size()-2;i>=0;i--){
            String now=al.get(i);
            if(now.charAt(index)=='Q'){
                return false;
            }
            int gap=row-i;
            if(index+gap<now.length()&&now.charAt(index+gap)=='Q'){
                return false;
            }

            if(index-gap>=0&&now.charAt(index-gap)=='Q'){
                return false;
            }
        }
        return true;
    }
}
