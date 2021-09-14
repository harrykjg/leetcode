package ArrayListAndNumbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductofTwoRunLengthEncodedArrays {
    public static void main(String[] args){
        ProductofTwoRunLengthEncodedArrays pt=new ProductofTwoRunLengthEncodedArrays();
        int[][] e1={{1,20},{19,14},{28,38}};
        int[][] e2={{15,42},{5,30}};

        pt.findRLEArray(e1,e2);
    }
    //8/18/2021 挺麻烦噁心的。我就先先2pointer同时展开2个数组，这个同时展开还不能简单的展开成一维数组简单的列出所有的单个数字，而是要搞成数字加
//frequency的（至少一部分能搞出的要搞出来） ， 再搞合并，他们貌似有合在一起做的
    //https://leetcode.com/problems/product-of-two-run-length-encoded-arrays/discuss/1231200/Java-two-pointer
    public List<List<Integer>> findRLEArray(int[][] encoded1, int[][] encoded2) {
        int i=0;
        int j=0;
        ArrayList<int[]> al=new ArrayList<>();
        while (i<encoded1.length&&j<encoded2.length){
            int[] cur1=encoded1[i];
            int[] cur2=encoded2[j];
            int num1=cur1[0];
            int num2=cur2[0];
            int range1=cur1[1];
            int range2=cur2[1];
            if (range1==range2){
                al.add(new int[]{num1*num2,range1});
                i++;
                j++;
            }else if (range1>range2){
                al.add(new int[]{num1*num2,range2});
                j++;
                cur1[1]=range1-range2;
            }else {
                al.add(new int[]{num1*num2,range1});
                i++;
                cur2[1]=range2-range1;//这里巧妙，把还没用完的那个的range改了，这样下次读到range1=cur1[1]的时候就对了
            }
        }
        //再把上面部分encode的再合并
        List<List<Integer>> rs=new ArrayList<>();
        int[] cur=al.get(0);
        for (i=1;i<al.size();i++){
            if (al.get(i)[0]==cur[0]){
                cur[1]+=al.get(i)[1];
            }else {
                rs.add(Arrays.asList(new Integer[]{cur[0],cur[1]}));//这个写法有点奇怪
                cur=al.get(i);
            }
        }
        rs.add(Arrays.asList(new Integer[]{cur[0],cur[1]}));

        return rs;
    }
}
