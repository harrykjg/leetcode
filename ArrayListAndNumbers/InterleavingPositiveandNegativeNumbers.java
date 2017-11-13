package ArrayListAndNumbers;


  //自己也大概想到了，就是2 pointer先把负数和正数inplace换到左边和右边，然后再换位置就可以了，但是要先判断一下
  //正数多还是负数多，多的那个放开头.最后交换的条件还不太好写
  //http://www.jiuzhang.com/solution/interleaving-positive-and-negative-numbers/ 他有了extra space
  public class InterleavingPositiveandNegativeNumbers {

    public static void main(String[] args){
      InterleavingPositiveandNegativeNumbers ip=new InterleavingPositiveandNegativeNumbers();
      int[] n={-13,-8,-12,-15,-14,35,7,-1,11,27,10,-7,-12,28,18};
      ip.rerange(n);
    }

    public void rerange(int[] A) {
      // write your code here
      boolean positiveMore=false;
      int count=0;
      for(int i=0;i<A.length;i++){
        if(A[i]>0){
          count++;
        }
      }
      if(count>=A.length-count){
        positiveMore=true;
      }
      int b=0;
      int e=A.length-1;
      while (b<e){
        if(A[b]>0){
          if(positiveMore){
            b++;
            continue;
          }else{
            int temp=A[b];
            A[b]=A[e];
            A[e]=temp;
            e--;
            continue;
          }
        }else {
          if(!positiveMore){
            b++;
            continue;
          }else{//如果当前b是负数并且正数多的话，把当前b扔到后面
            int temp=A[e];
            A[e]=A[b];
            A[b]=temp;
            e--;
          }
        }
      }
      //要举例子看，正负数相等，或者不等的,两数相等的话则b是从0开始换就对了，两数不等的话则b从1开始换就对
      if(positiveMore){
        if(count>A.length-count){
          b=1;
        }else {
          b=0;
        }
      }else {
        if(count<A.length-count){
          b=1;
        }else{
          b=0;
        }
      }
      e=A.length-1;
      while (b<e){
        int temp=A[b];
        A[b]=A[e];
        A[e]=temp;
        b+=2;
        e-=2;
      }
    }
  }
