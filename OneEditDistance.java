/**
 * Created by yufengzhu on 7/18/18.
 */
public class OneEditDistance {
    public static void main(String[]a){
        OneEditDistance oe=new OneEditDistance();
        System.out.println(oe.isOneEditDistance2("ab","acb"));
    }
    //还是写不对，首先是按edit distance来写的，想着初始化可以放到双重for循环里，结果不行，还是有就是对edit distnce理解还是不太够
    //这样写应该是对的，就是memery leak了，用不着edit distance 的方法
    //就是分析就行了
    public boolean isOneEditDistance(String s, String t) {
        if(s.equals(t)){
            return false;
        }
        if(Math.abs(s.length()-t.length())>=2){
            return false;
        }
        if(s.length()==t.length()){//存在1步就行的话，肯定就只能是替换了，并且只能替换一次
            int count=0;
            for(int i=0;i<s.length();i++){
                if(s.charAt(i)!=t.charAt(i)){
                    count++;
                    if(count>=2){
                        return false;
                    }
                }
            }
            return count==1;
        }
        if(s.length()>t.length()){//只能是从s删除一个，剩下的肯定都得相等
            for(int i=0;i<t.length();i++){
                if(s.charAt(i)!=t.charAt(i)){
                    return s.substring(i+1).equals(t.substring(i));
                }
            }
            return true;
        }else{//只能从s增加
            for(int i=0;i<s.length();i++){
                if(s.charAt(i)!=t.charAt(i)){
                    return s.substring(i).equals(t.substring(i+1));
                }
            }
            return true;//比如"acb","acbd"，前面都对，最后少一个
        }

    }

    //9/25/2018,还是写不好，没找到正确的方法,看上面的，要记
    public boolean isOneEditDistance2(String s, String t) {
        if((s.length()==1&&t.length()==0)||(s.length()==0&&t.length()==1)){
            return true;
        }
        if(Math.abs(s.length()-t.length())>=2||s.equals(t)){
            return false;
        }
        int len1=s.length();
        int len2=t.length();

        boolean changed=false;
        if(len1==len2){
            int i=0;
            int j=0;
            while (i<len1&&j<len2){
                if(s.charAt(i)!=t.charAt(j)){
                    if(changed){
                        return false;
                    }else{
                        changed=true;
                    }
                }
                i++;
                j++;
            }
            return true;
        }
        else if(len1>len2){
            for(int i=0;i<len2;i++){
                if(s.charAt(i)!=t.charAt(i)){
                    return s.substring(i+1).equals(t.substring(i));
                }
            }

        }else{
            for(int i=0;i<len1;i++){
                if(s.charAt(i)!=t.charAt(i)){
                    return s.substring(i).equals(t.substring(i+1));
                }
            }
        }

        return true;
    }
    //9/29/2018,大概记得，有些地方写的不太  看他的方法https://leetcode.com/problems/one-edit-distance/discuss/50098/My-CLEAR-JAVA-solution-with-explanation
    public boolean isOneEditDistance3(String s, String t) {
        if(s.length()==0&&t.length()==0){
            return false;
        }
        if(s.equals(t)){
            return false;
        }
        if(Math.abs(s.length()-t.length())>=2){
            return false;
        }
        if(s.length()==t.length()){
            for(int i=0;i<s.length();i++){
                if(s.charAt(i)!=t.charAt(i)){
                    return s.substring(i+1).equals(t.substring(i+1));
                }
            }
        }
        if(s.length()<t.length()){
            for(int i=0;i<s.length();i++){
                if(s.charAt(i)!=t.charAt(i)){
                    return s.substring(i).equals(t.substring(i+1));
                }
            }
        }
        if(s.length()>t.length()){
            for(int i=0;i<t.length();i++){
                if(s.charAt(i)!=t.charAt(i)){
                    return s.substring(i+1).equals(t.substring(i));
                }
            }
        }
        return true;////比如"acb","acbd"，前面都对，最后少一个.这个没想到
    }
}
