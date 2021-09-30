package SomeInterviews.karat;

import java.util.HashSet;

public class ValidMatrix {
    //9/23/2021 题目要求是长宽为n的matrix，每一行和每一列都必须是1到n的数字，否则错
    public static void main(String[] args){
        ValidMatrix vm=new ValidMatrix();
    }
    public boolean valid(int[][] m){
        if (m.length==0){
            return true;
        }
        int len=m.length;
        for (int i=0;i<m.length;i++){//去重容易，关键是怎么知道是不是1到n，看别人的是维护一个最大值和最小值，当遍历完一行之后，如果set没遇到重复元素，则，
                                            // 最小值肯定是1，最大值肯定是n，
            HashSet<Integer> rows=new HashSet<>();
            HashSet<Integer> cols=new HashSet<>();
            int rowmin=Integer.MAX_VALUE;
            int rowmax=Integer.MIN_VALUE;
            int colmin=Integer.MAX_VALUE;
            int colmax=Integer.MIN_VALUE;
            for (int j=0;j<len;j++){
                if (cols.contains(m[i][j])){
                    return false;
                }else {
                    cols.add(m[i][j]);
                    colmin=Math.min(colmin,m[i][j]);
                    colmax=Math.max(colmax,m[i][j]);
                }
            }

            for (int j=0;j<len;j++){
                if (rows.contains(m[j][i])){
                    return false;
                }else {
                    rows.add(m[j][i]);
                    rowmin=Math.min(rowmin,m[j][i]);
                    rowmax=Math.max(rowmax,m[j][i]);
                }
            }
            if (rowmax!=len||rowmin!=1||colmax!=len||colmin!=1){
                return false;
            }
        }
        return true;
    }
}
