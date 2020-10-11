package contest;

import java.util.stream.Stream;

public class FindKthBitinNthBinaryString {
    public static void main(String[] args){
        FindKthBitinNthBinaryString fk=new FindKthBitinNthBinaryString();
        fk.findKthBit(3,1);
    }
//只会暴力法,
    //别人有好一点的思路，就是找规律，如果k在左边，中间，或者右边，在右边。比如要找从右往左第2位，其实就是对称找从左往右第二位的值反过来（0变1，1变0。
    //答案无非是0或者1,中间肯定是1，base case就是0
    //https://leetcode.com/problems/find-kth-bit-in-nth-binary-string/discuss/780976/C%2B%2B-Simple-Recursion-with-Explanation
    //https://leetcode.com/problems/find-kth-bit-in-nth-binary-string/discuss/781132/Java-Simple-recursion
    public char findKthBit(int n, int k) {
        if(k==1){
            return '0';
        }
        String s1="0";
        String rs="";
        for(int i=1;i<n;i++){
            StringBuilder sb=new StringBuilder();
            String invert=invert(s1);
            sb.append(s1);
            sb.append(1);
            sb.append(invert);

            rs=sb.toString();
            s1=rs;
        }
        return rs.charAt(k-1);
    }
    String invert(String s){
        StringBuilder sb=new StringBuilder();
        char[] ch=s.toCharArray();
        for(int i=ch.length-1;i>=0;i--){
            sb.append(ch[i]=='0'?'1':'0');
        }
        return sb.toString();
    }
}
