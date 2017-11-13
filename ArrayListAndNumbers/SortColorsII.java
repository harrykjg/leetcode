package ArrayListAndNumbers;

public class SortColorsII {

  public static void main(String[] args){
    SortColorsII sortColorsII=new SortColorsII();
    int[] n={3,2,2,1,4};
    sortColorsII.sortColors2(n,4);
  }

  //用quick sort也行,还写不出来
  //http://www.cnblogs.com/yuzhangcmu/p/4177326.html  解法2没仔细看，不好理解
  //http://www.jianshu.com/p/9bad070d7802
  public void sortColors2(int[] colors, int k) {

    if (colors.length <= 1) {
      return;
    }
    quickSort(colors, 0, colors.length - 1);
  }

  void quickSort(int[] colors, int b, int e) {
    if (b >= e) {
      return;
    }
    int p = partition(colors, b, e, colors[b]);
    quickSort(colors, b, p - 1);
    quickSort(colors, p+1, e);
  }
  int partition(int[] colors,int b,int e,int piviot){
    int temp=piviot;
    while (b<e){
      while (b<e&&colors[e]>=piviot){
        e--;
      }
      if(b<e){
        colors[b]=colors[e];
      }
      while (b<e&&colors[b]<=piviot){
        b++;
      }
      if(b<e){
        colors[e]=colors[b];
        e--;
      }
    }
    colors[b]=temp;
    return b;
  }

}
