/**
 * Created by yufengzhu on 7/20/18.
 */
public class IntegertoEnglishWords {
    //看别人的把，自己想的写不对肯定
    // https://leetcode.com/problems/integer-to-english-words/discuss/70625/My-clean-Java-solution-very-easy-to-understand
    String[] lessTen={"","One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    String[] lessTwenty={"Ten", "Eleven", "Twelve", "Thirteen", "Fourtee", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    String[] lessHundred={"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    public String numberToWords(int num) {
        if(num==0){
            return "Zero";
        }

        return helper(num);
    }
    private String helper(int num) {
        String result = new String();
        if (num < 10) result = lessTen[num];
        else if (num < 20) result = lessTwenty[num -10];
        else if (num < 100) result = lessHundred[num/10] + " " + helper(num % 10);
        else if (num < 1000) result = helper(num/100) + " Hundred " +  helper(num % 100);
        else if (num < 1000000) result = helper(num/1000) + " Thousand " +  helper(num % 1000);
        else if (num < 1000000000) result = helper(num/1000000) + " Million " +  helper(num % 1000000);
        else result = helper(num/1000000000) + " Billion " + helper(num % 1000000000);
        return result.trim();
    }
}
