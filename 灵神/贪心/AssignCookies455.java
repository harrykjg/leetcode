package 灵神.贪心;

import java.util.Arrays;

public class AssignCookies455 {
    static void main() {

    }
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        int rs=0;
        int a=g.length-1;
        int b=s.length-1;
        while (a>=0&&b>=0){
            if(s[b]>=g[a]){
                rs++;
                a--;
                b--;
            }else {
                a--;
            }
        }
        return rs;
    }
}
