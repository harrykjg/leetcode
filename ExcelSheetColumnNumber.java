/**
 * Created by yufengzhu on 10/1/18.
 */
public class ExcelSheetColumnNumber {
    //一次过
    public int titleToNumber(String s) {
        char[] ch=s.toCharArray();
        int rs=0;
        for(int i=0;i<ch.length;i++){
            int num=ch[i]-'A'+1;
            rs=rs*26+num;
        }
        return rs;
    }
}
