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
}
