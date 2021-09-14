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
//6/3/2021
    public void sortLetters2(char[] chars) {//3种想法，一是partition array里别人写的for循环的写法，2是quick sort里while的写法，3是上面这样写
        int b=0;
        for(int i=0;i<chars.length;i++){
            if(Character.isLowerCase(chars[i])){//这是第一种写法，有点反直觉
                char temp=chars[b];
                chars[b]=chars[i];
                chars[i]=temp;
                b++;
            }

        }
        return;
    }
}
