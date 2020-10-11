package contest;

import java.util.HashMap;
import java.util.Map;

//自己想的
public class NumberofWaysWhereSquareofNumberIsEqualtoProductofTwoNumbers {
    public static void main(String[] args){
        int[] num1={100000,100000};
        int[] num2={100000};
        NumberofWaysWhereSquareofNumberIsEqualtoProductofTwoNumbers nw=new NumberofWaysWhereSquareofNumberIsEqualtoProductofTwoNumbers();
        nw.numTriplets(num1,num2);
    }
    public int numTriplets(int[] nums1, int[] nums2) {
        Map<Long, Integer> map1=new HashMap<>();
        Map<Long,Integer>  map2=new HashMap<>();
        for(int i=0;i<nums1.length;i++){
            for(int j=i+1;j<nums1.length;j++){
                long n=(long)nums1[i]*(long)nums1[j];
                if(map1.containsKey(n)){
                    map1.put(n,map1.get(n)+1);
                }else{
                    map1.put(n,1);
                }
            }
        }
        for(int i=0;i<nums2.length;i++){
            for(int j=i+1;j<nums2.length;j++){
                long n=(long)nums2[i]*(long)nums2[j];
                if(map2.containsKey(n)){
                    map2.put(n,map2.get(n)+1);
                }else{
                    map2.put(n,1);
                }
            }
        }
        int rs=0;
        for(int i=0;i<nums1.length;i++){
            long n=(long)nums1[i]*(long)nums1[i];
            if(map2.containsKey(n)){
                rs+=map2.get(n);
            }
        }
        for(int i=0;i<nums2.length;i++){
            long n=(long)nums2[i]*(long)nums2[i];
            if(map1.containsKey(n)){
                rs+=map1.get(n);
            }
        }
        return rs;
    }
}
