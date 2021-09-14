package tree;

import java.util.Stack;

public class ConstructBinaryTreefromString {
    //8/18/2021 一次过
    // 关键是找第一个字母之后的对应左右括号，递归就行了。找左右括号就维护一个left初始化为1，因为有子树的话肯定第一个是left，然后遇到右括号就-1，
    //当left==0就说明找到分割点了
    public TreeNode str2tree(String s) {
        if (s.length()==0){
            return null;
        }
        int i=0;
        char[] ch=s.toCharArray();
        int num=0;
        int sign=1;
        if (ch[i]=='-'){
            sign=-1;
            i++;
        }
        while (i<ch.length&&Character.isDigit(ch[i])){
            num=num*10+ch[i]-'0';
            i++;
        }
        num*=sign;
        TreeNode root=new TreeNode(num);
        int begin1=i+1;
        if (i<ch.length){
            i++;
            int left=1;
            while (i<ch.length&&left!=0){
                if (ch[i]=='('){
                    left++;
                }
                if (ch[i]==')'){
                    left--;
                }
                i++;
            }
            root.left=str2tree(s.substring(begin1,i));
        }
        int begin2=i+1;
        if (i<ch.length){
            i++;
            int left=1;
            while (i<ch.length&&left!=0){
                if (ch[i]=='('){
                    left++;
                }
                if (ch[i]==')'){
                    left--;
                }
                i++;
            }
            root.right=str2tree(s.substring(begin2,i));
        }
        return root;
    }
}
