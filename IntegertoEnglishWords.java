import java.util.HashMap;

/**
 * Created by yufengzhu on 7/20/18.
 */
public class IntegertoEnglishWords {
    public static void main(String[] args){
        IntegertoEnglishWords ie=new IntegertoEnglishWords();
        System.out.print(ie.numberToWords2(1234567));
    }
    //看别人的把，自己想的写不对肯定
    // https://leetcode.com/problems/integer-to-english-words/discuss/70627/Short-clean-Java-solution
    //https://leetcode.com/problems/integer-to-english-words/discuss/70625/My-clean-Java-solution-very-easy-to-understand
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
    String[] under20={"","One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine","Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    String[] under100={"","","Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    public String numberToWords2(int num) {
        if(num==0){
            return "Zero";
        }
        return helper2(num);
    }
    String helper2(int num){
        String rs="";
        if(num<20){
            rs+=under20[num];
        }
        else if(num<100){//注意只有num小于20的才是基础case，别的都要recursice调用的
            int temp=num/10;
            rs=under100[temp]+" "+helper2(num%10);
        }
        else if(num<1000){
            int temp=num/100;
            rs= helper2(temp)+" Hundred "+helper2(num%100);
        }
        else if(num<1000000){//还有注意这个取值，和下面除法和取模的取值
            int temp=num/1000;
            rs=helper2(temp)+" Thousand "+helper2(num%1000);
        }
        else if(num<1000000000){
            int temp=num/1000000;
            rs= helper2(temp)+" Million "+helper2(num%1000000);
        }else{
            int temp=num/1000000000;
            rs= helper2(temp)+" Billion "+helper2(num%1000000000);
        }

        return rs.trim();
    }

    //9/10/2018,改了几次对了
    String[] below20={"","One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine","Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    String[] below100={"","","Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    public String numberToWords3(int num) {
        if(num==0){
            return "Zero";
        }
        return helper3(num);

    }
    String helper3(int num){
        if(num<20){
            return below20[num];
        }
        if(num<100){
            return (below100[num/10]+" "+below20[num%10]).trim();
        }

        if(num<1000){
            return (helper3(num/100) +" Hundred "+helper3(num%100)).trim();
        }
        if(num<1000000){
            return (helper3(num/1000)+ " Thousand " +helper3(num%1000)).trim();
        }
        if(num<1000000000){
            return (helper3(num/1000000)+ " Million "+helper3(num%1000000)).trim();
        }
        return (helper3(num/1000000000)+ " Billion "+helper3(num%1000000000)).trim();
    }

    //7/15/2021,还是看以前的吧。这次是从大的if开始写，感觉比以前直观。除了前两位数和百位数，后面的每三位数是一个单位.还真得写个helper方法容易处理zero
    String[] oneTo20={"","One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine","Ten", "Eleven", "Twelve",
            "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};//注意开头还放个空字符代表0。
    String[] twentyTo100={"","","Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};//前两个是空是因为想对齐20/10=2即twenty
    public String numberToWords4(int num) {
        if (num == 0) {
            return "Zero";
        }
        String rs=helper4(num);
        return rs.trim();
    }
    String helper4(int num){
        String rs="";
        if (num>=1000000000){            //billion
            int billion=num/1000000000;
            rs= helper4(billion)+" Billion "+helper4(num%1000000000);
        }
        else if (num>=1000000){            //million
            int million=num/1000000;
            rs= helper4(million)+" Million "+helper4(num%1000000);
        }
        else if (num>=1000){
            int thousand=num/1000;
            rs= helper4(thousand)+" Thousand "+helper4(num%1000);
        }
        else if (num>=100){            //million
            int hundred=num/100;
            rs= helper4(hundred)+" Hundred "+helper4(num%100);
        }else {
            if (num<20){
                rs= oneTo20[num];
            }else {
                rs= twentyTo100[num/10]+" "+oneTo20[num%10];
            }
        }
        return rs.trim();
    }
}
