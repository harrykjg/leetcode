import com.sun.xml.internal.ws.addressing.WsaTubeHelperImpl;

public class IntegertoRoman {
    //不会，看答案的
    //8/15/2021
    public String intToRoman(int num) {
        String[] map=new String[]{"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        int[] number=new int[]{1000,900,500,400,100,90,50,40,10,9,5,4,1};
        StringBuilder sb=new StringBuilder();
        while (num>0){
            for (int i=0;i<number.length;i++){
                if (num>=number[i]){
                    num-=number[i];
                    sb.append(map[i]);
                    break;
                }
            }
        }
        return sb.toString();
    }
}
