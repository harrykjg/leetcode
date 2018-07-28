import java.util.HashMap;
import java.util.Random;

/**
 * Created by yufengzhu on 7/26/18.
 */
public class EncodeandDecodeTinyURL {
    //https://leetcode.com/problems/encode-and-decode-tinyurl/solution/  看答案写的
    String base="0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    HashMap<String,String> map=new HashMap<>();
    public String encode(String longUrl) {
        String key=genKey();
        while (map.containsKey(key)){
            key=genKey();
        }
        map.put("http://tinyurl.com/"+key,longUrl);
        return "http://tinyurl.com/"+key;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return map.get(shortUrl);
    }
    String genKey(){
        StringBuilder sb=new StringBuilder();
        Random ran=new Random();

        for(int i=0;i<6;i++){
            sb.append(base.charAt(ran.nextInt(62)));
        }
        return sb.toString();
    }
}
