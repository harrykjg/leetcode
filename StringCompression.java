public class StringCompression {
    //8/31/2021  就是直接写
    public int compress(char[] chars) {
        int index=0;
        int i=0;
        while (i<chars.length){
            int count=1;
            while (i+1<chars.length&&chars[i+1]==chars[i]){
                count++;
                i++;
            }
            if (count>1){
                chars[index++]=chars[i];
                String num=String.valueOf(count);
                for (int j=0;j<num.length();j++){
                    chars[index++]=num.charAt(j);
                }
            }else {
                chars[index++]=chars[i];
            }
            i++;
        }
        return index;
    }

    public int compress2(char[] chars) {
        int i=0;
        int index=0;
        while(i<chars.length){
            char cur=chars[i];
            int count=1;
            while(i+1<chars.length&&chars[i+1]==cur){
                count++;
                i++;
            }
            chars[index++]=cur;
            if(count>1){
                String s=String.valueOf(count);
                for(int j=0;j<s.length();j++){
                    chars[index++]=s.charAt(j);
                }
            }
            i++;
        }
        return index;
    }
}
