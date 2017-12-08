import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
//http://blog.csdn.net/yutianzuijin/article/details/12887747 ˼·����
//http://blog.csdn.net/linhuanmars/article/details/23029973  ���뿴��
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
	
 //�����֪����bfs���Լ�д�ģ���Ȼһ�ξͶԣ������ǳ�ʱ�ġ�
	//˼·������һ��queue���ҳ���start���ʲ�һ����ĸ�ĵ��ʣ��Ž�q�Ȼ����bfs��ȡ��q��ĵ��ʣ�
	//�ж��Ƿ��endֻ��һ������Ļ������ҳ�������һ���ĵ��ʣ��Ž�q�and so on��������bfs������
	//һ��������q��ȡ���ĵ��ʺ�endֻ��1���Ļ������·���������·����
	public int ladderLength(String start, String end, HashSet<String> dict) {
		if(start.equals(end)){
			return 1;
		}
		if(diff(start,end)){
			return 2;
		}
		//Ҫ��setת�����飬����hashsetû����get��i�������õ�ָ����Ԫ�أ�ֻ����iterator��������
		//�ó����������
		String[] a=(String[]) dict.toArray(new String[0]);//ע�������toarray�ķ���Ҫ����д
		return go(start,end,a,2);	
	}
	public int go(String start,String end,String[] a,int cur){
		LinkedList<String> q=new LinkedList<String>();
		HashMap<String,Integer> memo=new HashMap<String,Integer>();//������¼��ǰ�����ù�û
		for(int i=0;i<a.length;i++){
			memo.put(a[i], 0);
		}
		boolean flag=false;
		int count1=0;//count1������¼��ǰ���������count2������¼��һ���������flag������¼�ò�
		int count2=0;//�Ĳ�������û������˵������3������Ҫ��⣬������ֻ�ܼ�1�������ø�flag���ж�
	//���flagΪfalse��û�ӣ����1��falg���true�����򲻼ӣ���flag�ڵ�����һ���ʱ�����Ϊfalse��
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
			if(count1==0){//��ǰ��������ˣ��ͻ�����һ���������flagҲ���false
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
//����Ͳ���ʱ�ˣ���ʵ�ַ�������һ������ͬ���ǣ�ǰһ�����ǰѵ�ǰstart������diffbyone���������
	//dict������е��ʣ������diffbyone�Ǿͼӽ�q�����������ǰѵ�ǰstart��ÿ����ĸ�滻��
	//26����ĸ��һ�£���dictʱ���б任һλ֮��ĵ��ʣ��еĻ��ͼӽ�q����Ȼ�������ȽϿ�
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
					if(temp2.equals(end)){//ע������equals����
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
	//�ڶ���д��˼·���еģ���������Ҫ����count��¼������²�����֣����㶼��������֮�����ż�1
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
	//30/9 ˼·�����ˣ�ԭ����bfs,������Ҫ��memo��������ĵ�
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
//			if(count1==0){ //��ʼ���step++�����⣬���еģ�����
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
