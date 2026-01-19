package 灵神.贪心;

public class ScoreAfterFlippingMatrix861 {
    static void main() {

    }
    //1/1/2026 主要是要看出来第一列肯定是要全部弄到1的，就算是0，1，1，1这样也要把第一个换成1，因为二进制第一个换成1肯定比后面111大
    //那到底是怎么翻第一列呢，是翻第一列，再看每一行翻？还是通过翻行达到第一列全是1的目的？
    //看答案下面有个写的就是先通过翻行把第一列全弄成1，再从第二列开始看1的数量是否大于0来决定翻不翻
    //https://leetcode.cn/problems/score-after-flipping-matrix/solutions/511825/fan-zhuan-ju-zhen-hou-de-de-fen-by-leetc-cxma/
    public int matrixScore(int[][] grid) {

    }
}
