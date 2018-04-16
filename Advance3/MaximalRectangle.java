package Advance3;

import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by 502575560 on 7/23/17.
 */
public class MaximalRectangle {
    public static void main(String[] args){
        MaximalRectangle mr=new MaximalRectangle();
        char[][] ch={{'1'}};
        mr.maximalRectangle2(ch);
    }
    //还是不会
    public int maximalRectangle(char[][] matrix) {
        if(matrix.length==0){
            return 0;
        }
        int rs=0;
        int[] a=new int[matrix[0].length];
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                if(matrix[i][j]=='1'){
                    a[j]+=1;
                }else{
                    a[j]=0;
                }
            }
            rs=Math.max(rs,find(a));
        }
        return rs;
    }
    int find(int[] a){
        LinkedList<Integer> st=new LinkedList<>();
        int i=0;
        int rs=0;
        while (i<a.length){
            if(st.isEmpty()||a[i]>=a[st.peek()]){
                st.push(i);
                i++;
                continue;
            }
            while (!st.isEmpty()&&a[st.peek()]>=a[i]){
                int index=st.poll();
                int temp=st.isEmpty()?a[index]*i:a[index]*(i-st.peek()-1);
                rs=Math.max(temp,rs);
            }
            st.push(i);
            i++;
        }
        while (!st.isEmpty()){
            int index=st.poll();
            int temp=st.isEmpty()?a[index]*a.length:a[index]*(a.length-st.peek()-1);
            rs=Math.max(temp,rs);
        }
        return rs;
    }
    //4/15/2018九章第二轮,还是不会
    // http://www.cnblogs.com/easonliu/p/3657489.html
    public int maximalRectangle2(char[][] matrix) {
        if(matrix.length==0){
            return 0;
        }
        int rs=Integer.MIN_VALUE;
        int[][] m=new int[matrix.length][matrix[0].length];
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[0].length;j++){
                if(i==0){
                    m[i][j]=matrix[i][j]=='1'?1:0;
                    continue;
                }
                if(matrix[i][j]=='0'){
                    m[i][j]=0;
                }else{
                    m[i][j]=1+m[i-1][j];
                }
            }
            rs=Math.max(find2(i,m),rs);
        }
        return rs;
    }
    int find2(int row,int[][] m){
        Stack<Integer> st=new Stack<>();
        int rs=Integer.MIN_VALUE;
        for(int i=0;i<m[0].length;i++){
            if(st.isEmpty()||m[row][st.peek()]<=m[row][i]){
                st.push(i);
                continue;
            }

            while (!st.isEmpty()&&m[row][i]<m[row][st.peek()]){
                int temp=st.pop();
                if(st.isEmpty()){
                    rs=Math.max(m[row][temp]*i,rs);
                }else{
                    rs=Math.max(m[row][temp]*(i-1-st.peek()),rs);
                }
            }
            st.push(i);
        }
        while (!st.isEmpty()){
            int temp=st.pop();
            if(st.isEmpty()){
                rs=Math.max(rs,m[row].length*m[row][temp]);
            }else{
                rs=Math.max(rs,(m[row].length-1-st.peek())*m[row][temp]);
            }
        }
        return rs;
    }
}
