package dp;

import java.util.*;

public class MaximumProfitinJobScheduling {
    public static void main(String[] args){
        MaximumProfitinJobScheduling mp=new MaximumProfitinJobScheduling();
        int[] s={1,2,3,4,6};
        int[] e={3,5,10,6,9};
        int[] p={20,20,100,70,60};
        System.out.println(mp.jobScheduling3(s,e,p));

    }
    //8/10/2021 不会
    //思路看他https://www.youtube.com/watch?v=0C7re8lam7M  5分45开始
    //https://blog.csdn.net/u013325815/article/details/106991444 代码参考他
    //不知道为啥还是超时，和第二个链接的代码其实只是他那里创建了一个class去存start end profit而我这用的数组
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int[][] pairs=new int[startTime.length][startTime.length];
        for (int i=0;i<startTime.length;i++){
            pairs[i]=new int[3];
            pairs[i][0]=startTime[i];
            pairs[i][1]=endTime[i];
            pairs[i][2]=profit[i];
        }
        Arrays.sort(pairs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[1]-o2[1];
            }
        });
        //开始想着用treemap可以得到小于end的数的最大值，结果floor是小于等于的，所以就会取到自己
        int[] dp=new int[pairs.length];
        dp[0]=pairs[0][2];
        for (int i=1;i<dp.length;i++){
            int[] cur=pairs[i];
            int floor=find(pairs,i);
            if (floor==-1){
                dp[i]=Math.max(dp[i-1],cur[2]);
                continue;
            }else {
                dp[i]=Math.max(dp[i-1],cur[2]+dp[floor]);
            }
        }
        return dp[dp.length-1];
    }
    int find(int[][] paris,int index){
        int b=0;
        int e=index-1;
        while (b<e){
            int m=b+(e-b)/2;
            if (paris[m][1]<=paris[index][0]){
                b=m+1;
            }else {
                e=m;
            }
        }
        if (paris[b][1]<=paris[index][0]){
            return b;
        }else if (b>0&&paris[b-1][1]<=paris[index][0]){
            return b-1;
        }
        return -1;
    }
//不二分法搜就超时
    public int jobScheduling2(int[] startTime, int[] endTime, int[] profit) {
        int[] dp=new int[startTime.length];
        Pair[] pairs=new Pair[startTime.length];
        for(int i=0;i<profit.length;i++){
            Pair p=new Pair(startTime[i],endTime[i],profit[i]);
            pairs[i]=p;
        }
        Arrays.sort(pairs,new Comparator<Pair>(){
            public int compare(Pair p1,Pair p2){
                return p1.end-p2.end;
            }
        });
        dp[0]=pairs[0].profit;
        for(int i=1;i<pairs.length;i++){
            int last=find2(pairs,i);
            if(last!=-1){
                dp[i]=Math.max(pairs[i].profit+dp[last],dp[i-1]);
            }else{
                dp[i]=Math.max(dp[i-1],pairs[i].profit);
            }
        }
        return dp[dp.length-1];
    }
    int find2(Pair[] paris,int index){
        int b=0;
        int e=index-1;
        while (b<e){
            int m=b+(e-b)/2;
            if (paris[m].end<=paris[index].start){
                b=m+1;
            }else {
                e=m;
            }
        }
        if (paris[b].end<=paris[index].start){
            return b;
        }else if (b>0&&paris[b-1].end<=paris[index].start){
            return b-1;
        }
        return -1;
    }

    //9/14/2021 写不对了，以为以star排序可以，其实不行，非得按end排序，按start肯定也行就是不会写
    public int jobScheduling3(int[] startTime, int[] endTime, int[] profit) {
        int[] dp=new int[profit.length+1];//注意这里是dp长度加1了，
        List<Pair> ls=new ArrayList<>();
        for(int i=0;i<startTime.length;i++){
            ls.add(new Pair(startTime[i],endTime[i],profit[i]));
        }
        Collections.sort(ls, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o1.end-o2.end;
            }
        });
        int rs=0;
        for(int i=1;i<dp.length;i++){
            dp[i]=Math.max(ls.get(i-1).profit+find3(ls,i-1,dp) ,dp[i-1]);//这里find3直接找出dp的最大值，可能是0，和以前写的不一样
        }
        return dp[dp.length-1];
    }
    int find3(List<Pair> ls,int index,int[] dp){
        int bound=ls.get(index).start;
        int b=0;
        int e=ls.size()-1;
        while (b<e){
            int m=(b+e)/2;
            if (ls.get(m).end<bound){
                b=m+1;
            }else {
                e=m;
            }
        }
        if (ls.get(b).end<=bound){
            return dp[b+1];//这里由于是dp比ls的size长一位所以比较恶心。
        }
        return dp[b];
    }

    class Pair{
        int start;
        int end;
        int profit;
        public Pair(int s,int e,int p){
            start=s;
            end=e;
            profit=p;
        }
    }


}