package 灵神.常用数据结构.差分数组interval;

public class CarPooling1094 {
    static void main() {

    }
    //暴力的思路就是把每个区间加上乘客数，然后遍历这个区间看有没有超capacity就行了。但是把区间加乘客数这个操作是o（n)操作
    //查分数组具体怎么写见帖子
    //https://leetcode.cn/problems/car-pooling/solutions/2550264/suan-fa-xiao-ke-tang-chai-fen-shu-zu-fu-9d4ra/
    //貌似英文的leetcode没有说差分数组的，就是用treemap，写法也是差不多
    //https://leetcode.com/problems/car-pooling/solutions/317610/javacpython-meeting-rooms-iii-by-lee215-i3ac/
    public boolean carPooling(int[][] trips, int capacity) {
        int[] chafen=new int[1001];//题目范围就是1000
        for(int[] a:trips){
            int num=a[0];
            int from=a[1];
            int to=a[2];
            chafen[from]+=num;
            chafen[to]-=num;
        }
        int s=0;
        for (int i:chafen){
            s+=i;
            if(s>capacity){
                return false;
            }
        }
        return true;
    }
}
