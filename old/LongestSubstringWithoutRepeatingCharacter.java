import java.util.ArrayList;
//http://blog.csdn.net/linhuanmars/article/details/19949159


//������ǳ�ʱ��
public class LongestSubstringWithoutRepeatingCharacter {
	
	public static void main(String[] args) {
		
		
		
	}
	int num=0;
//���Ǵӵ�һ����ĸ��ʼһֱ����ɨ������û���ظ��ģ��ٵڶ�����3,4����ĸ��ʼһֱ����ɨ
    public int lengthOfLongestSubstring(String s) {
		
		ArrayList<Character> memo = new ArrayList<Character>();
		for (int i = 0; i < s.length(); i++) {
			
			find(i, s, memo);
		}
		return num;

	}

	public void find(int begin, String s, ArrayList<Character> memo) {

		if (begin >= s.length()) {
			return;
		}
		ArrayList<Character> mm = new ArrayList<Character>();
		if (memo.size() != 0) {
			
			mm.addAll(memo);
		}
		int count = mm.size();

		if (!mm.contains(s.charAt(begin))) {
			count++;
			mm.add(s.charAt(begin));
			if (num == 0) {
				num = count;
			} else if (num < count) {
				num = count;
			}
			find(begin + 1, s, mm);
		}
		

	}

}
