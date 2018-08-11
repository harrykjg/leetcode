/**
 * Created by yufengzhu on 8/8/18.
 */
public class SentenceScreenFitting {
    public static void main(String[] args){
        SentenceScreenFitting ss=new SentenceScreenFitting();
        String[] words={"I", "had", "apple", "pie"};
        System.out.print(ss.wordsTyping(words,4,5));
    }
    //瞎写的写的不顺，暴力法超时了,这里就没有必要保留了，别的写法都很难想的
    //https://www.cnblogs.com/EdwardLiu/p/6196187.html  dp做法，还是很难写
    //https://blog.csdn.net/xinqrs01/article/details/54628693  这以下的做法都看不懂
    //https://blog.csdn.net/u013325815/article/details/53147022
    //https://segmentfault.com/a/1190000009488678
    //https://www.jianshu.com/p/d8f0ad71c8a7  另一种做法
    public int wordsTyping(String[] sentence, int rows, int cols) {
        //思路是，暴力法的话就是每一行去看这个个sentence里的每个word去填当前行，这个行为要进行rows次，所以会有重复，重复的地方在于：（见链接的解释）。因此可以记录下去填一行时，拿每一个sentence里的word
        //作为开头单词的情况下，这一行可以填几个sentence，并且下一行由哪个word开始，或者填不了的话，下一行应该是哪个word开始
        int[] nextWord=new int[sentence.length];
        int[] counts=new int[sentence.length];
        for(int i=0;i<sentence.length;i++){
            int index=i;
            int cur=0;
            int count=0;

            while (cur+sentence[index].length()<=cols){//这里while不是这样写的话就难写了，因为我们其实是想看下一个单词能不能加进来，所以写在while里

                cur+=sentence[index].length()+1;//加上空格，因为只要想append下一个单词的话，就肯定要加个空格，但是上面while判断的能不能加进下一个单词的时候就不用加个空格了
                index++;

                if(index==sentence.length){//这里index只要到达最后一个，count就要加一了，开始以为是要到sentence遍历一轮之后才加1，其实不是，counts[i]的意义其实是，到了第i行，见到了几个sentence最后一个
                    index=0;            //word，见到几次就说明i行前可以装下几个sentence
                    count++;
                }

            }

            nextWord[i]=index;
            counts[i]=count;
        }

        int next=0;
        int r=0;
        int rs=0;
        while (r<rows){
            rs+=counts[next];
            next=nextWord[next];
            r++;
        }
        return rs;

    }
}
