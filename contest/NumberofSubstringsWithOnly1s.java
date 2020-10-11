package contest;

public class NumberofSubstringsWithOnly1s {
    //想不到好的解法，看了之后发现就是找到规律就好了
    //https://leetcode.com/problems/number-of-substrings-with-only-1s/discuss/731580/JavaC%2B%2BPython-Count
    public int numSub(String s) {
        int rs=0;
        char[] ch=s.toCharArray();
        int i=0;
        int count=0;
        while (i<ch.length){
            if(ch[i]=='1'){
                count++;
            }else{
                count=0;
            }
            rs+=count;
            rs%=(int)(Math.pow(10,9)+7);//注意取模分配律，不写在这rs可能会超过int就错了
            i++;
        }
        return rs;
    }
}
