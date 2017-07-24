package Advance3;

import java.util.LinkedList;

/**
 * Created by 502575560 on 7/23/17.
 */
public class MaximalRectangle {
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
}
