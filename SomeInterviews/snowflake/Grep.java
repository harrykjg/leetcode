package SomeInterviews.snowflake;

import java.util.ArrayList;
import java.util.List;

public class Grep {
    //https://leetcode.com/discuss/post/1744936/google-final-round-l4-by-anonymous_user-p5y9/
    //gpt代码
    public List<String> grep(String[] lines, String target, int linesAround) {
        List<String> res = new ArrayList<>();
        boolean[] printed = new boolean[lines.length];//防止重复

        for (int i = 0; i < lines.length; i++) {
            if (lines[i].contains(target)) {
                int start = Math.max(0, i - linesAround);
                int end = Math.min(lines.length - 1, i + linesAround);

                for (int j = start; j <= end; j++) {
                    if (!printed[j]) {
                        res.add(lines[j]);
                        printed[j] = true;
                    }
                }
            }
        }

        return res;
    }
    //Followup 1: 支持流处理 Followup2: 优化复杂度 Followup3: 多线程
    //不太清楚题意，不写了

}
