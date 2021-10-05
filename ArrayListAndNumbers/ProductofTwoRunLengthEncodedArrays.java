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
    //10/4/2021
    public List<List<Integer>> findRLEArray2(int[][] encoded1, int[][] encoded2) {
        List<int[]> temp=new ArrayList<>();
        int i=0;
        int j=0;
        while(i<encoded1.length&&j<encoded2.length){
            int[] a=encoded1[i];
            int[] b=encoded2[j];
            int min=Math.min(a[1],b[1]);
            int[] cur=new int[2];
            cur[0]=a[0]*b[0];
            cur[1]=min;
            if(a[1]==b[1]){
                i++;
                j++;
            }else if(a[1]==min){
                i++;
                b[1]=b[1]-min;
            }else{
                j++;
                a[1]=a[1]-min;
            }
            temp.add(cur);
        }
        List<List<Integer>> rs=new ArrayList<>();
        ArrayList<Integer> al=new ArrayList<>();
        al.add(temp.get(0)[0]);
        al.add(temp.get(0)[1]);
        rs.add(al);

        for(i=1;i<temp.size();i++){
            List<Integer> pre=rs.get(rs.size()-1);
            if(pre.get(0)==temp.get(i)[0]){
                pre.set(1,pre.get(1)+temp.get(i)[1]);
            }else{
                List<Integer> ls=new ArrayList<>();
                ls.add(temp.get(i)[0]);
                ls.add(temp.get(i)[1]);
                rs.add(ls);
            }
        }
        return rs;
    }
}
