package contest;

public class SmallestStringWithAGivenNumericValue {
    //自己想的就是greedy，n位数绰绰有余则一直append 'a'，直到不绰绰有余的时候。
    public String getSmallestString(int n, int k) {
        StringBuilder sb=new StringBuilder();
        int i=n;
        while (i>0){
            int max=(i-1)*26;//看当前位能不能append 'a'，能就append，不能就看能append谁
            char cur='a';
            if(max>=k){//这个等号容易漏，要想清楚
                sb.append(cur);
                k-=1;
            }else{
                cur=(char)((k-max)-1+'a');//这里-1也容易漏
                sb.append(cur);
                k-=(cur-'a')+1;
            }
            i--;
        }
        return sb.toString();
    }
}
