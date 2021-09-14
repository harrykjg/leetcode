public class VerifyinganAlienDictionary {
    //8/20/2021 又不会做了
    public boolean isAlienSorted(String[] words, String order) {
        if (words.length==1){
            return true;
        }
        int[] or=new int[26];
        for (int i=0;i<order.length();i++){//先统计order，没个字符都有个出现的地方。然后再对words每两个字母相比看第一个不想等的字母在or离对应的值是否符合先后
            or[order.charAt(i)-'a']=i;
        }
        for (int i=1;i<words.length;i++){
            int i1=0;
            int i2=0;
            boolean ok=false;
            while (i1<words[i-1].length()&&i2<words[i].length()){
                if (words[i-1].charAt(i1)==words[i].charAt(i2)){
                    i1++;
                    i2++;
                }else {
                    if (or[words[i-1].charAt(i1)-'a']>or[words[i].charAt(i2)-'a']){
                        return false;
                    }
                    ok=true;
                    break;
                }
            }
            if (i1!=words[i-1].length()&&!ok){//如果words【i-1】还没结束并且没ok，则说明word【i-1】比较长但还排在了前面，就返回false
                return false;
            }
        }
        return true;
    }
}
