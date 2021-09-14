import java.util.HashMap;

public class BullsandCows {
    //8/16/2021 自己想的，先记录secret出现的字符和次数，然后第一便扫描把对应位置数字相同的搞出来，然后把对应字符再map中减掉，以便不被重复使用。
    // 第二遍扫描再把guess和map中都有但位置不对的搞出来。开始写在同一个循环就错了。还有一遍的方法，比较巧妙。边遍历边加hashmap，guess中出现的就先-1
    public String getHint(String secret, String guess) {
        char[] ch1=secret.toCharArray();
        char[] ch2=guess.toCharArray();
        int bull=0;
        int cow=0;
        HashMap<Character,Integer> map=new HashMap<>();
        for(char c:ch1){
            map.put(c,map.getOrDefault(c,0)+1);
        }
        for(int i=0;i<ch1.length;i++){
            if(ch1[i]==ch2[i]){
                map.put(ch1[i],map.get(ch1[i])-1);
                bull++;
                if(map.get(ch1[i])==0){
                    map.remove(ch1[i]);
                }
            }
        }
        for(int i=0;i<ch1.length;i++){

            if(ch1[i]!=ch2[i]&&map.containsKey(ch2[i])){
                cow++;
                map.put(ch2[i],map.get(ch2[i])-1);
                if(map.get(ch2[i])==0){
                    map.remove(ch2[i]);
                }
            }

        }
        return bull+"A"+cow+"B";
    }
}
