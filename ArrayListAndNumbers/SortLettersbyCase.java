package ArrayListAndNumbers;

/**
 * Created by 502575560 on 7/10/17.
 */
//双指针
public class SortLettersbyCase {
    public void sortLetters(char[] chars) {
        //简单一次过
        int b=0;
        int e=chars.length-1;
        while (b<e){
            if(Character.isLowerCase(chars[b])){
                b++;
                continue;
            }else{
                char temp=chars[e];
                chars[e]=chars[b];
                chars[b]=temp;
                e--;
            }
        }
    }
}
