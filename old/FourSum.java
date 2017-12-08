import java.util.ArrayList;
import java.util.Arrays;

//第一种是brute froce法，会time limit exceed

public class FourSum {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		FourSum fs = new FourSum();
		// int[]
		// num={-497,-494,-484,-477,-453,-453,-444,-442,-428,-420,-401,-393,-392,-381,-357,-357,
		// -327,-323,-306,-285,-284,-263,-262,-254,-243,-234,-208,-170,-166,-162,-158,-136,
		// -133,-130,-119,-114,-101,-100,-86,-66,-65,-6,1,3,4,11,69,77,78,107,108,108,121,
		// 123,136,137,151,153,155,166,170,175,179,211,230,251,255,266,288,306,308,310
		// ,314,321,322,331,333,334,347,349,356,357,360,361,361,367,375,378,387,387,408,414,
		// 421,435,439,440,441,470,492};

		int[] num = {-1,0,-5,-2,-2,-4,0,1,-2};

		ArrayList<ArrayList<Integer>> al = new ArrayList<ArrayList<Integer>>();
		al = fs.fourSum3(num, -9);
		for (ArrayList<Integer> a : al) {
			for (int i : a) {
				System.out.print(i);

			}
			System.out.println();
		}

	}

	ArrayList<ArrayList<Integer>> al = new ArrayList<ArrayList<Integer>>();

	// public ArrayList<ArrayList<Integer>> fourSum(int[] num,int target){
	//
	// if(num.length<4){
	// return al;
	// }
	//
	// Arrays.sort(num);
	// for(int i=0;i<num.length-3;i++){
	// ArrayList<Integer> a=new ArrayList<Integer>();
	// a.add(num[i]);
	// find(i+1,target,num[i],1,a,num);
	//
	// }
	// return al;
	//
	// }
	//
	//
	// public void find(int begin,int target,int cursum,int
	// aldy,ArrayList<Integer> a,int[] num){
	// if(aldy==4){
	// return;
	// }
	//
	// for(int i=begin;i<num.length-3+aldy;i++){
	// ArrayList<Integer> a2=new ArrayList<Integer>();
	// a2.addAll(a);
	// a2.add(num[i]);
	// int temp=cursum+num[i];
	// // if(temp>=target&&aldy+1<4){
	// // break;
	// // }
	// if(temp==target&&aldy+1==4){
	//
	//
	// if(!al.contains(a2)){
	// al.add(a2);
	// return;
	// }
	// }
	// find(i+1,target,temp,aldy+1,a2,num);
	//
	//
	//
	// }
	// }

	public ArrayList<ArrayList<Integer>> fourSum(int[] num, int target) {

		if (num.length < 4) {
			return al;
		}
		Arrays.sort(num);
		for (int i = 0; i < num.length - 3; i++) {
			if (i != 0 && num[i] == num[i - 1]) {  
                continue;  
            } 
			for (int j = i + 1; j < num.length - 2; j++) {

				
				if (j!=i+1&&num[j] == num[j - 1]) { // 加了这个后leetcode运行宽了200ms左右
					continue;
				}
				find(num, target, num[i]+num[j], i, j);
			}
		}

		return al;

	}

	public void find(int[] num, int target, int cursum, int i, int j) {

		int begin=j+1;
		int end=num.length-1;
		
		while(begin<end&&begin<num.length&&end>=0){
			if (cursum + num[end] + num[begin] == target) {
				ArrayList<Integer> a = new ArrayList<Integer>();
				a.add(num[i]);
				a.add(num[j]);
				a.add(num[begin]);
				a.add(num[end]);
				if (!al.contains(a)) {
					al.add(a);
				
				}
				end--;//f减小了，那么b必然要++才有可能再找到cursum + num[f] + num[begin] == 0
				begin++;
				//这两个while就是为了跳过相邻的相同的数字，没有的话leetcode会 time exceed
				while(end>begin&&num[begin]==num[begin-1]){
					begin++;      
				}
				while(end>begin&&num[end]==num[end+1]){
					end--;
				}
			}
			else if( num[end] + num[begin]+cursum >target){
				end--;
				while(begin>end&&num[end]==num[end+1]){//这个两个while是第二次做的时候
					end--;//发现应该加的，加了之后那就肯定不会产生重复数组了，因此前面那个
				}         //if (!al.contains(a))可以不写也对了
			}
			else{
				begin++;
				while(begin>end&&num[begin]==num[begin-1]){
					begin++;
				}
			}
		}
	}
	//第二次写，还是会忘记略去重复的数字，会超时，而且超的比较多.关键是程序都错了。。
	//这个go方法就不应该是递归，的就是个while循环解决。其他细节也错的蛮多的
	//最后可以了，但是运行时间还比第一次的长100ms左右.但是不一定
	public ArrayList<ArrayList<Integer>> fourSum2(int[] num, int target) {
		ArrayList<ArrayList<Integer>> al=new ArrayList<ArrayList<Integer>>();
		if(num.length<4){
			return al;
		}
		Arrays.sort(num);
		ArrayList<Integer> a=new ArrayList<Integer>();
		for(int i=0;i<num.length-3;i++){
			if(i>0&&num[i]==num[i-1]){
				continue;
			}
			a.add(num[i]);
			for(int j=i+1;j<num.length-2;j++){
				if(j>i+1&&num[j]==num[j-1]){
					continue;
				}
				a.add(num[j]);
				go(num,target,num[i],num[j],j+1,num.length-1,a,al);
				a.remove(a.size()-1);
			}
			a.remove(a.size()-1);
		}
		return al;
	}
	private void go(int[]num,int target,int cur1,int cur2,int begin,int end,
			ArrayList<Integer> a,ArrayList<ArrayList<Integer>> al){
		
		while(begin<end){
			int cursum=cur1+cur2+num[begin]+num[end];
			if(cursum==target){
				a.add(num[begin]);
				a.add(num[end]);
				al.add(new ArrayList<Integer>(a));
				a.remove(a.size()-1);
				a.remove(a.size()-1);
				while(end>begin&&num[begin]==num[begin+1]){
					begin++;
				}
				begin++;
				while(end>begin&&num[end]==num[end-1]){
					end--;
				}
				end--;
				
			}
			else if(cursum>target){
				      
				
				while(end>begin&&num[end]==num[end-1]){
					end--;
				}
				end--; 
//				go(num,target,cur1,cur2,begin,end,a,al); 之前写的是递归了，根本就不应该是递归
				
			}else{
				while(end>begin&&num[begin]==num[begin+1]){//注意这里和第一题就不同了，那里是-1
					begin++;        //因为他begin先加1了，我这里是begin在这个while之后才加1
				}
				begin++;
//				go(num,target,cur1,cur2,begin+1,end,a,al);
				
			}
		}
	}
	//第三轮，思路是对的，就是犯了一些小错，还是那个相邻相同数字略过那里的边界条件很容易出问题
	public ArrayList<ArrayList<Integer>> fourSum3(int[] num, int target) {
		ArrayList<ArrayList<Integer>> al=new ArrayList<ArrayList<Integer>>();
		if(num.length<4){
			return al;
		}
		ArrayList<Integer> a=new ArrayList<Integer>();
		Arrays.sort(num);
		for(int i=0;i<num.length-3;i++){
			if(i>0&&num[i]==num[i-1]){
				continue;
			}
			a.add(num[i]);
			go2(a,i+1,num[i],num,al,target);
			a.remove(a.size()-1);
		}
		return al;
	}
	private void go2(ArrayList<Integer> a,int b,int cur,int[]num,
			ArrayList<ArrayList<Integer>> al,int target){
		
		for(int i=b;i<num.length-2;i++){
			if(i>b&&num[i]==num[i-1]){
				continue;
			}
			a.add(num[i]);
			
			int cur2=cur+num[i];
//			if(cur2>target){    //这个if不应该有，开始想着如果前两个数加起来比target大，那么后面
//				a.remove(a.size()-1);//的数都是比前两个大的，所以4个加起来不可能等于target了，所以
//				break;              //break，其实不是，后两个数虽然会比前两个大，但是有可能后两个
//			}                  //数仍然都是负数，所以4个加起来还是有可能会等于target的
			int left=i+1;
			int right=num.length-1;
			while(left<right){
				if(cur2+num[left]+num[right]==target){
					a.add(num[left]);
					a.add(num[right]);
					al.add(new ArrayList<Integer>(a));
					a.remove(a.size()-1);
					a.remove(a.size()-1);
					left++;
					right--;
					//这里要记好了，就用left<right这个前提条件就不会错。我这里是先left++和right--了，
					//想一下，这个left已经右移一位了，所以要看之前的left那个点与它的右边一位是否
					//相同，即看left没有加1之前的位置和他的右边一个位置是否相同，写出来应该是看left-1
					//和left位置的，right同样，开始的时候就写错了
					while(left<right&&num[left]==num[left-1]){
						left++;
					}
					while(left<right&&num[right]==num[right+1]){
						right--;
					}
					continue;
				}
				if(cur2+num[left]+num[right]>target){
					right--;
					while(left<right&&num[right]==num[right+1]){
						right--;
					}
					continue;
				}
				if(cur2+num[left]+num[right]<target){
					left++;
					while(left<right&&num[left-1]==num[left]){
						left++;
					}
					continue;
				}
			}
			a.remove(a.size()-1);
		}
	}

}
