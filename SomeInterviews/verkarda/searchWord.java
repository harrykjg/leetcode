package SomeInterviews.verkarda;

public class searchWord {
    /*
    matrix = [ 'a', 'a', 'a', 'a', ] s = 'aa'
    要求在matrix里找到s可以被match的次数，可以从任何位置开始。只需要考虑三个方向：左右，上下，右下。 所以答案是 5 。
     */
    //应该意思是只能向右/下/右下吧，一旦选中就不能变方向,gpt的答案
    public int countMatches(char[][] matrix, String s) {
        int m = matrix.length;
        int n = matrix[0].length;
        int count = 0;
        // 只考虑：右、下、右下
        int[][] dirs = {
                {0, 1},
                {1, 0},
                {1, 1}
        };
        // 每个位置都可以作为起点
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                for (int[] dir : dirs) {
                    if (match(matrix, s, i, j, dir[0], dir[1])) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
    private boolean match(char[][] matrix, String s, int row, int col, int dr, int dc) {
        int m = matrix.length;
        int n = matrix[0].length;
        for (int k = 0; k < s.length(); k++) {
            int r = row + k * dr;
            int c = col + k * dc;
            // 越界或者字符不一样，就不能 match
            if (r < 0 || r >= m ||
                    c < 0 || c >= n ||
                    matrix[r][c] != s.charAt(k)) {
                return false;
            }
        }
        return true;
    }

    //3个方向可以拐弯，则是dfs但是不需要visited因为不会往回跑，注意dfs是返回int。每次找到就返回1.
    public int countMatches2(char[][] matrix, String s) {
        int m = matrix.length;
        int n = matrix[0].length;
        int count = 0;
        // 每个位置都可以作为起点
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                count += dfs(matrix, s, i, j, 0);
            }
        }
        return count;
    }
    private int dfs(char[][] matrix, String s, int row, int col, int index) {
        int m = matrix.length;
        int n = matrix[0].length;
        // 越界或者当前字符不匹配
        if (row >= m || col >= n ||
                matrix[row][col] != s.charAt(index)) {
            return 0;
        }
        // 整个字符串匹配成功
        if (index == s.length() - 1) {
            return 1;
        }
        int count = 0;
        // 下一步可以重新选择方向，所以可以转弯
        count += dfs(matrix, s, row,     col + 1, index + 1); // 右
        count += dfs(matrix, s, row + 1, col,     index + 1); // 下
        count += dfs(matrix, s, row + 1, col + 1, index + 1); // 右下

        return count;
    }
}
