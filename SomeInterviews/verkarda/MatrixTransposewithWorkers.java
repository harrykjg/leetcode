package SomeInterviews.verkarda;

public class MatrixTransposewithWorkers {
    /*
    (This question is a variation of the LeetCode question 867. Transpose Matrix. If you haven't completed that question yet, it is recommended to solve it first.)
Given a 2D integer array matrix, return the transpose of matrix. The transpose of a matrix is the matrix flipped over its main diagonal, switching the matrix's row and column indices.
You are given a square integer matrix of size n × n and a positive integer numThreads. You must using up to numThreads worker threads in parallel, return the transpose of matrix.
If numThreads exceeds the number of upper-triangle rows, the implementation may use fewer active workers.
Constraints:
1 ≤ n ≤ 103
matrix.length == n
matrix[i].length == n
-109 ≤ matrix[i][j] ≤ 109
1 ≤ numThreads ≤ 103
Example 1:
Input: matrix = [[1, 2, 3], [4, 5, 6], [7, 8, 9]], numThreads = 2
Output: [[1, 4, 7], [2, 5, 8], [3, 6, 9]]
Explanation: The matrix is flipped over its main diagonal: each element at position (i, j) moves to position (j, i). The result is [[1, 4, 7], [2, 5, 8], [3, 6, 9]].
Example 2:
Input: matrix = [[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12], [13, 14, 15, 16]], numThreads = 3
Output: [[1, 5, 9, 13], [2, 6, 10, 14], [3, 7, 11, 15], [4, 8, 12, 16]]
Example 3:
Input: matrix = [[1, 2, 3, 4, 5], [6, 7, 8, 9, 10], [11, 12, 13, 14, 15], [16, 17, 18, 19, 20], [21, 22, 23, 24, 25]], numThreads = 2
Output: [[1, 6, 11, 16, 21], [2, 7, 12, 17, 22], [3, 8, 13, 18, 23], [4, 9, 14, 19, 24], [5, 10, 15, 20, 25]]
     */
    //和867的区别是这里是正方形，那么就可以inplace 交换吧？看图找规律可知只需要换col>row的
    public int[][] transpose(int[][] matrix, int numThreads) {
        // TODO: Implement transpose logic
        for(int i=0;i<matrix.length;i++){
            for (int j=0;j<matrix[0].length;j++){
                if(j>i){
                    int temp=matrix[j][i];
                    matrix[j][i]=matrix[i][j];
                    matrix[i][j]=temp;
                }
            }
        }
        return matrix;
    }
    //这个是答案依据hack2hire的答案叫gpt改写的
    public int[][] transpose2(int[][] matrix, int numThreads) {
        int n = matrix.length;
        if (n <= 1) {
            return matrix;
        }
        // 真正需要处理的是 upper triangle 的 row: 0 ~ n-2
        // 所以最多只需要 n-1 个 worker
        int workers = Math.min(numThreads, n - 1);
        Thread[] threads = new Thread[workers];
        for (int t = 0; t < workers; t++) {
            final int tid = t; // 当前线程编号
            threads[t] = new Thread(new Runnable() {
                @Override
                public void run() {
                    /*
                     * Round Robin 分配： 假设 workers = 3
                     * thread 0: row 0, 3, 6, 9...
                     * thread 1: row 1, 4, 7, 10...
                     * thread 2: row 2, 5, 8, 11...
                     * 所以从自己的 tid 开始，
                     * 每次跳 workers 个 row
                     */
                    for (int i = tid; i < n - 1; i += workers) {
                        // 只处理主对角线上方 (i, j) 和 (j, i) 交换
                        for (int j = i + 1; j < n; j++) {
                            int temp = matrix[i][j];
                            matrix[i][j] = matrix[j][i];
                            matrix[j][i] = temp;
                        }
                    }
                }
            });
            threads[t].start();
        }
        // 等所有 worker 都做完
        for (Thread thread : threads) {
            try {
                thread.join();//就是一个一个的等
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException(e);
            }
        }
        return matrix;
    }
}
