package SomeInterviews.Confluent;

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
}
