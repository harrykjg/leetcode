package 灵神.常用数据结构.数学;

public class NumberofWaystoDivideaLongCorridor2147 {
    static void main() {

    }
    //就是乘法原理，自己写的比答案的复杂一些
    //https://leetcode.cn/problems/number-of-ways-to-divide-a-long-corridor/solutions/1226675/an-zhao-ti-yi-mo-ni-o1-e-wai-kong-jian-b-5oqd/
    public int numberOfWays(String corridor) {
        int mod=(int)Math.pow(10,9)+7;
        int count=0;
        long rs=1;
        char[] ch=corridor.toCharArray();
        int first=-1;//第一组椅子的第二个位置
        int sec=-1;//记录的是第二组数组的第一个椅子的位置
        boolean found=false;//找到第二组两个椅子
        boolean atleast=false;//整个数组至少有2个椅子
        for(int i=0;i<ch.length;i++){
            if(ch[i]=='S'){
                count++;
                if(count==2){
                    atleast=true;
                    count=0;
                    if(first==-1){//找到第一组
                        first=i;
                    }else{//找到第二组
                        found=true;
                    }
                } else if (count == 1) {
                    if(first==-1){
                        continue;
                    }else{//记录第二组的第一个椅子
                        sec=i;
                    }
                }
                if(found){//找到第二组就开始计数
                    rs*=(sec-first);
                    rs%=mod;
                    found=false;
                    first=i;
                    sec=-1;
                }

            }
        }
        if(!atleast||count!=0){//最后剩余的椅子是1个的话就不行
            return 0;
        }
        return (int)rs;
    }
}
