package 灵神.贪心;

public class PartitionStringIntoSubstringsWithValuesatMostK2522 {
    static void main() {
        System.out.println(minimumPartition("238182",5));
    }
    //1/1/2026 看了答案写的，想的是用substring 取得数字，结果会超时，正确写法就是直接边走边计，大于k就rs++
    //https://leetcode.cn/problems/partition-string-into-substrings-with-values-at-most-k/solutions/2040073/bian-li-by-endlesscheng-7ojw/
    public static int minimumPartition(String s, int k) {
        int rs=0;
        int i=0;
        long cur=0;//int就不行
        while (i<s.length()){
            if(s.charAt(i)-'0'>k){
                return -1;
            }
           cur=cur*10+s.charAt(i)-'0';
           if(cur>k){
               rs++;
               cur=s.charAt(i)-'0';
           }
           i++;

        }
        return rs+1;
    }
}
