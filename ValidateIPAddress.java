/**
 * Created by yufengzhu on 9/20/18.
 */
public class ValidateIPAddress {
    public static void main(String[] args){
        ValidateIPAddress vi=new ValidateIPAddress();
        vi.validIPAddress("2001:0db8:85a3:0:0:8A2E:0370:7334:");
//        vi.validIPAddress("11.11.11.11.");
    }
    //自己写的，就是条件比较多而已
    public String validIPAddress(String IP) {
        if(IP==null||IP.length()==0){
            return "Neither";
        }
        if(IP.indexOf(":")==-1){
            if(validIP4(IP)){
                return "IPv4";
            }
        }else{
            if(validIP6(IP)){
                return "IPv6";
            }
        }
        return "Neigther";
    }
    boolean validIP4(String s){
        String[] ss=s.split("\\.");
        if(ss.length!=4){
            return false;
        }
        if(s.lastIndexOf(".")==s.length()-1){
            return false;
        }
        for(int i=0;i<ss.length;i++){
            char[] ch=ss[i].toCharArray();
            if(ch.length>=4||ch.length<1){
                return false;
            }
            int range=0;
            for(int j=0;j<ch.length;j++){
                if(!Character.isDigit(ch[j])){
                    return false;
                }
                if(ch[0]=='0'&&ch.length>1){
                    return false;
                }
                range=range*10+ch[j]-'0';
            }
            if(range>=256||range<0){//ipv4最多到255
                return false;
            }
        }
        return true;
    }
    boolean validIP6(String s){
        String[] ss=s.split("\\:");
        if(ss.length!=8){
            return false;
        }
        if(s.lastIndexOf(":")==s.length()-1){
            return false;
        }
        for(int i=0;i<ss.length;i++){
            char[] ch=ss[i].toCharArray();
            if(ch.length>4||ch.length<1){
                return false;
            }
            for(int j=0;j<ch.length;j++){
                if(!Character.isDigit(ch[j])){
                    if((ch[j]-'a'<0||ch[j]-'f'>0)&&(ch[j]-'A'<0||ch[j]-'F'>0)){//16进制0123456789abcdef
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
