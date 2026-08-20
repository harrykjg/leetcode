package SomeInterviews.Confluent;

public class WildcardMatching {
    /*
    From an anonymous candidate: You are more recommended to use two pointers instead of dynamic programming to solve this problem during the interview

(This qustion is a variation of a LeetCode question 44. Wildcard Matching.)

Given an input string s and a pattern p, implement wildcard pattern matching with support for '*' where:

'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).
Constraints:
0 <= s.length, p.length <= 2000
s contains only lowercase English letters.
p contains only lowercase English letters and '*' characters.
Example 1:

Input: s = "aa", p = "a*"
Output: true
Explanation: The pattern "a*" matches the entire string "aa" by replacing "*" with "a".

Example 2:
Input: s = "ceebbccac", p = "c*b*a"
Output: false
Example 3:

Input: s = "abcde", p = "*"
Output: true

Hint 1
Avoid trying to expand every '*' immediately; instead, record its position and defer the decision until a mismatch occurs.

Hint 2
When a mismatch happens, consider reverting both pointers to the last recorded '*' position and advancing the input pointer by one to consume another character.

Hint 3
Maintain four pointers or indices: one for each string, plus two auxiliary variables to track the last '*' location and its corresponding matched index in the input.
     */
//gpt写的用dp
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        // empty string matches empty pattern
        dp[0][0] = true;
        // s 是 empty 的时候，
        // pattern 只有全是 * 才可能匹配
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 1];
            }
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                char sc = s.charAt(i - 1);
                char pc = p.charAt(j - 1);
                if (pc == sc) {
                    // 当前字符一样，看前面的
                    dp[i][j] = dp[i - 1][j - 1];
                } else if (pc == '*') {
                    // 1. * 匹配空
                    // 2. * 吃掉当前 s 字符
                    dp[i][j] =
                            dp[i][j - 1] || dp[i - 1][j];
                }
            }
        }
        return dp[m][n];
    }

}
