public class ValidNumber {
    //没啥技术含量就是强行写。
    public boolean isNumber(String s) {
        boolean sign=false;
        boolean dot=false;
        boolean exp=false;
        boolean foundDigitAfterExp=false;
        boolean foundDigit=false;
        s=s.trim();
        int i=0;
        char[] ch=s.toCharArray();
        while (i<s.length()){
            if (Character.isDigit(ch[i])){
                i++;
                if (ch[i]>'0'){
                    foundDigit=true;
                }
                continue;
            }
            if (ch[i] == '-'||ch[i]=='+') {
                if (!sign){
                    sign=true;
                }else {
                    return false;
                }
                if (foundDigit||dot){//如果是在数字中间出现或者在小数点之后出现（如果有e的话出现是可以的，那都在后面e那个while循环里处理了）
                    return false;
                }
                i++;
                continue;
            }
            if (ch[i]=='.'){
                if (!dot){
                    dot=true;
                }else {
                    return false;
                }
                if (exp){//e之后不能有小数点
                    return false;
                }
                i++;
                continue;
            }
            if (ch[i]=='e'||ch[i]=='E'){//e后面必须有数字
                if (!exp){
                    exp=true;
                    sign=false;//e前后可以个有一个正负号
                }else {
                    return false;
                }
                i++;
                while (i<ch.length){
                    if (Character.isDigit(ch[i])){
                        foundDigitAfterExp=true;

                        i++;
                        continue;
                    }
                    if(ch[i]=='+'||ch[i]=='-'){
                        if (foundDigitAfterExp){
                            return false;
                        }
                        if (!sign){
                            sign=true;
                        }else {
                            return false;
                        }
                        i++;
                        continue;
                    }
                    if (ch[i]=='.'){
                        return false;
                    }
                    if (Character.isAlphabetic(ch[i])){
                        return false;
                    }

                }
            }else {//别的字符
                return false;
            }

        }
        if (exp){
            return foundDigitAfterExp&&foundDigit;
        }
        return foundDigit;
    }

    //8/8/2021 还不如第一次那样，把看到e之后分开写。记者要这5个boolean好写一些
    public boolean isNumber2(String s) {//e前面(可以是小数点）和后面必须有数字,e后面不能有小数点.e后面要不是进阶数字，要不是就要接正负号
        boolean e=false;//有了e之后是可以再允许一个符号
        boolean sign=false;
        boolean dot=false;
        boolean foundDigit=false;
        boolean foundDigitAfterExp=false;
        char[] ch=s.toCharArray();
        for (int i=0;i<ch.length;i++){
            if (ch[i]=='e'||ch[i]=='E'){
                if (!e){
                    e=true;
                    sign=false;
                }else {
                    return false;
                }
                if (i==0||!foundDigit){
                    return false;
                }
                if (i==ch.length-1||(!Character.isDigit(ch[i+1])&&ch[i+1]!='+'&&ch[i+1]!='-')){
                    return false;
                }
                continue;
            }
            if (ch[i]=='+'||ch[i]=='-'){
                if (sign){
                    return false;
                }else {
                    sign=true;
                }
                if ((!e&&foundDigit)||(dot&&!e)){
                    return false;
                }
                if (e&&foundDigitAfterExp){
                    return false;
                }
                continue;
            }
            if (ch[i]=='.'){
                if (dot||(e)){
                    return false;
                }else {
                    dot=true;
                }
                continue;
            }
            if (Character.isDigit(ch[i])){
                foundDigit=true;
                if (e){
                    foundDigitAfterExp=true;
                }
                continue;
            }
            return false;
        }
        if (!e){
            return foundDigit;
        }
        return foundDigit&&foundDigitAfterExp;

    }
}
