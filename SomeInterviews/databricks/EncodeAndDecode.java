package SomeInterviews.databricks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EncodeAndDecode {

    static void main() {
        System.out.println("======== test 1: =========");
        EncodeAndDecode solution = new EncodeAndDecode();

        int[] input = {5, 5, 5, 5, 5, 5, 5, 5, 1, 2, 3};
        String[] encoded = solution.encode(input);
        System.out.println("encoded: " + Arrays.toString(encoded));
        // Expected: ["RLE[5,8]", "BP[1,2,3]"]

        int[] decoded = solution.decode(encoded);
        System.out.println("decoded: " + Arrays.toString(decoded));


        System.out.println("\n======== test 2: =========");


        input = new int[]{1, 1, 1};
        encoded = solution.encode(input);
        System.out.println("encoded: " + Arrays.toString(encoded));
        // Expected: ["RLE[1,3]"]

         decoded = solution.decode(encoded);
        System.out.println("decoded: " + Arrays.toString(decoded));
        // Expected: [1, 1, 1]
        System.out.println("\n======== test 3: =========");
        input = new int[]{1, 1, 1, 1, 2, 3, 4, 5};
        encoded = solution.encode(input);
        System.out.println("encoded: " + Arrays.toString(encoded));
        // Expected: ["BP[1,1,1,1,2,3,4,5]"]

        decoded = solution.decode(encoded);
        System.out.println("decoded: " + Arrays.toString(decoded));
        // Expected: [1, 1, 1, 1, 2, 3, 4, 5]

        System.out.println("\n======== test 4: =========");
        input = new int[]{1, 1, 1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
        encoded = solution.encode(input);
        System.out.println("encoded: " + Arrays.toString(encoded));
        // Expected: ["BP[1,1,1,1,2,3,4,5]", "BP[6,7,8,9,10,11,12,13]"]

        decoded = solution.decode(encoded);
        System.out.println("decoded: " + Arrays.toString(decoded));
        // Expected: [1, 1, 1, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13]

        System.out.println("\n======== test 5: =========");

        input = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 10, 11};
         encoded = solution.encode(input);
        System.out.println("encoded: " + Arrays.toString(encoded));
        // Expected: ["RLE[0,8]", "BP[1,2,3,4,5,6,7,8]", "RLE[9,10]", "BP[10,11]"]

        decoded = solution.decode(encoded);
        System.out.println("decoded: " + Arrays.toString(decoded));
        // Expected: [0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9, 9, 9, 9,
        // 9, 9, 9, 9, 10, 11]


        System.out.println("\n======== test 6: =========");
        input =new int[] {0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9};
        encoded = solution.encode(input);
        System.out.println("encoded: " + Arrays.toString(encoded));
        // Expected: ["RLE[0,8]", "BP[1,2,3,4,5,6,7,8]", "RLE[9,3]"]

        decoded = solution.decode(encoded);
        System.out.println("decoded: " + Arrays.toString(decoded));
        // Expected: [0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 9, 9]


    }


    //感觉也是没啥特别的，就是按规则写，BP规则必须是8个8个的，除非是
    public String[] encode(int[] values) {
        if(values.length==0){
            return new String[0];
        }
        int i=0;
        List<String> al=new ArrayList<>();

        while (i<values.length){
            //先看有几个连续的
            int first=values[i];
            int next=findSameIndex(values,i);
            if(next-i>=7){
                String s="RLE["+first+","+(1+next-i)+"]";
                al.add(s);
                i=next+1;
            }else if(1+next==values.length&&(next-i>=1)){//最后的情况
                String s="RLE["+first+","+(1+next-i)+"]";
                al.add(s);
                i=next+1;
            }else{//找BP rule，必须是8个的
                String s=findBP(i,values);
                al.add(s);
                i+=8;
            }

        }

        String[] rs=new String[al.size()];
        for (int j=0;j<al.size();j++){
            rs[j]=al.get(j);
        }
        return rs;
    }
    public int[] decode(String[] runs) {
        if(runs.length==0){
            return new int[0];
        }
        List<Integer> al=new ArrayList<>();
        for (String s:runs){
            if(s.startsWith("RLE")){
                String[] ele=s.substring(3).split(",");
                String num=ele[0].substring(1);
                String count=ele[1].substring(0,ele[1].length()-1);
                for (int i=0;i<Integer.valueOf(count);i++){
                    al.add(Integer.valueOf(num));
                }
            }else{
                s=s.substring(3,s.length()-1);
                String[] ele=s.split(",");
                for (int i=0;i<ele.length;i++){
                    al.add(Integer.valueOf(ele[i]));
                }
            }
        }
        int[] rs=new int[al.size()];
        for (int i=0;i<al.size();i++){
            rs[i]=al.get(i);
        }
        return rs;


    }

    int findSameIndex(int[] values,int idx){
        int i=idx;
        while (i+1<values.length&&values[i+1]==values[idx]){
            i++;
        }
        return i;
    }
    String findBP(int index,int[] values){
        int i=index;
        StringBuilder sb=new StringBuilder();
        sb.append("BP[");
        while (i<values.length&&i<index+8){
            sb.append(values[i]);
            sb.append(",");
            i++;
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append("]");
        return sb.toString();
    }

}
