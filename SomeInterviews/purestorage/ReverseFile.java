package SomeInterviews.purestorage;

public class ReverseFile {
    /*
    给一个文件，reverse里面内容char by char。
Example:
Input File Content: ABCDEFG
Output File Content: GFEDCBA
读写同一个文件。文件很大，需要读chunk by chunk而不能全读出来, reverse再写回去
Follow up Question：
如果在reverse或者写入过程中system crash，那么怎么保证内容正确重新正确写回去。
     */
    //基础解法就是前后两个pointer读然后reverse，但是由于是读file所以性能不好，因此要前后先读一段进内存，再reverse这一段，再把左边的那段写到右边（vice versa）,
    //因此避免了多次io，变成内存的操作
    /*
    void reverseFile(RandomAccessFile file, int chunkSize) throws IOException {
    long n = file.length();
    long left = 0;
    long right = n;
    while (left < right) {
        int leftSize = (int)Math.min(chunkSize, right - left);
        int rightSize = (int)Math.min(chunkSize, right - left - leftSize);//保证左边取完整，右边可以不取或者取一部分，如果说左右长度不一样也没关系，
        //因为他是左边和右边格子reverse，再把右边先写入，再写入左边
        if (rightSize <= 0) {
            // middle chunk: reverse itself
            byte[] mid = new byte[leftSize];
            file.seek(left);
            file.readFully(mid);
            reverse(mid, 0, mid.length - 1);
            file.seek(left);
            file.write(mid);
            break;
        }
        right -= rightSize;
        byte[] leftBuf = new byte[leftSize];
        byte[] rightBuf = new byte[rightSize];
        file.seek(left);
        file.readFully(leftBuf);
        file.seek(right);
        file.readFully(rightBuf);
        reverse(leftBuf, 0, leftBuf.length - 1);
        reverse(rightBuf, 0, rightBuf.length - 1);
        file.seek(left);
        file.write(rightBuf);//先写右边再写左边
        file.seek(right);
        file.write(leftBuf);
        left += leftSize;
    }
}

void reverse(byte[] arr, int l, int r) {
    while (l < r) {
        byte t = arr[l];
        arr[l] = arr[r];
        arr[r] = t;
        l++;
        r--;
    }
}
     */
    //follow up的话要防止crash那就是要写到新的file里，那就不是in place了，做法也基本一样
}
