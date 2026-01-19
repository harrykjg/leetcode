package 灵神.贪心;

import java.util.Arrays;

public class BoatstoSavePeople881 {
    static void main() {
        int[] a={3,5,3,4};
        System.out.println(numRescueBoats(a,5));
    }

    //12/31/2025，没看清题目，原来船是最多搭两个人,居然就是取头尾拼一条船就行
    //https://leetcode.cn/problems/boats-to-save-people/solutions/959517/gong-shui-san-xie-noxiang-xin-ke-xue-xi-hosg8/
    public static int numRescueBoats(int[] people, int limit) {
        int b=0;
        int e=people.length-1;
        Arrays.sort(people);
        int rs=0;
        while (b<=e){
            if(people[b]+people[e]<=limit){
                rs++;
                b++;
                e--;
            }else{
                rs++;
                e--;
            }
        }
        return rs;
    }
}
