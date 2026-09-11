package SomeInterviews.Confluent;

import java.io.BufferedReader;
import java.io.*;
import java.io.IOException;
import java.util.*;

public class TailNLines {
    public List<String> tailN(String[] lines, int n) {
        // 自己写就是反过来加n个line，然后再reverse。正确应该用queue，当quesue的size大于等于n时就poll，这样就不用reverse了
        Queue<String> q=new LinkedList<>();
        for (int i=0;i<lines.length;i++){
            if(q.size()==n){
                q.poll();
            }
            q.offer(lines[i]);
        }
        return new ArrayList<>(q);

    }
    //Follow-up 1：input 是 continuous stream 怎么办？
    //那还是用q就搞定了，和上面一样，就是input不是一个完整的array，而是steam一行一行读
    //followup2:Circular Buffer，gpt的答案，就是用一个固定的buffer，先写，写满之后回第一个元素开始覆盖，但是问题是覆盖到一半结束了，
    //那么你怎么读？那就要用一个next，然后next指向的就是下一个就是最老的，那么从最老的开始circular读n个就行了
    public List<String> tailStream(Iterable<String> stream, int n) {
        String[] buffer = new String[n];
        int count = 0;   // 总共读了多少行
        int next = 0;    // 下一次写入的位置
        for (String line : stream) {
            buffer[next] = line;
            // 到数组结尾后重新回到 0
            next = (next + 1) % n;
            count++;
        }
        int size = Math.min(count, n);
        List<String> res = new ArrayList<>(size);
        // 如果 buffer 已经满过：
        // next 正好指向当前最老的元素
        // 如果没满：
        // 最老元素就是 index 0
        int start = count >= n ? next : 0;
        for (int i = 0; i < size; i++) {
            int index = (start + i) % n;
            res.add(buffer[index]);
        }
        return res;
    }
    //实现类似打印文件最后 N 行的功能, 同时讨论一些 tradeoff 和优化, 比如用 buffer 还是用 file pointer offset 来做.
    // 后半部分偏理论, 给你一套 file API, 类似可以 read N bytes, 移动 file pointer +N 或 -N, 以及获取文件大小.
    // 需要你写 pseudocode 来实现这个功能. 另外还要求结果要 stream 到 stdout, 而不是一次性全部存到内存里. 结合n tails这题看
    public void tailN(RandomAccessFile file, int n) throws IOException {
        if (n <= 0) {
            return;
        }
        int BLOCK_SIZE = 4096; // 每次读 4KB
        byte[] buffer = new byte[BLOCK_SIZE];
        long fileSize = file.length();
        if (fileSize == 0) {
            return;
        }
        // pos 表示“下一块要从哪里开始读”
        // 一开始从文件末尾开始往前找
        long pos = fileSize;
        int newlineCount = 0;
        // 最后找到的：最后 n 行应该从哪个 byte 开始输出
        long startOffset = 0;
        boolean found = false;
        while (pos > 0 && !found) {
            /*
             * 假设：
             * fileSize = 10000
             * BLOCK_SIZE = 4096
             * 第一次：
             * pos = 10000
             * bytesToRead = 4096
             * 那我们应该读：
             * [5904, 9999]
             */
            int bytesToRead = (int) Math.min(BLOCK_SIZE, pos);
            // 往前移动一整个 block
            pos = pos - bytesToRead;
            /*
             * pointer 跳到这个 block 的开头
             * 例如：
             * seek(5904)
             */
            file.seek(pos);
            /*
             * 然后从 5904 往后读 4096 bytes
             * 存进 buffer
             */
            file.readFully(buffer, 0, bytesToRead);
            /*
             * 注意：
             * file.read 是往前读的，
             * 但我们要找“最后 n 行”，
             * 所以在 buffer 内部从后往前扫描。
             */
            for (int i = bytesToRead - 1; i >= 0; i--) {
                if (buffer[i] == '\n') {
                    newlineCount++;
                    /*
                     * 找到了足够多的 newline。
                     * 比如 tail -3，
                     * 我们要找到“第 3 行之前”的那个边界。
                     */
                    if (newlineCount == n + 1) {
                        /*
                         * buffer[i] 是那个换行符本身，
                         * 真正输出要从它后一个 byte 开始。
                         * 全局位置 = 当前 block 起点 pos+ buffer 内部位置 i +1
                         */
                        startOffset = pos + i + 1;
                        found = true;
                        break;
                    }
                }
            }
        }
        /*
         * 如果一直扫到文件开头，
         * newline 数都不够，
         * 说明整个文件行数 <= n。
         * 那就从 byte 0 开始输出整个文件。
         */
        if (!found) {
            startOffset = 0;
        }
        // -------------------------
        // 第二阶段：从 startOffset 正向输出
        // -------------------------
        file.seek(startOffset);
        while (true) {
            int len = file.read(buffer);
            if (len == -1) {
                break; // EOF
            }
            // 直接 stream 到 stdout
            System.out.write(buffer, 0, len);
        }
    }

    //写io的版本
    public static void tail(String fileName, int n) throws IOException {
        if (n <= 0) {
            return;
        }

        String[] buffer = new String[n];
        int count = 0;
        int next = 0;

        // 1. FileReader 负责读字符文件
        FileReader fileReader = new FileReader(fileName);

        // 2. BufferedReader 套在 FileReader 外面
        //    可以高效地一行一行读，filereader只能一个一个读
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String line;
        // 3. 逐行读取文件
        while ((line = bufferedReader.readLine()) != null) {
            // 写入 circular buffer
            buffer[next] = line;
            // 下一个位置
            next = (next + 1) % n;
            count++;
        }
        // 4. 读完以后关闭
        bufferedReader.close();
        fileReader.close();
        // 5. 计算实际输出多少行
        int size = Math.min(count, n);
        // 如果 buffer 满过，next 指向最老的一行
        // 如果没满，就从 0 开始
        int start = count >= n ? next : 0;
        // 6. 直接输出到 stdout
        for (int i = 0; i < size; i++) {
            int index = (start + i) % n;
            System.out.println(buffer[index]);
        }
    }

    public static void main(String[] args) {
        try {
            // HackerRank 里假设当前目录有 logfile.txt
            String fileName = "logfile.txt";
            // 打印最后 3 行
            tail(fileName, 3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
