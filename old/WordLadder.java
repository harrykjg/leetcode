import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
//http://blog.csdn.net/yutianzuijin/article/details/12887747 思路看他
//http://blog.csdn.net/linhuanmars/article/details/23029973  代码看他
//
public class WordLadder {
	
	public static void main(String[] args) {
		WordLadder wd=new WordLadder();
		HashSet<String> set=new HashSet<String>();
		set.add("hit");
		set.add("hik");
		set.add("hot");
		set.add("dot");
		set.add("dog");
		set.add("lot");
		set.add("log");
		System.out.println(wd.ladderLength3("hot", "cog", set));
		
	}
	
 //这个是知道用bfs后自己写的，居然一次就对，但是是超时的。
	//思路就是用一个queue，找出于start单词差一个字母的单词，放进q里，然后用bfs，取出q里的单词，
	//判断是否和end只差一个，否的话就再找出于他查一个的单词，放进q里，and so on。由于是bfs，所以
	//一旦遇到从q里取出的单词和end只差1个的话，这个路径就是最短路径了
	public int ladderLength(String start, String end, HashSet<String> dict) {
		if(start.equals(end)){
			return 1;
		}
		if(diff(start,end)){
			return 2;
		}
		//要把set转成数组，否则hashset没法用get（i）方法得到指定的元素，只能用iterator，但这样
		//得出的是无序的
		String[] a=(String[]) dict.toArray(new String[0]);//注意这里的toarray的方法要这样写
		return go(start,end,a,2);	
	}
	public int go(String start,String end,String[] a,int cur){
		LinkedList<String> q=new LinkedList<String>();
		HashMap<String,Integer> memo=new HashMap<String,Integer>();//用来记录当前单词用过没
		for(int i=0;i<a.length;i++){
			memo.put(a[i], 0);
		}
		boolean flag=false;
		int count1=0;//count1用来记录当前层的数量，count2用来记录下一层的数量，flag用来记录该层
		int count2=0;//的步数加了没，比如说当层有3个单词要检测，但步数只能加1，所以用个flag来判断
	//如果flag为false即没加，则加1，falg变成true，否则不加，而flag在到了下一层的时候更新为false；
		for(int i=0;i<a.length;i++){
			if(memo.get(a[i])==0&&diff(start,a[i])){
				memo.put(a[i], 1);
				q.add(a[i]);
				count1++;
			}
		}
		
		while(!q.isEmpty()){
			String temp=q.removeFirst();
			count1--;
			if(flag==false){
				cur++;
				flag=true;
			}
			if(diff(temp,end)){
				return cur;
			}
			for(int i=0;i<a.length;i++){
				if(memo.get(a[i])==0&&diff(temp,a[i])){
					count2++;
					memo.put(a[i], 1);
					q.add(a[i]);
				}
			}
			if(count1==0){//当前层遍历完了，就换成下一层的数量，flag也变成false
				count1=count2;
				count2=0;
				flag=false;
			}
		}
		return cur;
		
	}

	private boolean diff(String a, String b) {  
        int diff = 0;  
        for (int i = 0; i < a.length(); i++) {  
            if (a.charAt(i) != b.charAt(i)) {  
                diff++;  
                if (diff >= 2)  
                    return false;  
            }  
        }  
        return diff == 1;  
    }  
//这个就不超时了，但实现方法基本一样，不同就是：前一个法是把当前start单词用diffbyone方法，检测
	//dict里的所有单词，如果是diffbyone那就加进q里，而这个方法是把当前start，每个字母替换成
	//26个字母试一下，看dict时候有变换一位之后的单词，有的话就加进q，居然这样还比较快
	public int ladderLength2(String start, String end, HashSet<String> dict) {
		if(start.equals(end)){
			return 1;
		}
		LinkedList<String> q=new LinkedList<String>();
		HashSet<String> memo=new HashSet<String>();
		boolean flag=false;
		int count1=0;
		int count2=0;
		int cur=1;
		q.add(start);
		memo.add(start);
		count1++;
		
		while(!q.isEmpty()){
			String temp=q.poll();
			count1--;
			if(flag==false){
				cur++;
				flag=true;
			}
			
			for(int i=0;i<temp.length();i++){
				char[] c=temp.toCharArray();
				for(char j='a';j<'z';j++){
					if(c[i]==j){
						continue;
					}
					c[i]=j;
					String temp2=new String(c);
					if(temp2.equals(end)){//注意是用equals方法
						return cur;
					}
					if(dict.contains(temp2)&&memo.add(temp2)){
						q.add(temp2);
						count2++;
						memo.add(temp2);
					}
				}
			}
			if(count1==0){
				count1=count2;
				count2=0;
				flag=false;
			}
		}
		return 0;
	}
	//第二次写，思路是有的，就是忘了要两个count记录当层和下层的数字，当层都遍历完了之后步数才加1
	public int ladderLength3(String start, String end, HashSet<String> dict) {
		if(start.equals(end)){
			return 1;
		}
		int rs=1;
		LinkedList<String> q=new LinkedList<String>();
		HashSet<String> memo=new HashSet<String>();
		int count1=1;
		int count2=0;
		q.add(start);
		while(!q.isEmpty()){
			String temp=q.poll();
			count1--;
			
			char[] ch=temp.toCharArray();
			for(int i=0;i<ch.length;i++){
				for(char c='a';c<='z';c++){
					ch[i]=c;
					String s=new String(ch);
					if(s.equals(end)){
						return rs+1;
					}
					if(dict.contains(s)&&memo.add(s)){
						q.add(s);
						count2++;
					}
				}
				ch[i]=temp.charAt(i);
			}
			if(count1==0){
				rs++;
				count1=count2;
				count2=0;
			}
			
			
		}
		return 0;
	}
	//30/9 思路都忘了，原来是bfs,还忘了要个memo存遍历过的点
	public int ladderLength4(String start, String end, HashSet<String> dict) {
		if(start==null||end==null||dict==null){
			return 0;
		}
		if(start.equals(end)){
			return 1;
		}
		LinkedList<String> ls=new LinkedList<String>();
		Set<String> set=new HashSet<String>();
		set.add(start);
		ls.add(start);
		int count1=1;
		int count2=0;
		int step=1;
		while(!ls.isEmpty()){
			String temp=ls.poll();
			count1--;
//			if(count1==0){ //开始这个step++放在这，不行的，会错的
//				step++;
//			}
			char[] ch=temp.toCharArray();
			for(int i=0;i<ch.length;i++){
				ch=temp.toCharArray();
				for(char c='a';c<='z';c++){
					ch[i]=c;
					String temp2=new String(ch);
					if(temp2.equals(end)){
						return step+1;
					}
					if(dict.contains(temp2)&&set.add(temp2)){
						ls.add(temp2);
						count2++;
					}
				}
			}
			if(count1==0){
				step++;
				count1=count2;
				count2=0;
			}
		}
		return 0;
		
	}
	
}
