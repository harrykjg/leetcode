package contest;

public class BulbSwitcherIV {
    public static void main(String[] args){
        BulbSwitcherIV bs=new BulbSwitcherIV();
        System.out.println(bs.minFlips("1010111110101"));
    }
    public int minFlips(String target) {//就是找规律，肯定是从第一个不相同的字符那开始flip的，不存在从中间某个地方开始flip会得出更优解的情况
        char[] ch=target.toCharArray();
        int i=0;
        int rs=0;
        boolean isone=false;
        while (i<ch.length){
            if(ch[i]=='0'&&!isone){
                i++;
                isone=false;
                continue;
            }
            if(ch[i]=='0'&&isone){
                i++;
                rs++;
                isone=false;
                continue;
            }
            if(ch[i]=='1'&&isone){
                i++;
                isone=true;
                continue;
            }
            if(ch[i]=='1'&&!isone){
                i++;
                rs++;
                isone=true;
                continue;
            }
        }
        return rs;
    }
}
