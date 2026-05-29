package SomeInterviews.bloomberg;

public class RemoveLetterToEqualizeFrequency2423 {
    /*
    写一个function，来判断string s 中的每个character是否出现个数相同。如果 all characters have equal frequency return True
, otherwise return False
"abc" -> True
"abcabc" -> True
"aab" -> False
follow up: 再写一个function，还是given a string s，我们必须删除其中一个character。然后check 剩下的字符是是否可以做到每个出现的次数都一样。注意如果原本的字符串本来就平衡，比如每个字符次数一样，删掉一个之后有可能就不能平衡了，那就算 False。例如

'aabbccc' -> remove c ok, return True
'aaabbbccc' -> return False
'aaa' -> return True
     */
    //这里直接写followup,虽然是简单题但是这个解法还自己不好想，就是先统计，再算每一个字符减去1之后是否出现次数都相同
    public boolean equalFrequency(String word) {
        int[] count=new int[26];
        char[] ch=word.toCharArray();
        for (int i=0;i<ch.length;i++){
            count[ch[i]-'a']++;
        }
        for (char c='a';c<='z';c++){
            if(count[c-'a']>0){
                count[c-'a']--;
                if(check(count)){
                    return true;
                }
                count[c-'a']++;
            }
        }
        return false;
    }
    boolean check(int[] count){
        int num=-1;
        for (int i=0;i<count.length;i++){
            if(count[i]>0){
                if(num==-1){
                    num=count[i];
                }else if(num!=count[i]) {
                    return false;
                }

            }
        }
        return true;
    }
}
