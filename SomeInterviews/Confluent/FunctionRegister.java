package SomeInterviews.Confluent;

import java.util.*;

public class FunctionRegister {
    public static void main(String[] args){
        String[] s1={"integer","string"};
        String[] s2={"integer"};
        String[] s3={"integer","string","string"};
        String[] s4={"integer","string","integer"};
        String[] s5={"integer","string","string","integer"};
        String[] s6={"integer","xxx","integer","string"};
        FunctionRegister fr=new FunctionRegister();
        fr.put(s1);
        fr.put(s2);
        fr.put(s3);
        fr.put(s4);
        fr.put(s5);
        fr.put(s6);
        String[] request={"integer","null","integer..."};

        List<String[]> rs=fr.get(request);
        for (String[] s:rs){
            for (String ss:s){
                System.out.print(ss);
            }
            System.out.println();

        }

    }
    Map<String,String[]> map=new HashMap<>();
    public FunctionRegister(){

    }
    public void put(String[] input){
        String parameters="";
        for (String s:input){
            parameters+=s+",";
        }
        map.put(parameters,input);

    }
    public List<String[]> get(String[] input){
        String target="";
        for (String s:input){
            target+=s+",";
        }
        List<String[]> rs=new ArrayList<>();
        for (Map.Entry<String,String[]> entry:map.entrySet()){
            String s=entry.getKey();
            String[] ss=s.split(",");
            String[] tt=target.split(",");
            if (isMatch(ss,tt,0,0)){
                rs.add(entry.getValue());
            }
        }
        return rs;
    }
    boolean isMatch(String[] s,String[] t,int b1,int b2){
        if (b1==s.length&&b2==t.length){
            return true;
        }
        while (b1<s.length&&b2<t.length){
            if (t[b2].equals("null")){
                b1++;
                b2++;
                continue;
            }
            if (t[b2].indexOf("...")!=-1){
                String varid=t[b2].substring(0,t[b2].indexOf("..."));
                if(s[b1].equals(varid)&&checkIfRemaingMatch(s,b1+1,varid)){//假设可变参数必须是最后一个参数
                    return true;
                }else if (isMatch(s,t,b1,b2+1)){
                    return true;
                }else {
                    return false;
                }
            }else {
                if (!s[b1].equals(t[b2])){
                    return false;
                }else {
                    b1++;
                    b2++;
                }
            }
        }
        if (b1==s.length&&b2==t.length){
            return true;
        }else if (b1==s.length){
            if (t[b2].indexOf("...")!=-1){
                return true;
            }else {
                return false;
            }
        }else {
            return false;
        }
    }
    boolean checkIfRemaingMatch(String[] s,int b,String target){
        while (b<s.length){
            if (!s[b].equals(target)){
                return false;
            }
            b++;
        }
        return true;
    }
}
