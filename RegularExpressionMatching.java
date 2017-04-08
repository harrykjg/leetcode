/**
 * Created by 502575560 on 7/13/16.
 */
public class RegularExpressionMatching {
    public static void main(String[] args){
        System.out.print(isMatch("ab",".*c"));
    }

    //july2016,改了好多次每次加一点conner case过了
    public static boolean isMatch(String s, String p) {
        if(s.length()==0&&p.length()==0){
            return true;
        }
        if(p.length()==0){
            return false;
        }
        if(s.equals("")&&p.length()>=2&&p.charAt(1)=='*'){
            return isMatch("",p.substring(2));
        }
        if(s.equals("")){
            return false;
        }
            char c1=s.charAt(0);
            char c2=p.charAt(0);

            if(c1==c2){
                if(s.length()==p.length()&&s.length()==1){
                    return true;
                }

                if(1<p.length()&&p.charAt(1)!='*'){
                    return isMatch(s.substring(1),p.substring(1));
                }else if(1<p.length()&&p.charAt(1)=='*'){
                    if(isMatch(s.substring(0),p.substring(2))){
                        return true;
                    }
                    for(int i=0;i<s.length();i++){
                        if(i>0&&s.charAt(i)!=s.charAt(i-1)){
                            break;
                        }
                        if(isMatch(s.substring(i+1),p.substring(2))){
                            return true;
                        }
                    }
                    return false;
                }

            }else if(c2=='.'){
                if(p.length()==1&&isMatch(s.substring(1),p.substring(1))){
                    return true;
                }
                if(1<p.length()&&p.charAt(1)!='*'){
                    return isMatch(s.substring(1),p.substring(1));
                }else if(1<p.length()&&p.charAt(1)=='*'){
                    if(isMatch(s.substring(0),p.substring(2))){
                        return true;
                    }
                    for(int i=0;i<s.length();i++){
                        if(isMatch(s.substring(i+1),p.substring(2))){
                            return true;
                        }
                    }
                    return false;
                }
            }else if(1<p.length()&&p.charAt(1)=='*'){

                if(isMatch(s,p.substring(2))){
                    return true;
                }
            }
        return false;
    }

}
