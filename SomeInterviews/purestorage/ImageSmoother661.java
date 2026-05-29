package SomeInterviews.purestorage;

public class ImageSmoother661 {
    public int[][] imageSmoother(int[][] img) {
        int[][] rs=new int[img.length][img[0].length];
        int[] dirx={-1,-1,0,1,1,1,0,-1};
        int[] diry={0,1,1,1,0,-1,-1,-1};
        for (int i=0;i<img.length;i++){
            for (int j=0;j<img[0].length;j++){
                int count=1;
                int local=img[i][j];
                for (int k=0;k<dirx.length;k++){
                    int r=dirx[k]+i;
                    int c=diry[k]+j;
                    if(r>=0&&r<img.length&&c>=0&&c<img[0].length){
                        count++;
                        local+=img[r][c];
                    }
                }
                rs[i][j]=local/count;
            }
        }
        return rs;
    }
    //gpt说的in place的解法,就是题目说的值的范围是0-255，因此可以把旧的值存在右边8位，新的值存在左边8位，nb
    //int newVal = sum / count;
    //img[i][j] |= newVal << 8;
    /*
    class Solution {
    public int[][] imageSmoother(int[][] img) {
        int m = img.length;
        int n = img[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int sum = 0;
                int count = 0;

                for (int x = i - 1; x <= i + 1; x++) {
                    for (int y = j - 1; y <= j + 1; y++) {
                        if (x >= 0 && x < m && y >= 0 && y < n) {
                            sum += img[x][y] & 255; // 只取旧值
                            count++;
                        }
                    }
                }

                int newVal = sum / count;
                img[i][j] |= newVal << 8; // 高位存新值，低位保留旧值
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                img[i][j] >>= 8; // 取出新值
            }
        }

        return img;
    }
}
     */
}
