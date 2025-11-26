package 灵神.sidingWindow;

public class MinimumRecolorstoGetKConsecutiveBlackBlocks2379 {

//定长，一次过
    public int minimumRecolors(String blocks, int k) {

        int rs=Integer.MAX_VALUE;
        int black=0;

        int b=0;
        for(int i=0;i<blocks.length();i++){
            if(blocks.charAt(i)=='B'){
                black++;
            }
            if(i-b+1>=k){
                int white=k-black;
                rs=Math.min(rs,white);
                if (blocks.charAt(b)=='B'){
                    black--;
                }
                b++;
            }
        }
        return rs;
    }
}