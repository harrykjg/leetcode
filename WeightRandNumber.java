import java.util.Random;

/**
 * Created by 502575560 on 7/12/16.
 */
public class WeightRandNumber {
    /*
Weight Random Character，输入e.g. data = [{label: 'A', weight: 2}, {label: 'B', weight: 3}, {label: 'C', weight: 5}]，
实现randLabel(data)，按权重随机输出label（'A'，'B'或'C'，'A'的可能性为20%，'B'为30%，'C'为50%）
2 + 3 + 5=10;
*/
    public static void main(String[] args){
        weightRanNum w1=new weightRanNum("a",2);
        weightRanNum w2=new weightRanNum("b",3);
        weightRanNum w3=new weightRanNum("c",5);
        weightRanNum[] w={w1,w2,w3};
        System.out.print(rand(w));

    }
    public static String rand(weightRanNum[] w){
        for(int i=1;i<w.length;i++){
            w[i].a+=w[i-1].a;
        }
        Random ra=new Random();
        int i=ra.nextInt(w[w.length-1].a);
        System.out.print(i);
        int b=0;
        int e=w.length-1;
        while(b<=e){
            int m=(b+e)/2;
            if(w[m].a>=i){
                return w[m].name;
            }
            if(w[m].a<i){
                b=m+1;
            }else{
                e=m-1;
            }
        }
        return w[b].name;
    }


}
class weightRanNum{
    String name;
    int a;
    public weightRanNum(String s,int a){
        name=s;
        this.a=a;
    }
}
