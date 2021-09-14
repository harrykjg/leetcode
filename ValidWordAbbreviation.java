public class ValidWordAbbreviation {
    //8/18/2021 直接2pointer。改了几次accept
    public boolean validWordAbbreviation(String word, String abbr) {
        int b1=0;
        int b2=0;
        char[] ch1=word.toCharArray();
        char[] ch2=abbr.toCharArray();
        while (b1<word.length()&&b2<abbr.length()){

            int num=0;
            while (b2<ch2.length&&Character.isDigit(ch2[b2])){
                if (num==0&&ch2[b2]=='0'){//01这样开头是0的直接不合法
                    return false;
                }
                num=10*num+ch2[b2]-'0';
                b2++;
            }
            if (num==0){
                if (ch1[b1]!=ch2[b2]){
                    return false;
                }else {
                    b1++;
                    b2++;
                }
                continue;
            }else {
                b1+=num;
            }
        }
        if(b1!=ch1.length||b2!=ch2.length){//这里容易忘，这里可以检查abbreation过长或过短
            return false;
        }

        return true;
    }
}
