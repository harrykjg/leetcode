/**
 * Created by yufengzhu on 7/18/18.
 */
public class ReadNCharactersGivenRead4 {
    public int read(char[] buf, int n) {
        if(n==0){
            return 0;
        }
        int b=0;
        while (b<n){
            char[] buf4=new char[4];
            int index4=read4(buf4);
            if(index4==0){
                break;
            }
            int index=0;
            while (index<index4&&b<n){
                buf[b++]=buf4[index++];
            }

        }

        return b;

    }
    //8/16/2018,题目还是很怪,read4要先读一段东西放在一个长度为4的临时buff里，再放进输入参数的buf里
    public int read2(char[] buf, int n) {
        if (n==0){
            return n;
        }
        int cur=0;
        while (cur<n){
            char[] buf4=new char[4];
            int index=read4(buf4);
            if(index==0){
                break;
            }
            int i=0;
            while (i<index&&cur<n){
                buf[cur++]=buf4[i++];
            }

        }
        return cur;


    }
}
