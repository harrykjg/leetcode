package contest;

public class LargestNumberAfterMutatingSubstring {
    public static void main(String[] args){
        LargestNumberAfterMutatingSubstring ln=new LargestNumberAfterMutatingSubstring();
        System.out.println(ln.maximumNumber("334111",new int[]{0,9,2,3,3,2,5,5,5,5}));
    }
    //做不出来，主要是想的如果一个数换了和没换一样，那么到底换不换呢，换的话可能导致后面真的需要换的时候不能换了，因为把这个相等的换了，如("334111",new int[]{0,9,2,3,3,2,5,5,5,5}));
    //这个例子，把前两个3换了，后面到了1，真正该换了，但是却没法换了，因为33和1不连续了。如果说相等的不换的话，那么也可能导致中间某个换不换都行的数换了才能使前后链接起来，
    // 如 "214010" [6,7,9,7,4,0,3,4,4,7]所以写不出,
    //方法就是只有在第一个严格大于的情况下开始换，一旦开始了，那后续的相等的也可以换
    //https://leetcode.com/problems/largest-number-after-mutating-substring/discuss/1360811/Java
    public String maximumNumber(String num, int[] change) {
        char[] ch=num.toCharArray();
        int i=0;
        StringBuilder sb=new StringBuilder();
        boolean found=false;
        while (i<ch.length){
            int cur=ch[i]-'0';
            if (change[cur]>cur&&!found){
                while (i<ch.length&&change[ch[i]-'0']>=ch[i]-'0'){
                    sb.append(change[ch[i]-'0']);
                    i++;
                }
                found=true;
            }else {
                sb.append(cur);
                i++;
            }

        }
        return sb.toString();

    }

}
