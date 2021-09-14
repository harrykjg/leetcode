package binarysearch;

public class FindSmallestLetterGreaterThanTarget {
    //8/12/2021 暴力法一个循环也能过，写个二分法的
    public char nextGreatestLetter(char[] letters, char target) {
        int b=0;
        int e=letters.length-1;
        while (b+1<e){
            int m=b+(e-b)/2;
            if(letters[m]>target){
                e=m;
            }else{
                b=m;
            }
        }
        if(letters[b]>target){
            return letters[b];
        }
        if (letters[e]>target){
            return letters[e];
        }
        return letters[0];
    }
}
