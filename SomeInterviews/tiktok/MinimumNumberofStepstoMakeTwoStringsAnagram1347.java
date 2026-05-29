package SomeInterviews.tiktok;

public class MinimumNumberofStepstoMakeTwoStringsAnagram1347 {
    //还挺巧妙的，看这个答案，因为题目是让replace
    //https://leetcode.com/problems/minimum-number-of-steps-to-make-two-strings-anagram/solutions/4555349/9653easy-solutionwith-explanation-by-mra-hc61/
    public int minSteps(String s, String t) {
        int[] countS = new int[26];
        int[] countT = new int[26];

        for (char ch : s.toCharArray()) {
            countS[ch - 'a']++;
        }

        for (char ch : t.toCharArray()) {
            countT[ch - 'a']++;
        }

        int steps = 0;
        for (int i = 0; i < 26; i++) {
            steps += Math.abs(countS[i] - countT[i]);
        }

        return steps / 2;
    }
}
