package contest;

public class SumofDigitsofStringAfterConvert {
    public int getLucky(String s, int k) {
        StringBuilder sb=new StringBuilder();
        for (int i=0;i<s.length();i++){
            sb.append(String.valueOf(s.charAt(i)-'a'+1));
        }
        String temp=sb.toString();
        for(int i=0;i<k;i++){
            int rs=0;
            for (int j=0;j<temp.length();j++){
                rs+=temp.charAt(j)-'0';
            }
            temp=String.valueOf(rs);
        }
        return Integer.valueOf(temp);
    }
}
